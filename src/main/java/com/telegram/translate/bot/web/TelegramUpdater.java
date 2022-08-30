package com.telegram.translate.bot.web;

import com.telegram.translate.bot.web.dto.response.UpdatesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
class TelegramUpdater {

    private final WebClient webClient;

    protected UpdatesDto getUpdates(String botToken) {
        return webClient.get()
                .uri("https://api.telegram.org/bot" + botToken + "/getUpdates")
                .retrieve()
                .bodyToMono(UpdatesDto.class)
                .block();
    }
}
