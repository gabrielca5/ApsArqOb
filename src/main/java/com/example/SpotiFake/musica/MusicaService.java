package com.example.SpotiFake.musica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpotiFake.album.Album;
import com.example.SpotiFake.album.AlbumService;
import com.example.SpotiFake.artista.Artista;
import com.example.SpotiFake.artista.ArtistaService;
import com.example.SpotiFake.relatorio.TopMusicaDTO;
import com.example.SpotiFake.usuario.Usuario;
import com.example.SpotiFake.usuario.UsuarioService;


import java.util.ArrayList;
import java.util.List;

@Service
public class MusicaService {

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ArtistaService artistaService;

    public Musica criarMusica(Musica musica) {
        // Buscar album completo se ID foi informado
        Long albumId = musica.getAlbum() != null ? musica.getAlbum().getId() : null;
        if (albumId != null && albumId > 0) {
            Album albumCompleto = albumService.getAlbum(albumId);
            if (albumCompleto != null) {
                musica.setAlbum(albumCompleto);
            }
        }

        // Buscar artista completo se ID foi informado
        Long artistaId = musica.getArtista() != null ? musica.getArtista().getId() : null;
        if (artistaId != null && artistaId > 0) {
            Artista artistaCompleto = artistaService.getArtista(artistaId);
            if (artistaCompleto != null) {
                musica.setArtista(artistaCompleto);
            }
        }

        return musicaRepository.save(musica);
    }

    public Musica getMusica(long id) {
        return musicaRepository.findById(id).orElse(null);
    }

    public ArrayList<Musica> getMusicas() {
        return new ArrayList<>(musicaRepository.findAll());
    }

    public Musica atualizarMusica(long id, Musica musicaAtualizada) {
        Musica musicaExistente = musicaRepository.findById(id).orElse(null);

        if (musicaExistente != null) {
            if (musicaAtualizada.getTitulo() != null) {
                musicaExistente.setTitulo(musicaAtualizada.getTitulo());
            }
            if (musicaAtualizada.getDuracaoSegundos() != null) {
                musicaExistente.setDuracaoSegundos(musicaAtualizada.getDuracaoSegundos());
            }
            if (musicaAtualizada.getNumeroFaixa() != null) {
                musicaExistente.setNumeroFaixa(musicaAtualizada.getNumeroFaixa());
            }
            musicaRepository.save(musicaExistente);
        }

        return musicaExistente;
    }

    public void deletarMusica(long id){
        musicaRepository.deleteById(id);
    }

    public void atualizarReproducoes(long musicaId, long idUser){
        Musica musica = musicaRepository.findById(musicaId).orElse(null);
        Usuario usuario = usuarioService.obterUsuario(idUser);
        if(usuario != null && usuario.getAtivo() && musica != null){
            musica.setTotalReproducoes(musica.getTotalReproducoes() + 1);
            musicaRepository.save(musica);
        }
    }

    public List<TopMusicaDTO> getMusicasMaisReproduzidas() {
        List<TopMusicaDTO> resultado = new ArrayList<>();

        musicaRepository.findAll().stream()
                .sorted((m1, m2) -> Long.compare(m2.getTotalReproducoes(), m1.getTotalReproducoes()))
                .limit(10)
                .forEach(musica -> {
                    if (musica.getArtista() != null) {
                        resultado.add(new TopMusicaDTO(
                                musica.getTitulo(),
                                musica.getArtista().getNome(),
                                musica.getTotalReproducoes()
                        ));
                    }
                });

        return resultado;
    }
}


