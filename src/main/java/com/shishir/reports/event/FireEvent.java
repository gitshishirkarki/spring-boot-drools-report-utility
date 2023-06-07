package com.shishir.reports.event;

import org.springframework.context.ApplicationEvent;

public class FireEvent extends ApplicationEvent {
    private final String jsonParams;

    public FireEvent(Object source, String jsonParams) {
        super(source);
        this.jsonParams = jsonParams;
    }

    public String getJsonParams() {
        return jsonParams;
    }
}

