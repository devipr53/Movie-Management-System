package repo;

import entities.MovieDetails;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Function;

@Singleton
public class MovieRepo {

    @Inject
    private JPAApi jpaApi;

    // wrap this.jpaApi.withTransaction : to make it reusable
    private <T> T wrap(Function<EntityManager, T> function){
        return this.jpaApi.withTransaction(function);
    }

    public List<MovieDetails> getMovielist(){
        return this.wrap(entityManager -> {
            List<MovieDetails> movieList  =  entityManager.createQuery("select p from MovieDetails p", MovieDetails.class).getResultList();
            return movieList;
        });
    }

    public List<MovieDetails> getMovielistByName(String searchParam){
        return this.wrap(entityManager -> {
            List<MovieDetails> movieList  =  entityManager.createQuery("select p from MovieDetails p where p.movieName like lower(:searchParam)", MovieDetails.class)
                    .setParameter("searchParam", "%" + searchParam + "%").getResultList();
            return movieList;
        });
    }

    public MovieDetails findMovieDetailsById(Integer movieId) {
        return this.wrap(entityManager -> entityManager.createQuery("select m from MovieDetails m where m.id=" + movieId , MovieDetails.class).getSingleResult());
    }

    public void deleteMovieById(Integer id) {
        this.wrap(entityManager ->
                entityManager.createQuery("delete from MovieDetails m where m.id=" + id).executeUpdate()
        );
    }

    public List<MovieDetails> getMovielistOpts(){
        return this.wrap(entityManager -> {
            List<MovieDetails> movieList  =  entityManager.createQuery("select p.movieName from MovieDetails p", MovieDetails.class).getResultList();
            //System.out.println("Movie Size :"+movieList.size());
            return movieList;
        });
    }

    public MovieDetails insertOrUpdateMovieDetails(MovieDetails movie) {
        return this.wrap(eM -> {
            if (movie.getId() == null) {
                eM.persist(movie);
            } else {
                eM.merge(movie);
            }
            return movie;
        });
    }

}
