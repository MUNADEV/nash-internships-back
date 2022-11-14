package com.nash.nashinternshipsback.controller.archivos;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.nash.nashinternshipsback.model.Archivo;
import com.nash.nashinternshipsback.model.Carrera;
import com.nash.nashinternshipsback.model.Usuario;
import com.nash.nashinternshipsback.model.enums.Etapas;
import com.nash.nashinternshipsback.model.enums.TipoDocumento;
import com.nash.nashinternshipsback.service.archivos.ArchivoService;
import com.nash.nashinternshipsback.service.coordinacion.CarreraService;
import com.nash.nashinternshipsback.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/archivos")
@CrossOrigin
public class ArchivoController {

    @Autowired
    ArchivoService archivoService;
    @Autowired
    CarreraService carreraService;

    @Autowired
    BlobServiceClient blobServiceClient;

    @Autowired
    BlobContainerClient blobContainerClient;
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/todo")
    public ResponseEntity<?> obtenerTodo(){
      return new ResponseEntity(archivoService.obtenerTodo(), HttpStatus.OK);
    }


    @GetMapping("/{rut}")
    public ResponseEntity<?> obtenerArchivosPorRut(@PathVariable("rut") String rut){
        if(usuarioService.existePorRut(rut)){
            return new ResponseEntity(archivoService.archivosPorRut(rut),HttpStatus.OK);
        }else{
            return new ResponseEntity("Usuario no encontrado",HttpStatus.BAD_REQUEST);
        }

    }
}
