package com.github.stengerh.demo.soap.springws.server.endpoint;


import com.github.stengerh.demo.soap.springws.binding.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class WeatherSoapEndpoint {

    public static final String NAMESPACE = "http://ws.cdyne.com/WeatherWS/";

    @PayloadRoot(namespace = NAMESPACE, localPart = "GetWeatherInformation")
    @ResponsePayload
    public GetWeatherInformationResponse getWeatherInformation(
            @RequestPayload GetWeatherInformation request
    ) {
        return null;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "GetCityWeatherByZIP")
    @ResponsePayload
    public GetCityWeatherByZIPResponse getCityWeatherByZIP(
            @RequestPayload GetCityWeatherByZIP request
    ) {
        return null;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "GetCityForecastByZIP")
    @ResponsePayload
    public ForecastReturn getCityForecastByZIP(
            @RequestPayload GetCityForecastByZIP request
    ) {
        return null;
    }
}
