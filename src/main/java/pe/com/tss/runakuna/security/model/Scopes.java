package pe.com.tss.runakuna.security.model;

/**
 * Created by josediaz on 23/11/2016.
 */
public enum Scopes {
    REFRESH_TOKEN;

    public String authority() {
        return "ROLE_" + this.name();
    }
}
