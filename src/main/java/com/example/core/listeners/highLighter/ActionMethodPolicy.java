package com.example.core.listeners.highLighter;

import java.lang.reflect.Method;
import java.util.Set;

public class ActionMethodPolicy implements HighlightPolicy {
    private static final Set<String> ACTION_METHODS = Set.of("click", "sendKeys", "clear", "submit");

    @Override
    public boolean shouldHighlight(Method method) {
        return ACTION_METHODS.contains(method.getName());
    }
}

