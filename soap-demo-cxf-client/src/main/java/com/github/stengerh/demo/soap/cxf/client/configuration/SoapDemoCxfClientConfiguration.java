package com.github.stengerh.demo.soap.cxf.client.configuration;

import com.github.stengerh.demo.soap.cxf.binding.Weather;
import com.github.stengerh.demo.soap.cxf.binding.WeatherSoap;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transport.http.HTTPConduitConfigurer;
import org.apache.cxf.transport.http.HttpConduitConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.soap.MTOMFeature;
import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class SoapDemoCxfClientConfiguration {

    @Bean
    public WeatherSoap weatherSoap(
            SpringBus cxf,
            @Value("${weather.client.wsdl-url:#{null}}") URL wsdlUrl
    ) {
        MTOMFeature mtomFeature = new MTOMFeature();

        LoggingFeature loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);

        Weather weather = new Weather(wsdlUrl, mtomFeature, loggingFeature);

        return weather.getWeatherSoap();
    }

    @Bean("org.apache.cxf.transport.http.HTTPConduitConfigurer")
    public HTTPConduitConfigurer httpConduitConfigurer(
            @Value("${weather.client.wsdl-url:#{null}}") URL wsdlUrl,
            @Value("${weather.client.username:#{null}}") String username,
            @Value("${weather.client.password:#{null}}") String password
    ) throws MalformedURLException {
        String matchedAddress = wsdlUrl.toString();
        if (matchedAddress.indexOf('?') >= 0) {
            matchedAddress = matchedAddress.substring(0, matchedAddress.indexOf('?'));
        }
        String matchedName = Weather.WeatherSoap.toString() + ".http-conduit";
        return new MyHTTPConduitConfigurer(matchedName, matchedAddress, username, password);
    }

    private static class MyHTTPConduitConfigurer implements HTTPConduitConfigurer {
        private final String matchedName;
        private final String matchedAddress;
        private final String username;
        private final String password;

        public MyHTTPConduitConfigurer(String matchedName, String matchedAddress, String username, String password) {
            this.matchedName = matchedName;
            this.matchedAddress = matchedAddress;
            this.username = username;
            this.password = password;
        }

        @Override
        public void configure(String name, String address, HTTPConduit c) {
            if (matchedName.equals(name) || matchedAddress.equals(address)) {
                AuthorizationPolicy authorization = new AuthorizationPolicy();
                authorization.setUserName(username);
                authorization.setPassword(password);

                c.setAuthorization(authorization);
            }
        }
    }
}
