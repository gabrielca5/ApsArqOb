package com.example.SpotiFake.relatorio;

public class TopMusicaDTO {
    private String         titulo;
    private String        artista;
    private long totalReproducoes;

    public TopMusicaDTO(String titulo, String artista, long totalReproducoes) {
        this.titulo = titulo;
        this.artista = artista;
        this.totalReproducoes = totalReproducoes;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public long getTotalReproducoes() {
        return totalReproducoes;
    }
}


