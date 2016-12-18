package pe.com.tss.runakuna.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by josediaz on 28/10/2016.
 */
public class JsonDateSerializer extends JsonSerializer<Date> {
    private SimpleDateFormat formatter =
            new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss.zz");

    @Override
    public void serialize (Date value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
        gen.writeString(formatter.format(value));
    }
}