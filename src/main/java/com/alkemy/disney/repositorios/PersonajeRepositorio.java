package com.alkemy.disney.repositorios;

import com.alkemy.disney.entidades.Personaje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje, String> {
    
    
    Personaje findByPersonajeId(Integer id);

    Personaje findByNombre(String nombre);

    Personaje findByEdad(Integer edad);
   
    @Query("SELECT p FROM Personaje p WHERE p.nombre = :nombre")
    public Personaje buscarPorNombre(@Param("nombre") String nombre);
    
    @Query("SELECT p FROM Personaje p WHERE p.edad = :edad")
    public List<Personaje> buscarPorEdad(@Param("edad") Integer edad);
    
    @Query("SELECT p FROM Personaje p WHERE p.pelicula.id = :peliculaId")
    public List<Personaje> buscarPorPelicula(@Param("peliculaId") String peliculaId );
    
    
    
    
}
