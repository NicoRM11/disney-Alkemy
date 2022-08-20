package com.alkemy.disney.servicios;

import com.alkemy.disney.entidades.Genero;
import com.alkemy.disney.entidades.Pelicula;
import com.alkemy.disney.excepciones.MyException;
import com.alkemy.disney.modelos.response.PeliculaResponse;
import com.alkemy.disney.repositorios.GeneroRepositorio;
import com.alkemy.disney.repositorios.PeliculaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaServicio {

    @Autowired
    private PeliculaRepositorio peliculaRepositorio;
    @Autowired
    private GeneroRepositorio generoRepositorio;

    //metodo para crear pelicula
    @Transactional
    public Pelicula crearPelicula(String imagen, String titulo, Date fechaCreacion, Integer calificacion, String generoId) throws MyException {
        
        validar(imagen, titulo);
       
        Genero genero = generoRepositorio.findById(generoId).get();
       
        Pelicula pelicula = new Pelicula();

        pelicula.setImagen(imagen);
        pelicula.setTitulo(titulo);
        pelicula.setFechaCreacion(fechaCreacion);
        pelicula.setCalificacion(calificacion);
        pelicula.setGenero(genero);
        
        genero.agregarPelicula(pelicula);
        
        peliculaRepositorio.save(pelicula);

        return pelicula;

    }

    //metodo para modificar pelicula
    public Pelicula modificarPelicula(String id, String imagen, String titulo, Date fechaCreacion, Integer calificacion, String generoId) throws MyException {

        validar(imagen, titulo);

        Optional<Pelicula> respuesta = peliculaRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Genero genero = generoRepositorio.findById(generoId).get();

            Pelicula pelicula = respuesta.get();

            pelicula.setImagen(imagen);
            pelicula.setTitulo(titulo);
            pelicula.setFechaCreacion(fechaCreacion);
            pelicula.setCalificacion(calificacion);
            pelicula.setGenero(genero);

            peliculaRepositorio.save(pelicula);

            return pelicula;
        } else {
            throw new MyException("El id indicado no corresponde con una pelicula");
        }

    }

    //metodo para eliminar peliculas
    @Transactional
    public void eliminarPelicula(String id) throws MyException {

        Optional<Pelicula> respuesta = peliculaRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Pelicula pelicula = respuesta.get();

            peliculaRepositorio.delete(pelicula);

        } else {
            throw new MyException("El id indicado no corresponde con una pelicula");
        }

    }
    
    //metodo para listar peliculas, con todos sus atributos
    public List<Pelicula> listarPeliculas(){
         
        List<Pelicula> listaPeliculas = peliculaRepositorio.findAll();
        
        return listaPeliculas;
    }
    
    //metodo para listar peliculas con titulo, imagen y fecha de creacion
    public List<PeliculaResponse> listarPeliculasResponse(){
       
        List<Pelicula> listaPeliculas = listarPeliculas();
        
        List<PeliculaResponse> listaPeliculasResponse = new ArrayList<>();
        
        for (Pelicula aux : listaPeliculas) {

            PeliculaResponse pr = new PeliculaResponse();
            pr.titulo = aux.getTitulo();
            pr.imagen = aux.getImagen();
            pr.fechaCreacion = aux.getFechaCreacion();
            //se agrega a lista de personaje response
            listaPeliculasResponse.add(pr);
        }
        
        return listaPeliculasResponse;
    
    }
    
    
    
    //metodo para buscar pelicula por titulo
    public Pelicula buscarPorTitulo(String titulo){
       
       Pelicula pelicula = peliculaRepositorio.buscarPeliculaPorTitulo(titulo);
       
       return pelicula;
    }
    
    //metodo para buscar pelicula por genero
    public List<Pelicula> buscarPorGenero(String idGenero){
     
        return peliculaRepositorio.buscarPeliculasPorGenero(idGenero);
    }
    
    //buscar por fecha de creacion, en orden ascendente
    public List<Pelicula> buscarPorFechaAsc(){
       
        return peliculaRepositorio.buscarPeliculasPorCreacionAsc();
    }
    
    
    //buscar por fecha de creacion, en orden descendente
    public List<Pelicula> buscarPorFechaDesc(){
       
        return peliculaRepositorio.buscarPeliculasPorCreacionDesc();
    }

    //metodo para validar
    private void validar(String imagen, String titulo) throws MyException {

        if (imagen.isEmpty()) {
            throw new MyException("La imagen no puede estar vacia o ser nula");
        }
        if (titulo.isEmpty()) {
            throw new MyException("El titulo no puede estar vacio o ser nulo");
        }

    }

}
