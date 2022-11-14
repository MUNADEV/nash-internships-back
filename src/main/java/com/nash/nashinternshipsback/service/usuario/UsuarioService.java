package com.nash.nashinternshipsback.service.usuario;

import com.nash.nashinternshipsback.model.Usuario;
import com.nash.nashinternshipsback.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Servicios Componente Usuario
 */
@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public void guardar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Optional<Usuario> obtenerPorID(String id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> obtenerPorRut(String rut) {
        return usuarioRepository.findByRut(rut);
    }

    public Optional<Usuario> obtenerPorNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    public Optional<Usuario> obtenerPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    public Optional<Usuario> obtenerPorRutOCorreo(String nombreOCorreo) {
        return usuarioRepository.findByRutOrCorreo(nombreOCorreo, nombreOCorreo);
    }

    public Optional<Usuario> obtenerContrasenaToken(String token) {
        return usuarioRepository.findByTokenPassword(token);
    }

    public boolean existePorID(String id) {
        return usuarioRepository.existsById(id);
    }

    public boolean existePorRut(String rut) {
        return usuarioRepository.existsByRut(rut);
    }

    public boolean existePorNombre(String nombre) {
        return usuarioRepository.existsByNombre(nombre);
    }

    public boolean existePorCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    public boolean eliminarPorID(String id) {

        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }


}