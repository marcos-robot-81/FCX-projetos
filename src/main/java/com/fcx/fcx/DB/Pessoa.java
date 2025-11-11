package com.fcx.fcx.DB;

public class Pessoa {
    private String id;
    private String nome;
    private String quantidade;


    Pessoa(){}
    Pessoa(String id, String nome, String quantidade){
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
    }    

    public String getId(){
        return this.id;
    }
    public String getNome(){
        return this.nome;
    }
    public String getQuantidade(){
        return this.quantidade;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setNome(String nome){
        this.nome = nome;  
    }
    public void setQuantidade(String quantidade){
        this.quantidade = quantidade;
    }
    
}
