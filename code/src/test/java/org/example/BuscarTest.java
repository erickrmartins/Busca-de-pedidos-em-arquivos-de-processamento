package org.example;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuscarTest {

    @Test
    public void deveRetornarPrintResultado() {
        Buscar busca = new Buscar("signatarios-fev-2002.txt", "Carlos");
        Thread thread = new Thread(busca);
        thread.start();
        String resultado = "[signatarios-fev-2002.txt]\n" +
                "Linha 1: Carlos Eduardo Menezes\n" +
                "Linha 13: Antônio Carlos Teixeira\n";
        try {
            thread.join();
            assertEquals(busca.printResultado(), resultado);
        } catch (InterruptedException e) {
            e.printStackTrace();}
    }

    @Test
    public void deveRetornarNenhumaOcorrencia() {
        Buscar busca = new Buscar("signatarios-fev-2002.txt", "Erick");
        Thread thread = new Thread(busca);
        thread.start();
        try {
            thread.join();
            assertEquals(busca.getOcorrencias(), 0);
        } catch (InterruptedException e) {
            e.printStackTrace();}
    }

    @Test
    public void deveRetornar1Ocorrencia() {
        Buscar busca = new Buscar("signatarios-fev-2002.txt", "Carlos Eduardo Menezes");
        Thread thread = new Thread(busca);
        thread.start();
        try {
            thread.join();
            assertEquals(busca.getOcorrencias(), 1);
        } catch (InterruptedException e) {
            e.printStackTrace();}
    }

    @Test
    public void deveRetornarValorInvalidoPesquisa() {
        try {
            Buscar busca = new Buscar("signatarios-fev-2002.txt", "");
        }
        catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Digite um nome para pesquisa");
        }
    }


}