package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "weather-service")
@RibbonClient(name="weather-service")
public interface WeatherServiceProxy {
    @GetMapping("/weather/on/{cityName}")
    public Double retrieveCurrentWeatherByCity(@PathVariable("cityName") String cityName);
    
    @GetMapping("/weather/on/{lat}/{lon}")
    public Double retrieveCurrentWeatherByGeoCoordinates(@PathVariable("lat") double lat, @PathVariable("lon") double lon);
}
