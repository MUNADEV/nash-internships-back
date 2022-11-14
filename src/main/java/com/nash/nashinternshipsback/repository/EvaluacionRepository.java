package com.nash.nashinternshipsback.repository;


import com.nash.nashinternshipsback.model.Evaluacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionRepository extends MongoRepository<Evaluacion, Integer> {


}
