package com.nash.nashinternshipsback.controller.practicante;

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
@RequestMapping("/practicante")
@CrossOrigin
public class PracticanteController {
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



    @PostMapping("/subir-documento/{etapa}")
    public ResponseEntity<?> subirPlantilla(@RequestParam("file") MultipartFile multipartFile, @PathVariable("etapa") String etapa) throws IOException {

        //usuario
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String rut = userDetails.getUsername();
        Usuario practicante = usuarioService.obtenerPorRut(rut).get();

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
                archivo.setCarrera(practicante.getCarrera());
                archivo.setTipoDocumento(TipoDocumento.PLANTILLA);
                archivo.setUsuarioCreador(practicante);
                //practicante.getArchivos().add(archivo);
                //usuarioService.guardar(practicante);
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
                archivo.setCarrera(practicante.getCarrera());
                archivo.setTipoDocumento(TipoDocumento.PLANTILLA);
                archivo.setUsuarioCreador(practicante);
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
                archivo.setCarrera(practicante.getCarrera());
                archivo.setTipoDocumento(TipoDocumento.PLANTILLA);
                archivo.setUsuarioCreador(practicante);
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
                archivo.setCarrera(practicante.getCarrera());
                archivo.setTipoDocumento(TipoDocumento.PLANTILLA);
                archivo.setUsuarioCreador(practicante);
                archivoService.guardar(archivo);
                return new ResponseEntity("Se ha subido la plantilla con exito", HttpStatus.OK);
            }

            return new ResponseEntity("Etapa no encontrada", HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity("Usuario no encontrado, inicie sesión nuevamente", HttpStatus.BAD_REQUEST);
        }
    }



   /* @PutMapping("/{id}/actualizar")
    public ResponseEntity<?> actualizarUsuario(@RequestBody Usuario usuario, @PathVariable String id){
        this.usuarioService.actualizar(Optional.of(usuario),id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario Actualizado");
    }*/
}
