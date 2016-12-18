package pe.com.tss.runakuna.dto;

import java.util.List;

/**
 * Created by josediaz on 7/11/2016.
 */
public class ListResult {

    private List<BusquedaEmpleadoDto> resultList;

    public List<BusquedaEmpleadoDto> getResultList() {
        return resultList;
    }

    public void setResultList(List<BusquedaEmpleadoDto> resultList) {
        this.resultList = resultList;
    }
}
