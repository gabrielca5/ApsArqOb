package com.example.SpotiFake.musica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @PostMapping("/musicas")
    public Musica criarMusica(@RequestBody Musica musica) {
        return musicaService.criarMusica(musica);
    }

    @GetMapping("/musicas")
    public ArrayList<Musica> getMusicas() {
        return musicaService.getMusicas();
    }

    @GetMapping("/musicas/{id}")
    public Musica getMusica(@PathVariable long id) {
        return musicaService.getMusica(id);
    }

    @PutMapping("/musicas/{id}")
    public Musica atualizarMusica(@PathVariable long id, @RequestBody Musica musica) {
        return musicaService.atualizarMusica(id, musica);
    }

    @DeleteMapping("/musicas/{id}")
    public void deletarMusica(@PathVariable long id){
        musicaService.deletarMusica(id);
    }
    @PostMapping("/musicas/{id}/reproduzir")
    public void reproduzirMusica(@PathVariable long id, @RequestHeader("X-USER-ID") long usuarioId) {
        musicaService.atualizarReproducoes(id, usuarioId);
    }

}



