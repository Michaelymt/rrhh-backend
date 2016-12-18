package pe.com.tss.runakuna.gateway.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by josediaz on 28/10/2016.
 */
public class GatewayExceptionHandler {


    public ResponseEntity<?> handleResourceNotFoundException(
            GatewayValidationException validationException,
            HttpServletRequest request) {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setCode(validationException.getErrorCode());
        errorDetail.setMessage(validationException.getErrorMessage());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.BAD_REQUEST);
    }
}
