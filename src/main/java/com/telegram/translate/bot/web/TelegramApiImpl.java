package com.telegram.translate.bot.web;

import com.telegram.translate.bot.web.dto.response.UpdatesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TelegramApiImpl implements TelegramApi {
    private final TelegramUpdater telegramUpdater;

    @Override
    public UpdatesDto getUpdates(String botToken) {
        return telegramUpdater.getUpdates(botToken);
    }
}
