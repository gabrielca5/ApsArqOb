package com.example.SpotiFake.album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping("/albuns")
    public Album criarAlbum(@RequestBody Album album) {
        return albumService.criarAlbum(album);
    }

    @GetMapping("/albuns")
    public ArrayList<Album> getAlbuns() {
        return albumService.getAlbuns();
    }

    @GetMapping("/albuns/{id}")
    public Album getArtista(@PathVariable long id) {
        return albumService.getAlbum(id);
    }

    @PutMapping("/albuns/{id}")
    public Album atualizarAlbum(@PathVariable long id, @RequestBody Album album) {
        return albumService.atualizarAlbum(id, album);
    }

    @DeleteMapping("/albuns/{id}")
    public void deletarAlbum(@PathVariable long id) {
        albumService.deletarAlbum(id);
    }
}


