package com.shishir.reports.config;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;

@Slf4j
@Configuration
public class DroolConfig {

    private final KieServices kieServices = KieServices.Factory.get();

    @Value("${reports.drools.rules.files}")
    private List<String> ruleFiles;

    private KieFileSystem getKieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        if(!ruleFiles.isEmpty()) {
            ruleFiles.forEach(s -> kieFileSystem.write(ResourceFactory.newClassPathResource(s)));
        }
        return kieFileSystem;
    }

    @Bean
    public KieContainer getKieContainer() throws IOException {
        log.info("KieContainer created.");
        getKieRepository();
        KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem());
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }

    private void getKieRepository() {
        final KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
    }

    @Bean
    public KieSession getKieSession() throws IOException {
        log.info("KieSession created.");
        return getKieContainer().newKieSession();
    }
}