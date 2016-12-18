package pe.com.tss.runakuna.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class JsonVarbinarySerializer extends JsonSerializer<byte[]> {

    @Override
    public void serialize (byte[] value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
    	
    	String cadena = Base64.getEncoder().encodeToString(value);
    	
        gen.writeString(cadena);
        
    }
}