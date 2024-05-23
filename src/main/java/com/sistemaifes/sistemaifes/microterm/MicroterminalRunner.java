package com.sistemaifes.sistemaifes.microterm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MicroterminalRunner implements CommandLineRunner {

    private final MicroterminalService microterminalService;

    public MicroterminalRunner(MicroterminalService microterminalService) {
        this.microterminalService = microterminalService;
    }

    @Override
    public void run(String... args) throws Exception {
        microterminalService.start();
    }
}