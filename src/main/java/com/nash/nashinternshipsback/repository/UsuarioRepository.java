package com.nash.nashinternshipsback.repository;



import com.nash.nashinternshipsback.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, Integer> {

    Optional<Usuario> findById(String id);
    Optional<Usuario> findByRut(String rut);
    Optional<Usuario> findByNombre(String nombre);
    Optional<Usuario> findByCorreo(String correo);
    Optional<Usuario> findByRutOrCorreo(String rut, String email);
    Optional<Usuario> findByTokenPassword(String tokenPassword);
    boolean existsById(String id);
    boolean existsByRut(String rut);
    boolean existsByNombre(String nombre);
    boolean existsByCorreo(String correo);
    void deleteById(String id);
}
