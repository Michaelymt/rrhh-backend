package pe.com.tss.runakuna.domain.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import pe.com.tss.runakuna.domain.model.base.AuditingEntity;

@Entity
@Table(name = "Alerta")
@NamedQuery(name = "Alerta.findAll", query = "SELECT e FROM Alerta e")
public class Alerta extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idAlerta;
	private Empresa empresa;
	private String tipoAlerta;
	private String codigo;
	private Integer correoElectronico;
	private Integer dashboard;
	private String personalAsunto;
	private String personalCuerpo;
	private String grupalAsunto;
	private String grupalCuerpo;
	private Integer jefeInmediato;
	private String estado;
	private List<AlertaSubscriptor> subscriptores;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAlerta")
	public Long getIdAlerta() {
		return idAlerta;
	}
	public void setIdAlerta(Long idAlerta) {
		this.idAlerta = idAlerta;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdEmpresa")
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	@Column(name = "TipoAlerta")
	public String getTipoAlerta() {
		return tipoAlerta;
	}
	public void setTipoAlerta(String tipoAlerta) {
		this.tipoAlerta = tipoAlerta;
	}
	
	@Column(name = "Codigo")
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "CorreoElectronico")
	public Integer getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(Integer correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	@Column(name = "Dashboard")
	public Integer getDashboard() {
		return dashboard;
	}
	public void setDashboard(Integer dashboard) {
		this.dashboard = dashboard;
	}
	
	@Column(name = "PersonalAsunto")
	public String getPersonalAsunto() {
		return personalAsunto;
	}
	public void setPersonalAsunto(String personalAsunto) {
		this.personalAsunto = personalAsunto;
	}
	
	@Column(name = "PersonalCuerpo")
	public String getPersonalCuerpo() {
		return personalCuerpo;
	}
	public void setPersonalCuerpo(String personalCuerpo) {
		this.personalCuerpo = personalCuerpo;
	}
	
	@Column(name = "GrupalAsunto")
	public String getGrupalAsunto() {
		return grupalAsunto;
	}
	public void setGrupalAsunto(String grupalAsunto) {
		this.grupalAsunto = grupalAsunto;
	}
	
	@Column(name = "GrupalCuerpo")
	public String getGrupalCuerpo() {
		return grupalCuerpo;
	}
	public void setGrupalCuerpo(String grupalCuerpo) {
		this.grupalCuerpo = grupalCuerpo;
	}
	
	@Column(name = "JefeInmediato")
	public Integer getJefeInmediato() {
		return jefeInmediato;
	}
	public void setJefeInmediato(Integer jefeInmediato) {
		this.jefeInmediato = jefeInmediato;
	}
	
	@Column(name = "Estado")
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},orphanRemoval=true, mappedBy = "alerta")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE,
			org.hibernate.annotations.CascadeType.MERGE,
			org.hibernate.annotations.CascadeType.PERSIST})
	public List<AlertaSubscriptor> getSubscriptores() {
		return subscriptores;
	}
	public void setSubscriptores(List<AlertaSubscriptor> subscriptores) {
		this.subscriptores = subscriptores;
	}
	
	
	public AlertaSubscriptor removeAlertaSubscriptor(AlertaSubscriptor alertaSubscriptor) {
    	getSubscriptores().remove(alertaSubscriptor);
    	alertaSubscriptor.setAlerta(null);

        return alertaSubscriptor;
    }
	
	
		      

}
