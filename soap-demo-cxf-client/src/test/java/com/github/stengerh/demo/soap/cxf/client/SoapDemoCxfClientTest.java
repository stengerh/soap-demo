package com.github.stengerh.demo.soap.cxf.client;

import com.github.stengerh.demo.soap.cxf.binding.ArrayOfWeatherDescription;
import com.github.stengerh.demo.soap.cxf.binding.WeatherSoap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        properties = {
                "weather.client.wsdl-url=http://localhost:8080/soap-demo/services/weather?wsdl",
                "weather.client.username=username",
                "weather.client.password=password",
        }
)
class SoapDemoCxfClientTest {

    @Autowired
    public WeatherSoap client;

    @Test
    public void test() {
        ArrayOfWeatherDescription response = client.getWeatherInformation();
        System.out.println(response);
    }
}