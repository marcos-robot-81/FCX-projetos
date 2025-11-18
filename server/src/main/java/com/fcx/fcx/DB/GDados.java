package com.fcx.fcx.DB;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
//import java.util.Date;

public class GDados {
    private ArrayList<Pessoa> pessoas = new ArrayList<>();

    // Método para ler dados de um arquivo CSV e retornar uma lista de pessoas
    public ArrayList<Pessoa> GetDados(String data) {
        File file = new File("src/main/java/com/fcx/fcx/DB/2000-01.csv");
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String linha = scan.nextLine();

                int vigula = linha.indexOf(",");

                String id = linha.substring(0, vigula);
                linha = linha.substring(vigula + 1, linha.length());
                vigula = linha.indexOf(",");

                String nome = linha.substring(0 + 1, vigula);
                String quantidade = linha.substring(vigula + 1, linha.length());

                Pessoa p = new Pessoa(id, nome, quantidade);
                this.pessoas.add(p);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return (pessoas);
    }

    public void odenaLitas(){}

    public ArrayList<Pessoa> boscarPorId(String id){
        ArrayList<Pessoa> resultado = new ArrayList<>();

        for (Pessoa p : this.pessoas) {
            if (p.getId().equals(id)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

}
