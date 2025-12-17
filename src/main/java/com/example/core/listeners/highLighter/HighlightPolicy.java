package com.example.core.listeners.highLighter;

import java.lang.reflect.Method;

public interface HighlightPolicy {
    boolean shouldHighlight(Method method);
}

