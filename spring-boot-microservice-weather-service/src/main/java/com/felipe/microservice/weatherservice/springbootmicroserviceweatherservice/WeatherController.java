package com.felipe.microservice.weatherservice.springbootmicroserviceweatherservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.microservice.weatherservice.springbootmicroserviceweatherservice.bo.WeatherBO;

@RestController
public class WeatherController {
    /**
     * Service responsible for getting the current temperature for the given cityname
     *
     * @param cityName
     *            to get the current temperature
     * @return temperature in celsius
     */
    @GetMapping("/weather/on/{cityName}")
    public Double getPlayListByCityName(@PathVariable String cityName) {
        WeatherBO weatherBO = new WeatherBO();

        return weatherBO.getWeatherByCityName(cityName);
    }

    /**
     * Service responsible for getting the current temperature for the given geo coordinates
     *
     * @param lat
     *            latitude
     * @param lon
     *            longitude
     * @return temperature in celsius
     */
    @GetMapping("/weather/on/{lat}/{lon}")
    public Double getPlayListByCityName(@PathVariable double lat, @PathVariable double lon) {
        WeatherBO weatherBO = new WeatherBO();

        return weatherBO.getWeatherByGeoCoordinates(lat, lon);
    }
}
