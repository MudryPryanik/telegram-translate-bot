package com.telegram.translate.bot.web.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UpdatesDto {
    private Boolean ok;
    @JsonProperty("result")
    private List<UpdateDto> updates;

    @Data
    public static class UpdateDto {
        @JsonProperty("update_id")
        private Integer updateId;
        private MessageDto message;
        private ChatDto chat;
        private String date; //TODO
        private String text;
    }

    @Data
    public static class MessageDto {
        private Long id;
        @JsonProperty("is_bot")
        private Boolean isBot;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
        private String username;
        @JsonProperty("language_code")
        private String languageCode;
    }

    @Data
    public static class ChatDto {
        private Long id;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
        private String username;
        private String type;
    }
}
