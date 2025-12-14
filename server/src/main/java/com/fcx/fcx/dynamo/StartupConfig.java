package com.fcx.fcx.dynamo;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Component
public class StartupConfig implements ApplicationListener<ApplicationReadyEvent> {

    private final DynamoDbEnhancedClient enhancedClient;

    public StartupConfig(DynamoDbEnhancedClient enhancedClient) {
        this.enhancedClient = enhancedClient;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("--- ROBÔ DE SETUP: INICIANDO ---");
        
        try {
            // 1. Pega o molde exato da sua classe Funcionario
            DynamoDbTable<Funcionario> tabela = enhancedClient.table("Funcionario", TableSchema.fromBean(Funcionario.class));

            // 2. Tenta criar a tabela
            System.out.println("Verificando se a tabela 'Funcionario' existe...");
            tabela.createTable();
            
            System.out.println(">>> SUCESSO: Tabela CRIADA agora mesmo! Pode usar. <<<");

        } catch (Exception e) {
            // Se der erro dizendo que já existe, é uma boa notícia!
            if (e.getMessage().contains("ResourceInUseException")) {
                System.out.println(">>> AVISO: A tabela JÁ EXISTIA. Tudo pronto. <<<");
            } else {
                // Se for outro erro, mostra no log
                System.err.println("ERRO NO SETUP AUTOMÁTICO: " + e.getMessage());
                e.printStackTrace();
            }
        }
        System.out.println("--- ROBÔ DE SETUP: FIM ---");
    }
}