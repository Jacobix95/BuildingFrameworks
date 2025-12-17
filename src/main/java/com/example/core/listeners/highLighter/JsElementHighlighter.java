package com.example.core.listeners.highLighter;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsElementHighlighter implements ElementHighlighter {
    private static final Logger LOG = LoggerFactory.getLogger(JsElementHighlighter.class);
    private static final String PREV_STYLE_ATTR = "__orig_style";

    private final JavascriptExecutor js;
    private final HighlightStyle style;

    public JsElementHighlighter(WebDriver driver, HighlightStyle style) {
        this.js = (JavascriptExecutor) driver;
        this.style = style;
    }

    @Override
    public void highlight(WebElement el) {
        try {
            js.executeScript(
                    "try{if(!arguments[0].hasAttribute(arguments[2])){" +
                            "arguments[0].setAttribute(arguments[2], arguments[0].getAttribute('style')||'');}" +
                            "arguments[0].style.outline=arguments[1];" +
                            "arguments[0].style.transition=arguments[3];}catch(e){}",
                    el, style.outlineCss(), PREV_STYLE_ATTR, style.transitionCss()
            );
        } catch (StaleElementReferenceException ignored) {
        } catch (Exception e) {
            LOG.debug("Highlight JS failed: {}", e.getMessage());
        }
    }

    @Override
    public void unhighlight(WebElement el) {
        try {
            js.executeScript(
                    "try{var s = arguments[0].getAttribute(arguments[1]);" +
                            "if(s!==null){arguments[0].setAttribute('style', s);arguments[0].removeAttribute(arguments[1]);}" +
                            "else{arguments[0].style.outline='';}}catch(e){}",
                    el, PREV_STYLE_ATTR
            );
        } catch (StaleElementReferenceException ignored) {
        } catch (Exception e) {
            LOG.debug("Unhighlight JS failed: {}", e.getMessage());
        }
    }
}

