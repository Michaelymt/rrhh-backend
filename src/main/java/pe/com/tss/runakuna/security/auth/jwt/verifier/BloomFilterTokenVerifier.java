package pe.com.tss.runakuna.security.auth.jwt.verifier;

import org.springframework.stereotype.Component;

/**
 * Created by josediaz on 23/11/2016.
 */
@Component
public class BloomFilterTokenVerifier implements TokenVerifier {
    @Override
    public boolean verify(String jti) {
        return true;
    }
}
