package pe.com.tss.runakuna.exception;

/**
 * Created by josediaz on 28/10/2016.
 */
public class GenericRestException extends RuntimeException {
    private String errorCode;

    public GenericRestException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }


    public GenericRestException(String errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}