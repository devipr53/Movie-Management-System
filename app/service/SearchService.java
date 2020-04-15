package service;

import entities.SearchDetails;
import models.SearchDto;
import org.modelmapper.ModelMapper;
import repo.SearchRepo;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class SearchService {
    @Inject
    ModelMapper mapper;

    @Inject
    SearchRepo searchRepo;

    // To get all available movies and multiplex details by search param
    public List<SearchDto> getSearchResults(String searchParam){
        List<SearchDto> searchResults= new ArrayList<>();
        List<SearchDetails> searchDetailsList=this.searchRepo.getSearchResults(searchParam);
        searchResults=searchDetailsList.stream()
                .map(search -> new SearchDto(null,null,search.getMultiplexName(),search.getMovieName(),search.getAvailableFrom(),
                        search.getAvailableTo(),search.getScreenName(),search.getTicketCost(),search.getMovieImageUrl()))
                .collect(Collectors.toList());
        return searchResults;
    }
}
