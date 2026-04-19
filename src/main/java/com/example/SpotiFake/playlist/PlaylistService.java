package com.example.SpotiFake.playlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SpotiFake.musica.Musica;
import com.example.SpotiFake.musica.MusicaService;
import com.example.SpotiFake.usuario.Usuario;
import com.example.SpotiFake.usuario.UsuarioService;


import java.util.ArrayList;
import java.util.Collection;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private MusicaService musicaService;


    @Autowired
    private UsuarioService usuarioService;

    public Playlist criarPlaylist(Playlist playlist) {
        // Buscar usuÃ¡rio completo se ID foi informado
        Long usuarioId = playlist.getUsuario() != null ? playlist.getUsuario().getId() : null;
        if (usuarioId != null && usuarioId > 0) {
            Usuario usuarioCompleto = usuarioService.obterUsuario(usuarioId);
            if (usuarioCompleto != null) {
                playlist.setUsuario(usuarioCompleto);
            }
        }

        if (playlist.getUsuario() != null && playlist.getUsuario().getAtivo() != null && playlist.getUsuario().getAtivo()) {
            playlistRepository.save(playlist);
        }
        return playlist;
    }

    public Playlist getPlaylist(long id) {
        Playlist playlist = playlistRepository.findById(id).orElse(null);
        if (playlist != null) {
            recarregarDados(playlist);
        }
        return playlist;
    }

    private void recarregarDados(Playlist playlist) {
        // Recarregar usuÃ¡rio completo
        Long usuarioId = playlist.getUsuario() != null ? playlist.getUsuario().getId() : null;
        if (usuarioId != null && usuarioId > 0) {
            Usuario usuarioCompleto = usuarioService.obterUsuario(usuarioId);
            if (usuarioCompleto != null) {
                playlist.setUsuario(usuarioCompleto);
            }
        }
    }

    @Transactional
    public void adicionarMusica(long playlistId, long musicaId, long usuarioId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElse(null);
        Musica musica = musicaService.getMusica(musicaId);

        if (playlist != null && musica != null) {
            Usuario dono = playlist.getUsuario();
            if (dono != null && dono.getId() != null && dono.getId() == usuarioId && Boolean.TRUE.equals(dono.getAtivo())) {
                if (!playlist.getMusicas().contains(musica)) {
                    playlist.addMusica(musica);
                    playlistRepository.save(playlist);
                }
            }
        }
    }

    @Transactional
    public void removerMusica(long playlistId, long musicaId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElse(null);
        if (playlist != null) {
            Musica musica = musicaService.getMusica(musicaId);
            if (musica != null) {
                playlist.removeMusica(musica);
                playlistRepository.save(playlist);
            }
        }
    }

    public void alterarTitulo(long id, String novoTitulo) {
        Playlist playlist = playlistRepository.findById(id).orElse(null);
        if (playlist != null) {
            playlist.setNome(novoTitulo);
            playlistRepository.save(playlist);
        }
    }

    public Collection<Playlist> getPlaylists(long userId) {
        ArrayList<Playlist> resultado = new ArrayList<>();
        for (Playlist playlist : playlistRepository.findByUsuarioId(userId)) {
            if (playlist.getUsuario() != null && playlist.getUsuario().getId() != null && playlist.getUsuario().getId() == userId) {
                resultado.add(playlist);
            }
        }
        return resultado;
    }

    public void deletarPlaylist(long id){
        for(Playlist playlist: playlistRepository.findAll()){
            if(playlist.getId() != null && playlist.getId() == id){
                playlistRepository.deleteById(id);
            }
        }
    }

    public ArrayList<Playlist> todasPlaylists(){
        return new ArrayList<>(playlistRepository.findAll());
    }

    public Playlist atualizarPlaylist(long id, Playlist playlistAtualizada) {
        Playlist playlistExistente = playlistRepository.findById(id).orElse(null);

        if (playlistExistente != null) {
            if (playlistAtualizada.getNome() != null) {
                playlistExistente.setNome(playlistAtualizada.getNome());
            }
            if (playlistAtualizada.getPublica() != null) {
                playlistExistente.setPublica(playlistAtualizada.getPublica());
            }
            if (playlistAtualizada.getUsuario() != null) {
                playlistExistente.setUsuario(playlistAtualizada.getUsuario());
            }
            if(playlistAtualizada.getMusicas() != null) {
                playlistExistente.setMusicas(playlistAtualizada.getMusicas());
            }
            recarregarDados(playlistExistente);
            playlistRepository.save(playlistExistente);
        }
        return playlistExistente;
    }
}


