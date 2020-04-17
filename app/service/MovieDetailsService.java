package service;

import entities.MovieDetails;
import models.MoviesDto;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class MovieDetailsService {

    @Inject
    private repo.MovieRepo movieRepo;

    @Inject
    ModelMapper mapper;

    public MoviesDto addMovieDetails(MoviesDto moviesDto) {
        return mapper.map(movieRepo.insertOrUpdateMovieDetails(mapper.map(moviesDto, MovieDetails.class)), MoviesDto.class);
    }

    // To get all available movies
    public List<MoviesDto> getAllMovieDetails(){
        List<MoviesDto> allMovies= new ArrayList<>();
        List<MovieDetails> moviesList=this.movieRepo.getMovielist();
        allMovies=moviesList.stream()
                .map(movies -> new MoviesDto(movies.getId(),movies.getMovieName(), movies.getMovieCategory(), movies.getMovieProducer(),
                        movies.getMovieDirector(), movies.getMovieReleaseDate(), movies.getMovieDesc(),
                        movies.getMovieImageUrl())).collect(Collectors.toList());
        return allMovies;
    }

    // To get Movies By Search Name
    public List<MoviesDto> getMovielistByName(String searchParam){
        List<MoviesDto> allMovies= new ArrayList<>();
        List<MovieDetails> moviesList=this.movieRepo.getMovielistByName(searchParam);
        allMovies=moviesList.stream()
                .map(movies -> new MoviesDto(movies.getId(),movies.getMovieName(), movies.getMovieCategory(), movies.getMovieProducer(),
                        movies.getMovieDirector(), movies.getMovieReleaseDate(), movies.getMovieDesc(),
                        movies.getMovieImageUrl())).collect(Collectors.toList());
        return allMovies;
    }

    public void removeMovieById(Integer movieId){
       this.movieRepo.deleteMovieById(movieId);
    }

    public MoviesDto findMovieDetailsById(Integer movieId) {
        return mapper.map(this.movieRepo.findMovieDetailsById(movieId), MoviesDto.class);
    }

}
