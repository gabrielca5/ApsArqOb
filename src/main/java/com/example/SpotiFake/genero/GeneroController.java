package com.example.SpotiFake.genero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @PostMapping
    public Genero criarGenero(@RequestBody Genero genero) {
        return generoService.criarGenero(genero);
    }

    @GetMapping
    public ArrayList<Genero> listarGeneros() {
        return generoService.listarGeneros();
    }

    @GetMapping("/{id}")
    public Genero obterGenero(@PathVariable long id) {
        return generoService.obterGenero(id);
    }

    @PutMapping("/{id}")
    public Genero atualizarGenero(@PathVariable long id, @RequestBody Genero genero) {
        return generoService.atualizarGenero(id, genero);
    }

    @DeleteMapping("/{id}")
    public void deletarGenero(@PathVariable long id) {
        generoService.deletarGenero(id);
    }
}



