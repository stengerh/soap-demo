package com.github.stengerh.demo.soap.cxf.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoapDemoCxfClientApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SoapDemoCxfClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
