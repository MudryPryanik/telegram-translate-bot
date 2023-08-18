package com.telegram.translate.bot.scheduler;

import com.telegram.translate.bot.service.BotStarter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class BotStartScheduler {

    private final BotStarter botStarter;

    @Scheduled(fixedDelay = 500)
    public void update() {
        botStarter.startBot();
    }
}
