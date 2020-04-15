package models;

import entities.MovieDetails;
import entities.MultiplexDetails;

public class SearchAPIResponse {

    private MultiplexDetails multiplexDetails;
    private MovieDetails movieDetails;

    public SearchAPIResponse(){}

    public SearchAPIResponse(MultiplexDetails multiplexDetails, MovieDetails movieDetails) {
        this.multiplexDetails = multiplexDetails;
        this.movieDetails = movieDetails;
    }

    public MultiplexDetails getMultiplexDetails() {
        return multiplexDetails;
    }

    public void setMultiplexDetails(MultiplexDetails multiplexDetails) {
        this.multiplexDetails = multiplexDetails;
    }

    public MovieDetails getMovieDetails() {
        return movieDetails;
    }

    public void setMovieDetails(MovieDetails movieDetails) {
        this.movieDetails = movieDetails;
    }
}
