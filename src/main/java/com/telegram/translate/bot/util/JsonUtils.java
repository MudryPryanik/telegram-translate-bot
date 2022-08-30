package com.telegram.translate.bot.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = JsonMapper.builder()
            .build();

    @SneakyThrows
    public static <T> String objectToJson(T object) {
        return OBJECT_MAPPER.writeValueAsString(object);
    }

    @SneakyThrows
    public static <T> T jsonToObject(String json, Class<T> objectClass) {
        return OBJECT_MAPPER.readValue(json, objectClass);
    }
}