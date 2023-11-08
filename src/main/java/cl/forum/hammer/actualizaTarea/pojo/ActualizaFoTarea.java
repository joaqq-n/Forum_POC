package cl.forum.hammer.actualizaTarea.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "IdtplTarea"
})

public class ActualizaFoTarea  implements Serializable	{
		
	@JsonProperty("IdtplTarea")	
    private BigDecimal idtplTarea;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public ActualizaFoTarea() { }

	public ActualizaFoTarea(BigDecimal idtplTarea) {
		super();
		this.idtplTarea = idtplTarea;
	}

	@JsonProperty("IdtplTarea")	
	public BigDecimal getIdtplTarea() {
		return idtplTarea;
	}

	@JsonProperty("IdtplTarea")	
	public void setIdtplTarea(BigDecimal idtplTarea) {
		this.idtplTarea = idtplTarea;
	}


	
	
	
	
}
