package cl.forum.hammer.actualizaTarea.restGet;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedCaseInsensitiveMap;

import cl.forum.hammer.actualizaTarea.pojo.ActualizaFoTarea;
import cl.forum.hammer.actualizaTarea.pojo.Body;
import cl.forum.hammer.actualizaTarea.pojo.ResponseForum;
import cl.forum.hammer.actualizaTarea.pojo.Return;
import io.quarkus.runtime.annotations.RegisterForReflection;


@ApplicationScoped
@Named("actualizaTareaBean")
@RegisterForReflection
public class ActualizaTarea implements ActualizaTareaInterface {
	private final String OK = "OK";
	private static final Logger LOG = LoggerFactory.getLogger(ActualizaTarea.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");

	/*******************************************************************************************************
	 * leerFoDistribuidorXSesion
	 *******************************************************************************************************/

	@Override
	public ResponseForum actualizaTarea(Exchange ex) {

		ResponseForum response = new ResponseForum();
		Return retorno = new Return();
		Body cuerpo = new Body();
		List<ActualizaFoTarea> listaActualizaFoTarea = new ArrayList<ActualizaFoTarea>();

		try {

			Object body = ex.getIn().getBody();

			LinkedHashMap<Object, Object> objeto = (LinkedHashMap<Object, Object>) body;

			@SuppressWarnings({ "unused", "unchecked" })
			LinkedCaseInsensitiveMap<Object> linkedMap = null;

			// @SuppressWarnings("unchecked")
			// List<Object>
			// salidaProcedimiento=(ArrayList<Object>)objeto.get("#result-set-1");
			//
			@SuppressWarnings("unchecked")
			Integer update_count_1 = (Integer) objeto.get("#update-count-1");

			if (update_count_1 == 2) {
				// cuerpo.setUpdate_Count(update_count_1.toString());
				retorno.setCode("200");
				retorno.setMessage("Filas Insertada: cod." + update_count_1);
				response.setSuccess("true");

			} else if (update_count_1 == 1) {
				// cuerpo.setUpdate_Count(update_count_1.toString());
				retorno.setCode("200");
				retorno.setMessage("Filas actualizada: cod." + update_count_1);
				response.setSuccess("true");

			} else {
				LOG.info("modificaCliente.2 DATOS NO MODIFICADOS");
				retorno.setCode("DATOS NO MODIFICADOS");
				retorno.setMessage(OK);
				response.setSuccess("false");
			}

		} catch (Exception e) {

			LOG.info("Ocurrio un error : " + e.getMessage());
			e.printStackTrace();

			retorno.setMessage(e.getMessage());
			retorno.setCode("101");
			response.setSuccess("false");
		}

		response.setBody(cuerpo);
		response.setRetorno(retorno);
		return response;
	}

}