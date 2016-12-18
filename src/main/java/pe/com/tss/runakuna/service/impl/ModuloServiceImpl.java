package pe.com.tss.runakuna.service.impl;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.tss.runakuna.domain.model.repository.jdbc.ModuloRepository;
import pe.com.tss.runakuna.dto.ModuloDto;
import pe.com.tss.runakuna.dto.ModuloPadreDto;
import pe.com.tss.runakuna.dto.RolUsuarioDto;
import pe.com.tss.runakuna.dto.RolUsuarioPadreDto;
import pe.com.tss.runakuna.service.intf.ModuloService;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by josediaz on 25/11/2016.
 */
@Service
public class ModuloServiceImpl implements ModuloService {

    @Autowired
    private ModuloRepository moduloRepository;

    @Override
    public List<ModuloDto> getModulosPermitidosPorUsuario(String cuentaUsuario) {
        List<ModuloPadreDto> allowedMods = moduloRepository.getModulosPermitidosPorUsuario(cuentaUsuario);
        List<ModuloPadreDto> distintc = new ArrayList<>(new LinkedHashSet<>(allowedMods));
        List<ModuloDto> moduleDtos = groupByModuloPadre(distintc);
        return moduleDtos;
    }
    
    @Override
	public List<RolUsuarioDto> getRolPorUsuario(String cuentaUsuario) {
		
    	List<RolUsuarioPadreDto> allRol = moduloRepository.getRolPorUsuario(cuentaUsuario);
    	List<RolUsuarioPadreDto> distintc = new ArrayList<>(new LinkedHashSet<>(allRol));
    	List<RolUsuarioDto> moduleDtos = groupByRolPadre(distintc);
		return moduleDtos;
	}

    private List<RolUsuarioDto> groupByRolPadre(List<RolUsuarioPadreDto> padres) {
		
    	List<RolUsuarioDto> roles = new ArrayList<>();
    	if(padres.size() == 0){
    		return roles;
    	}
    	
    	Mapper mapper = new DozerBeanMapper();
    	
    	int idx = 0;
    	RolUsuarioPadreDto padre = padres.get(idx);
        while(idx < padres.size()){
        	Long idEmpl = padre.getIdEmpleado();
        	RolUsuarioDto rolDto = new RolUsuarioDto();
            rolDto.setIdEmpleado(padre.getIdEmpleado());
            List<RolUsuarioDto> rolNames = new ArrayList<>();
            do {
            	RolUsuarioDto roName = mapper.map(padre, RolUsuarioDto.class);
            	rolNames.add(roName);
                if (idx++ == padres.size()-1){
                    break;
                }
                padre = padres.get(idx);
            } while (idEmpl.equals(padre.getIdEmpleado()));
            rolDto.setRolNames(rolNames);
            roles.add(rolDto);

        }

        return roles;
	}

	protected List<ModuloDto> groupByModuloPadre(List<ModuloPadreDto> padres){
        List<ModuloDto> modulos = new ArrayList<>();

        if (padres.size() == 0) {
            return modulos;
        }

        Mapper mapper = new DozerBeanMapper();

        int idx = 0;
        ModuloPadreDto padre = padres.get(idx);
        while(idx < padres.size()){
            String codMod = padre.getCodigoPadre();
            ModuloDto moduloDto = new ModuloDto();
            moduloDto.setCodigo(padre.getCodigoPadre());
            moduloDto.setNombre(padre.getNombrePadre());
            moduloDto.setUrl(padre.getUrlPadre());
            moduloDto.setRoleName(padre.getRoleName());
            moduloDto.setIdEmpleado(padre.getIdEmpleado());
            List<ModuloDto> subModulos = new ArrayList<>();
            do {
                ModuloDto subModulo = mapper.map(padre, ModuloDto.class);
                subModulos.add(subModulo);
                if (idx++ == padres.size()-1){
                    break;
                }
                padre = padres.get(idx);
            } while (codMod.equals(padre.getCodigoPadre()));
            moduloDto.setSubModulos(subModulos);
            modulos.add(moduloDto);

        }

        return modulos;
    }
	
}
