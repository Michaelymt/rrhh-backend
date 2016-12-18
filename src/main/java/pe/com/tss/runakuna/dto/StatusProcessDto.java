package pe.com.tss.runakuna.dto;

/**
 * Created by josediaz on 7/11/2016.
 */
public class StatusProcessDto {

    private String status;
    private String message;

    public StatusProcessDto(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public StatusProcessDto() {
        //Mapper
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
