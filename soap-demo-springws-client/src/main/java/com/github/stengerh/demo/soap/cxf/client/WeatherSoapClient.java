package com.github.stengerh.demo.soap.cxf.client;

import com.github.stengerh.demo.soap.springws.binding.GetWeatherInformation;
import com.github.stengerh.demo.soap.springws.binding.GetWeatherInformationResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class WeatherSoapClient extends WebServiceGatewaySupport {

    public GetWeatherInformationResponse getWeatherInformation() {
        GetWeatherInformation request = new GetWeatherInformation();

        return (GetWeatherInformationResponse) getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback("http://ws.cdyne.com/WeatherWS/GetWeatherInformation"));
    }
}
