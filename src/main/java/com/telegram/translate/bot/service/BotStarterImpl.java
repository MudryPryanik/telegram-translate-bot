package com.telegram.translate.bot.service;

import com.telegram.translate.bot.web.TelegramApi;
import com.telegram.translate.bot.web.dto.response.UpdatesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class BotStarterImpl implements BotStarter {
    @Value("${application.token}")
    private String botToken;


    private final TelegramApi telegramApi;
    private final OffsetContainer offsetContainer;

    @Override
    public void startBot() {
        UpdatesDto updates = telegramApi.getUpdates(botToken);


        System.out.println(updates);

        offsetContainer.set(updates.getUpdates()
                .stream()
                .map(UpdatesDto.UpdateDto::getUpdateId)
                .max(Comparator.comparing(id -> id))
                .orElse(null));
    }
}
