package com.nash.nashinternshipsback.repository;

import com.nash.nashinternshipsback.model.Practica;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PracticaRepository extends MongoRepository<Practica, Integer> {

    Optional<Practica> findById(String id);
}
