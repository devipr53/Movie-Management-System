package controllers;

import annotations.Catch;
import models.MultiplexDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.MultiplexDetailsService;

import javax.inject.Inject;
import java.util.List;

@Catch(send = true)
public class MultiplexController extends Controller {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Inject
    FormFactory formFactory;

    @Inject
    MessagesApi messagesApi;

    @Inject
    MultiplexDetailsService multiplexDetailsService;

    public Result displayMultiplexDetails(){
        int multiplexId=0;
        List<MultiplexDto> multiplexDet=this.multiplexDetailsService.getAllMultiplexDetails();
        return ok(views.html.multiplex.showMultiplexDetails.render(multiplexDet,multiplexId));
    }

    public Result addNewMultiplex(Http.Request request, Integer multiplexId){
        Form<MultiplexDto> multiplexForm = formFactory.form(MultiplexDto.class);
        return ok(views.html.multiplex.addNewMultiplex.render(multiplexForm,request, messagesApi.preferred(request),multiplexId));
    }

    // action method to generate a view for new movie entry form
    public Result showMultiplexDetails(Http.Request request) {
        // createEvents a Virtual form from Product Model
        Form<MultiplexDto> multiplexForm = formFactory.form(MultiplexDto.class);
        int multiplexId=0;
        // return response :  render-view
        return ok(views.html.multiplex.addNewMultiplex.render(multiplexForm, request, messagesApi.preferred(request),multiplexId));
    }

    // action method to save new movie details
    public Result saveMultiplexDetails(Http.Request request,Integer multiplexId) {
        Form<MultiplexDto> multiplexForm = this.formFactory.form(MultiplexDto.class).bindFromRequest(request);
        if (multiplexForm.hasErrors()) {
            logger.error("errors = {}", multiplexForm.errors());
            request.flash().adding("failed", "Constraints not satisfied!!!");
            return badRequest(views.html.multiplex.addNewMultiplex.render(multiplexForm, request, messagesApi.preferred(request),multiplexId));
        }
        //multiplexForm.get().setMultiplexId(multiplexId);
        this.multiplexDetailsService.addMultiplexDetails(multiplexForm.get());
        request.flash().adding("success", "Multiplex Details Added Successfully");
        return redirect(routes.MultiplexController.displayMultiplexDetails());
    }

    public Result removeMultiplexById(Http.Request request,Integer multiplexId) {
        System.out.println("delete multiplexId Id : "+multiplexId);
        this.multiplexDetailsService.removeMultiplexById(multiplexId);
        request.flash().adding("success", "Multiplex Details Deleted Successfully");
        return redirect(routes.MultiplexController.displayMultiplexDetails());
    }

    public Result editMultiplexDetails(Http.Request request, Integer multiplexId) {
        Form<MultiplexDto> multiplexForm = this.formFactory.form(MultiplexDto.class).fill(multiplexDetailsService.findMultiplexDetailsById(multiplexId));
        if(multiplexForm==null){
            //return notFound(_404.render());
        }
        return ok(views.html.multiplex.editMultiplexDetails.render(multiplexForm,request, messagesApi.preferred(request),multiplexId));
    }
}
