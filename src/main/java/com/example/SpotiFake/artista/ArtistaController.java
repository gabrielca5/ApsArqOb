package com.example.SpotiFake.artista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @PostMapping("/artistas")
    public Artista criarArtista(@RequestBody Artista artista) {
        return artistaService.criarArtista(artista);
    }

    @GetMapping("/artistas")
    public ArrayList<Artista> getArtistas() {
        return artistaService.getArtistas();
    }

    @GetMapping("/artistas/{id}")
    public Artista getArtista(@PathVariable long id) {
        return artistaService.getArtista(id);
    }

    @PutMapping("/artistas/{id}")
    public Artista atualizarArtista(@PathVariable long id, @RequestBody Artista artista) {
        return artistaService.atualizarArtista(id, artista);
    }

    @DeleteMapping("/artistas/{id}")
    public void deletarArtista(@PathVariable long id) {
        artistaService.deletarArtista(id);
    }
}



