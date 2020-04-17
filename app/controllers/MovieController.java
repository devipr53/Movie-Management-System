package controllers;

import annotations.Catch;
import models.MoviesDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.MovieDetailsService;

import javax.inject.Inject;
import java.util.List;

@Catch(send = true)
public class MovieController extends Controller {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Inject
    FormFactory formFactory;

    @Inject
    MessagesApi messagesApi;

    @Inject
    MovieDetailsService movieDetailsService;

    public Result displayMoviesList(){
        int movieId=0;
        List<MoviesDto> movies =  this.movieDetailsService.getAllMovieDetails();
        return ok(views.html.movies.showMovieLists.render(movies,movieId));
    }

       public Result addNewMovies(Http.Request request,Integer movieId){
        Form<MoviesDto> movieForm = formFactory.form(MoviesDto.class);
        return ok(views.html.movies.addNewMovies.render(movieForm,request, messagesApi.preferred(request),movieId));
    }

    // action method to generate a view for new movie entry form
    public Result showMovieDetails(Http.Request request) {
        // createEvents a Virtual form from Product Model
        Form<MoviesDto> movieForm = formFactory.form(MoviesDto.class);
        int movieId=0;
        // return response :  render-view
        return ok(views.html.movies.addNewMovies.render(movieForm, request, messagesApi.preferred(request),movieId));
    }

    // action method to save new movie details
    public Result saveMovieDetails(Http.Request request,Integer movieId) {
        Form<MoviesDto> movieModelForm = this.formFactory.form(MoviesDto.class).bindFromRequest(request);
        //System.out.println("movieModelForm.get().getMovieName() : "+movieModelForm.get().getMovieName());
        List<MoviesDto> movieList=this.movieDetailsService.getMovielistByName(movieModelForm.get().getMovieName());
        if (movieModelForm.hasErrors()) {
            logger.error("errors = {}", movieModelForm.errors());
            request.flash().adding("failed", "Constraints not satisfied!!!");
            return badRequest(views.html.movies.addNewMovies.render(movieModelForm, request, messagesApi.preferred(request),movieId));
        }
        if(movieList.size()>0){
            request.flash().adding("error", "Movie Already Exists");
            return ok(views.html.movies.addNewMovies.render(movieModelForm,request, messagesApi.preferred(request),movieId)).flashing("warning", "Movie Already Exists");
        }
        movieModelForm.get().setId(movieId);
        //System.out.println(" movieModelForm movieId : "+movieModelForm.get().getId());
        this.movieDetailsService.addMovieDetails(movieModelForm.get());
        request.flash().adding("success", "Movie Added Successfully");
        return redirect(routes.MovieController.displayMoviesList());
    }

    public Result removeMovieById(Http.Request request,Integer movieId) {
       // System.out.println("delete movie Id : "+movieId);
        movieDetailsService.removeMovieById(movieId);
        request.flash().adding("success", "Movie Deleted Successfully");
        return redirect(routes.MovieController.displayMoviesList());
    }

    public Result editMovieDetails(Http.Request request, Integer movieId) {
        Form<MoviesDto> movieModelForm = this.formFactory.form(MoviesDto.class).fill(movieDetailsService.findMovieDetailsById(movieId));
        if(movieModelForm==null){
            //return notFound(_404.render());
        }
        return ok(views.html.movies.addNewMovies.render(movieModelForm,request, messagesApi.preferred(request),movieId));
    }

}
