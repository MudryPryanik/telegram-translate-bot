package com.telegram.translate.bot.web;

import com.telegram.translate.bot.service.OffsetContainer;
import com.telegram.translate.bot.web.dto.response.UpdatesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class TelegramUpdater {

    private final OffsetContainer offsetContainer;
    private final WebClient webClient;

    protected UpdatesDto getUpdates(String botToken) {
        String finalUri = buildUri("https://api.telegram.org/bot" + botToken + "/getUpdates");
        return webClient.get()
                .uri(finalUri)
                .retrieve()
                .bodyToMono(UpdatesDto.class)
                .block();
    }

    private String buildUri(String defaultUri) {
        return Optional.ofNullable(offsetContainer.getOffset())
                .map(existsOffset -> defaultUri + "?offset=" + existsOffset)
                .orElse(defaultUri);
    }
}
