package pe.com.tss.runakuna.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pe.com.tss.runakuna.security.auth.JwtAuthenticationToken;
import pe.com.tss.runakuna.security.model.UserContext;

/**
 * Created by josediaz on 23/11/2016.
 */
@RestController
public class ProfileEndpoint {
    @RequestMapping(value = "/api/me", method = RequestMethod.GET)
    public
    @ResponseBody
    UserContext get(JwtAuthenticationToken token) {
        return (UserContext) token.getPrincipal();
    }
}
