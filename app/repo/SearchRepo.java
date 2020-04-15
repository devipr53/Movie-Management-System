package repo;

import entities.SearchDetails;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.function.Function;

public class SearchRepo {
    @Inject
    private JPAApi jpaApi;

    private <T> T wrap(Function<EntityManager, T> function){
        return this.jpaApi.withTransaction(function);
    }

    public List<SearchDetails> getSearchResults(String searchParam){
        return this.wrap(entityManager -> {
            Query q = entityManager.createQuery("select new entities.SearchDetails (movie.movieName,movie.movieReleaseDate,multi.multiplexName,multi.ticketCost," +
                    " DATE_FORMAT(STR_TO_DATE(allotment.availableFrom,'%Y-%m-%D'), '%d-%m-%Y') as availableFrom," +
                    " DATE_FORMAT(STR_TO_DATE(allotment.availableTo,'%Y-%m-%D'), '%d-%m-%Y') as availableTo ," +
                    "  allotment.screenName) from AllotmentDetails allotment " +
                    "left outer join MultiplexDetails multi on multi.multiplexName=allotment.multiplexName " +
                    "left outer join MovieDetails movie on movie.id=allotment.movieId " +
                            " where lower(movie.movieName) like lower( CONCAT('%', :searchParam, '%')) or lower(multi.multiplexName) like lower(CONCAT('%', :searchParam, '%')) "+
                    " order by movieReleaseDate desc"
                    , SearchDetails.class)
                    .setParameter("searchParam", searchParam);
            System.out.println("Query : "+q);
            List<SearchDetails> searchResults = q.getResultList();
            System.out.println("Search list size: "+searchResults.size());
            return searchResults;
        });
    }
}
