package com.fcx.fcx.dynamo;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

import java.util.UUID;

@Repository
public class FuncionarioRepository {

    private final DynamoDbTemplate dynamoDbTemplate;
    // ---
    public FuncionarioRepository(DynamoDbTemplate dynamoDbTemplate) {
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    public Funcionario salvar(Funcionario funcionario) {
        if (funcionario.getMatricola() == null) {
            funcionario.setMatricola(UUID.randomUUID().toString());
        }
        return dynamoDbTemplate.save(funcionario);
    }

    public boolean adcionaFuncionario(Funcionario funcionario) {
        if (funcionario.getMatricola() == null) {
            funcionario.setMatricola(UUID.randomUUID().toString());
        }
        while( buscarPorId(funcionario.getMatricola()).getMatricola().equals(funcionario.getMatricola()) ) {
            funcionario.setMatricola(UUID.randomUUID().toString());
        }
        dynamoDbTemplate.save(funcionario);
        return true;
    }

    public Funcionario buscarPorId(String matricola) {
        Key key = Key.builder().partitionValue(matricola).build();
        return dynamoDbTemplate.load(key, Funcionario.class);
    }

    public void deletar(String matricola) {
        Key key = Key.builder().partitionValue(matricola).build();
        dynamoDbTemplate.delete(key, Funcionario.class);
    }

    public PageIterable<Funcionario> listarTodos() {
        return dynamoDbTemplate.scanAll(Funcionario.class);
    }
}
