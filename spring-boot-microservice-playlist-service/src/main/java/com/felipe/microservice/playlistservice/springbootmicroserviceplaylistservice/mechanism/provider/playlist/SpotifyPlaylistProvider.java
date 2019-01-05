package com.felipe.microservice.playlistservice.springbootmicroserviceplaylistservice.mechanism.provider.playlist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsTracksRequest;

/**
 * Spotify playlist provider
 * 
 * @author ffrazato
 */
public class SpotifyPlaylistProvider implements PlaylistProvider {

    private static SpotifyApi spotifyApi;
    private static ClientCredentialsRequest clientCredentialsRequest;

    /**
     * Singleton instance
     */
    private static volatile SpotifyPlaylistProvider instance;

    /**
     * Initializer spotify api including the oauth2 authentication
     */
    private SpotifyPlaylistProvider() {
        spotifyApi = new SpotifyApi.Builder().setClientId(getClientId()).setClientSecret(getClientSecret()).build();
        clientCredentialsRequest = spotifyApi.clientCredentials().build();

        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            // Set access token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<String> getPlaylistTracksByGenres(GenreEnum genre) {
        List<String> playlistTracks = new ArrayList<>();

        String playListID = null;

        switch (genre) {
            case PARTY:
                playListID = "37i9dQZF1DXaXB8fQg7xif";
                break;
            case POP:
                playListID = "37i9dQZF1DX6aTaZa0K6VA";
                break;
            case ROCK:
                playListID = "37i9dQZF1DWXRqgorJj26U";
                break;
            case CLASSICAL:
                playListID = "37i9dQZF1DWWEJlAGA9gs0";
                break;

            default:
                break;
        }

        GetPlaylistsTracksRequest getPlaylistsTracksRequest = spotifyApi.getPlaylistsTracks(playListID).build();

        Paging<PlaylistTrack> playlistTrackPaging = null;

        try {
            playlistTrackPaging = getPlaylistsTracksRequest.execute();
        } catch (SpotifyWebApiException | IOException e) {
            e.printStackTrace();
        }

        for (PlaylistTrack playlistTrack : playlistTrackPaging.getItems()) {
            playlistTracks.add(playlistTrack.getTrack().getName());
        }

        return playlistTracks;
    }

    /**
     * Singleton method to get the instance of SpotifyPlaylistProvider
     *
     * @return singleton instance for SpotifyPlaylistProvider
     */
    public static SpotifyPlaylistProvider getInstance() {
        // double check to avoid synchronizing it
        if (instance == null) {
            synchronized (SpotifyPlaylistProvider.class) {
                if (instance == null) {
                    instance = new SpotifyPlaylistProvider();
                }
            }
        }

        return instance;
    }

    private String getClientId() {
        return "3d80084d55ed48c2b76596f20c5f7e7d";
    }

    private String getClientSecret() {
        return "1e09b995924e443f9dfa459e7d81f1b8";
    }

}
