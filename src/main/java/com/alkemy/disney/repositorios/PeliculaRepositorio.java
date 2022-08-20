package com.alkemy.disney.repositorios;

import com.alkemy.disney.entidades.Pelicula;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PeliculaRepositorio extends JpaRepository<Pelicula, String>  {
    
    
    
    
    @Query("SELECT p FROM Pelicula p WHERE p.titulo = :titulo")
    public Pelicula buscarPeliculaPorTitulo(@Param("titulo") String titulo);
    
    @Query("SELECT p FROM Pelicula p WHERE p.genero.id = :idGenero")
    public List<Pelicula> buscarPeliculasPorGenero(@Param("idGenero") String idGenero);
    
    @Query("SELECT p FROM Pelicula p ORDER BY fechaCreacion ASC")
    public List<Pelicula> buscarPeliculasPorCreacionAsc();
    
    @Query("SELECT p FROM Pelicula p ORDER BY fechaCreacion DESC")
    public List<Pelicula> buscarPeliculasPorCreacionDesc();
    
}
