package pe.com.tss.runakuna.dto.importxls;

/**
 * Created by josediaz on 7/11/2016.
 */
public class XlsMessageRsl {

    private String dtoId;
    private String message;

    public XlsMessageRsl() {
        //Mapper
    }

    public XlsMessageRsl(String dtoId, String message) {
        this.dtoId = dtoId;
        this.message = message;
    }

    public String getDtoIdSafeNull() {
        return dtoId == null ? "" : dtoId;
    }

    public String getDtoId() {
        return dtoId;
    }

    public void setDtoId(String dtoId) {
        this.dtoId = dtoId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
