package com.epam.training.webshop.ui.command;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public abstract class AbstractCommand {

    private final String userType;
    private final String entityType;
    private final String action;

    public String process(String input) {
        String[] commandParts = input.split(" ");
        return process(Arrays.copyOfRange(commandParts, 3, commandParts.length));
    }

    protected abstract String process(String[] params);
}
