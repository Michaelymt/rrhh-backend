package pe.com.tss.runakuna.dto;

import java.util.List;

/**
 * Created by josediaz on 14/12/2016.
 */
public class AlertaDto {

    private Long idAlerta;
    private Long idEmpresa;
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
    private List<AlertaSubscriptorDto> subscriptores;
    

    public Long getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(Long idAlerta) {
        this.idAlerta = idAlerta;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(Integer correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Integer getDashboard() {
        return dashboard;
    }

    public void setDashboard(Integer dashboard) {
        this.dashboard = dashboard;
    }

    public String getPersonalAsunto() {
        return personalAsunto;
    }

    public void setPersonalAsunto(String personalAsunto) {
        this.personalAsunto = personalAsunto;
    }

    public String getPersonalCuerpo() {
        return personalCuerpo;
    }

    public void setPersonalCuerpo(String personalCuerpo) {
        this.personalCuerpo = personalCuerpo;
    }

    public String getGrupalAsunto() {
        return grupalAsunto;
    }

    public void setGrupalAsunto(String grupalAsunto) {
        this.grupalAsunto = grupalAsunto;
    }

    public String getGrupalCuerpo() {
        return grupalCuerpo;
    }

    public void setGrupalCuerpo(String grupalCuerpo) {
        this.grupalCuerpo = grupalCuerpo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

	public Integer getJefeInmediato() {
		return jefeInmediato;
	}

	public void setJefeInmediato(Integer jefeInmediato) {
		this.jefeInmediato = jefeInmediato;
	}

	public List<AlertaSubscriptorDto> getSubscriptores() {
		return subscriptores;
	}

	public void setSubscriptores(List<AlertaSubscriptorDto> subscriptores) {
		this.subscriptores = subscriptores;
	}
}
