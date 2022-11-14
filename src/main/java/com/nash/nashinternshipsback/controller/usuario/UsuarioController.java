package com.nash.nashinternshipsback.controller.usuario;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.nash.nashinternshipsback.model.Archivo;
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

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    ArchivoService archivoService;
    @Autowired
    CarreraService carreraService;

    @Autowired
    BlobServiceClient blobServiceClient;

    @Autowired
    BlobContainerClient blobContainerClient;

    /**
     * Obtiene un Usuario mediante el identificador
     *
     * @param id Cadena de texto representativa de un id
     * @return ResponseEntity de tipo Usuario coincidente con el identificador
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<?> obtenerPorID(@PathVariable("id") String id) {

        if (usuarioService.existePorID(id)) {
            Usuario usuario = usuarioService.obtenerPorID(id).get();

            return ResponseEntity.status(HttpStatus.OK).body(usuario);

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    /**
     * Obtiene un Usuario mediante su nombre
     *
     * @param nombre Cadena de texto representativa de un nombre
     * @return ResponseEntity de tipo Usuario coincidente con el nombre
     */
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> obtenerPorNombre(@PathVariable("nombre") String nombre) {
        if (usuarioService.existePorNombre(nombre)) {
            Usuario usuario = usuarioService.obtenerPorNombre(nombre).get();

            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    /**
     * Obtiene un Usuario mediante su rut
     *
     * @param rut Cadena de texto representativa de rut
     * @return ResponseEntity de tipo Usuario coincidente con el rut
     */
    @GetMapping("/rut/{rut}")
    public ResponseEntity<?> obtenerPorRut(@PathVariable("rut") String rut) {
        if (usuarioService.existePorRut(rut)) {
            Usuario usuario = usuarioService.obtenerPorRut(rut).get();

            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    /**
     * Obtiene un Usuario mediante su correo electronico
     *
     * @param correo Cadena de texto representativa de correo electronico
     * @return ResponseEntity de tipo Usuario coincidente con el correo electronico
     */
    @GetMapping("/correo/{correo}")
    public ResponseEntity<?> obtenerPorCorreo(@PathVariable("correo") String correo) {
        if (usuarioService.existePorCorreo(correo)) {
            Usuario usuario = usuarioService.obtenerPorCorreo(correo).get();

            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    /**
     * Elimina un usuario
     * @param id Identificador unico de un Usuario
     * @return ResponseEntity indicando el estado de la operación
     */
    @DeleteMapping("{id}/eliminar")
    public ResponseEntity<Object> eliminarUsuario(@PathVariable("id") String id) {
        if (usuarioService.existePorID(id)) {
            if (usuarioService.eliminarPorID(id)) {
                return ResponseEntity.status(HttpStatus.OK).body("Operacion exitosa: Usuario eliminado");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Operacion no valida: No es posible eliminar usuario");

    }




   /* @PutMapping("/{id}/actualizar")
    public ResponseEntity<?> actualizarUsuario(@RequestBody Usuario usuario, @PathVariable String id){
        this.usuarioService.actualizar(Optional.of(usuario),id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario Actualizado");
    }*/
}
