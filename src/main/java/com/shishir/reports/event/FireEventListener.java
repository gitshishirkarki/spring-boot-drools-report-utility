package com.shishir.reports.event;

import com.shishir.reports.service.ReportIgnitionComponent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class FireEventListener implements ApplicationListener<FireEvent> {
    private final ReportIgnitionComponent reportIgnitionComponent;

    public FireEventListener(ReportIgnitionComponent reportIgnitionComponent) {
        this.reportIgnitionComponent = reportIgnitionComponent;
    }

    @Override
    public void onApplicationEvent(FireEvent event) {
        String jsonParams = event.getJsonParams();
        reportIgnitionComponent.fire(jsonParams);
    }
}

