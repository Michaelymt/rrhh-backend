package pe.com.tss.runakuna.domain.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import pe.com.tss.runakuna.domain.model.base.AuditingEntity;

@Entity
@Table(name = "PeriodoEmpleadoTipoLicencia")
@NamedQuery(name = "PeriodoEmpleadoTipoLicencia.findAll", query = "SELECT e FROM PeriodoEmpleadoTipoLicencia e")
public class PeriodoEmpleadoTipoLicencia extends AuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idPeriodoEmpleadoTipoLicencia;
	private PeriodoEmpleado periodoEmpleado;
	private TipoLicencia tipoLicencia;
	private Integer diasLicencia;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPeriodoEmpleadoTipoLicencia")
	public Long getIdPeriodoEmpleadoTipoLicencia() {
		return idPeriodoEmpleadoTipoLicencia;
	}

	public void setIdPeriodoEmpleadoTipoLicencia(Long idPeriodoEmpleadoTipoLicencia) {
		this.idPeriodoEmpleadoTipoLicencia = idPeriodoEmpleadoTipoLicencia;
	}

	public PeriodoEmpleado getPeriodoEmpleado() {
		return periodoEmpleado;
	}

	public void setPeriodoEmpleado(PeriodoEmpleado periodoEmpleado) {
		this.periodoEmpleado = periodoEmpleado;
	}

	public TipoLicencia getTipoLicencia() {
		return tipoLicencia;
	}

	public void setTipoLicencia(TipoLicencia tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}
	
	@Column(name = "DiasLicencia")
	public Integer getDiasLicencia() {
		return diasLicencia;
	}

	public void setDiasLicencia(Integer diasLicencia) {
		this.diasLicencia = diasLicencia;
	}

	
	
	
	
	
}
