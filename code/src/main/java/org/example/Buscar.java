package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Buscar implements Runnable {
    private String arquivo;
    private String pesquisa;
    private List<Integer> resultadoLinha;
    private List<String> resultadoNome;
    private int ocorrencias;
    private long tempoExecucao;

    public Buscar(String arquivo, String pesquisa) {
        this.arquivo = arquivo;
        setPesquisa(pesquisa);
        resultadoLinha = new ArrayList<>();
        resultadoNome = new ArrayList<>();
    }

    public void setPesquisa(String pesquisa) {
        if (Objects.equals(pesquisa, "")) {
            throw new IllegalArgumentException("Digite um nome para pesquisa");
        }
        this.pesquisa = pesquisa;
    }

    public void buscar() {
        long tempoInicio = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            int numLinha = 0;

            while ((linha = reader.readLine()) != null) {
                numLinha++;
                if (linha.contains(pesquisa)) {
                    resultadoNome.add(linha);
                    resultadoLinha.add(numLinha);
                    ocorrencias++;
                }
            }
        } catch (IOException e) {
            IO.println("Erro ao processar o arquivo:\n" +
                    arquivo +
                    "\n\n" +
                    "Motivo: " + e.getMessage() + "\n");
        }
        long tempoFinal = System.currentTimeMillis();
        tempoExecucao = tempoFinal - tempoInicio;
    }

    public int getOcorrencias() {
        return ocorrencias;
    }

    public void printResultado() {
        if (ocorrencias > 0) {
            Interface.resultado(arquivo, resultadoLinha, resultadoNome);
        }
    }

    public void printResumo() {
        Interface.resumo(arquivo, ocorrencias, tempoExecucao);
    }

    @Override
    public void run() {
        buscar();
    }
}
