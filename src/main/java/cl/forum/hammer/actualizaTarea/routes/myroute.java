package cl.forum.hammer.actualizaTarea.routes;

import org.apache.camel.builder.RouteBuilder;

public class myroute extends RouteBuilder{
    @Override
    public void configure() throws Exception {
        // from("timer:foo?period=1s" )
        //     .bean("actualizaTareaBean","actualizaTarea")
        //         .to("log:foo");
    }
}
