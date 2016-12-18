package pe.com.tss.runakuna.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * Created by josediaz on 28/10/2016.
 */
public class DevelopmentException extends RuntimeException {
    private static final Logger LOG = LoggerFactory.getLogger(DevelopmentException.class);

    public DevelopmentException(String message) {
        super("Development Exception " + message);
        LOG.error("Development Exception " + message);
    }

    public DevelopmentException addData(String key, Object data) {
        LOG.error("Development Exception " +key+"="+toStr(data));
        return this;
    }

    private String toStr(Object data) {
        if (data==null) return "NULL";
        else if (data instanceof Collection<?>)  return StringUtils.collectionToCommaDelimitedString((Collection<?>) data);
        return data.toString();
    }
}
