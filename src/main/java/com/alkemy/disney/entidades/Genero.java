package com.alkemy.disney.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Genero {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "uuid2")
    @Column(name = "generoId")
    private String generoId;

    private String nombre;
    private String imagen;

    @OneToMany(mappedBy = "genero" , cascade = {CascadeType.REFRESH , CascadeType.PERSIST})
    @JsonIgnore
    private List<Pelicula> pelicula;

    public Genero() {
    }

    public String getGeneroId() {
        return generoId;
    }

    public void setGeneroId(String generoId) {
        this.generoId = generoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Pelicula> getPelicula() {
        return pelicula;
    }

    public void setPelicula(List<Pelicula> pelicula) {
        this.pelicula = pelicula;
    }
    
    
    public void agregarPelicula(Pelicula pelicula){
        this.pelicula.add(pelicula);
    }

    

}
