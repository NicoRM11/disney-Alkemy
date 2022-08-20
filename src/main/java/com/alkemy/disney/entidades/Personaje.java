package com.alkemy.disney.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class Personaje {
    
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "uuid2")
    @Column(name = "personajeId")
    private String personajeId;

    private String imagen;
    private String nombre;
    private Integer edad;
    private Integer peso;
    private String historia;
    
    @ManyToOne(cascade = {CascadeType.REFRESH , CascadeType.PERSIST})
    @JoinColumn(name = "peliculaId")
    private Pelicula pelicula;

    public Personaje() {
    }

    public Personaje(String imagen, String nombre) {
        this.imagen = imagen;
        this.nombre = nombre;
    }

    public String getPersonajeId() {
        return personajeId;
    }

    public void setPersonajeId(String personajeId) {
        this.personajeId = personajeId;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
    
    

    
    
    
   
    
    
    
    
}
