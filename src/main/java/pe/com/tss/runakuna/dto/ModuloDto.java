package pe.com.tss.runakuna.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by josediaz on 25/11/2016.
 */
public class ModuloDto {

    protected Long idModulo;

    private String codigo;

    private String helpUrl;

    private String etiquetaMenu;

    private BigDecimal orden;

    private String nombre;

    private String url;

    private String tipoPermiso;

    private String visible;
    
    private String roleName;
    
    private String idEmpleado;

    private List<ModuloDto> subModulos;

    public ModuloDto(){}

    public ModuloDto(Long idModulo, String codigo, String helpUrl, String etiquetaMenu, BigDecimal orden, String nombre, String url, String roleName, String idEmpleado) {
        this.idModulo = idModulo;
        this.codigo = codigo;
        this.helpUrl = helpUrl;
        this.etiquetaMenu = etiquetaMenu;
        this.orden = orden;
        this.nombre = nombre;
        this.url = url;
        this.roleName = roleName;
        this.idEmpleado = idEmpleado;
    }

    public Long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getHelpUrl() {
        return helpUrl;
    }

    public void setHelpUrl(String helpUrl) {
        this.helpUrl = helpUrl;
    }

    public String getEtiquetaMenu() {
        return etiquetaMenu;
    }

    public void setEtiquetaMenu(String etiquetaMenu) {
        this.etiquetaMenu = etiquetaMenu;
    }

    public BigDecimal getOrden() {
        return orden;
    }

    public void setOrden(BigDecimal orden) {
        this.orden = orden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTipoPermiso() {
        return tipoPermiso;
    }

    public void setTipoPermiso(String tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public List<ModuloDto> getSubModulos() {
        return subModulos;
    }

    public void setSubModulos(List<ModuloDto> subModulos) {
        this.subModulos = subModulos;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
    
	
    
}
