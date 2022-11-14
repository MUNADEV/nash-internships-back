package com.nash.nashinternshipsback.repository;

import com.nash.nashinternshipsback.model.Responsable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponsableRepository extends MongoRepository<Responsable, Integer> {

    Optional<Responsable> findById(String id);
    Optional<Responsable> findByCorreo(String correo);
    Optional<Responsable> findByNombre(String nombre);
    Optional<Responsable> findByRut(String rut);
    boolean existsByCorreo(String correo);
    boolean existsByNombre(String nombre);
    boolean existsByRut(String rut);
}
