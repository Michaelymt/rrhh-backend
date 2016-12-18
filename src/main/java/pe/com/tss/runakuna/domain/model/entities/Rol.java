package pe.com.tss.runakuna.domain.model.entities;

import org.hibernate.annotations.Type;
import pe.com.tss.runakuna.domain.model.base.AuditingEntity;
import pe.com.tss.runakuna.enums.SiNoEnum;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by josediaz on 24/11/2016.
 */
@Entity
@Table(name="Rol")
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol extends AuditingEntity implements Serializable {

    private Long idRol;
    private String nombre;
    private String descripcion;
    private Integer rolSistema;
    private SiNoEnum estado;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdRol")
    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }
    @Column(name="Nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Column(name="Descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    @Column(name = "RolSistema")
    public Integer getRolSistema() {
        return rolSistema;
    }

    public void setRolSistema(Integer rolSistema) {
        this.rolSistema = rolSistema;
    }

    @Column(name="Estado")
    @Type(type = "pe.com.tss.runakuna.enums.GenericEnumUserType",
            parameters = {@org.hibernate.annotations.Parameter(name = "enumClass", value = "pe.com.tss.runakuna.enums.SiNoEnum")})
    public SiNoEnum getEstado() {
        return estado;
    }

    public void setEstado(SiNoEnum estado) {
        this.estado = estado;
    }

    public String authority() {
        return "ROLE_" + this.nombre;
    }
}
