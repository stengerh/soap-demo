package com.github.stengerh.demo.soap.springws.server.endpoint;

import com.github.stengerh.demo.soap.cxf.binding.ArrayOfWeatherDescription;
import com.github.stengerh.demo.soap.cxf.binding.ForecastReturn;
import com.github.stengerh.demo.soap.cxf.binding.WeatherReturn;
import com.github.stengerh.demo.soap.cxf.binding.WeatherSoap;

import javax.jws.WebService;

@WebService(
        serviceName = "Weather",
        portName = "WeatherSoap",
        targetNamespace = "http://ws.cdyne.com/WeatherWS/",
        wsdlLocation = "/wsdl/weather.wsdl",
        endpointInterface = "com.github.stengerh.demo.soap.cxf.binding.WeatherSoap"
)
public class WeatherSoapEndpoint implements WeatherSoap {

    @Override
    public ArrayOfWeatherDescription getWeatherInformation() {
        return null;
    }

    @Override
    public WeatherReturn getCityWeatherByZIP(String zip) {
        return null;
    }

    @Override
    public ForecastReturn getCityForecastByZIP(String zip) {
        return null;
    }
}
