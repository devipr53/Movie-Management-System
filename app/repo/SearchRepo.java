package repo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.MovieDetails;
import entities.MultiplexDetails;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.function.Function;

public class SearchRepo {
    @Inject
    private JPAApi jpaApi;

    private <T> T wrap(Function<EntityManager, T> function){
        return this.jpaApi.withTransaction(function);
    }

    /*public List<SearchDetails> getSearchResults(String searchParam){
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
    }*/

    public JsonNode getAPISearchResult(String searchType, String searchParam) {
        //System.out.println(" Search Type: "+searchType);
       // System.out.println(" Search Param: "+searchParam);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode responseObject = null;
        if(searchType!= null && searchType.equalsIgnoreCase("movie")){
            responseObject = mapper.convertValue(this.wrap(entityManager -> entityManager.createQuery("select p from MovieDetails p where p.movieName like lower(:searchParam)", MovieDetails.class)
                    .setParameter("searchParam", "%" + searchParam + "%").getResultList()), JsonNode.class);
        }else if(searchType!= null && searchType.equalsIgnoreCase("multi")){
            responseObject = mapper.convertValue(this.wrap(entityManager -> entityManager.createQuery("select p from MultiplexDetails p where p.multiplexName like lower(:searchParam)", MultiplexDetails.class)
                    .setParameter("searchParam", "%" + searchParam + "%").getResultList()), JsonNode.class);
        }
       // System.out.println("Response Object :"+responseObject);
        return responseObject;
    }
}
