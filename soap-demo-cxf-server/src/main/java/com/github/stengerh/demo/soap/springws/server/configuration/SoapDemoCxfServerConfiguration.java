package com.github.stengerh.demo.soap.springws.server.configuration;

import com.github.stengerh.demo.soap.cxf.binding.WeatherSoap;
import com.github.stengerh.demo.soap.springws.server.endpoint.WeatherSoapEndpoint;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.http.HttpConduitConfig;
import org.apache.cxf.transport.http.HttpConduitFeature;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.xml.ws.Endpoint;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.soap.MTOMFeature;

@Configuration
public class SoapDemoCxfServerConfiguration {

    @Bean
    public Endpoint weatherEndpoint(
            SpringBus springBus,
            WeatherSoap weatherSoap
    ) {
        WebServiceFeature[] features = {
                new MTOMFeature()
        };

        EndpointImpl endpoint = new EndpointImpl(springBus, weatherSoap, null, features);
        endpoint.publish("/weather");
        return endpoint;
    }

    @Bean
    public WeatherSoap weatherSoap() {
        return new WeatherSoapEndpoint();
    }
}
