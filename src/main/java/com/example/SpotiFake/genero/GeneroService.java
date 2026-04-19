package com.example.SpotiFake.genero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public Genero criarGenero(Genero genero) {
        return generoRepository.save(genero);
    }

    public Genero obterGenero(long id) {
        return generoRepository.findById(id).orElse(null);
    }

    public ArrayList<Genero> listarGeneros() {
        return new ArrayList<>(generoRepository.findAll());
    }

    public Genero atualizarGenero(long id, Genero generoAtualizado) {
        Genero generoExistente = generoRepository.findById(id).orElse(null);
        if (generoExistente != null) {
            if (generoAtualizado.getNome() != null) {
                generoExistente.setNome(generoAtualizado.getNome());
            }
            if (generoAtualizado.getDescricao() != null) {
                generoExistente.setDescricao(generoAtualizado.getDescricao());
            }
            generoRepository.save(generoExistente);
        }
        return generoExistente;
    }

    public void deletarGenero(long id) {
        generoRepository.deleteById(id);
    }
}


