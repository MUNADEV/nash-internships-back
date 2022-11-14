package com.nash.nashinternshipsback.controller.autentificacion;

import com.nash.nashinternshipsback.dto.JwtDTO;
import com.nash.nashinternshipsback.dto.LoginUsuarioDTO;
import com.nash.nashinternshipsback.dto.NuevoUsuarioDTO;
import com.nash.nashinternshipsback.model.enums.Roles;
import com.nash.nashinternshipsback.security.jwt.JwtProvider;
import com.nash.nashinternshipsback.model.*;

import com.nash.nashinternshipsback.service.RolService;
import com.nash.nashinternshipsback.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

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

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuarioDTO nuevoUsuario, BindingResult bindingResult){
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
        roles.add(rolService.getByRolNombre(Roles.ROLE_USER).get());
        /* if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(Roles.ROLE_ADMIN).get());
        if(nuevoUsuario.getRoles().contains("coordinador"))
            roles.add(rolService.getByRolNombre(Roles.ROLE_COORDINADOR).get());
        if(nuevoUsuario.getRoles().contains("practicante"))
            roles.add(rolService.getByRolNombre(Roles.ROLE_PRACTICANTE).get());
             */
        usuario.setRoles(roles);
        usuarioService.guardar(usuario);

        return new ResponseEntity("Usuario registrado",HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuarioDTO loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity("Campos no validos", HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getRut(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        JwtDTO jwtDto = new JwtDTO();
        jwtDto.setToken(jwt);
        return new ResponseEntity(jwtDto.allToken(), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtDTO> refresh(@RequestBody JwtDTO jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        JwtDTO jwt = new JwtDTO();
        jwt.setToken(token);
        return new ResponseEntity(jwt, HttpStatus.OK);
    }
}