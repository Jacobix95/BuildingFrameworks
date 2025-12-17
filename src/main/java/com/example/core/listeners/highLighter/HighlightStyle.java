package com.example.core.listeners.highLighter;

public record HighlightStyle(String outlineCss, String transitionCss) {
    public static HighlightStyle defaultRed() {
        return new HighlightStyle("3px solid red", "outline 0.12s ease-in-out");
    }
}

