package pe.com.tss.runakuna.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;

import java.util.Date;

/**
 * Created by josediaz on 8/11/2016.
 */
public class BusquedaPermisoEmpleadoDto extends PageableFilter {

    private Long idEmpleado;
    private String nombreEmpleado;


    @JsonSerialize(using=JsonDateSimpleSerializer.class)
    @JsonDeserialize(using=JsonDateSimpleDeserializer.class)
    private Date desde;

    @JsonSerialize(using=JsonDateSimpleSerializer.class)
    @JsonDeserialize(using=JsonDateSimpleDeserializer.class)
    private Date hasta;


    private Long unidadNegocio;
    private Long departamento;
    private Long proyecto;

    private String codigoPermiso;
    private String jefeInmediato;

    private String estado;
    private String estadoString;

    private Long idJefeInmediato;


    public String getCodigoPermiso() {
        return codigoPermiso;
    }

    public void setCodigoPermiso(String codigoPermiso) {
        this.codigoPermiso = codigoPermiso;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }


    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public Long getUnidadNegocio() {
        return unidadNegocio;
    }

    public void setUnidadNegocio(Long unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    public Long getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Long departamento) {
        this.departamento = departamento;
    }

    public Long getProyecto() {
        return proyecto;
    }

    public void setProyecto(Long proyecto) {
        this.proyecto = proyecto;
    }

    public String getJefeInmediato() {
        return jefeInmediato;
    }

    public void setJefeInmediato(String jefeInmediato) {
        this.jefeInmediato = jefeInmediato;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstadoString() {
        return estadoString;
    }

    public void setEstadoString(String estadoString) {
        this.estadoString = estadoString;
    }

	public Long getIdJefeInmediato() {
		return idJefeInmediato;
	}

	public void setIdJefeInmediato(Long idJefeInmediato) {
		this.idJefeInmediato = idJefeInmediato;
	}
    
}
