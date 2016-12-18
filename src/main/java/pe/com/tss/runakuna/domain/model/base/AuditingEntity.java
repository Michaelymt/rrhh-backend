package pe.com.tss.runakuna.domain.model.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Date;

@MappedSuperclass
public class AuditingEntity implements BaseEntity {
    private String creador;

    private Date fechaCreacion;

    private String actualizador;

    private Date fechaActualizacion;

    private Long rowVersion;

    @Column(name = "Creador")
    public String getCreador() {
        return this.creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    @Column(name = "FechaCreacion")
    public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Column(name = "Actualizador")
	public String getActualizador() {
		return actualizador;
	}

	public void setActualizador(String actualizador) {
		this.actualizador = actualizador;
	}

	@Column(name = "FechaActualizacion")
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@Version
    @Column(name = "RowVersion")
    public Long getRowVersion() {
        return rowVersion;
    }

    public void setRowVersion(Long rowVersion) {
        this.rowVersion = rowVersion;
    }
}
