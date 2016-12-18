package pe.com.tss.runakuna.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Base64;

public class JsonFlagIntegerDeserializer extends JsonDeserializer<Integer> {

    @Override
    public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    	
        
    	boolean blobAsString = p.getBooleanValue();
        try {
            if (blobAsString) {
                return new Integer(1);
            }
            
           return new Integer(0);
        }
        catch (Exception pe) {
            throw new RuntimeException(pe);
        }
    }

}