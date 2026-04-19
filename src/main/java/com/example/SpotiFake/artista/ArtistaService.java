package com.example.SpotiFake.artista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    public Artista criarArtista(Artista artista) {
        return artistaRepository.save(artista);
    }

    public Artista getArtista(long id) {
        return artistaRepository.findById(id).orElse(null);
    }

    public ArrayList<Artista> getArtistas() {
        return new ArrayList<>(artistaRepository.findAll());
    }

    public Artista atualizarArtista(long id, Artista artistaAtualizado) {
        Artista artistaExistente = artistaRepository.findById(id).orElse(null);

        if (artistaExistente != null) {
            if (artistaAtualizado.getNome() != null) {
                artistaExistente.setNome(artistaAtualizado.getNome());
            }
            if (artistaAtualizado.getGeneroMusical() != null) {
                artistaExistente.setGeneroMusical(artistaAtualizado.getGeneroMusical());
            }
            if (artistaAtualizado.getPaisOrigem() != null) {
                artistaExistente.setPaisOrigem(artistaAtualizado.getPaisOrigem());
            }
            artistaRepository.save(artistaExistente);
        }

        return artistaExistente;
    }

    public void deletarArtista(long id) {
        for (Artista artista : artistaRepository.findAll()) {
            if (artista.getId() == id) {
                artistaRepository.deleteById(id);
            }
        }
    }
}


