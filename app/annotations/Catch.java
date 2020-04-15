package annotations;

import interceptors.ExceptionInterceptor;
import play.mvc.With;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// need to have behavior of interceptor
@With(ExceptionInterceptor.class)
@Target({ElementType.METHOD,ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Catch { // registers it as annotation
    // add an attribute to decide if email to be sent or not
    boolean send() default true;


}
