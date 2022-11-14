package com.nash.nashinternshipsback.controller.administrador;


import com.nash.nashinternshipsback.dto.NuevoUsuarioDTO;
import com.nash.nashinternshipsback.model.Rol;
import com.nash.nashinternshipsback.model.enums.Etapas;
import com.nash.nashinternshipsback.model.enums.Roles;
import com.nash.nashinternshipsback.model.Usuario;
import com.nash.nashinternshipsback.security.jwt.JwtProvider;
import com.nash.nashinternshipsback.service.RolService;
import com.nash.nashinternshipsback.service.administrador.AdminService;
import com.nash.nashinternshipsback.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/administracion")
@CrossOrigin
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    /**
     * Obtiene todos los Usuarios del sistema
     * @return ResponseEntity con el objeto y status correspondiente a la búsqueda
     */
    @GetMapping("/obtener-todos")
    public ResponseEntity<?> obtenerTodo() {

        return ResponseEntity.status(HttpStatus.OK).body(adminService.obtenerTodos());
    }

    /**
     * Obtiene todos los Usuarios con el rol Administrador
     * @return ResponseEntity con el objeto y status correspondiente a la búsqueda
     */
    @GetMapping("/obtener-administradores")
    public ResponseEntity<?> obtenerAdministradores() {

        return ResponseEntity.status(HttpStatus.OK).body(adminService.obtenerAdministradores());
    }

    /**
     * Obtiene todos los Usuarios con el rol Practicante
     * @return ResponseEntity con el objeto y status correspondiente a la búsqueda
     */
    @GetMapping("/obtener-practicantes")
    public ResponseEntity<?> obtenerPracticantes() {

        return ResponseEntity.status(HttpStatus.OK).body(adminService.obtenerPracticantes());
    }

    /**
     * Obtiene todos los Usuarios con el rol Coordinador
     * @return ResponseEntity con el objeto y status correspondiente a la búsqueda
     */
    @GetMapping("/obtener-coordinadores")
    public ResponseEntity<?> obtenerCoordinadores() {

        return ResponseEntity.status(HttpStatus.OK).body(adminService.obtenerCoordinadores());
    }

    @PostMapping("/registrar-admin")
    public ResponseEntity<?> registrarAdmin(@Valid @RequestBody NuevoUsuarioDTO nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity("Campos no validos", HttpStatus.BAD_REQUEST);
        if(usuarioService.existePorRut(nuevoUsuario.getRut()))
            return new ResponseEntity("Nombre de usuario ya existente",HttpStatus.BAD_REQUEST);
        if(usuarioService.existePorCorreo(nuevoUsuario.getCorreo()))
            return new ResponseEntity("Correo electronico ya existente",HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getRut(), nuevoUsuario.getCorreo(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(Roles.ROLE_USER).get());
        roles.add(rolService.getByRolNombre(Roles.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.guardar(usuario);

        return new ResponseEntity("Aministrador registrado",HttpStatus.CREATED);
    }
    @PostMapping("/registrar-practicante")
    public ResponseEntity<?> resgistrarPracticante(@Valid @RequestBody NuevoUsuarioDTO nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity("Campos no validos", HttpStatus.BAD_REQUEST);
        if(usuarioService.existePorRut(nuevoUsuario.getRut()))
            return new ResponseEntity("Nombre de usuario ya existente",HttpStatus.BAD_REQUEST);
        if(usuarioService.existePorCorreo(nuevoUsuario.getCorreo()))
            return new ResponseEntity("Correo electronico ya existente",HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getRut(), nuevoUsuario.getCorreo(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(Roles.ROLE_USER).get());
        roles.add(rolService.getByRolNombre(Roles.ROLE_PRACTICANTE).get());
        usuario.setEtapaActual(Etapas.ETAPA_I);

        usuario.setCarrera(nuevoUsuario.getCarrera());
        usuario.setNivel(nuevoUsuario.getNivel());
        usuario.setEtapaActual(nuevoUsuario.getEtapaActual());
        usuario.setRoles(roles);
        usuarioService.guardar(usuario);

        return new ResponseEntity("Practicante registrado",HttpStatus.CREATED);
    }
    @PostMapping("/registrar-coordinador")
    public ResponseEntity<?> registrarCoordinador(@Valid @RequestBody NuevoUsuarioDTO nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity("Campos no validos", HttpStatus.BAD_REQUEST);
        if(usuarioService.existePorRut(nuevoUsuario.getRut()))
            return new ResponseEntity("Nombre de usuario ya existente",HttpStatus.BAD_REQUEST);
        if(usuarioService.existePorCorreo(nuevoUsuario.getCorreo()))
            return new ResponseEntity("Correo electronico ya existente",HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getRut(), nuevoUsuario.getCorreo(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        usuario.setCarrera(nuevoUsuario.getCarrera());
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(Roles.ROLE_USER).get());
        roles.add(rolService.getByRolNombre(Roles.ROLE_COORDINADOR).get());

        usuario.setRoles(roles);

        usuarioService.guardar(usuario);

        return new ResponseEntity("Coordinador registrado",HttpStatus.CREATED);
    }
}
