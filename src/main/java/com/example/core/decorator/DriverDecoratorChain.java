package com.example.core.decorator;

import java.util.ArrayList;
import java.util.List;

import com.example.core.config.Config;
import org.openqa.selenium.WebDriver;

public class DriverDecoratorChain {
    private final List<DriverDecorator> decorators = new ArrayList<>();

    public DriverDecoratorChain add(DriverDecorator decorator) {
        decorators.add(decorator);
        return this;
    }

    public WebDriver apply(WebDriver driver) {
        WebDriver current = driver;
        for (DriverDecorator d : decorators) {
            current = d.decorate(current);
        }
        return current;
    }

    public static DriverDecoratorChain defaultChain(Config cfg) {
        return new DriverDecoratorChain()
                .add(new HighlightDriverDecorator(cfg));
    }
}

