package cl.forum.hammer.actualizaTarea.restGet;

import javax.ws.rs.GET;
import org.apache.camel.Exchange;

import cl.forum.hammer.actualizaTarea.pojo.ResponseForum;

//@Path("/autorizaciones/v0/")
public interface ActualizaTareaInterface {
	
	//@Path("autorizaciones")
	@GET	
	public ResponseForum actualizaTarea(Exchange ex);
	//@QueryParam("idCliente") String idCliente, @QueryParam("idFuncionalidad") String idFuncionalidad);
		

}
