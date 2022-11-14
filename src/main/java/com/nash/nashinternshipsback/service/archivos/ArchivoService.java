package com.nash.nashinternshipsback.service.archivos;

import com.azure.core.http.rest.Page;
import com.nash.nashinternshipsback.model.Archivo;
import com.nash.nashinternshipsback.model.Carrera;
import com.nash.nashinternshipsback.model.enums.Etapas;
import com.nash.nashinternshipsback.repository.ArchivoRespostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArchivoService {

    @Autowired
    ArchivoRespostory archivoRespostory;

    public void guardar(Archivo archivo){
        archivoRespostory.save(archivo);
    }

    public List<Archivo> obtenerTodo(){
        return archivoRespostory.findAll();
    }

    public Optional<Archivo> obtenerPorId(String id){
        return archivoRespostory.findById(id);
    }

    public Optional<Archivo> obtenerPorNombre(String nombre){
        return archivoRespostory.findByNombre(nombre);
    }

    public Optional<Archivo> obtenerPorUrl(String url){
        return archivoRespostory.findByUrl(url);
    }

    public List<Archivo> plantillasPorCarreraEtapa(String carrera, Etapas etapa){
        List<Archivo> archivos = archivoRespostory.findAll().stream()
                .filter(a ->a.getCarrera().getNombre().equalsIgnoreCase(carrera) && a.getEtapa().equals(etapa)).collect(Collectors.toList());
                //.filter(a ->a.getCarrera().getNombre().contains(carrera) && a.getEtapa().equals(etapa)).collect(Collectors.toList());
        return archivos;
    }

    public List<Archivo> archivosPorRut(String rut){
        List<Archivo> archivos = archivoRespostory.findAll().stream()
                .filter(a ->a.getUsuarioCreador().getRut().equalsIgnoreCase(rut)).collect(Collectors.toList());
        //.filter(a ->a.getCarrera().getNombre().contains(carrera) && a.getEtapa().equals(etapa)).collect(Collectors.toList());
        return archivos;
    }

    public boolean existePorId(String id){
        return archivoRespostory.existsById(id);
    }

    public boolean existePorNombre(String nombre){
        return archivoRespostory.existsByNombre(nombre);
    }

    public boolean existePorCarrera(String carrera){
        return archivoRespostory.existsByCarrera(carrera);
    }

}
