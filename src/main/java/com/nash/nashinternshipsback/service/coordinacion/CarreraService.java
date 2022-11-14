package com.nash.nashinternshipsback.service.coordinacion;

import com.nash.nashinternshipsback.model.Carrera;
import com.nash.nashinternshipsback.repository.CarreraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarreraService {
    @Autowired
    CarreraRepository carreraRepository;

    public void guardar(Carrera carrera){
        carreraRepository.save(carrera);
    }

    public List<Carrera> obtenerTodo(){
        return carreraRepository.findAll();
    }

    public Optional<Carrera> obtenerPorId(String id){
        return carreraRepository.findById(id);
    }

    public Optional<Carrera> obtenerPorNombre(String nombre){
        return carreraRepository.findByNombre(nombre);
    }

    public boolean existePorId(String id){
        return carreraRepository.existsById(id);
    }

    public boolean existePorNombre(String nombre){
        return carreraRepository.existsByNombre(nombre);
    }

}


