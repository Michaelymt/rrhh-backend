package pe.com.tss.runakuna.dto;

/**
 * Created by josediaz on 14/12/2016.
 */
public class AlertaSubscriptorDto {

    private Long idAlertaSubscriptor;
	private Long idAlerta;
    private Long idEmpleado;
    

    public Long getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(Long idAlerta) {
        this.idAlerta = idAlerta;
    }

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Long getIdAlertaSubscriptor() {
		return idAlertaSubscriptor;
	}

	public void setIdAlertaSubscriptor(Long idAlertaSubscriptor) {
		this.idAlertaSubscriptor = idAlertaSubscriptor;
	}

   
}
