package pe.com.tss.runakuna.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OptimisticLock;
import pe.com.tss.runakuna.domain.model.base.AuditingEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by josediaz on 24/11/2016.
 */
@Entity
@Table(name="UsuarioRol")
@NamedQuery(name="UsuarioRol.findAll", query="SELECT u FROM UsuarioRol u")
public class UsuarioRol extends AuditingEntity implements Serializable {

    private Long IdUsuarioRol;
    private Rol rol;
    private Usuario usuario;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuarioRol")
    public Long getIdUsuarioRol() {
        return IdUsuarioRol;
    }

    public void setIdUsuarioRol(Long idUsuarioRol) {
        IdUsuarioRol = idUsuarioRol;
    }
    @ManyToOne
    @JoinColumn(name="IdRol")
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }


    @ManyToOne
    @JoinColumn(name="IdUsuario")
    @OptimisticLock(excluded = false)
    @JsonIgnore
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
