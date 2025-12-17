package com.example.core.listeners.highLighter;

import org.openqa.selenium.WebElement;

public interface ElementHighlighter {
    void highlight(WebElement element);

    void unhighlight(WebElement element);
}

