package pe.com.tss.runakuna.security.model.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.UsuarioDto;
import pe.com.tss.runakuna.security.config.JwtSettings;
import pe.com.tss.runakuna.security.model.Scopes;
import pe.com.tss.runakuna.security.model.UserContext;
import pe.com.tss.runakuna.service.EmpleadoService;
import pe.com.tss.runakuna.service.UsuarioService;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by josediaz on 23/11/2016.
 */
@Component
public class JwtTokenFactory {
    private final JwtSettings settings;


    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    public JwtTokenFactory(JwtSettings settings) {
        this.settings = settings;
    }

    /**
     * Factory method for issuing new JWT Tokens.
     *
     * @param username
     * @param roles
     * @return
     */
    public AccessJwtToken createAccessJwtToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUsername()))
            throw new IllegalArgumentException("Cannot create JWT Token without username");

        if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty())
            throw new IllegalArgumentException("User doesn't have any privileges");

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.put("scopes", userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

         EmpleadoDto empleadoDto = new EmpleadoDto();

        UsuarioDto usuarioDto = usuarioService.cargarUsuarioPorcuentaUsuario(userContext.getUsername());
        empleadoDto.setIdEmpleado(usuarioDto.getIdEmpleado());
        EmpleadoDto respuesta = empleadoService.verEmpleado(empleadoDto);

        claims.put("empleado", respuesta.getNombre() + " " + respuesta.getApellidoPaterno() + " " + respuesta.getApellidoMaterno() );

        DateTime currentTime = new DateTime();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setIssuedAt(currentTime.toDate())
                .setExpiration(currentTime.plusMinutes(settings.getTokenExpirationTime()).toDate())
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();

        return new AccessJwtToken(token, claims);
    }

    public JwtToken createRefreshToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUsername())) {
            throw new IllegalArgumentException("Cannot create JWT Token without username");
        }

        DateTime currentTime = new DateTime();

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.put("scopes", Arrays.asList(Scopes.REFRESH_TOKEN.authority()));

        EmpleadoDto empleadoDto = new EmpleadoDto();

        UsuarioDto usuarioDto = usuarioService.cargarUsuarioPorcuentaUsuario(userContext.getUsername());
        empleadoDto.setIdEmpleado(usuarioDto.getIdEmpleado());
        EmpleadoDto respuesta = empleadoService.verEmpleado(empleadoDto);

        claims.put("empleado", respuesta.getNombre() + " " + respuesta.getApellidoPaterno() + " " + respuesta.getApellidoMaterno() );


        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(currentTime.toDate())
                .setExpiration(currentTime.plusMinutes(settings.getRefreshTokenExpTime()).toDate())
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();

        return new AccessJwtToken(token, claims);
    }
}