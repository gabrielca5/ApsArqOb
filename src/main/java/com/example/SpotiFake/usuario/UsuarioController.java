package com.example.SpotiFake.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Collection;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios/")
    public Usuario createUser(@RequestBody Usuario usuario) {
        return usuarioService.createUser(usuario);
    }

    @GetMapping("/usuarios/{id}")
    public Usuario getUsuario(@PathVariable long id) {
        return usuarioService.obterUsuario(id);
    }

    @GetMapping("/usuarios/")
    public ArrayList<Usuario> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    @PutMapping("/usuarios/{id}")
    public Usuario atualizarUsuario(@PathVariable long id, @RequestBody Usuario usuario) {
        return usuarioService.atualizarUsuario(id, usuario);
    }

    @PutMapping("/usuarios/{id}/tipo-plano")
    public Usuario atualizarTipoPlano(@PathVariable long id, @RequestParam String tipoPlano) {
        Usuario usuario = usuarioService.obterUsuario(id);
        if (usuario != null) {
            usuario.setTipoPlano(TipoPlano.valueOf(tipoPlano.toUpperCase()));
            usuarioService.atualizarUsuario(id, usuario);
        }
        return usuario;
    }

    @PutMapping("/usuarios/{id}/status")
    public Usuario toggleStatus(@PathVariable long id) {
        Usuario usuario = usuarioService.obterUsuario(id);
        if (usuario != null) {
            usuario.setAtivo(!usuario.getAtivo());
            usuarioService.atualizarUsuario(id, usuario);
        }
        return usuario;
    }

    @DeleteMapping("/usuarios/{id}")
    public void deletarUsuario(@PathVariable long id) {
        usuarioService.deletarUsuario(id);
    }
}


