package cl.forum.hammer.actualizaTarea.processor;

import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.forum.hammer.actualizaTarea.pojo.Body;
import cl.forum.hammer.actualizaTarea.pojo.ResponseForum;
import cl.forum.hammer.actualizaTarea.pojo.Return;
import io.quarkus.runtime.annotations.RegisterForReflection;



@ApplicationScoped
@Named("errorResponseProcessor")
@RegisterForReflection
public class ProcesorException implements Processor {
	
	private static final Logger log = LoggerFactory.getLogger(ProcesorException.class);
	@Override
	public void process(Exchange ex) throws Exception {
		
		
		ResponseForum response = new ResponseForum();
		Return retorno= new Return();
		Body body= new Body();
		
		ConcurrentHashMap<String,Object> prop=(ConcurrentHashMap<String,Object>)ex.getProperties();
		
		if(prop!=null){
			Exception exec=(Exception)prop.get("CamelExceptionCaught");
			
			
			
			log.info("ProcesorException.error.1");
			exec.printStackTrace();
			
			String tipoExecption=(String)ex.getIn().getHeader("tipoError");
			
			if(tipoExecption.equals("org.apache.camel.TypeConversionException")){
				retorno.setMessage("Parametros de entrada Null");
				retorno.setCode("1");
				response.setSuccess("false");//
			}else if(tipoExecption.equals("java.lang.NumberFormatException")){
				retorno.setMessage("Error en Tipo de datos");
				retorno.setCode("2");
				response.setSuccess("false");
			}else if(tipoExecption.equals("java.sql.SQLException")){
				retorno.setMessage("Error en en ejecucion de base de datos");
				retorno.setCode("3");
				response.setSuccess("false");
			}else if(tipoExecption.equals("java.lang.Exception")){
				retorno.setMessage(exec.getMessage());
				retorno.setCode("10");
				response.setSuccess("false");
			}
		}else{
			retorno.setMessage("ERROR");
			retorno.setCode("2");
			response.setSuccess("false");
		}
		
		response.setRetorno(retorno);
		
		ex.getIn().setBody(response);
		
	}

}
