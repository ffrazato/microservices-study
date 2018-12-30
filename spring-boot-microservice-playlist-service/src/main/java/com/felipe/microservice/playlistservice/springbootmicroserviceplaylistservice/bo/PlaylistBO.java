package com.felipe.microservice.playlistservice.springbootmicroserviceplaylistservice.bo;

import java.util.List;

import com.felipe.microservice.playlistservice.springbootmicroserviceplaylistservice.mechanism.provider.playlist.GenreEnum;
import com.felipe.microservice.playlistservice.springbootmicroserviceplaylistservice.mechanism.provider.playlist.PlaylistProvider;
import com.felipe.microservice.playlistservice.springbootmicroserviceplaylistservice.mechanism.provider.playlist.SpotifyPlaylistProvider;

/**
 * Business class to handle playlist business logic
 * 
 * @author ffrazato
 */
public class PlaylistBO {
    /**
     * Retrieve playlist sound track names by genre
     * 
     * @param genre
     * @return soud track names
     */
    public List<String> getPlaylisSoundtrackNamesByGenre(GenreEnum genre) {
        // get playlist for chosen genre
        PlaylistProvider spotifyProvider = SpotifyPlaylistProvider.getInstance();

        return spotifyProvider.getPlaylistTracksByGenres(genre);

    }
}
