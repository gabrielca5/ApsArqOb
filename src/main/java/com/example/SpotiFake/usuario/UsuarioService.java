package com.example.SpotiFake.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario createUser(Usuario usuario){
        usuario.setAtivo(true);
        return usuarioRepository.save(usuario);
    }

    public Usuario getUserId(long id){
        for(Usuario usuario : usuarioRepository.findAll()){
            if(usuario.getId() != null && usuario.getId() == id){
                return usuario;
            }
        }
        return null;
    }

    public void setStatusUser(long id){
        for(Usuario usuario : usuarioRepository.findAll()){
            if(usuario.getId() != null && usuario.getId() == id){
                if(Boolean.TRUE.equals(usuario.getAtivo())){
                    usuario.setAtivo(false);
                }else{
                    usuario.setAtivo(true);
                }
                usuarioRepository.save(usuario);
            }
        }
    }

    public ArrayList<Usuario> getUsuarios() {
        return new ArrayList<>(usuarioRepository.findAll());
    }

    public Usuario obterUsuario(long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario atualizarUsuario(long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);

        if (usuarioExistente != null) {
            if (usuarioAtualizado.getNome() != null) {
                usuarioExistente.setNome(usuarioAtualizado.getNome());
            }
            if (usuarioAtualizado.getEmail() != null) {
                usuarioExistente.setEmail(usuarioAtualizado.getEmail());
            }
            if (usuarioAtualizado.getAtivo() != null) {
                usuarioExistente.setAtivo(usuarioAtualizado.getAtivo());
            }
            if (usuarioAtualizado.getTipoPlano() != null) {
                usuarioExistente.setTipoPlano(usuarioAtualizado.getTipoPlano());
            }
            usuarioRepository.save(usuarioExistente);
        }

        return usuarioExistente;
    }

    public Usuario atualizarPlano(long id){
        for(Usuario usuario : usuarioRepository.findAll()){
            if(usuario.getId() != null && usuario.getId() == id){
                if(Boolean.TRUE.equals(usuario.getAtivo())){
                    usuario.setAtivo(false);
                }else{
                    usuario.setAtivo(true);
                }
                usuarioRepository.save(usuario);
                return usuario;
            }
        }
        return null;
    }

    public void deletarUsuario(long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setAtivo(false);
            usuarioRepository.save(usuario);
        }
    }
}


