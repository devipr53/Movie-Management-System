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

    //To Respond Back to Rest API Search
    public JsonNode getAPISearchResult(String searchString,String searchParam) {
        return searchRepo.getAPISearchResult(searchString, searchParam);
    }
}
