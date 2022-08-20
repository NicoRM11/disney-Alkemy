package com.alkemy.disney.servicios;

import com.alkemy.disney.entidades.Pelicula;
import com.alkemy.disney.entidades.Personaje;
import com.alkemy.disney.excepciones.MyException;
import com.alkemy.disney.modelos.response.PersonajeResponse;
import com.alkemy.disney.repositorios.PeliculaRepositorio;
import com.alkemy.disney.repositorios.PersonajeRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonajeServicio {

    //CRUD
    @Autowired
    private PersonajeRepositorio personajeRepositorio;
    @Autowired
    private PeliculaRepositorio peliculaRepositorio;

    //creacion
    @Transactional
    public Personaje creacionPersonaje(String idPelicula, String imagen, String nombre, Integer edad, Integer peso, String historia) throws MyException {

        validar(imagen, nombre, edad, peso, historia);

        Pelicula pelicula = peliculaRepositorio.findById(idPelicula).get();

        Personaje personaje = new Personaje();

        personaje.setImagen(imagen);
        personaje.setNombre(nombre);
        personaje.setEdad(edad);
        personaje.setPeso(peso);
        personaje.setHistoria(historia);
        personaje.setPelicula(pelicula);
        
        pelicula.agregarPersonaje(personaje);
        
        personajeRepositorio.save(personaje);

        return personaje;
    }

    //modificar , menos su listado de peliculas
    @Transactional
    public Personaje modificarPersonaje(String id, String imagen, String nombre, Integer edad, Integer peso, String historia) throws MyException {

        Optional<Personaje> respuesta = personajeRepositorio.findById(id);

        if (respuesta.isPresent()) {

            validar(imagen, nombre, edad, peso, historia);

            Personaje personaje = respuesta.get();

            personaje.setImagen(imagen);
            personaje.setNombre(nombre);
            personaje.setEdad(edad);
            personaje.setPeso(peso);
            personaje.setHistoria(historia);
            personaje.setPelicula(personaje.getPelicula());

            personajeRepositorio.save(personaje);

            return personaje;
        } else {
            throw new MyException("El id indicado no corresponde con un personaje");
        }

    }

    //metodo de eliminacion
    @Transactional
    public void eliminarPersonaje(String id) throws MyException {

        Optional<Personaje> respuesta = personajeRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Personaje personaje = respuesta.get();

            personajeRepositorio.delete(personaje);

        } else {
            throw new MyException("El id indicado no corresponde con un personaje");
        }

    }

    //metodo para listar todos los personajes, con todos sus atributos
    public List<Personaje> listarPersonajes() {

        List<Personaje> listaPersonajes = personajeRepositorio.findAll();

        return listaPersonajes;

    }

    //metodo para mostrar personajes, con solo su imagen y nombre
    public List<PersonajeResponse> mostrarPorNombreImagen() {

        List<Personaje> listaPersonajes = listarPersonajes();

        List<PersonajeResponse> listaPersonajesResponse = new ArrayList();

        for (Personaje aux : listaPersonajes) {

            PersonajeResponse pr = new PersonajeResponse();
            pr.nombre = aux.getNombre();
            pr.imagen = aux.getImagen();
            //se agrega a lista de personaje response
            listaPersonajesResponse.add(pr);
        }

        return listaPersonajesResponse;

    }

    //metodo para buscar personaje por nombre
    @Transactional
    public Personaje buscarPersonajePorNombre(String nombre) {

        return personajeRepositorio.buscarPorNombre(nombre);

    }

    //metodo para buscar personaje por edad
    @Transactional
    public List<Personaje> buscarPersonajePorEdad(Integer edad) {

        return personajeRepositorio.buscarPorEdad(edad);

    }

    //metodo para buscar personajes por id de pelicula
    @Transactional
    public List<Personaje> buscarPersonajesPorPelicula(String peliculaId) {

        List<Personaje> personajes = personajeRepositorio.buscarPorPelicula(peliculaId);

        return personajes;

    }

    //metodo de validacion
    private void validar(String imagen, String nombre, Integer edad, Integer peso, String historia) throws MyException {

        if (imagen.isEmpty()) {
            throw new MyException("La imagen no puede estar vacia o ser nula");
        }
        if (nombre.isEmpty()) {
            throw new MyException("El nombre no puede estar vacio o ser nulo");
        }
        if (edad == null) {
            throw new MyException("La edad no puede estar vacia o ser nula");
        }
        if (peso == null) {
            throw new MyException("El peso no puede estar vacio o ser nulo");
        }
        if (historia.isEmpty()) {
            throw new MyException("La historia no puede estar vacia o ser nula");
        }

    }

}
