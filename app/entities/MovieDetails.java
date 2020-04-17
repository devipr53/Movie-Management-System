package entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class MovieDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    public String movieName;
    public String movieCategory;
    public String movieProducer;
    public String movieDirector;
    public String movieReleaseDate;
    public String movieDesc;
    public String movieImageUrl;

    public MovieDetails() {
    }

    public MovieDetails(String movieName, String movieCategory, String movieProducer, String movieDirector, String movieReleaseDate, String movieDesc, String movieImageUrl) {
        this.movieName = movieName;
        this.movieCategory = movieCategory;
        this.movieProducer = movieProducer;
        this.movieDirector = movieDirector;
        this.movieReleaseDate = movieReleaseDate;
        this.movieDesc = movieDesc;
        this.movieImageUrl = movieImageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(String movieCategory) {
        this.movieCategory = movieCategory;
    }

    public String getMovieProducer() {
        return movieProducer;
    }

    public void setMovieProducer(String movieProducer) {
        this.movieProducer = movieProducer;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }

    public String getMovieImageUrl() {
        return movieImageUrl;
    }

    public void setMovieImageUrl(String movieImageUrl) {
        this.movieImageUrl = movieImageUrl;
    }
}
