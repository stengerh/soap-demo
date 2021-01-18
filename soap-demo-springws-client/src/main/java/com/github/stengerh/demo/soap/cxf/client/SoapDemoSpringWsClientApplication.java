package com.github.stengerh.demo.soap.cxf.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoapDemoSpringWsClientApplication implements CommandLineRunner {

    private final WeatherSoapClient client;

    @Autowired
    public SoapDemoSpringWsClientApplication(WeatherSoapClient client) {
        this.client = client;
    }

    public static void main(String[] args) {
        SpringApplication.run(SoapDemoSpringWsClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        client.
    }
}
