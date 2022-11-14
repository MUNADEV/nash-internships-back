package com.nash.nashinternshipsback.service.administrador;

import com.nash.nashinternshipsback.model.Rol;
import com.nash.nashinternshipsback.model.enums.Roles;
import com.nash.nashinternshipsback.model.Usuario;
import com.nash.nashinternshipsback.repository.UsuarioRepository;
import com.nash.nashinternshipsback.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RolService rolService;

    /**
     * Obtiene todos los Usuarios del sistema
     * @return Lista de tipo Usuario
     */
    public List<Usuario> obtenerTodos(){

        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) usuarioRepository.findAll();

        Rol rolUsuario = rolService.getByRolNombre(Roles.ROLE_USER).get();
        return usuarios.stream()
                .filter(u -> u.getRoles().contains(rolUsuario)).collect(Collectors.toList());
    }

    /**
     * Obtiene todos los Usuarios con el rol Practicante
     * @return Lista de tipo Usuario
     */
    public List<Usuario> obtenerAdministradores(){

        List<Usuario> usuarios = usuarioRepository.findAll();

        Rol rolAdmin = rolService.getByRolNombre(Roles.ROLE_ADMIN).get();

        return  usuarios.stream()
                .filter(u -> u.getRoles().contains(rolAdmin)).collect(Collectors.toList());
    }

    /**
     * Obtiene todos los Usuarios con el rol Practicante
     * @return List de tipo Usuario
     */
    public List<Usuario> obtenerPracticantes(){

        List<Usuario> usuarios = usuarioRepository.findAll();

        Rol rolPracticante = rolService.getByRolNombre(Roles.ROLE_PRACTICANTE).get();

        return  usuarios.stream()
                .filter(u -> u.getRoles().contains(rolPracticante)).collect(Collectors.toList());
    }

    /**
     * Obtiene todos los Usuarios con el rol Coordinador
     * @return List de tipo Coordinador
     */
    public List<Usuario> obtenerCoordinadores(){

        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) usuarioRepository.findAll();

        Rol rolCoordinador = rolService.getByRolNombre(Roles.ROLE_COORDINADOR).get();

        return usuarios.stream()
                .filter(u -> u.getRoles().contains(rolCoordinador)).collect(Collectors.toList());
    }

}
