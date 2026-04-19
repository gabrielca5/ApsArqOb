package com.example.SpotiFake.album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpotiFake.artista.Artista;
import com.example.SpotiFake.artista.ArtistaService;


import java.util.ArrayList;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistaService artistaService;

    public void deletarAlbum(long id) {
        for(Album album: albumRepository.findAll()){
            if(album.getId() == id){
                albumRepository.deleteById(id);
            }
        }
    }

    public Album atualizarAlbum(long id, Album albumAtualizado) {
        Album albumExistente = albumRepository.findById(id).orElse(null);

        if (albumExistente != null) {
            if (albumAtualizado.getTitulo() != null) {
                albumExistente.setTitulo(albumAtualizado.getTitulo());
            }
            if (albumAtualizado.getArtista() != null) {
                albumExistente.setArtista(albumAtualizado.getArtista());
            }
            albumRepository.save(albumExistente);
        }

        return albumExistente;
    }

    public Album getAlbum(long id) {
        return albumRepository.findById(id).orElse(null);
    }

    public ArrayList<Album> getAlbuns() {
        return new ArrayList<>(albumRepository.findAll());
    }

    public Album criarAlbum(Album album) {
        Long artistaId = album.getArtista() != null ? album.getArtista().getId() : null;
        if (artistaId != null && artistaId > 0) {
            Artista artistaCompleto = artistaService.getArtista(artistaId);
            if (artistaCompleto != null) {
                album.setArtista(artistaCompleto);
            }
        }
        return albumRepository.save(album);
    }
}


