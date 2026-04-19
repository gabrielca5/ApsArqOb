package com.example.SpotiFake.playlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/playlists")
    public Playlist criarPlaylist(@RequestBody Playlist playlist) {
        return playlistService.criarPlaylist(playlist);
    }

    @GetMapping("/usuarios/{userId}/playlists")
    public Collection<Playlist> getPlaylistsUsuario(@PathVariable long userId) {
        return playlistService.getPlaylists(userId);
    }

    @PostMapping("/playlists/{playlistId}/musicas/{musicaId}")
    public void adicionarMusica(@PathVariable long playlistId, @PathVariable long musicaId, @RequestHeader("X-USER-ID") long usuarioId  ) {
        playlistService.adicionarMusica(playlistId, musicaId, usuarioId);
    }

    @DeleteMapping("/playlists/{playlistId}/musicas/{musicaId}")
    public void removerMusica(@PathVariable long playlistId, @PathVariable long musicaId) {
        playlistService.removerMusica(playlistId, musicaId);
    }

    @PutMapping("/playlists/{id}/titulo")
    public void alterarTitulo(@PathVariable long id, @RequestParam String titulo) {
        playlistService.alterarTitulo(id, titulo);
    }
    @DeleteMapping("/playlists/{id}")
    public void deletarPlaylist(@PathVariable long id) {
        playlistService.deletarPlaylist(id);
    }
    @GetMapping("/playlists")
    public Collection<Playlist> todasPlaylists() {
        return playlistService.todasPlaylists();
    }

    @GetMapping("/playlists/{id}")
    public Playlist getPlaylist(@PathVariable long id) {
        return playlistService.getPlaylist(id);
    }

    @PutMapping("/playlists/{id}")
    public Playlist atualizarPlaylist(@PathVariable long id, @RequestBody Playlist playlist) {
        return playlistService.atualizarPlaylist(id, playlist);
    }


}

