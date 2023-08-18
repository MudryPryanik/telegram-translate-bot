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
        private Long updateId;
        private MessageDto message;
    }

    @Data
    public static class CallbackQueryDto {
        private String id;
        @JsonProperty("from")
        private UserDto user;
        private MessageDto message;
        private String data;
    }

    @Data
    public static class UserDto {
        private long id;

        @JsonProperty("is_bot")
        private boolean isBot;

        @JsonProperty("first_name")
        private String firstName;

        @JsonProperty("last_name")
        private String lastName;

        @JsonProperty("username")
        private String userName;
    }

    @Data
    public static class MessageDto {
        @JsonProperty("message_id")
        private Long id;
        private UserDto from;
        private ChatDto chat;
        private String text;
        private Long date;
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
