package com.nash.nashinternshipsback.repository;

import com.nash.nashinternshipsback.model.Carrera;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarreraRepository extends MongoRepository<Carrera, Integer> {

    boolean existsById(String id);
    boolean existsByNombre(String nombre);
    Optional<Carrera> findByNombre(String nombre);
    Optional<Carrera> findById(String id);
}
