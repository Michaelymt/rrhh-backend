package pe.com.tss.runakuna.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import pe.com.tss.runakuna.util.EnumBase;

import java.io.IOException;

/**
 * Created by josediaz on 28/10/2016.
 */
public class JsonEnumSerializer extends JsonSerializer<EnumBase> {

    @Override
    public void serialize (EnumBase value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
        gen.writeString((value.getCode()));
    }
}
