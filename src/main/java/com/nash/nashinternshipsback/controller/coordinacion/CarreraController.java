package com.nash.nashinternshipsback.controller.coordinacion;

import com.nash.nashinternshipsback.model.Usuario;
import com.nash.nashinternshipsback.service.archivos.ArchivoService;
import com.nash.nashinternshipsback.service.coordinacion.CarreraService;
import com.nash.nashinternshipsback.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/carrera")
@CrossOrigin
public class CarreraController {
    @Autowired
    CarreraService carreraService;

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    ArchivoService archivoService;

    @GetMapping("/todo")
    public ResponseEntity<?> obtenerTodo(){

        return new ResponseEntity(carreraService.obtenerTodo(), HttpStatus.OK);
    }

}
