package com.felipe.microservice.weatherservice.springbootmicroserviceweatherservice.bo;

import org.apache.commons.lang3.StringUtils;

import com.felipe.microservice.weatherservice.springbootmicroserviceweatherservice.mechanism.provider.weather.OpenWeatherMapWeatherProvider;
import com.felipe.microservice.weatherservice.springbootmicroserviceweatherservice.mechanism.provider.weather.WeatherProvider;

/**
 * Business class to handle the weather logic
 * 
 * @author ffrazato
 */
public class WeatherBO {

    /**
     * Get current weather by cityname
     * 
     * @param cityName
     * @return temperature in celsius
     */
    public double getWeatherByCityName(String cityName) {
        double temperature;
        if (StringUtils.isNotBlank(cityName)) {
            // get current tempreature for given city
            WeatherProvider owm = OpenWeatherMapWeatherProvider.getInstance();
            temperature = owm.getCurrentCelsiusTemperatureByCityName(cityName);
        } else {
            // it can be enhancend by creating our own exception and setting an error pattern to display on client
            throw new NullPointerException("City name can't be null");
        }

        return temperature;
    }

    /**
     * Get current weather by geo coordinates
     * 
     * @param lat
     *            latitude
     * @param lon
     *            longitude
     * @return temperature in celsius
     */
    public double getWeatherByGeoCoordinates(double lat, double lon) {
        double temperature;
        if (lat != 0D && lon != 0D) {
            // get current temperature for given geo coordinates
            WeatherProvider owm = OpenWeatherMapWeatherProvider.getInstance();
            temperature = owm.getCurrentCelsiusTemperatureByGeoCoordinates(lat, lon);
        } else {
            // it can be enhanced by creating our own exception and setting an error pattern to display on client
            throw new NullPointerException("Missing geo coordinates");
        }

        return temperature;
    }
}
