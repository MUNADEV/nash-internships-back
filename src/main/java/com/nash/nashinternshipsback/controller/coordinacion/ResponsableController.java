package com.nash.nashinternshipsback.controller.coordinacion;

import com.nash.nashinternshipsback.service.coordinacion.ResponsableService;
import com.nash.nashinternshipsback.model.Responsable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/responsable")
@CrossOrigin
public class ResponsableController {

    @Autowired
    private ResponsableService responsableService;

    /**
     * Guarda el responsable
     * @param responsable Objeto de tipo Responsable
     */
    @PostMapping()
    public void guardar(@RequestBody Responsable responsable){
        responsableService.guardar(responsable);
    }

    /**
     * Obtiene un Responsable mediante el identificador
     * @param id Cadena de texto representativa de un id
     * @return Responsable coincidente con el identificador
     */
    @GetMapping("/{id}")
    public Optional<Responsable> obtenerPorID(@PathVariable("id") String id){
        return responsableService.obtenerPorID(id);
    }

    /**
     * Obtiene un Responsable mediante su nombre
     * @param nombre Cadena de texto representativa de un nombre
     * @return Responsable coincidente con el nombre
     */
    @GetMapping("/{nombre}")
    public Optional<Responsable> obtenerPorNombre(@PathVariable("nombre") String nombre){
        return responsableService.obtenerPorNombre(nombre);
    }

    /**
     * Obtiene un Responsable mediante su rut
     * @param rut Cadena de texto representativa de rut
     * @return Responsable coincidente con el rut
     */
    @GetMapping("/{rut}")
    public Optional<Responsable> obtenerPorRut(@PathVariable("rut") String rut){
        return responsableService.obtenerPorRut(rut);
    }


    @PutMapping("/{id}/editar")
    public void editar(@RequestParam String rut,
                                  @RequestParam String nombre,
                                  @RequestParam String correo){
         responsableService.editarResponsable(rut,nombre,correo);
    }


}
