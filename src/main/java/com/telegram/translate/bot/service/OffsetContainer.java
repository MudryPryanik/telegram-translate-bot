package com.telegram.translate.bot.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class OffsetContainer {
    private AtomicLong offset;

    public void setOffset(Long offset) {
        this.offset.setPlain(offset);
    }

    public Long getOffset() {
        return this.offset.get();
    }
}
