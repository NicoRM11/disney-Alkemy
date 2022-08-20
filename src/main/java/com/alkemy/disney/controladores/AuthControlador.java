package com.alkemy.disney.controladores;

import com.alkemy.disney.entidades.Usuario;
import com.alkemy.disney.modelos.request.LoginRequest;
import com.alkemy.disney.modelos.request.UsuarioRequest;
import com.alkemy.disney.modelos.response.UsuarioResponse;
import com.alkemy.disney.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    @PostMapping("/auth/register")
    public ResponseEntity<UsuarioResponse> registroUsuario(@RequestBody UsuarioRequest req) {

        UsuarioResponse respuesta = new UsuarioResponse();

        Usuario usuario = usuarioServicio.registrar(req.nombre, req.email, req.password);

        respuesta.nombre = usuario.getNombre();
        respuesta.email = usuario.getEmail();

        return ResponseEntity.ok(respuesta);

    }

    @PostMapping("/auth/login")
    public ResponseEntity<Usuario> loginUsuario(@RequestBody LoginRequest loginUsuario) {

        Usuario usuario = usuarioServicio.login(loginUsuario.email, loginUsuario.password);

        return ResponseEntity.ok(usuario);

    }

}
