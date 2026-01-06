package com.fcx.fcx.dynamo;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Funcionario {
    
    // Nomes em Português Correto
    private String matricula;
    private String nome;
    private String cargo; // Antes era "qualtidade"
    private String data;
    
    public Funcionario() {
    }
    public Funcionario(String matricula) {
        this.matricula = matricula;
    }
    public Funcionario(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;

    }
    public Funcionario(String matricula, String nome, String cargo) {
        this.matricula = matricula;
        this.nome = nome;
        this.cargo = cargo;
    }
    public Funcionario(String matricula, String nome, String cargo, String data) {
        this.matricula = matricula;
        this.nome = nome;
        this.cargo = cargo;
        this.data = data;
    }

    // --- GETTERS E SETTERS ---

    // A Chave Primária (Partition Key)
    @DynamoDbPartitionKey
    public String getMatricula(){
        return matricula;
    }   
    public void setMatricula(String matricula){
        this.matricula = matricula;
    }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return this.cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getData() {
        return this.data;
    }
    public void setData(String data) {
        this.data = data;
    }
}