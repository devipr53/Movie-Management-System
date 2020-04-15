package service;

import com.fasterxml.jackson.databind.JsonNode;
import org.modelmapper.ModelMapper;
import repo.SearchRepo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SearchService {
    @Inject
    ModelMapper mapper;

    @Inject
    SearchRepo searchRepo;

    /*// To get all available movies and multiplex details by search param
    public List<SearchDto> getSearchResults(String searchParam){
        List<SearchDto> searchResults= new ArrayList<>();
        List<SearchDetails> searchDetailsList=this.searchRepo.getSearchResults(searchParam);
        searchResults=searchDetailsList.stream()
                .map(search -> new SearchDto(null,null,search.getMultiplexName(),search.getMovieName(),search.getAvailableFrom(),
                        search.getAvailableTo(),search.getScreenName(),search.getTicketCost(),search.getMovieImageUrl()))
                .collect(Collectors.toList());
        return searchResults;
    }*/

    //To Respond Back to Rest API Search
    public JsonNode getAPISearchResult(String searchString,String searchParam) {
        return searchRepo.getAPISearchResult(searchString, searchParam);
    }
}
