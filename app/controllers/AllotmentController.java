package controllers;

import annotations.Catch;
import models.AllotmentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.AllotmentDetailsService;

import javax.inject.Inject;
import java.util.List;

@Catch(send = true)
public class AllotmentController extends Controller {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    FormFactory formFactory;

    @Inject
    MessagesApi messagesApi;

    @Inject
    AllotmentDetailsService allotmentDetailsService;

    List<String> movieOptions;

    List<String> multiplexOptions;

    public Result displayAllotmentDetails(){
        List<AllotmentDto> allotmentDet =  this.allotmentDetailsService.getAllAllotmentDetails();
        return ok(views.html.allotment.showAllotmentDetails.render(allotmentDet,null));
    }

    public Result addNewAllotment(Http.Request request, Integer allotId){
        movieOptions= this.allotmentDetailsService.getAllMovieNames();
        multiplexOptions=this.allotmentDetailsService.getAllMultiplexNames();
        System.out.println("Movie options : "+movieOptions.size());
        Form<AllotmentDto> allotmentForm = formFactory.form(AllotmentDto.class);
        return ok(views.html.allotment.addNewAllotment.render(allotmentForm,request, messagesApi.preferred(request),allotId,movieOptions,multiplexOptions));
    }

    // action method to generate a view for new movie entry form
    public Result showAllotmentDetails(Http.Request request) {
        // createEvents a Virtual form from Product Model
        int allotId=0;
        movieOptions= this.allotmentDetailsService.getAllMovieNames();
        multiplexOptions=this.allotmentDetailsService.getAllMultiplexNames();
        System.out.println("Movie options1 : "+movieOptions.size());
        Form<AllotmentDto> allotmentForm = formFactory.form(AllotmentDto.class);
        // return response :  render-view
       // return ok(views.html.allotment.addNewAllotment.render(allotmentForm,movieListOptions, request, messagesApi.preferred(request),allotId));
        return ok(views.html.allotment.addNewAllotment.render(allotmentForm,request, messagesApi.preferred(request),allotId,movieOptions,multiplexOptions));
    }

    // action method to save new movie details
    public Result saveAllotmentDetails(Http.Request request,Integer allotId) {
        Form<AllotmentDto> allotmentForm = this.formFactory.form(AllotmentDto.class).bindFromRequest(request);
        if (allotmentForm.hasErrors()) {
            logger.error("errors = {}", allotmentForm.errors());
            request.flash().adding("failed", "Constraints not satisfied!!!");
            //return badRequest(views.html.allotment.addNewAllotment.render(allotmentForm,movieListOptions, request, messagesApi.preferred(request),allotId));
            return badRequest(views.html.allotment.addNewAllotment.render(allotmentForm, request, messagesApi.preferred(request),allotId,movieOptions,multiplexOptions));
        }
        allotmentForm.get().setAllotmentId(allotId);
        this.allotmentDetailsService.addAllotmentDetails(allotmentForm.get());
        request.flash().adding("success", "Allotment Added Successfully");
        return redirect(routes.AllotmentController.displayAllotmentDetails());
    }

    public Result removeAllotmentById(Http.Request request,Integer allotId) {
        System.out.println("delete allotId Id : "+allotId);
        this.allotmentDetailsService.removeAllotmentById(allotId);
        request.flash().adding("success", "Allotment Deleted Successfully");
        return redirect(routes.AllotmentController.displayAllotmentDetails());
    }

    public Result editAllotmentDetails(Http.Request request, Integer allotId) {
        Form<AllotmentDto> allotmentForm = this.formFactory.form(AllotmentDto.class).fill(this.allotmentDetailsService.findAllotmentDetailsById(allotId));
        if(allotmentForm==null){
            //return notFound(_404.render());
        }
       // return ok(views.html.allotment.addNewAllotment.render(movieModelForm,movieListOptions,request, messagesApi.preferred(request),allotId));
        return ok(views.html.allotment.addNewAllotment.render(allotmentForm,request, messagesApi.preferred(request),allotId,movieOptions,multiplexOptions));
    }
}
