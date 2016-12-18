package pe.com.tss.runakuna.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class JsonFlagIntegerSerializer extends JsonSerializer<Integer> {

    @Override
    public void serialize (Integer value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
    	
    	Boolean data = false;
    	
    	
    	if(value != null && value.equals(1)){
    		data = true;
    	}
    	
        gen.writeBoolean(data);
    }
}