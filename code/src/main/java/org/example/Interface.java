package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interface {

    public static String start() {
        Scanner scanner = new Scanner(System.in);

        IO.println("========================================\n" +
                "       SISTEMA DE BUSCA DE NOMES\n" +
                "========================================");
        IO.println("\nDigite um nome para pesquisa: ");
        String pesquisa = scanner.nextLine();

        IO.println("\nIniciando pesquisa nos arquivos...\n");

        return pesquisa;
    }

    public static String end(int ocorrencias, long tempo) {
        String resultado = "----------------------------------------\n" +
                "Total de ocorrências: " +  ocorrencias + "\n" +
                "\n" +
                "Todas as buscas foram finalizadas.\n" +
                "\n" +
                "Tempo execução: " + tempo + " ms\n" +
                "========================================\n"
        IO.println(resultado);

        return resultado;
    }

    public static void resultado(String arquivo, List<Integer> linhas, List<String> nomes) {
        IO.println("[" + arquivo + "]");
        for (int i = 0; i < linhas.size(); i++) {
            IO.println("Linha " + linhas.get(i) + ": " + nomes.get(i));
        }
        IO.println();
    }

    public static void resumo(String arquivo, int ocorrencias, long tempo) {
        IO.println("Arquivo: " + arquivo);
        IO.println("Ocorrências: " + ocorrencias);
        IO.println("Tempo total: " + tempo + " ms\n");
    }
}
