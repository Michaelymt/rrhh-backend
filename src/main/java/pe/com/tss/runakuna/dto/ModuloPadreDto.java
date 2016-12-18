package pe.com.tss.runakuna.dto;

import java.math.BigDecimal;

/**
 * Created by josediaz on 25/11/2016.
 */
public class ModuloPadreDto extends  ModuloDto {

    private String codigoPadre;
    private String nombrePadre;
    private String urlPadre;


    public ModuloPadreDto() {
    }

    public ModuloPadreDto(String codigoPadre, String nombrePadre, String urlPadre,
                          long idModulo, String codigo, String helpUrl, String etiquetaMenu,
                          BigDecimal orden, String nombre, String url, String roleName, String idEmpleado) {
        super(idModulo, codigo, helpUrl, etiquetaMenu, orden, nombre, url, roleName, idEmpleado);
        this.codigoPadre = codigoPadre;
        this.nombrePadre = nombrePadre;
        this.urlPadre = urlPadre;
    }

    public String getCodigoPadre() {
        return codigoPadre;
    }

    public void setCodigoPadre(String codigoPadre) {
        this.codigoPadre = codigoPadre;
    }

    public String getNombrePadre() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }

    public String getUrlPadre() {
        return urlPadre;
    }

    public void setUrlPadre(String urlPadre) {
        this.urlPadre = urlPadre;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ModuloPadreDto that = (ModuloPadreDto) o;

        if (!codigoPadre.equals(that.codigoPadre)) return false;
        return idModulo  == that.idModulo;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + codigoPadre.hashCode();
        result = 31 * result + Long.hashCode(idModulo);
        return result;
    }

    @Override
    public String toString() {
        return "ModuloPadreDto{" +
                "codigoPadre='" + codigoPadre + '\'' +
                ", nombrePadre='" + nombrePadre + '\'' +
                '}';
    }

}
