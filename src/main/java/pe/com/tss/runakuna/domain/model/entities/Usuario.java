package pe.com.tss.runakuna.domain.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import pe.com.tss.runakuna.domain.model.base.AuditingEntity;
import pe.com.tss.runakuna.enums.UserStatusEnum;

/**
 * Created by josediaz on 10/10/2016.
 */
@Entity
@Table(name = "Usuario")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario extends AuditingEntity implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idUsuario;
	private Long idEmpleado;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private UserStatusEnum estado;
    private String password;
    private String cuentaUsuario;

    private List<UsuarioRol> roles;

    @Transient
    public String getFullName() {
        return nombre + " " + apellidoPaterno + " " + apellidoMaterno;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuario")
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Column(name = "IdEmpleado")
    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Column(name = "Nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "ApellidoPaterno")
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    @Column(name = "ApellidoMaterno")
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "Estado")
    @Type(type = "pe.com.tss.runakuna.enums.GenericEnumUserType",
            parameters = {@org.hibernate.annotations.Parameter(name = "enumClass", value = "pe.com.tss.runakuna.enums.UserStatusEnum")})
    public UserStatusEnum getEstado() {
        return estado;
    }

    public void setEstado(UserStatusEnum estado) {
        this.estado = estado;
    }
    @Column(name = "Password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "CuentaUsuario")
    public String getCuentaUsuario() {
        return cuentaUsuario;
    }

    public void setCuentaUsuario(String cuentaUsuario) {
        this.cuentaUsuario = cuentaUsuario;
    }

    @OneToMany
    @JoinColumn(name="IdUsuario", referencedColumnName="IdUsuario")
    public List<UsuarioRol> getRoles() {
        return roles;
    }

    public void setRoles(List<UsuarioRol> roles) {
        this.roles = roles;
    }
}
