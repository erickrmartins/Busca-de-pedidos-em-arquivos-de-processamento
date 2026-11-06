package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        String pesquisa;
        int ocorrencias = 0;

        List<String> arquivos = new ArrayList();
        arquivos.add("signatarios-fev-2002.txt");
        arquivos.add("signatarios-fev-2016.txt");
        arquivos.add("signatarios-out-2014.txt");

        Thread[] threads = new Thread[arquivos.size()];
        List<Buscar> buscas = new ArrayList<>();

        pesquisa = Interface.start();
        long tempoInicio = System.currentTimeMillis();

        for (int i = 0; i < arquivos.size(); i++) {
            Buscar busca = new Buscar(arquivos.get(i), pesquisa);
            buscas.add(busca);
            threads[i] = new Thread(busca);
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Buscar busca : buscas) {
            ocorrencias += busca.getOcorrencias();
            busca.printResultado();
        }

        IO.println("----------------------------------------\n" +
                "RESUMO DA PESQUISA\n" +
                "----------------------------------------\n");

        for (Buscar busca : buscas) {
            busca.printResumo();
        }

        long tempoFim = System.currentTimeMillis();
        Interface.end(ocorrencias, tempoFim-tempoInicio);

    }
}
