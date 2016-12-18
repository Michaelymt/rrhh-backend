package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.dto.AlertaDto;

/**
 * Created by josediaz on 14/12/2016.
 */
public interface AlertaRepository {

    public AlertaDto obtenerAlerta(String codigo);
    List<AlertaDto> obtenerAlertas(AlertaDto dto);
    
}
