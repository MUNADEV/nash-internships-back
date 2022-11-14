package com.nash.nashinternshipsback.repository;

import com.nash.nashinternshipsback.model.CentroPractica;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroPracticaRepository extends MongoRepository<CentroPractica, Integer> {
}
