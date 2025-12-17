package com.example.core.listeners.highLighter;

import com.example.core.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.Method;
import java.util.Objects;

public class HighlightListener implements WebDriverListener {

    private final ElementHighlighter highlighter;
    private final HighlightPolicy policy;
    private final boolean enabled;

    public HighlightListener(WebDriver driver, Config cfg) {
        this(new JsElementHighlighter(driver, HighlightStyle.defaultRed()),
                new ActionMethodPolicy(),
                Objects.requireNonNull(cfg, "cfg must not be null").highlight());
    }

    public HighlightListener(ElementHighlighter highlighter, HighlightPolicy policy, boolean enabled) {
        this.highlighter = Objects.requireNonNull(highlighter);
        this.policy = Objects.requireNonNull(policy);
        this.enabled = enabled;
    }

    @Override
    public void beforeAnyWebElementCall(WebElement element, Method method, Object[] args) {
        if (enabled && policy.shouldHighlight(method)) {
            highlighter.highlight(element);
        }
    }

    @Override
    public void afterAnyWebElementCall(WebElement element, Method method, Object[] args, Object result) {
        if (enabled && policy.shouldHighlight(method)) {
            highlighter.unhighlight(element);
        }
    }
}

