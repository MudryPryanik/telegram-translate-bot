package com.telegram.translate.bot.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class OffsetContainer {
    private final AtomicLong offset = new AtomicLong(0);

    public void set(Long lastMessageId) {
        if (lastMessageId == null) return;
        this.offset.set(lastMessageId + 1);
    }

    public Long getOffset() {
        return this.offset.get();
    }
}
