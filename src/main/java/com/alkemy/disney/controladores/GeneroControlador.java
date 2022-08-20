package com.alkemy.disney.controladores;

import com.alkemy.disney.entidades.Genero;
import com.alkemy.disney.excepciones.MyException;
import com.alkemy.disney.modelos.request.GeneroRequest;
import com.alkemy.disney.modelos.response.GeneroResponse;
import com.alkemy.disney.servicios.GeneroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneroControlador {

    @Autowired
    private GeneroServicio generoServicio;

    @GetMapping("/gender")
    public ResponseEntity<List<GeneroResponse>> listarGeneros() {

        List<GeneroResponse> listaGeneros = generoServicio.mostrarPorNombreImagen();

        return ResponseEntity.ok(listaGeneros);
    }

    @PostMapping("/gender/create")
    public ResponseEntity<Genero> crearGenero(@RequestBody GeneroRequest generoEnvio) {

        try {
            Genero g = generoServicio.crearGenero(generoEnvio.nombre, generoEnvio.imagen);

            return ResponseEntity.ok(g);

        } catch (MyException ex) {
            ex.getMessage();
            return null;
        }

    }

    @DeleteMapping("gender/{id}")
    public ResponseEntity<String> eliminarGenero(@PathVariable String id) {

        try {
            generoServicio.eliminarGenero(id);

            return new ResponseEntity("El genero ah sido eliminado con exito", HttpStatus.OK);

        } catch (MyException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
