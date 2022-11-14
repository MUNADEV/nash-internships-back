package com.nash.nashinternshipsback.repository;


import com.nash.nashinternshipsback.model.Archivo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArchivoRespostory extends MongoRepository<Archivo, Integer> {

    boolean existsById(String id);
    boolean existsByNombre(String nombre);
    boolean existsByCarrera(String carrera);
    boolean existsByUrl(String url);
    Optional<Archivo> findByUrl(String url);
    Optional<Archivo> findByNombre(String nombre);
    Optional<Archivo> findByCarrera(String nombre);
    Optional<Archivo> findByEtapa(String nombre);
    Optional<Archivo> findById(String id);

}
