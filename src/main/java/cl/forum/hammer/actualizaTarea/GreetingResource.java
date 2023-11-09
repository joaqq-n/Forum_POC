package cl.forum.hammer.actualizaTarea;

import java.time.Instant;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.opentelemetry.api.trace.Span;



@Path("/hello")
public class GreetingResource {
    Span span = Span.current();
    
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        var hello="hello";
        span.setAttribute("entry", hello);
        hello="mid";
        Instant timestamp = Instant.now();
        span.addEvent("formatGreeting", timestamp);
        hello="bye";
        span.setAttribute("response", hello);
        return "Hello RESTEasy "+hello;
    }


}
