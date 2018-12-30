package com.felipe.microservice.playlistservice.springbootmicroserviceplaylistservice;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.microservice.playlistservice.springbootmicroserviceplaylistservice.bo.PlaylistBO;
import com.felipe.microservice.playlistservice.springbootmicroserviceplaylistservice.mechanism.provider.playlist.GenreEnum;

@RestController
public class PlaylistController {
    @GetMapping("/soudtracknames/for/{genre}")
    public List<String> getPlayListSoundtracksByGenre(@PathVariable String genre) {
        PlaylistBO playlistBO = new PlaylistBO();
        return playlistBO.getPlaylisSoundtrackNamesByGenre(GenreEnum.valueOf(genre));
    }
}
