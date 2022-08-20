package com.alkemy.disney.controladores;

import com.alkemy.disney.entidades.Personaje;
import com.alkemy.disney.excepciones.MyException;
import com.alkemy.disney.modelos.request.PersonajeRequest;
import com.alkemy.disney.modelos.response.PersonajeResponse;
import com.alkemy.disney.servicios.PersonajeServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonajeControlador {

    @Autowired
    private PersonajeServicio personajeServicio;

    @GetMapping("/characters")
    public ResponseEntity<List<PersonajeResponse>> listaPersonajes() {

        List<PersonajeResponse> listaPersonajes = personajeServicio.mostrarPorNombreImagen();
        return ResponseEntity.ok(listaPersonajes);

    }

    @PostMapping("/characters/create")
    public ResponseEntity<Personaje> crearPersonaje(@RequestBody PersonajeRequest personajeEnvio) {

        try {
            Personaje p = personajeServicio.creacionPersonaje(personajeEnvio.idPelicula, personajeEnvio.imagen,
                    personajeEnvio.nombre, personajeEnvio.edad, personajeEnvio.peso, personajeEnvio.historia);

            return ResponseEntity.ok(p);

        } catch (MyException ex) {
            ex.getMessage();
            return null;
        }

    }

    @PutMapping("characters/{id}")
    public ResponseEntity<Personaje> modificarPersonaje(@PathVariable String id, @RequestBody PersonajeRequest personajeEnvio) {

        try {
            Personaje personaje = personajeServicio.modificarPersonaje(id, personajeEnvio.imagen, personajeEnvio.nombre,
                    personajeEnvio.edad, personajeEnvio.peso, personajeEnvio.historia);

            return new ResponseEntity<>(personaje, HttpStatus.OK);

        } catch (MyException ex) {
            ex.getMessage();
            return null;
        }

    }

    @DeleteMapping("characters/{id}")
    public ResponseEntity<String> eliminarPersonaje(@PathVariable String id) {

        try {
            personajeServicio.eliminarPersonaje(id);

            return new ResponseEntity("El personaje ah sido eliminado con exito", HttpStatus.OK);

        } catch (MyException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("characters/name/{nombre}")
    public ResponseEntity<Personaje> buscarPersonajePorNombre(@PathVariable String nombre) {

        return ResponseEntity.ok(personajeServicio.buscarPersonajePorNombre(nombre));

    }
    
    
    @GetMapping("characters/age/{edad}")
    public ResponseEntity<List<Personaje>> buscarPersonajesPorNombre(@PathVariable Integer edad) {

        return ResponseEntity.ok(personajeServicio.buscarPersonajePorEdad(edad));

    }
    
    
     @GetMapping("characters/movies/{peliculaId}")
    public ResponseEntity<List<Personaje>> buscarPersonajesPorPelicula(@PathVariable String peliculaId) {

        return ResponseEntity.ok(personajeServicio.buscarPersonajesPorPelicula(peliculaId));

    }
    

}
