package com.angelos.koinoxrhsta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.vaadin.flow.component.page.AppShellConfigurator;

@ComponentScan({"com.angelos.koinoxrhsta.*"})
@SpringBootApplication
public class KoinoxrhstaApplication implements CommandLineRunner {

    private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
        SpringApplication.run(KoinoxrhstaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
