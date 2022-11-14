package com.nash.nashinternshipsback.service.coordinacion;

import com.nash.nashinternshipsback.model.Responsable;
import com.nash.nashinternshipsback.repository.ResponsableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ResponsableService {

    @Autowired
    ResponsableRepository responsableRepository;

    /**
     * Guarda-Actualiza un Responsable
     * @param responsable Objeto tipo Responsable
     */
    public void guardar(Responsable responsable){
        responsableRepository.save(responsable);
    }


    public void editarResponsable(String rut, String nombre, String correo){
        responsableRepository.save(new Responsable(rut,nombre,correo));
    }

    public Optional<Responsable> obtenerPorRut(String rut){
        return responsableRepository.findByRut(rut);
    }

    public Optional<Responsable> obtenerPorNombre(String nombre){
        return responsableRepository.findByNombre(nombre);
    }

    public Optional<Responsable> obtenerPorCorreo(String correo){
        return responsableRepository.findByCorreo(correo);
    }

    public Optional<Responsable> obtenerPorID(String id){
        return responsableRepository.findById(id);
    }

    public boolean existePorRut(String rut){
        return responsableRepository.existsByRut(rut);
    }

    public boolean existePorNombre(String nombre){
        return responsableRepository.existsByNombre(nombre);
    }

    public boolean existePorCorreo(String email){
        return responsableRepository.existsByCorreo(email);
    }
}
