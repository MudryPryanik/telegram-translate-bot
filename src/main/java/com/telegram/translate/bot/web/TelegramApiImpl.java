package com.telegram.translate.bot.web;

import com.telegram.translate.bot.service.OffsetContainer;
import com.telegram.translate.bot.web.dto.request.MessageDto;
import com.telegram.translate.bot.web.dto.response.UpdatesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
class TelegramApiImpl implements TelegramApi {
    private static final String URL_TEMPLATE_GET_UPDATES = "https://api.telegram.org/bot%s/getUpdates?offset=%s";
    private static final String URL_TEMPLATE_SEND_MESSAGE = "https://api.telegram.org/bot%s/sendMessage";

    private final OffsetContainer offsetContainer;
    private final WebClient webClient;

    public UpdatesDto getUpdates(String botToken) {
        String finalUri = String.format(URL_TEMPLATE_GET_UPDATES, botToken, offsetContainer.getOffset());
        return webClient.get()
                .uri(finalUri)
                .retrieve()
                .bodyToMono(UpdatesDto.class)
                .block();
    }

    public void sendMessage(String botToken, MessageDto messageDto) {
        String finalUri = String.format(URL_TEMPLATE_SEND_MESSAGE, botToken);
        webClient.post()
                .uri(finalUri)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(messageDto)
                .retrieve();
    }
}