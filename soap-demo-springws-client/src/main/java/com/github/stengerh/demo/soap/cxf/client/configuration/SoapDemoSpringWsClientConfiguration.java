package com.github.stengerh.demo.soap.cxf.client.configuration;

import com.github.stengerh.demo.soap.cxf.client.WeatherSoapClient;
import com.github.stengerh.demo.soap.springws.binding.ObjectFactory;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.Base64Utils;
import org.springframework.ws.client.support.destination.DestinationProvider;
import org.springframework.ws.client.support.destination.Wsdl11DestinationProvider;
import org.springframework.ws.transport.WebServiceMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.springframework.ws.transport.http.HttpUrlConnectionMessageSender;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Configuration
public class SoapDemoSpringWsClientConfiguration {
    @Value("${weather.client.wsdl-url:#{null}}")
    URL wsdlUrl;

    @Value("${weather.client.username:#{null}}")
    String username;

    @Value("${weather.client.password:#{null}}")
    String password;

    @Bean
    public WeatherSoapClient weatherSoapClient(
            WebServiceMessageSender weatherSoapMessageSender,
            Jaxb2Marshaller weatherSoapMarshaller,
            DestinationProvider weatherSoapDestinationProvider
    ) {
        WeatherSoapClient client = new WeatherSoapClient();
        client.setDestinationProvider(weatherSoapDestinationProvider);
        client.setMessageSender(weatherSoapMessageSender);
        client.setMarshaller(weatherSoapMarshaller);
        client.setUnmarshaller(weatherSoapMarshaller);
        return client;
    }

    @Bean
    public Wsdl11DestinationProvider weatherSoapDestinationProvider() throws MalformedURLException {
        UrlResource wsdlResource = new AuthenticatingUrlResource(wsdlUrl, username, password);

        Wsdl11DestinationProvider destinationProvider = new Wsdl11DestinationProvider();
        destinationProvider.setWsdl(wsdlResource);
        destinationProvider.setCache(true);
        return destinationProvider;
    }

    @Bean
    public Jaxb2Marshaller weatherSoapMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(ObjectFactory.class.getPackage().getName());
        marshaller.setMtomEnabled(true);
        return marshaller;
    }

    @Bean
    public WebServiceMessageSender weatherSoapMessageSender() {
        return new HttpUrlConnectionMessageSender() {
            @Override
            protected void prepareConnection(HttpURLConnection connection) throws IOException {
                super.prepareConnection(connection);
                connection.setRequestProperty("Authorization", "Basic " + Base64Utils.encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8)));
            }
        };
    }

}
