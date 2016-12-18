package pe.com.tss.runakuna.security.auth.jwt.extractor;

/**
 * Created by josediaz on 23/11/2016.
 */
public interface TokenExtractor {
    public String extract(String payload);
}
