package com.alkemy.disney.servicios;

import com.alkemy.disney.entidades.Usuario;
import com.alkemy.disney.repositorios.UsuarioRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public Usuario registrar(String nombre, String email, String password) {

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);

    
        usuario.setPassword(password);

        usuarioRepositorio.save(usuario);

        return usuario;

    }
    
    public Usuario login(String email, String password){
        
        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
        
        return usuario;
        
    
    }

    

}
