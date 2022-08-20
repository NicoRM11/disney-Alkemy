package com.alkemy.disney.servicios;

import com.alkemy.disney.entidades.Genero;
import com.alkemy.disney.excepciones.MyException;
import com.alkemy.disney.modelos.response.GeneroResponse;
import com.alkemy.disney.repositorios.GeneroRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroServicio {

    @Autowired
    private GeneroRepositorio generoRepositorio;

    //metodo para crear genero
    @Transactional
    public Genero crearGenero(String nombre, String imagen) throws MyException {

        validar(nombre, imagen);

        Genero genero = new Genero();

        genero.setNombre(nombre);
        genero.setImagen(imagen);

        generoRepositorio.save(genero);

        return genero;

    }

    //metodo para eliminar genero
    @Transactional
    public void eliminarGenero(String id) throws MyException {

        Optional<Genero> respuesta = generoRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Genero genero = respuesta.get();

            generoRepositorio.delete(genero);

        } else {
            throw new MyException("El id indicado no corresponde con un genero");
        }

    }

    //metodo para listar todos los generos, por nombre e imagen
    public List<GeneroResponse> mostrarPorNombreImagen() {

        List<Genero> listaGenero = generoRepositorio.findAll();

        List<GeneroResponse> listaGenerosResponse = new ArrayList();

        for (Genero aux : listaGenero) {

            GeneroResponse gr = new GeneroResponse();
            gr.nombre = aux.getNombre();
            gr.imagen = aux.getImagen();
            //se agrega a lista de genero response
            listaGenerosResponse.add(gr);
        }

        return listaGenerosResponse;

    }

    //metodo validar
    private void validar(String nombre, String imagen) throws MyException {

        if (nombre.isEmpty()) {
            throw new MyException("El nombre no puede estar vacio o ser nulo");
        }
        if (imagen.isEmpty()) {
            throw new MyException("La imagen no puede estar vacia o ser nula");
        }

    }

}
