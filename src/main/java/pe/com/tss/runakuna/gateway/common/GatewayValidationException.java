package pe.com.tss.runakuna.gateway.common;

/**
 * Created by josediaz on 28/10/2016.
 */
public class GatewayValidationException extends RuntimeException{
    private String errorCode;
    private String errorMessage;

    public GatewayValidationException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
