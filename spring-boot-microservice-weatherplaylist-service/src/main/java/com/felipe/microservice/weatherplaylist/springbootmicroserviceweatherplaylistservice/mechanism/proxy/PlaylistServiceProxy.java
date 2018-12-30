package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "playlist-service")
@RibbonClient(name="playlist-service")
public interface PlaylistServiceProxy {
    @GetMapping("/soudtracknames/for/{genre}")
    public List<String> retrieveSoundtrackNamesByGenre(@PathVariable("genre") String genre);
}