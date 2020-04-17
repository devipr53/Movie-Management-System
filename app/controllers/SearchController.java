package controllers;

import annotations.Catch;
import models.MoviesDto;
import models.MultiplexDto;
import models.SearchDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.MovieDetailsService;
import service.MultiplexDetailsService;
import service.SearchService;

import javax.inject.Inject;
import java.util.List;

@Catch(send = true)
public class SearchController extends Controller {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Inject
    FormFactory formFactory;

    @Inject
    MessagesApi messagesApi;

    @Inject
    SearchService searchService;

    @Inject
    MovieDetailsService movieDetailsService;

    @Inject
    MultiplexDetailsService multiplexDetailsService;

    public Result displaySearchResults(Http.Request request){
        Form<SearchDto> searchDtoForm = this.formFactory.form(SearchDto.class).bindFromRequest(request);
        String searchType=searchDtoForm.get().getSearchType();
        //System.out.println("Search Type :"+searchType);
        String searchParam=searchDtoForm.get().getSearchParam();
        //System.out.println("Search Param :"+searchParam);
        if(searchType!= null && searchType.equalsIgnoreCase("Search By Movie Name")){
            List<MoviesDto> movies =  this.movieDetailsService.getMovielistByName(searchParam);
            return ok(views.html.movies.showMovieLists.render(movies,0));
        }else if(searchType!= null && searchType.equalsIgnoreCase("Search By Multiplex Name")){
            List<MultiplexDto> multiplexDet=this.multiplexDetailsService.getMultiplexlistByName(searchParam);
            return ok(views.html.multiplex.showMultiplexDetails.render(multiplexDet,0));
        }
        return ok();
    }

    public Result displaySearchPage(Http.Request request,Integer searchId){
        Form<SearchDto> searchDtoForm = this.formFactory.form(SearchDto.class);
        Form<MoviesDto> movieForm = formFactory.form(MoviesDto.class);
        return ok(views.html.search.searchForm.render(searchDtoForm,request, messagesApi.preferred(request),searchId));
    }
}
