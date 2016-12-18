package pe.com.tss.runakuna.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by josediaz on 9/11/2016.
 */
public class ListEmpleadosNuevosYAntiguos {

    private List<EmpleadoDto> empleadosNuevos;
    private List<EmpleadoDto> empleadosAntiguos;

    public ListEmpleadosNuevosYAntiguos(){
        this.empleadosNuevos = new ArrayList<>();
        this.empleadosAntiguos = new ArrayList<>();
    }

    public List<EmpleadoDto> getEmpleadosNuevos() {
        return empleadosNuevos;
    }

    public void setEmpleadosNuevos(List<EmpleadoDto> empleadosNuevos) {
        this.empleadosNuevos = empleadosNuevos;
    }

    public List<EmpleadoDto> getEmpleadosAntiguos() {
        return empleadosAntiguos;
    }

    public void setEmpleadosAntiguos(List<EmpleadoDto> empleadosAntiguos) {
        this.empleadosAntiguos = empleadosAntiguos;
    }
}
