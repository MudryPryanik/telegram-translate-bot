package com.telegram.translate.bot.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDto {
    @JsonProperty("chat_id")
    private Long chatId;
    private String text;
}
