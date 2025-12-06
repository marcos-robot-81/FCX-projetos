package com.fcx.fcx.dynamo;

import java.util.Date;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Funcionario {
    
    // atributos
    private String matricola;
    private String nome;
    private String Qualtidade;
    private Date data;
    
    //Constructor
    public Funcionario() {

    }

    // get sets
     @DynamoDbPartitionKey
    public String getMatricola(){
        return matricola;
    }   
    public String getNome() {
        return this.nome;
    }
    public String getQualtidade() {
        return this.Qualtidade;
    }
    public Date getData() {
        return this.data;
    }
    // set sets
    public void setMatricola(String matricola){
        this.matricola = matricola;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setQualtidade(String Qualtidade) {
        this.Qualtidade = Qualtidade;
    }
    public void setData(Date data) {
        this.data = data;
    }



}
