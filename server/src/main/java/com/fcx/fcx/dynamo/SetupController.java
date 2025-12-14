package com.fcx.fcx.dynamo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

@RestController
public class SetupController {

    private final DynamoDbClient dynamoDbClient;

    public SetupController(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @GetMapping("/resetar-tabela")
    public String resetarTabela() {
        String tableName = "Funcionario"; // Nome exato da Classe
        StringBuilder log = new StringBuilder();

        // 1. Tenta Deletar a tabela antiga
        try {
            log.append("1. Tentando apagar tabela antiga '").append(tableName).append("'... ");
            DeleteTableRequest deleteRequest = DeleteTableRequest.builder().tableName(tableName).build();
            dynamoDbClient.deleteTable(deleteRequest);
            log.append("SUCESSO (Apagada).\n");
            
            // Espera um pouco pro DynamoDB processar
            Thread.sleep(2000); 
            
        } catch (ResourceNotFoundException e) {
            log.append("Não existia (OK).\n");
        } catch (Exception e) {
            log.append("Erro ao apagar: ").append(e.getMessage()).append("\n");
        }

        // 2. Cria a Tabela Nova (Com a estrutura CERTA: matricula)
        try {
            log.append("2. Criando nova tabela '").append(tableName).append("'... ");
            
            CreateTableRequest request = CreateTableRequest.builder()
                    .tableName(tableName)
                    .attributeDefinitions(
                            // ATENÇÃO: Aqui definimos que no banco é 'matricula' (com U)
                            AttributeDefinition.builder().attributeName("matricula").attributeType(ScalarAttributeType.S).build()
                    )
                    .keySchema(
                            KeySchemaElement.builder().attributeName("matricula").keyType(KeyType.HASH).build()
                    )
                    .provisionedThroughput(
                            ProvisionedThroughput.builder().readCapacityUnits(5L).writeCapacityUnits(5L).build()
                    )
                    .build();

            dynamoDbClient.createTable(request);
            log.append("SUCESSO! Tabela criada.\n");
            
        } catch (Exception e) {
            return log.toString() + "ERRO FATAL AO CRIAR: " + e.getMessage();
        }

        return log.toString() + "CONCLUSÃO: Tabela resetada. Pode testar o POST agora.";
    }
}