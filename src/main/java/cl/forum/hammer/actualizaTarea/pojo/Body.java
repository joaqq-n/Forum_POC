
package cl.forum.hammer.actualizaTarea.pojo;

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
    "ActualizaFoTarea"
})
public class Body {

	
    @JsonProperty("ActualizaFoTarea")
    ActualizaFoTarea[] actualizaFoTarea ;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ActualizaFoTarea")
    public ActualizaFoTarea[] getActualizaFoTareas() {
        return actualizaFoTarea;
    }

    @JsonProperty("ActualizaFoTarea")
    public void setActualizaFoTarea(ActualizaFoTarea[] actualizaFoTarea) {
        this.actualizaFoTarea = actualizaFoTarea;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
