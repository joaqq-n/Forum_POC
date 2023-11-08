package cl.forum.hammer.actualizaTarea.restGet;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import io.quarkus.runtime.annotations.RegisterForReflection;

@ApplicationScoped
@Named("testBean")
@RegisterForReflection
public class BeanTest {
    public String hello(String name) {
        return "Hello " + name + " from the NamedBean";
    }
}
