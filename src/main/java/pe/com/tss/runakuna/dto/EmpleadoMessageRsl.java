package pe.com.tss.runakuna.dto;

import pe.com.tss.runakuna.dto.importxls.XlsMessageRsl;

/**
 * Created by josediaz on 8/11/2016.
 */
public class EmpleadoMessageRsl extends XlsMessageRsl {

    private Long drayMilesId;
    private Integer rowNumber;
    private String processStatus;
    private String errorMessage;

    public EmpleadoMessageRsl(String dtoId, String message) {
        super(dtoId, message);
    }


    public EmpleadoMessageRsl() {

    }
    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Long getDrayMilesId() {
        return drayMilesId;
    }

    public void setDrayMilesId(Long drayMilesId) {
        this.drayMilesId = drayMilesId;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}