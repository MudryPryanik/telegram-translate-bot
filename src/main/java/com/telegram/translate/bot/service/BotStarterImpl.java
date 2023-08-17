package com.telegram.translate.bot.service;

import com.telegram.translate.bot.web.TelegramApi;
import com.telegram.translate.bot.web.dto.request.MessageDto;
import com.telegram.translate.bot.web.dto.response.UpdatesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

        UpdatesDto.UpdateDto current = updates.getUpdates()
                .stream()
                .min(Comparator.comparing(UpdatesDto.UpdateDto::getUpdateId))
                .orElse(null);

        if (current == null) {
            return;
        }

        telegramApi.sendMessage(botToken,
                new MessageDto(current.getMessage().getChat().getId(), getAnswer(current.getText())));

        offsetContainer.set(current.getUpdateId());
    }

    private String getAnswer(String inputText) {
        try {
            String[] numbers = inputText.split(";");
            BigDecimal a = BigDecimal.valueOf(Double.parseDouble(numbers[0]));
            BigDecimal b = BigDecimal.valueOf(Double.parseDouble(numbers[1]));
            BigDecimal c = BigDecimal.valueOf(Double.parseDouble(numbers[2]));
            return String.valueOf((a.min(b)).divide(a.min(c)).multiply(BigDecimal.valueOf(100.0)));
        } catch (Exception e) {
            return "Петя, ты закинул что то не то";
        }
    }
}