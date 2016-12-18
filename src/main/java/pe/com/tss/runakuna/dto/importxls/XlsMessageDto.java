package pe.com.tss.runakuna.dto.importxls;

import java.util.List;

/**
 * Created by josediaz on 8/11/2016.
 */
public interface XlsMessageDto<TXlsMessageRsl extends XlsMessageRsl> {

    public Object getDtoReferenceId();

    public void addError(String value);

    public List<TXlsMessageRsl> getErrorList();

}
