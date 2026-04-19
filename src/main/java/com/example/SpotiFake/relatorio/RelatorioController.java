package com.example.SpotiFake.relatorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.SpotiFake.musica.MusicaService;


@RestController
public class RelatorioController {

    @Autowired
    private MusicaService musicaService;

    @GetMapping("/relatorios/top-musicas")
    public List<TopMusicaDTO> topMusicas() {
        return musicaService.getMusicasMaisReproduzidas();
    }
}


