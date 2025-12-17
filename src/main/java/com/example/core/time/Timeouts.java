package com.example.core.time;

import java.time.Duration;

public final class Timeouts {
    private Timeouts() {
    }

    public static final Duration IMPLICIT = Duration.ZERO;
    public static final Duration PAGE_LOAD = Duration.ofSeconds(20);
    public static final Duration EXPLICIT_DEFAULT = Duration.ofSeconds(10);
}

