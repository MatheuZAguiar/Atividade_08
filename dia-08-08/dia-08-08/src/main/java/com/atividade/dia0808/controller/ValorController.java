package com.atividade.dia0808.controller;


import com.atividade.dia0808.DTO.ResultadoDTO;
import com.atividade.dia0808.model.Valor;
import com.atividade.dia0808.service.ValorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/valores")
public class ValorController {

    @Autowired
    private ValorService valorService;

    @PostMapping
    public ResponseEntity<String> adicionarValor(@RequestBody Valor valor) {
        if (valor.getValor() < 0) {
            return ResponseEntity.badRequest().body("O valor deve ser positivo.");
        }

        valorService.adicionarValor(valor.getValor());
        return ResponseEntity.ok("Valor adicionado com sucesso.");
    }

    @GetMapping("/resultado")
    public ResponseEntity<ResultadoDTO> obterResultado() {
        ResultadoDTO resultado = valorService.calcularResultado();
        return ResponseEntity.ok(resultado);
    }
}