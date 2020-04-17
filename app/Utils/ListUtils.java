package Utils;

import entities.MovieDetails;
import entities.MultiplexDetails;
import entities.MultiplexScreenDetails;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.function.Function;

@Singleton
public class ListUtils {
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

    public List<MultiplexDetails> getMultiplexlist(){
        return this.wrap(entityManager -> {
            List<MultiplexDetails> multiplexDetailsList  =  entityManager.createQuery("select m from MultiplexDetails m", MultiplexDetails.class).getResultList();
            return multiplexDetailsList;
        });
    }
    public List<MultiplexScreenDetails> findScreenDetailsByMultiName(String multiplexName) {
        return this.wrap(entityManager -> {
            Query query1 = entityManager.createQuery("select s from MultiplexScreenDetails s " +
                    " inner join MultiplexDetails multi on multi.multiplexId=s.multiplexId" +
                    " where multi.multiplexName=:multiplexName order by s.screenName asc", MultiplexScreenDetails.class)
                    .setParameter("multiplexName", multiplexName);
            List<MultiplexScreenDetails> screenDetails=query1.getResultList();
            return screenDetails;
        });
    }
}
