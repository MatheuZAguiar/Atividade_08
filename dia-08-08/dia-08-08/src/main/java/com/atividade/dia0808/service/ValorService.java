package com.atividade.dia0808.service;

import com.atividade.dia0808.DTO.ResultadoDTO;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ValorService {

    private List<Double> valores = new ArrayList<>();

    public void adicionarValor(double valor) {
        valores.add(valor);
    }

    public ResultadoDTO calcularResultado() {
        if (valores.isEmpty()) {
            throw new RuntimeException("Nenhum valor foi inserido ainda.");
        }

        ResultadoDTO resultado = new ResultadoDTO();
        resultado.setMedia(calcularMedia(valores));
        resultado.setDesvioPadrao(calcularDesvioPadrao(valores, resultado.getMedia()));
        resultado.setMediana(calcularMediana(valores));
        resultado.setQuantidade(valores.size());
        return resultado;
    }

    private double calcularMedia(List<Double> valores) {
        double soma = 0;
        for (Double valor : valores) {
            soma += valor;
        }
        return soma / valores.size();
    }

    private double calcularDesvioPadrao(List<Double> valores, double media) {
        double somaDasDiferencasAoQuadrado = 0;
        for (Double valor : valores) {
            double diferenca = valor - media;
            somaDasDiferencasAoQuadrado += diferenca * diferenca;
        }
        return Math.sqrt(somaDasDiferencasAoQuadrado / valores.size());
    }

    private double calcularMediana(List<Double> valores) {
        Collections.sort(valores);
        int tamanho = valores.size();
        if (tamanho % 2 == 0) {
            int meio = tamanho / 2;
            return (valores.get(meio - 1) + valores.get(meio)) / 2;
        } else {
            return valores.get(tamanho / 2);
        }
    }
}