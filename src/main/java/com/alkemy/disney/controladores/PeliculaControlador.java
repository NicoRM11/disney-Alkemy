package com.alkemy.disney.controladores;

import com.alkemy.disney.entidades.Pelicula;
import com.alkemy.disney.excepciones.MyException;
import com.alkemy.disney.modelos.request.PeliculaRequest;
import com.alkemy.disney.modelos.response.PeliculaResponse;
import com.alkemy.disney.servicios.PeliculaServicio;
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
public class PeliculaControlador {
    
    @Autowired
    private PeliculaServicio peliculaServicio;
    
    
    @GetMapping("/movies")
    public ResponseEntity<List<PeliculaResponse>> listaPeliculas(){
      
        List<PeliculaResponse> listaPeliculasResponse = peliculaServicio.listarPeliculasResponse();
        
        return ResponseEntity.ok(listaPeliculasResponse);
    }
    
    @PostMapping("/movies/create")
    public ResponseEntity<Pelicula> crearPelicula(@RequestBody PeliculaRequest peliculaEnvio) {

        try {
            
            Pelicula p = peliculaServicio.crearPelicula(peliculaEnvio.imagen, peliculaEnvio.titulo, 
                    peliculaEnvio.fechaCreacion, peliculaEnvio.calificacion, peliculaEnvio.generoId);
            
            return ResponseEntity.ok(p);

        } catch (MyException ex) {
            ex.getMessage();
            return null;
        }

    }
    
    
    @PutMapping("/movies/{id}")
    public ResponseEntity<Pelicula> modificarPelicula(@PathVariable String id, @RequestBody PeliculaRequest peliculaEnvio) {

        try {
            Pelicula pelicula = peliculaServicio.modificarPelicula(id, peliculaEnvio.imagen, peliculaEnvio.titulo, peliculaEnvio.fechaCreacion, 
                    peliculaEnvio.calificacion, peliculaEnvio.generoId);

            return new ResponseEntity<>(pelicula, HttpStatus.OK);

        } catch (MyException ex) {
            ex.getMessage();
            return null;
        }

    }
    
    
    @DeleteMapping("movies/{id}")
    public ResponseEntity<String> eliminarPelicula(@PathVariable String id) {

        try {
            peliculaServicio.eliminarPelicula(id);

            return new ResponseEntity("La pelicula ah sido eliminada con exito", HttpStatus.OK);

        } catch (MyException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    
    @GetMapping("/movies/name/{nombre}")
    public ResponseEntity<Pelicula> buscarPeliculaPorNombre(@PathVariable String nombre) {

        return ResponseEntity.ok(peliculaServicio.buscarPorTitulo(nombre));

    }
    
    @GetMapping("/movies/gender/{generoId}")
    public ResponseEntity<List<Pelicula>> buscarPeliculasPorGenero(@PathVariable String generoId) {

        return ResponseEntity.ok(peliculaServicio.buscarPorGenero(generoId));

    }
    
    @GetMapping("/movies/order/ASC")
    public ResponseEntity<List<Pelicula>> ordenarPeliculasPorOrdenAsc() {

        return ResponseEntity.ok(peliculaServicio.buscarPorFechaAsc());

    }
    
    @GetMapping("/movies/order/DESC")
    public ResponseEntity<List<Pelicula>> ordenarPeliculasPorOrdenDesc() {

        return ResponseEntity.ok(peliculaServicio.buscarPorFechaDesc());

    }
    
    
    
    
}
