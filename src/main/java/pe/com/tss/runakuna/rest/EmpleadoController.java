package pe.com.tss.runakuna.rest;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.com.tss.runakuna.dto.*;
import pe.com.tss.runakuna.dto.importxls.ExcelImporter;
import pe.com.tss.runakuna.exception.GenericRestException;
import pe.com.tss.runakuna.service.EmpleadoService;
import pe.com.tss.runakuna.service.MarcacionEmpleadoService;
import pe.com.tss.runakuna.service.PeriodoEmpleadoService;
import pe.com.tss.runakuna.util.Constants;
import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.util.FileUtils;
import pe.com.tss.runakuna.util.TemplateExcelExporter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(value = "/empleado")
public class EmpleadoController {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmpleadoController.class);
    private static String OS = null;

	@Autowired
	EmpleadoService empleadoService;
	
	@Autowired
	MarcacionEmpleadoService marcacionEmpleadoService;

	@Autowired
	PeriodoEmpleadoService periodoEmpleadoService;
	
	@Autowired
	private ResourceLoader resourceLoader;

	@RequestMapping(value = "/exportarEmpleados", method = RequestMethod.POST)
	@ResponseBody
	public void exportarEmpleados(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String templateFileName = "excel/Empleados.xlsx";

		downloadTemplate(request,response,templateFileName);
	}

	@RequestMapping(value = "/exportarMarcaciones", method = RequestMethod.POST)
	@ResponseBody
	public void exportarMarcaciones(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String templateFileName = "excel/Marcaciones.xlsx";

		downloadTemplateMarcaciones(request,response,templateFileName);
	}

    @RequestMapping(value = "/busquedaPermisoEmpleado", method = RequestMethod.POST)
    public @ResponseBody  ResponseEntity<List<PermisoEmpleadoDto>>  busquedaPermisoEmpleado(@RequestBody BusquedaPermisoEmpleadoDto busquedaPermisoEmpleadoDto) {

        List<PermisoEmpleadoDto> result = new ArrayList<>();
        result = empleadoService.busquedaPermisoEmpleado(busquedaPermisoEmpleadoDto);
		if(result == null)
			result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }
    
    @RequestMapping(value = "/busquedaVacacionesEmpleado", method = RequestMethod.POST)
    public @ResponseBody  ResponseEntity<List<VacacionEmpleadoDto>>  busquedaVacacionesEmpleado(@RequestBody BusquedaVacacionesEmpleadoDto busquedaVacacionesEmpleadoDto) {

        List<VacacionEmpleadoDto> result = new ArrayList<>();
        result = empleadoService.busquedaVacacionesEmpleado(busquedaVacacionesEmpleadoDto);
		if(result == null)
			result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }
    
    @RequestMapping(value = "/busquedaHorasExtrasEmpleado", method = RequestMethod.POST)
    public @ResponseBody  ResponseEntity<List<HorasExtraDto>>  busquedaHorasExtrasEmpleado(@RequestBody BusquedaHorasExtraEmpleadoDto busquedaHorasExtraEmpleadoDto) {

        List<HorasExtraDto> result = new ArrayList<>();
        result = empleadoService.busquedaHorasExtrasEmpleado(busquedaHorasExtraEmpleadoDto);
		if(result == null)
			result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }
    
    @RequestMapping(value = "/registrarHorasExtra", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto registrarHorasExtra(@RequestBody HorasExtraDto horasExtraDto) {

		NotificacionDto dto = empleadoService.registrarHorasExtra(horasExtraDto);
		return dto;

	}
    
    @RequestMapping(value = "/eliminarHorasExtraEmpleado", method = RequestMethod.POST)
    public ResponseEntity<String> eliminarHorasExtraEmpleado(@RequestBody HorasExtraDto horasExtraDto) {

        Long idHorasExtraEmpleado =  empleadoService.eliminarHorasExtra(horasExtraDto.getIdHorasExtra());

        return new ResponseEntity<String>(idHorasExtraEmpleado.toString(),HttpStatus.OK);
    }
    
    @RequestMapping(value = "/aprobarHorasExtraEmpleado", method = RequestMethod.POST)
    public @ResponseBody NotificacionDto aprobarHorasExtraEmpleado(@RequestBody HorasExtraDto horasExtraDto) {

    	NotificacionDto dto =  empleadoService.aprobarHorasExtra(horasExtraDto);

        return dto;
    }
    
    @RequestMapping(value = "/rechazarHorasExtraEmpleado", method = RequestMethod.POST)
    public @ResponseBody NotificacionDto rechazarHorasExtraEmpleado(@RequestBody HorasExtraDto horasExtraDto) {

    	NotificacionDto dto =  empleadoService.rechazarHorasExtra(horasExtraDto);

        return dto;
    }
    
	@RequestMapping(value = "/informacionAdicionalHorasExtras", method = RequestMethod.POST)
	public @ResponseBody HorasExtraDto informacionAdicionalHorasExtras(@RequestBody EmpleadoDto empleado) {
		HorasExtraDto result = empleadoService.informacionAdicionalHorasExtras(empleado);
		return result;
	}

	@RequestMapping(value = "/busquedaMarcacionesEmpleado", method = RequestMethod.POST)
	public @ResponseBody  ResponseEntity<List<MarcacionDto>>  busquedaMarcacionesEmpleado(@RequestBody BusquedaMarcacionDto busquedaMarcacionDto) {

		List<MarcacionDto> result = new ArrayList<>();
		result = empleadoService.busquedaMarcacionesEmpleado(busquedaMarcacionDto);
		if(result == null)
			result = new ArrayList<>();
		
		return new ResponseEntity<>(result, HttpStatus.OK);

	}
	@RequestMapping(value = "/busquedaEmpleado", method = RequestMethod.POST)
	public @ResponseBody  ResponseEntity<List<EmpleadoDto>>  busquedaEmpleado(@RequestBody BusquedaEmpleadoDto busquedaEmpleadoDto) {

		List<EmpleadoDto> result = new ArrayList<>();
		result = empleadoService.busquedaEmpleado(busquedaEmpleadoDto);
		if(result == null)
			result = new ArrayList<>();
		
		return new ResponseEntity<>(result, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/busquedaCodigoPermiso", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PermisoEmpleadoDto>> obtenerCodigoPermiso(
			@RequestParam(name = "codigo", required = true) String codigo) {
		List<PermisoEmpleadoDto> result = new ArrayList<>();
		result = empleadoService.obtenerCodigoPermiso(codigo);
		if (result == null)
			result = new ArrayList<>();
		LOG.info("Msg obtenerCodigoPermiso: " + result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/registrarEmpleado", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto registrarEmpleado(@RequestBody EmpleadoDto empleado) {
		
		NotificacionDto notificacion = new NotificacionDto(); 
		NotificacionDto notificacionPeriodoEmpleado = new NotificacionDto(); 
		
		if(empleado.getIdEmpleado() == null){
			empleado.setEstado("A");
			empleado.setFechaIngreso(new Date());
			notificacion = empleadoService.registrarEmpleado(empleado);
			notificacionPeriodoEmpleado = periodoEmpleadoService.registrarPeriodoEmpleado(empleado);
			
		}else{
			notificacion = empleadoService.actualizarEmpleado(empleado);
		}
		
		return notificacion;
	}
	
	@RequestMapping(value = "/registrarDarBajaEmpleado", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto registrarDarBajaEmpleado(@RequestBody EmpleadoDto empleado) {
		
		NotificacionDto notificacion = new NotificacionDto(); 
		
		notificacion = empleadoService.registrarDarBajaEmpleado(empleado);
		
		return notificacion;
	}
	
	@RequestMapping(value = "/registrarHorarioEmpleado", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto registrarHorarioEmpleado(@RequestBody HorarioEmpleadoDto horarioEmpleado) {
		
		NotificacionDto notificacion = new NotificacionDto(); 
		
		if(horarioEmpleado.getIdHorarioEmpleado() ==null)
			notificacion = empleadoService.registrarHorarioEmpleado(horarioEmpleado);
		else
			notificacion = empleadoService.actualizarHorarioEmpleado(horarioEmpleado);
		
		return notificacion;
	}
	
	@RequestMapping(value = "/verEmpleado", method = RequestMethod.POST)
	public @ResponseBody EmpleadoDto verEmpleado(@RequestBody EmpleadoDto empleado) {
		EmpleadoDto dto = empleadoService.verEmpleado(empleado);
		return dto;
	}
	
	@RequestMapping(value = "/actualizarDatosPersonales", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto actualizarDatosPersonales(@RequestBody EmpleadoDto empleado) {
		
		NotificacionDto notificacion = new NotificacionDto(); 
		
		notificacion = empleadoService.actualizarDatosPersonales(empleado);
		
		return notificacion;
	}
	
	@RequestMapping(value = "/verDocumentos", method = RequestMethod.POST)
	public @ResponseBody List<DocumentoEmpleadoDto> verDocumentos(@RequestBody EmpleadoDto empleado) {
		List<DocumentoEmpleadoDto> dto = empleadoService.verDocumentos(empleado);
		return dto;
	}
	
	@RequestMapping(value = "/verEducacion", method = RequestMethod.POST)
	public @ResponseBody List<EducacionDto> verEducacion(@RequestBody EmpleadoDto empleado) {
		List<EducacionDto> dto = empleadoService.verEducacion(empleado);
		return dto;
	}
	
	@RequestMapping(value = "/verExperienciaLaboral", method = RequestMethod.POST)
	public @ResponseBody List<ExperienciaLaboralDto> verExperienciaLaboral(@RequestBody EmpleadoDto empleado) {
		List<ExperienciaLaboralDto> dto = empleadoService.verExperienciaLaboral(empleado);
		return dto;
	}
	
	@RequestMapping(value = "/verEquipoEntregado", method = RequestMethod.POST)
	public @ResponseBody List<EquipoEntregadoDto> verEquipoEntregado(@RequestBody EmpleadoDto empleado) {
		List<EquipoEntregadoDto> dto = empleadoService.verEquipoEntregado(empleado);
		return dto;
	}
	
	@RequestMapping(value = "/verDependiente", method = RequestMethod.POST)
	public @ResponseBody List<DependienteDto> verDependiente(@RequestBody EmpleadoDto empleado) {
		List<DependienteDto> dto = empleadoService.verDependiente(empleado);
		return dto;
	}
	
	@RequestMapping(value = "/verLicencia", method = RequestMethod.POST)
	public @ResponseBody List<LicenciaDto> verLicencia(@RequestBody PeriodoEmpleadoDto periodoEmpleado) {
		List<LicenciaDto> dto = empleadoService.verLicencia(periodoEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/verHorarioEmpleado", method = RequestMethod.POST)
	public @ResponseBody HorarioEmpleadoDto verHorarioEmpleado(@RequestBody EmpleadoDto empleado) {
		HorarioEmpleadoDto dto = empleadoService.verHorarioEmpleado(empleado);
		return dto;
	}
	
	@RequestMapping(value = "/verHorariosEmpleado", method = RequestMethod.POST)
	public @ResponseBody List<HorarioEmpleadoDto> verHorariosEmpleado(@RequestBody EmpleadoDto empleado) {
		List<HorarioEmpleadoDto> dto = empleadoService.verHorariosEmpleado(empleado);
		return dto;
	}
	
	@RequestMapping(value = "/verHistoriaLaboral", method = RequestMethod.POST)
	public @ResponseBody List<HistoriaLaboralDto> verHistoriaLaboral(@RequestBody EmpleadoDto empleado) {
		List<HistoriaLaboralDto> dto = new ArrayList<>();
	    dto = empleadoService.obtenerHistoriaLaboral(empleado.getIdEmpleado());
	    
	    return dto;
	    
	}
	
	@RequestMapping(value = "/verPeriodoEmpleado", method = RequestMethod.POST)
	public @ResponseBody List<PeriodoEmpleadoDto> verPeriodoEmpleado(@RequestBody EmpleadoDto empleado) {
		List<PeriodoEmpleadoDto> dto = new ArrayList<>();
	    dto = empleadoService.verPeriodoEmpleado(empleado);
	    
	    return dto;
	    
	}
	
	@RequestMapping(value = "/verPermisoEmpleado", method = RequestMethod.POST)
	public @ResponseBody List<PermisoEmpleadoDto> verPermisoEmpleado(@RequestBody PeriodoEmpleadoDto permisoEmpleado) {
		List<PermisoEmpleadoDto> dto = new ArrayList<>();
	    dto = empleadoService.verPermisoEmpleado(permisoEmpleado);
	    
	    return dto;
	    
	}
	
	@RequestMapping(value = "/verVacaciones", method = RequestMethod.POST)
	public @ResponseBody List<VacacionDto> verVacaciones(@RequestBody PeriodoEmpleadoDto permisoEmpleado) {
		List<VacacionDto> dto = new ArrayList<>();
	    dto = empleadoService.verVacacion(permisoEmpleado);
	    
	    return dto;
	    
	}
	
	@RequestMapping(value = "/verMarcaciones", method = RequestMethod.POST)
	public @ResponseBody List<MarcacionDto> verMarcaciones(@RequestBody EmpleadoDto empleado) {
		List<MarcacionDto> dto = new ArrayList<>();
	    dto = empleadoService.verMarcacion(empleado);
	    
	    return dto;
	    
	}
	
	@RequestMapping(value = "/cargarArchivoDocumento", method=RequestMethod.POST)
	public  @ResponseBody FileDto cargarArchivoDocumento(@RequestParam List<MultipartFile> files, HttpServletResponse response) {
		
		FileDto dto = new FileDto();
		
		String cadena = "";
		MultipartFile file = files.get(0);
		
		try {
			
			cadena = Base64.getEncoder().encodeToString(file.getBytes());
			
			dto.setContent(cadena);
			dto.setName(file.getOriginalFilename());
			dto.setContentType(file.getContentType());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
    private void createDocExcelEmpty(HttpServletResponse response,String templateFileName) throws IOException {

        List<EmpleadoDto> pageableResult = new ArrayList<>();
        generateExcel(response,templateFileName,pageableResult);

    }


    @RequestMapping(value = "/descargarTemplateEmpleados", method = RequestMethod.GET)
    @ResponseBody
    public void downloadTemplateFormatProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String templateFileName = "excel/EmpleadosDownloadFormat.xlsx";

        createDocExcelEmpty(response, templateFileName);

    }


    @RequestMapping(value = "/descargarArchivoDocumento", method = RequestMethod.POST)
    @ResponseBody
    public void descargarArchivoDocumento(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String nombre = request.getParameter("nombre");
        String tipoArchivo = request.getParameter("tipoArchivo");
        String contenidoArchivo = request.getParameter("contenidoArchivo");
        String nombreArchivo = request.getParameter("nombreArchivo");

        byte[] contenido = Base64.getDecoder().decode(contenidoArchivo);

        response.setContentType(tipoArchivo);
        response.setHeader("Content-disposition", "attachment;filename=" + nombreArchivo);
        response.getOutputStream().write(contenido);

    }

    @RequestMapping(value = "/eliminarArchivoDocumento", produces = "application/json")
    public void eliminarArchivoDocumento(@RequestParam("fileNames") MultipartFile files,
                                         HttpServletResponse response) {

        String extention = files.getOriginalFilename();

        try {
            System.out.println(extention);
            System.out.println(files.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @RequestMapping(value = "/templateEmpleadosProcess", method = RequestMethod.GET)
    @ResponseBody
    public void downloadTemplateDrayListProcess(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String docname = (request.getParameter("docname").equalsIgnoreCase("undefined")) ? null
                : request.getParameter("docname");

        String templateFileName = System.getProperty("java.io.tmpdir", null) + ((isWindows()) ? "" : "/") + docname; // fileTemp


        TemplateExcelExporter report = new TemplateExcelExporter();

        report.exportX(templateFileName);

        report.writeExcelToResponse(response, "Empleados Process");

    }


    public static String getOsName() {
        if (OS == null) {
            OS = System.getProperty("os.name");
        }
        return OS;
    }

    public static boolean isWindows() {
        return getOsName().startsWith("Windows");
    }


    @RequestMapping(value = "importarArchivoEmpleados", produces = Constants.JSON)
    @ResponseBody
    public ResponseEntity<JsonResult> uploadFile(
            @RequestParam("files") MultipartFile uploadfile,
            HttpServletRequest request, HttpServletResponse response

    ) {

        try {
            String extention = uploadfile.getOriginalFilename();

            JsonResult resultFront = new JsonResult();

            List<EmpleadoDto> messageList = new ArrayList<>();


            List<EmpleadoDto> baseLaneList = parseEmpleadosFromExcel(resultFront,
                    uploadfile.getBytes());

            List<EmpleadoDto> empleadoListSend = new ArrayList<>();
            List<String> empleadoList= new ArrayList<>();

            for (EmpleadoDto empleadoDto : baseLaneList) {
                if (empleadoDto.hasError()) {
                    empleadoListSend.add(empleadoDto);
                }else{
                    if (!empleadoList.contains(empleadoDto.getCodigo())) {

                            empleadoListSend.add(empleadoDto);

                            empleadoList.add(empleadoDto.getCodigo());

                    }else{
                        empleadoDto
                                .addErrorStatus("Codigo ya repetido en el archivo.");

                        messageList.add(empleadoDto);
                    }
                }

            }

            ResponseEntity<List<EmpleadoDto>> responseEntity  = this.procesarMasivamenteEmpleados(empleadoListSend);

            List<EmpleadoDto> contextResponse = new ArrayList<>();

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                contextResponse = responseEntity.getBody();

            }

            messageList.addAll(contextResponse);
            Collections.sort(messageList, comparador);

            String docName = CreateFileExportResultEmpleado(messageList);

            resultFront.setFileDocName(docName);

            if (messageList.stream()
                    .filter(p -> p.getStatusProcessDtoList().stream()
                            .filter(e -> e.getStatus().equalsIgnoreCase("error")).collect(Collectors.toList())
                            .size() > 0)
                    .collect(Collectors.toList()).size() > 0) {
                resultFront.setDescription("Archivo cargado con errores");
                resultFront.setStatus("error");
                return new ResponseEntity<JsonResult>(resultFront, HttpStatus.ACCEPTED);
            }

            resultFront.setDescription("Archivo cargado correctamente");

            return new ResponseEntity<JsonResult>(resultFront, HttpStatus.OK);


       } catch (Exception e) {
            String msg = (e.getMessage() == null) ? "Error " : e.getMessage();
            LOG.error(Constants.ERROR + msg, e);

            throw new GenericRestException("ERR_001", "No se pudo realizar el import de empleados");
        }

    }


    public String CreateFileExportResultEmpleado(List<EmpleadoDto> messageList)
            throws IOException {

        String templateFileName = "excel/EmpleadosProcess.xlsx";

        TemplateExcelExporter report = new TemplateExcelExporter();

        loadRegistroEmpleadoProcess(templateFileName, report, messageList);

        String fileExcelDoc = "EmpleadoProcess" + System.currentTimeMillis() + "";// +".xlsx";

        File file = File.createTempFile(fileExcelDoc, ".xlsx");

        report.writeExcelToFile(file);
        return file.getName();
    }


    private void loadRegistroEmpleadoProcess(String templateFileName, TemplateExcelExporter report,
                                              List<EmpleadoDto> empleadosDtos) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + templateFileName);

        report.exportH(resource.getInputStream());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int index = 0;
        for (EmpleadoDto item : empleadosDtos) {
            index++;
            report.addRow(index);

            report.addColumnValue(0, item.getEmpleadoOrden());
            if (item.getCodigo() != null) {
                report.addColumnValue(1, item.getCodigo());
            }
            report.addColumnValue(2, item.getStatusProcessDtoList().get(0).getStatus());
            String listString = "";
            for (StatusProcessDto status : item.getStatusProcessDtoList()) {
                if (!status.getStatus().equalsIgnoreCase("Procesado")) {
                    listString += status.getMessage() + ". " + "\n";
                }
            }

            report.addColumnValue(3, listString);

        }
    }


    private Comparator<EmpleadoDto> comparador = new Comparator<EmpleadoDto>() {
        public int compare(EmpleadoDto a, EmpleadoDto b) {
            int resultado = Double.compare((double) a.getEmpleadoOrden(),
                    (double) b.getEmpleadoOrden());
            if (resultado != 0) {
                return resultado;
            }
            return resultado;
        }
    };


    @RequestMapping(value = "/procesarMasivamenteEmpleados", method = RequestMethod.POST)
    public ResponseEntity<List<EmpleadoDto>> procesarMasivamenteEmpleados(@RequestBody List<EmpleadoDto> dtos) {

        List<EmpleadoDto> empleadoDtoList = new ArrayList<>();
        try {

            LOG.info("Procesar excel de empleados");

            empleadoDtoList = empleadoService.procesarMasivamenteEmpleados(dtos);
        }catch (Exception e){
            LOG.debug("ERROR", e);
            String msg = ((e.getMessage()==null)?"":e.getMessage());

            throw new GenericRestException("ERR_001",msg);
        }


        return new ResponseEntity<>( empleadoDtoList , HttpStatus.OK);

    }



    private List<EmpleadoDto> parseEmpleadosFromExcel(JsonResult resultFront, byte[] bytes) throws Exception{


        List<EmpleadoDto> result = new ArrayList<>();

        File excel = FileUtils.createTempFile(".xls");
        FileUtils.writeToFile(excel, bytes);
        FileInputStream myInput = new FileInputStream(excel);
        Workbook myWorkBook = new XSSFWorkbook(myInput);
        Sheet mySheet = myWorkBook.getSheetAt(0);

        int numRow = mySheet.getLastRowNum();

        if(numRow>10000){
            EmpleadoDto empleadoDtoSendError = new EmpleadoDto();
            empleadoDtoSendError.addErrorStatus("Excel excede los 10,000 registros.");
            result.add(empleadoDtoSendError);
        }else {

            if(numRow==0){
                result.clear();
                EmpleadoDto empleadoDtoSendError = new EmpleadoDto();
                empleadoDtoSendError.addErrorStatus("Excel esta vacío");
                result.add(empleadoDtoSendError);

            }else {
                for (int i = 1; i < numRow + 1; i++) {
                    Row row = (Row) mySheet.getRow(i);
                    EmpleadoDto empleadoDto = null;

                    empleadoDto = poblarLineaDesdeTemplate(row);
                    empleadoDto.setEmpleadoOrden(i);

                    String validate = validateExcelEmpty(numRow + 1, empleadoDto);

                    if (validate.equalsIgnoreCase("EMPTY EXCEL")) {
                        result.clear();
                        EmpleadoDto empleadoDtoSendError = new EmpleadoDto();
                        empleadoDtoSendError.addErrorStatus("Excel esta vacío");
                        result.add(empleadoDtoSendError);
                        break;
                    }
                    if (validate.equalsIgnoreCase("EMPTY ROW")) {
                        continue;
                    }


                    if (empleadoDto.getErrorList().size() > 0) {
                        for (EmpleadoMessageRsl empleadoMessageRsl  : empleadoDto.getErrorList()) {
                            empleadoDto.addErrorStatus(empleadoMessageRsl.getMessage());
                        }
                    }

                    result.add(empleadoDto);
                }


                if(result.size()==0){
                    result.clear();
                    EmpleadoDto empleadoDtoSendError = new EmpleadoDto();
                    empleadoDtoSendError.addErrorStatus("Excel esta vacío");
                    result.add(empleadoDtoSendError);
                }
            }
        }


        return result;

    }


    private String validateExcelEmpty(int i, EmpleadoDto empleadoDto) {
        LOG.info("Validate Row Empty or Doc Empty");


        if(StringUtils.isEmpty((empleadoDto.getCodigo()==null)?"":empleadoDto.getCodigo().trim())
                && StringUtils.isEmpty((empleadoDto.getApellidoPaterno()==null)?"":empleadoDto.getApellidoPaterno().trim())
                &&StringUtils.isEmpty((empleadoDto.getApellidoMaterno()==null)?"":empleadoDto.getApellidoMaterno().trim())
                &&StringUtils.isEmpty((empleadoDto.getTipoDocumento()==null)?"":empleadoDto.getTipoDocumento().trim())
                &&StringUtils.isEmpty((empleadoDto.getNumeroDocumento()==null)?"":empleadoDto.getNumeroDocumento().trim())
                &&StringUtils.isEmpty((empleadoDto.getGenero()==null)?"":empleadoDto.getGenero().trim())
                &&StringUtils.isEmpty((empleadoDto.getEstadoCivil()==null)?"":empleadoDto.getEstadoCivil().trim())
                &&StringUtils.isEmpty((empleadoDto.getGrupoSangre()==null)?"":empleadoDto.getGrupoSangre().trim())
                &&empleadoDto.getDiscapacitado()==null
                &&empleadoDto.getFechaNacimiento()==null
                &&StringUtils.isEmpty((empleadoDto.getPaisNacimiento()==null)?"":empleadoDto.getPaisNacimiento().trim())){

            if(i<=2){
                LOG.error("EMPTY EXCEL");
                return "EMPTY EXCEL";
            }else{
                LOG.info("EMPTY ROW");
                return "EMPTY ROW";
            }
        }
        return "OK";
    }

    private EmpleadoDto poblarLineaDesdeTemplate(Row row) {

        EmpleadoDto result = new EmpleadoDto();

        ExcelImporter excelImporter = new ExcelImporter(row, result);
        result.setCodigo(excelImporter.build(ExcelTemplate.CODIGO).title("Codigo").readStr());
        result.setNombre(excelImporter.build(ExcelTemplate.NOMBRES).title("Nombres").readStr());
        result.setApellidoPaterno(excelImporter.build(ExcelTemplate.APELLIDO_PATERNO).title("Apellido Paterno").readStr());
        result.setApellidoMaterno(excelImporter.build(ExcelTemplate.APELLIDO_MATERNO).title("Apellido Materno").readStr());
        result.setTipoDocumentoString(excelImporter.build(ExcelTemplate.TIPO_DOCUMENTO).title("Tipo Documento").readStr());

        result.setNumeroDocumento(excelImporter.build(ExcelTemplate.NUMERO_DOCUMENTO).title("Numero Documento").readStr());
        result.setGenero(excelImporter.build(ExcelTemplate.GENERO).title("Genero").readStr());
        result.setEstadoCivil(excelImporter.build(ExcelTemplate.ESTADO_CIVIL).title("Estado Civil").readStr());
        result.setGrupoSangre(excelImporter.build(ExcelTemplate.GRUPO_SANGUINEO).title("Grupo Sanguineo").readStr());
        result.setDiscapacitado(excelImporter.build(ExcelTemplate.DISCAPACITADO).title("Discapacitado").readNumberLong().intValue());
        result.setFechaNacimiento(excelImporter.build(ExcelTemplate.FECHA_NACIMIENTO).title("Fecha Nacimiento").readDate("dd/MM/yyyy"));

        result.setPaisNacimiento(excelImporter.build(ExcelTemplate.PAIS).title("Pais").readStr());
        result.setEmailInterno(excelImporter.build(ExcelTemplate.CORREO_ELECTRONICO_INTERNO).title("Correo Electronico Interno").readStr());

        result.setTelefonoInterno(excelImporter.build(ExcelTemplate.TELEFONO_INTERNO).title("Telefono Interno").readStr());
        result.setAnexoInterno(excelImporter.build(ExcelTemplate.ANEXO_INTERNO).title("Anexo Interno").readStr());

        result.setCentroCostoString(excelImporter.build(ExcelTemplate.CENTRO_COSTO).title("Centro de Costo").readStr());

        result.setContratoTrabajo(excelImporter.build(ExcelTemplate.CONTRATO_TRABAJO).title("Contrato Trabajo").readStr());
        result.setTipoTrabajador(excelImporter.build(ExcelTemplate.TIPO_TRABAJO).title("Tipo Trabajo").readStr());
        result.setRegimenHorario(excelImporter.build(ExcelTemplate.REGIMEN_HORARIO).title("Regimen Horario").readStr());
        result.setRegimenLaboral(excelImporter.build(ExcelTemplate.REGIMEN_LABORAL).title("Regimen Laboral").readStr());
        result.setEsPersonalDeConfianza(excelImporter.build(ExcelTemplate.PERSONAL_CONFIANZA).title("Personal de Confianza").readNumberLong().intValue());

        result.setDireccionDomicilio(excelImporter.build(ExcelTemplate.DIRECCION).title("Direccion").readStr());
        result.setTipoDomicilioString(excelImporter.build(ExcelTemplate.TIPO_DOMICILIO).title("Tipo de Domicilio").readStr());
        result.setDistritoDomicilio(excelImporter.build(ExcelTemplate.DISTRITO).title("Distrito").readStr());
        result.setTelefonoCasa(excelImporter.build(ExcelTemplate.TELEFONO_CASA).title("Telefono de Casa").readStr());

        result.setEmailPersonal(excelImporter.build(ExcelTemplate.CORREO_ELECTRONICO).title("Correo Electronico").readStr());
        result.setEmailPersonal(excelImporter.build(ExcelTemplate.CORREO_ELECTRONICO).title("Correo Electronico").readStr());
        result.setRelacionContactoEmergencia(excelImporter.build(ExcelTemplate.RELACION_CONTACTO).title("Relacion Contacto").readStr());
        result.setNombreContactoEmergencia(excelImporter.build(ExcelTemplate.NOMBRE_CONTACTO).title("Nombre Completo Contacto").readStr());
        result.setTelefonoContactoEmergencia(excelImporter.build(ExcelTemplate.TELEFONO_CONTACTO).title("Telefono Contacto").readStr());
        result.setEmailContactoEmergencia(excelImporter.build(ExcelTemplate.CORREO_ELECTRONICO_CONTACTO).title("Correo Electronico Contacto").readStr());

        result.setEstado(excelImporter.build(ExcelTemplate.ESTADO).title("Estado").readStr());
        return result;
    }


    @RequestMapping(value = "/eliminarArchivoEmpleados", produces = "application/json")
    public void eliminarArchivoEmpleados(@RequestParam("fileNames") MultipartFile files,
                                         HttpServletResponse response) {


        String extention = files.getOriginalFilename();
        try {
            System.out.println(extention);
            System.out.println(files.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void downloadTemplate(HttpServletRequest request, HttpServletResponse response, String templateFileName) throws IOException {

        BusquedaEmpleadoDto busquedaEmpleadoDto = adapterFilterEmpleado(request);

        createDocExcel(response, busquedaEmpleadoDto, templateFileName);

    }


    private BusquedaEmpleadoDto adapterFilterEmpleado(HttpServletRequest request) {

    	BusquedaEmpleadoDto busquedaEmpleadoDto = new BusquedaEmpleadoDto();

        String nombres = (request.getParameter("nombres").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("nombres");
        String apellidoPaterno = (request.getParameter("apellidoPaterno").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("apellidoPaterno");
        String apellidoMaterno = (request.getParameter("apellidoMaterno").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("apellidoMaterno");
        String codigo = (request.getParameter("codigo").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("codigo");
        String tipoDocumento = (request.getParameter("tipoDocumento").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("tipoDocumento");
        String numeroDocumento = (request.getParameter("numeroDocumento").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("numeroDocumento");

        String unidadNegocio = (request.getParameter("unidadNegocio").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("unidadNegocio");
        String departamento = (request.getParameter("departamento").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("departamento");
        String proyecto = (request.getParameter("proyecto").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("proyecto");

        String jefeInmediato = (request.getParameter("jefeInmediato").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("jefeInmediato");
        String centroCosto = (request.getParameter("centroCosto").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("centroCosto");

        String correoElectronico = (request.getParameter("correoElectronico").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("correoElectronico");
        String estado = (request.getParameter("estado").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("estado");

        String isSearch = (request.getParameter("isSearch").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("isSearch");

        String isEmpty = (request.getParameter("isEmpty").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("isEmpty");

        busquedaEmpleadoDto.setNombres(nombres);
        busquedaEmpleadoDto.setApePaterno(apellidoPaterno);
        busquedaEmpleadoDto.setApeMaterno(apellidoMaterno);
        busquedaEmpleadoDto.setCodigo(codigo);
        busquedaEmpleadoDto.setTipoDocumento(tipoDocumento);
        busquedaEmpleadoDto.setNumeroDocumento(numeroDocumento);
        if(unidadNegocio!=null && !unidadNegocio.trim().equals("")) {
            busquedaEmpleadoDto.setUnidadNegocio(new Long(unidadNegocio));
        }
        if(departamento!=null && !departamento.trim().equals("")) {
            busquedaEmpleadoDto.setDepartamento(new Long(departamento));
        }
        if(proyecto!=null && !proyecto.trim().equals("")) {
            busquedaEmpleadoDto.setProyecto(new Long(proyecto));
        }

        busquedaEmpleadoDto.setJefeInmediato(jefeInmediato);
        busquedaEmpleadoDto.setCentroCosto(centroCosto);
        busquedaEmpleadoDto.setCorreoElectronico(correoElectronico);
        busquedaEmpleadoDto.setEstado(estado);

        return busquedaEmpleadoDto;
    }

    private void createDocExcel(HttpServletResponse response, BusquedaEmpleadoDto busquedaEmpleadoDto, String templateFileName) throws IOException {


        ResponseEntity<List<EmpleadoDto>> responseEntity = busquedaEmpleado(busquedaEmpleadoDto);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            List<EmpleadoDto> pageableResult = responseEntity.getBody();
            generateExcel(response, templateFileName, pageableResult);
        }
    }

    private void generateExcel(HttpServletResponse response, String templateFileName, List<EmpleadoDto> pageableResult) throws IOException {

        TemplateExcelExporter report = new TemplateExcelExporter();

        loadRegistrosEmpleados(templateFileName, report, pageableResult);

        report.writeExcelToResponse(response, "Reporte de Empleados");
    }

    private void loadRegistrosEmpleados(String templateFileName, TemplateExcelExporter report, List<EmpleadoDto> empleadoDtos) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + templateFileName);

        report.exportH(resource.getInputStream());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int index = 0;
        if (empleadoDtos != null) {
            for (EmpleadoDto item : empleadoDtos) {
                index++;
                report.addRow(index);
                report.addColumnValue(0, item.getCodigo());
                report.addColumnValue(1, item.getNombre());
                report.addColumnValue(2, item.getApellidoPaterno());
                report.addColumnValue(3, item.getApellidoMaterno());
                report.addColumnValue(4, item.getTipoDocumentoString());
                report.addColumnValue(5, item.getNumeroDocumento());
                report.addColumnValue(6, item.getGeneroString());
                report.addColumnValue(7, item.getEstadoCivilString());
                report.addColumnValue(8, item.getGrupoSangreString());
                report.addColumnValue(9, item.getDiscapacitadoString());
                report.addColumnValue(10, sdf.format(item.getFechaNacimiento()));
                report.addColumnValue(11, item.getPaisNacimiento());
                report.addColumnValue(12, item.getEmailInterno());
                report.addColumnValue(13, item.getEstado());
            }
        }
    }
    
    private void downloadTemplateMarcaciones(HttpServletRequest request, HttpServletResponse response, String templateFileName) throws IOException {

    	BusquedaMarcacionDto busquedaMarcacionDto = adapterFilterMarcacion(request);

        createDocExcelMarcacion(response, busquedaMarcacionDto, templateFileName);

    }
    
    private BusquedaMarcacionDto adapterFilterMarcacion(HttpServletRequest request) {

    	BusquedaMarcacionDto busquedaMarcacionDto = new BusquedaMarcacionDto();

        String idEmpleado = (request.getParameter("idEmpleado").equalsIgnoreCase(Constants.NULL) || request.getParameter("idEmpleado").equals("")) ? null : request.getParameter("idEmpleado");
        String desde = (request.getParameter("desde").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("desde");
        String hasta = (request.getParameter("hasta").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("hasta");
        
        String unidadNegocio = (request.getParameter("unidadNegocio").equalsIgnoreCase(Constants.NULL) || request.getParameter("unidadNegocio").equals("")) ? null : request.getParameter("unidadNegocio");
        String departamento = (request.getParameter("departamento").equalsIgnoreCase(Constants.NULL) || request.getParameter("departamento").equals("")) ? null : request.getParameter("departamento");
        String proyecto = (request.getParameter("proyecto").equalsIgnoreCase(Constants.NULL) || request.getParameter("proyecto").equals("")) ? null : request.getParameter("proyecto");

        String idJefeInmediato = (request.getParameter("idJefeInmediato").equalsIgnoreCase(Constants.NULL) || request.getParameter("idJefeInmediato").equals("")) ? null : request.getParameter("idJefeInmediato");

        
        
        busquedaMarcacionDto.setUnidadNegocio(unidadNegocio == null ? null : new Long(unidadNegocio));
        busquedaMarcacionDto.setDepartamento(idEmpleado == null ? null : new Long(departamento));
        busquedaMarcacionDto.setProyecto(proyecto == null ? null : new Long(proyecto));
        busquedaMarcacionDto.setIdJefeInmediato(idJefeInmediato == null ? null : new Long(idJefeInmediato));
        busquedaMarcacionDto.setIdEmpleado(idEmpleado == null ? null : new Long(idEmpleado));
        
        busquedaMarcacionDto.setDesde(DateUtil.formatoFechaArchivoMarcacion(desde));
        busquedaMarcacionDto.setHasta(DateUtil.formatoFechaArchivoMarcacion(hasta));

        return busquedaMarcacionDto;
    }

    private void createDocExcelMarcacion(HttpServletResponse response, BusquedaMarcacionDto busquedaMarcacionDto, String templateFileName) throws IOException {


        ResponseEntity<List<MarcacionDto>> responseEntity = busquedaMarcacionesEmpleado(busquedaMarcacionDto);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            List<MarcacionDto> pageableResult = responseEntity.getBody();
            generateExcelMarcacion(response, templateFileName, pageableResult);
        }
    }

    private void generateExcelMarcacion(HttpServletResponse response, String templateFileName, List<MarcacionDto> pageableResult) throws IOException {

        TemplateExcelExporter report = new TemplateExcelExporter();

        loadRegistrosMarcaciones(templateFileName, report, pageableResult);

        report.writeExcelToResponse(response, "Reporte de Marcaciones");
    }

    private void loadRegistrosMarcaciones(String templateFileName, TemplateExcelExporter report, List<MarcacionDto> marcacionDtos) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + templateFileName);

        report.exportH(resource.getInputStream());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int index = 0;
        if (marcacionDtos != null) {
            for (MarcacionDto item : marcacionDtos) {
                index++;
                report.addRow(index);
                report.addColumnValue(0,  sdf.format(item.getFecha()));
                report.addColumnValue(1, item.getCodigoEmpleado());
                report.addColumnValue(2, item.getNombreCompletoEmpleado());
               
                report.addColumnValue(3, item.getHoraIngreso());
                report.addColumnValue(4, item.getHoraInicioAlmuerzo());
                report.addColumnValue(5, item.getHoraFinAlmuerzo());
                report.addColumnValue(6, item.getHoraSalida());
                report.addColumnValue(7, item.getHoraIngresoHorario());
                report.addColumnValue(8, item.getHoraSalidaHorario());

                report.addColumnValue(9, item.getDemoraEntrada());
                report.addColumnValue(10, item.getDemoraAlmuerzo());
                report.addColumnValue(11, item.getDemoraSalida());
                report.addColumnValue(12, item.getTardanza());
                report.addColumnValue(13, item.getSolicitudCambio());
            }
        }
    }

    @RequestMapping(value = "/historiaLaboral", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HistoriaLaboralDto>> obtenerHistoriaLaboral(
            @RequestParam(name = "idEmpleado", required = true) Long idEmpleado) {

        List<HistoriaLaboralDto> result = new ArrayList<>();
        result = empleadoService.obtenerHistoriaLaboral(idEmpleado);
        if (result == null)
            result = new ArrayList<>();
        LOG.info("Msg obtenerHistoriaLaboral: " + result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/obtenerEquiposPendientesDevolucion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EquipoEntregadoDto>> obtenerEquiposPendientesDevolucion(
            @RequestParam(name = "idEmpleado", required = true) Long idEmpleado) {

        List<EquipoEntregadoDto> result = new ArrayList<>();
        result = empleadoService.obtenerEquiposPendientesDevolucion(idEmpleado);
        if (result == null)
            result = new ArrayList<>();
        LOG.info("Msg obtenerHistoriaLaboral: " + result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/editHistoriaLaboral", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HistoriaLaboralDto>> obtenerIdHistoriaLaboral(
            @RequestParam(name = "idHistorialLaboral", required = true) Long idHistorialLaboral) {

        List<HistoriaLaboralDto> result = new ArrayList<>();
        result = empleadoService.obtenerIdHistoriaLaboral(idHistorialLaboral);
        if (result == null)
            result = new ArrayList<>();
        LOG.info("Msg obtenerIdHistoriaLaboral: " + result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @RequestMapping(value = "/eliminarPermisoEmpleado", method = RequestMethod.POST)
    public ResponseEntity<String> eliminarPermisoEmpleado(@RequestBody PermisoEmpleadoDto permisoEmpleadoDto) {

        Long idPermisoEmpleado =  empleadoService.eliminarPermisoEmpleado(permisoEmpleadoDto.getIdPermisoEmpleado());

        return new ResponseEntity<String>(idPermisoEmpleado.toString(),HttpStatus.OK);
    }
    
    @RequestMapping(value = "/obtenerHorarioEmpleadoDiasPorHorarioEmpleado", method = RequestMethod.POST)
    public List<HorarioEmpleadoDiaDto> obtenerHorarioEmpleadoDiasPorHorarioEmpleado(@RequestBody HorarioEmpleadoDto horarioEmpleadoDto) {
    	List<HorarioEmpleadoDiaDto> dto  =  empleadoService.obtenerHorarioEmpleadoDiasPorHorarioEmpleado(horarioEmpleadoDto);
        return dto;
    }
    
    @RequestMapping(value="/countEquiposPendientesDevolucion", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto countEquiposPendientesDevolucion(@RequestBody EmpleadoDto empleadoDto){
	
		NotificacionDto dto = empleadoService.countEquiposPendientesDevolucion(empleadoDto);
		return dto;
		
	}
    
    @RequestMapping(value = "/obtenerMarcacionEmpleado", method = RequestMethod.POST)
    public MarcacionDto obtenerMarcacionEmpleado(@RequestBody EmpleadoDto EmpleadoDto) {
    	MarcacionDto dto  =  marcacionEmpleadoService.obtenerMarcacionPorEmpleadoyFechaActual(EmpleadoDto);
        return dto;
    }
    
}
