package com.telegram.translate.bot.service;

import com.telegram.translate.bot.web.TelegramApi;
import com.telegram.translate.bot.web.dto.request.MessageDto;
import com.telegram.translate.bot.web.dto.response.UpdatesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.TimeZone;

@Service
@RequiredArgsConstructor
public class BotStarterImpl implements BotStarter {
    @Value("${application.token}")
    private String botToken;


    private final TelegramApi telegramApi;
    private final OffsetContainer offsetContainer;

    @Override
    public void startBot() {
        UpdatesDto.UpdateDto current = null;
        try {
            UpdatesDto updates = telegramApi.getUpdates(botToken);

            System.out.println(updates);

            current = updates.getUpdates()
                    .stream()
                    .max(Comparator.comparing(UpdatesDto.UpdateDto::getUpdateId))
                    .orElse(null);

            if (current == null) {
                return;
            }

            LocalDateTime messageTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(current.getMessage().getDate()),
                            TimeZone.getDefault().toZoneId());

            if (ChronoUnit.SECONDS.between(messageTime, LocalDateTime.now()) > 5) {
                return;
            }

            telegramApi.sendMessage(botToken,
                    new MessageDto(current.getMessage().getChat().getId(), getAnswer(current.getMessage().getText())));
        } finally {
            if (current != null) {
                offsetContainer.set(current.getUpdateId());
            }
        }
    }

    private String getAnswer(String inputText) {
        try {
            String[] numbers = inputText.split(";");
            Double a = Double.parseDouble(numbers[0]);
            Double b = Double.parseDouble(numbers[1]);
            Double c = Double.parseDouble(numbers[2]);
            return String.valueOf((a-b)/(a-c)*100);
        } catch (Exception e) {
            return "Петя, ты закинул что то не то";
        }
    }
}