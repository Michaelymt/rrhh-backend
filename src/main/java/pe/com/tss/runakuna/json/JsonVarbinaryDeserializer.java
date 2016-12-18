package pe.com.tss.runakuna.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Base64;

public class JsonVarbinaryDeserializer extends JsonDeserializer<byte[]> {

    @Override
    public byte[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    	
        String blobAsString = p.getText();
        try {
            if (!StringUtils.hasText(blobAsString)) {
                return null;
            }
            if(blobAsString.length()==0){
            	return null;
            }
            
           return Base64.getDecoder().decode(blobAsString);
        }
        catch (Exception pe) {
            throw new RuntimeException(pe);
        }
    }

}