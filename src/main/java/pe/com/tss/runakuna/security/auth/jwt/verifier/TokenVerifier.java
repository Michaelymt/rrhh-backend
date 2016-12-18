package pe.com.tss.runakuna.security.auth.jwt.verifier;

/**
 * Created by josediaz on 23/11/2016.
 */
public interface TokenVerifier {
    public boolean verify(String jti);
}
