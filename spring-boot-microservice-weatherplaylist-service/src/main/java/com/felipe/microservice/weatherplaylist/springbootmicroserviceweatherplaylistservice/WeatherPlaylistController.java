package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.provider.playlist.GenreEnum;
import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy.PlaylistServiceProxy;
import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy.WeatherServiceProxy;

@RestController
public class WeatherPlaylistController {

    @Autowired
    private PlaylistServiceProxy playListServiceProxy;

    @Autowired
    private WeatherServiceProxy weatherServiceProxy;

    /**
     * Service responsible for retrieving the playlist track names based in the current temperature for a given city name
     *
     * @param cityName
     *            to get the current temperature
     * @return playlist track names
     */
    @GetMapping("/playlist/{cityName}")
    public List<String> getPlayListByCityName(@PathVariable String cityName) {
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
     * Service responsible for retrieving the playlist track names based in the current temperature for given geographic coordinates
     *
     * @param latitude
     * @param longitude
     * @return playlist track names
     */
    @GetMapping("/playlist/{latitude}/{longitude}")
    public List<String> getPlayListByGeoCoordinates(@PathVariable double latitude, @PathVariable double longitude) {
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
