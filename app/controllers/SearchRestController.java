package controllers;

import Utils.ResponseDesigner;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import service.SearchService;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class SearchRestController extends Controller {
    @Inject
    private HttpExecutionContext httpExecutionContext;

    @Inject
    private SearchService searchService;

    public CompletionStage<Result> search(String searchType, String searchParam) {
        return supplyAsync(() -> ok(ResponseDesigner.design(this.searchService.getAPISearchResult(searchType, searchParam), true)), httpExecutionContext.current());
    }

}
