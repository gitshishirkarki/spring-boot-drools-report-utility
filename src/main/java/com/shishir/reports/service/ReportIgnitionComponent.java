package com.shishir.reports.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shishir.reports.dto.BeanNameMapper;
import com.shishir.reports.event.FireEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReportIgnitionComponent {
    private final KieSession kieSession;
    private final ApplicationContext applicationContext;

    private final ApplicationEventPublisher eventPublisher;


    public void fireReportEvent(String jsonParams) {
        FireEvent fireEvent = new FireEvent(this, jsonParams);
        eventPublisher.publishEvent(fireEvent);
    }

    public void fire(String jsonParams) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            BeanNameMapper beanNameMapper = mapper.readValue(jsonParams, BeanNameMapper.class);
            if (beanNameMapper.getReportType() == null || beanNameMapper.getReportType().isEmpty()) {
                throw new IllegalArgumentException("Report type cannot be null or empty");
            }

            this.fireRules(beanNameMapper);

            if (beanNameMapper.getBeanName() == null || beanNameMapper.getBeanName().isEmpty()) {
                throw new IllegalArgumentException("Bean name cannot be null or empty");
            }

            this.triggerBeanAndPrimaryMethod(beanNameMapper.getBeanName(), jsonParams);
        } catch (Exception ex) {
            log.error("Unable to map json : {} : error message:  {}", jsonParams, ex.getMessage());
        }
    }

    private void fireRules(Object filter) {
        kieSession.insert(filter);
        kieSession.fireAllRules();
    }

    private void triggerBeanAndPrimaryMethod(String beanName, String params) {
        Object bean = applicationContext.getBean(beanName);

        try {
            Method processMethod = bean.getClass().getMethod("execute", String.class);
            // Invoke the process() method on the bean
            processMethod.invoke(bean, params);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // Handle any exceptions that may occur
            e.printStackTrace();
        }
    }
}
