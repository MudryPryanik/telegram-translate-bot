package com.telegram.translate.bot.web;

import com.telegram.translate.bot.web.dto.request.MessageDto;
import com.telegram.translate.bot.web.dto.response.UpdatesDto;

public interface TelegramApi {
    UpdatesDto getUpdates(String botToken);

    void sendMessage(String botToken, MessageDto messageDto);
}
