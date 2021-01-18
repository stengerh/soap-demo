package com.github.stengerh.demo.soap.springws.client;

import com.github.stengerh.demo.soap.cxf.client.WeatherSoapClient;
import com.github.stengerh.demo.soap.cxf.client.configuration.SoapDemoSpringWsClientConfiguration;
import com.github.stengerh.demo.soap.springws.binding.GetWeatherInformationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        classes = {
                SoapDemoSpringWsClientConfiguration.class
        },
        properties = {
                "weather.client.wsdl-url=http://localhost:8080/soap-demo/services/weather?wsdl",
                "weather.client.username=username",
                "weather.client.password=password",
        }
)
class SoapDemoSpringWsClientTest {

    @Autowired
    public WeatherSoapClient client;

    @Test
    public void test() {
        GetWeatherInformationResponse response = client.getWeatherInformation();
        System.out.println(response);
    }
}