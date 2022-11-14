package com.nash.nashinternshipsback.controller.coordinacion;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.nash.nashinternshipsback.dto.NuevoUsuarioDTO;
import com.nash.nashinternshipsback.model.Archivo;
import com.nash.nashinternshipsback.model.Rol;
import com.nash.nashinternshipsback.model.Usuario;
import com.nash.nashinternshipsback.model.enums.Etapas;
import com.nash.nashinternshipsback.model.enums.Roles;
import com.nash.nashinternshipsback.model.enums.TipoDocumento;
import com.nash.nashinternshipsback.security.jwt.JwtProvider;
import com.nash.nashinternshipsback.service.RolService;
import com.nash.nashinternshipsback.service.administrador.AdminService;
import com.nash.nashinternshipsback.service.archivos.ArchivoService;
import com.nash.nashinternshipsback.service.coordinacion.CarreraService;
import com.nash.nashinternshipsback.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/coordinacion")
@CrossOrigin
public class CoordinacionController {
    @Autowired
    ArchivoService archivoService;

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
    @Autowired
    BlobServiceClient blobServiceClient;

    @Autowired
    BlobContainerClient blobContainerClient;


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

        /*
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String rut = userDetails.getUsername();
        Usuario coordinador = usuarioService.obtenerPorRut(rut).get();
         */

        usuario.setCarrera(nuevoUsuario.getCarrera());
        usuario.setNivel(nuevoUsuario.getNivel());
        usuario.setEtapaActual(nuevoUsuario.getEtapaActual());
        usuario.setRoles(roles);

        usuarioService.guardar(usuario);

        return new ResponseEntity("Practicante registrado",HttpStatus.CREATED);
    }

    @PostMapping("/subir-plantilla/{etapa}")
    public ResponseEntity<?> subirPlantilla(@RequestParam("file") MultipartFile multipartFile,@PathVariable("etapa") String etapa) throws IOException {

        //usuario
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String rut = userDetails.getUsername();
        Usuario coordinador = usuarioService.obtenerPorRut(rut).get();

        //carrera
        //  if(!Optional.of(coordinador.getCarrera()).isPresent()) {
         //   return new ResponseEntity("Carrera no encontrada", HttpStatus.BAD_REQUEST);
        //}

        if(usuarioService.existePorRut(rut)){

            if(etapa.equalsIgnoreCase("etapaI") || etapa.equalsIgnoreCase("ETAPA_I")){
                // Todo UUID
                BlobClient blob = blobContainerClient.getBlobClient(multipartFile.getOriginalFilename());
                blob.upload(multipartFile.getInputStream(), multipartFile.getSize(), true);

                Archivo archivo = new Archivo();
                String url = blob.getBlobUrl();
                archivo.setNombre(blob.getBlobName());
                archivo.setUrl(url);
                archivo.setEtapa(Etapas.ETAPA_I);
                archivo.setCarrera(coordinador.getCarrera());
                archivo.setTipoDocumento(TipoDocumento.PLANTILLA);
                archivo.setUsuarioCreador(coordinador);
                archivoService.guardar(archivo);

                return new ResponseEntity("Se ha subido la plantilla con exito", HttpStatus.OK);

            } else if (etapa.equalsIgnoreCase("etapaII") || etapa.equalsIgnoreCase("ETAPA_II")) {
                BlobClient blob = blobContainerClient.getBlobClient(multipartFile.getOriginalFilename());
                blob.upload(multipartFile.getInputStream(), multipartFile.getSize(), true);

                Archivo archivo = new Archivo();
                String url = blob.getBlobUrl();
                archivo.setNombre(blob.getBlobName());
                archivo.setUrl(url);
                archivo.setEtapa(Etapas.ETAPA_II);
                archivo.setCarrera(coordinador.getCarrera());
                archivo.setTipoDocumento(TipoDocumento.PLANTILLA);
                archivo.setUsuarioCreador(coordinador);
                archivoService.guardar(archivo);
                return new ResponseEntity("Se ha subido la plantilla con exito", HttpStatus.OK);

            } else if (etapa.equalsIgnoreCase("etapaIV") || etapa.equalsIgnoreCase("ETAPA_IV")) {
                BlobClient blob = blobContainerClient.getBlobClient(multipartFile.getOriginalFilename());
                blob.upload(multipartFile.getInputStream(), multipartFile.getSize(), true);

                Archivo archivo = new Archivo();
                String url = blob.getBlobUrl();
                archivo.setNombre(blob.getBlobName());
                archivo.setUrl(url);
                archivo.setEtapa(Etapas.ETAPA_IV);
                archivo.setCarrera(coordinador.getCarrera());
                archivo.setTipoDocumento(TipoDocumento.PLANTILLA);
                archivo.setUsuarioCreador(coordinador);
                archivoService.guardar(archivo);
                return new ResponseEntity("Se ha subido la plantilla con exito", HttpStatus.OK);

            } else if (etapa.equalsIgnoreCase("etapaV") || etapa.equalsIgnoreCase("ETAPA_V")) {
                BlobClient blob = blobContainerClient.getBlobClient(multipartFile.getOriginalFilename());
                blob.upload(multipartFile.getInputStream(), multipartFile.getSize(), true);

                Archivo archivo = new Archivo();
                String url = blob.getBlobUrl();
                archivo.setNombre(blob.getBlobName());
                archivo.setUrl(url);
                archivo.setEtapa(Etapas.ETAPA_V);
                archivo.setCarrera(coordinador.getCarrera());
                archivo.setTipoDocumento(TipoDocumento.PLANTILLA);
                archivo.setUsuarioCreador(coordinador);
                archivoService.guardar(archivo);
                return new ResponseEntity("Se ha subido la plantilla con exito", HttpStatus.OK);
            }

            return new ResponseEntity("Etapa no encontrada", HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity("Usuario no encontrado, inicie sesión nuevamente", HttpStatus.BAD_REQUEST);
        }
    }
}
