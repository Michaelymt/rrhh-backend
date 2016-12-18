package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.dto.AlertaDto;
import pe.com.tss.runakuna.dto.NotificacionDto;

/**
 * Created by josediaz on 14/12/2016.
 */
public interface AlertaService {

    public AlertaDto obtenerAlerta(String codigo);
    NotificacionDto registrarAlerta(AlertaDto alertaDto);
    NotificacionDto actualizarAlerta(AlertaDto alertaDto);
    List<AlertaDto> obtenerAlertas(AlertaDto alertaDto);
    AlertaDto obtenerAlertaDetalle(Long idAlerta);
}
