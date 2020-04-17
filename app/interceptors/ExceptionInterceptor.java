package interceptors;

import Utils.ExceptionEmailHandler;
import annotations.Catch;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;

// public class ExceptionInterceptor extends Action.Simple {
// need to specify on which annotation it is working
public class ExceptionInterceptor extends Action<Catch> {

    // intercept the calls to controller methods
    @Override
    public CompletionStage<Result> call(Http.Request req) {
        // this method is required to delegate the call to actual resource

        // pre processing
        System.out.println("Inside Interceptor...");
        try {
            // explicit call to actual resource
            CompletionStage<Result> result = delegate.call(req);

            // post processing activity

            // return back result to complete the cycle
            return result;
        }catch(Throwable e){
            if(configuration.send()) {
                ExceptionEmailHandler.send(e);
            }
            throw new RuntimeException(e);
        }
    }
}
