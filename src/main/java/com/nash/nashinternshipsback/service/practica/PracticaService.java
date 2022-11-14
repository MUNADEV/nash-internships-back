package com.nash.nashinternshipsback.service.practica;

import com.nash.nashinternshipsback.model.Practica;
import com.nash.nashinternshipsback.repository.PracticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PracticaService {

    @Autowired
    PracticaRepository practicaRepository;

    public void guardar(Practica practica){
         practicaRepository.save(practica);
    }

    public void listarPorCarrera(){

    }

    public void listarPorFacultad(){

    }

    public void listarPorCoordinador(){

    }



}
