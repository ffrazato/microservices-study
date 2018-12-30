package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.BO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.provider.playlist.GenreEnum;
import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy.PlaylistServiceProxy;
import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy.WeatherServiceProxy;

/**
 * Handle business logic for weather playlist service
 * 
 * @author ffrazato
 */
public class WeatherPlaylistBO {

    @Autowired
    private PlaylistServiceProxy playListServiceProxy;

    @Autowired
    private WeatherServiceProxy weatherServiceProxy;

    /**
     * Get the playlist track names based on the current temperature given a city name
     *
     * @param cityName
     * @return playlist track names
     */
    public List<String> getPlaylistTrackNamesByCityName(String cityName) {
        List<String> playlistSoundTracks = null;

        double temperature = weatherServiceProxy.retrieveCurrentWeatherByCity(cityName);

        System.out.println("Current temperature on " + cityName + " is: " + temperature);

        if (temperature > 30D) {
            playlistSoundTracks = playListServiceProxy.retrieveSoundtrackNamesByGenre(GenreEnum.PARTY.name());
        } else if (temperature >= 15D && temperature <= 30D) {
            playlistSoundTracks = playListServiceProxy.retrieveSoundtrackNamesByGenre(GenreEnum.POP.name());
        } else if (temperature >= 10D && temperature <= 14D) {
            playlistSoundTracks = playListServiceProxy.retrieveSoundtrackNamesByGenre(GenreEnum.ROCK.name());
        } else {
            playlistSoundTracks = playListServiceProxy.retrieveSoundtrackNamesByGenre(GenreEnum.CLASSICAL.name());
        }

        return playlistSoundTracks;
    }

    /**
     * Get the playlist track names based on the current temperature given geographic coordinates
     *
     * @param cityName
     * @return playlist track names
     */
    public List<String> getPlaylistTrackNamesByGeoCoordinates(double latitude, double longitude) {
        List<String> playlistSoundTracks = null;

        double temperature = weatherServiceProxy.retrieveCurrentWeatherByGeoCoordinates(latitude, longitude);

        System.out.println("Current temperature on latitute " + latitude + " and longitude " + longitude + " is: " + temperature);

        if (temperature > 30D) {
            playlistSoundTracks = playListServiceProxy.retrieveSoundtrackNamesByGenre(GenreEnum.PARTY.name());
        } else if (temperature >= 15D && temperature <= 30D) {
            playlistSoundTracks = playListServiceProxy.retrieveSoundtrackNamesByGenre(GenreEnum.POP.name());
        } else if (temperature >= 10D && temperature <= 14D) {
            playlistSoundTracks = playListServiceProxy.retrieveSoundtrackNamesByGenre(GenreEnum.ROCK.name());
        } else {
            playlistSoundTracks = playListServiceProxy.retrieveSoundtrackNamesByGenre(GenreEnum.CLASSICAL.name());
        }

        return playlistSoundTracks;
    }
}
