#!/bin/bash

# Configurações
TABELA="Funcionario"
ENDPOINT="http://localhost:8000"
REGION="us-east-1"

echo "--- INICIANDO SETUP DO BANCO DE DADOS ---"

# 1. Tenta apagar a tabela antiga (Reset)
# O '2>/dev/null' serve para esconder o erro se a tabela não existir (primeira vez)
echo "1. Tentando apagar tabela antiga (se existir)..."
aws dynamodb delete-table \
    --table-name $TABELA \
    --endpoint-url $ENDPOINT \
    --region $REGION 2>/dev/null

# Espera 2 segundos para o DynamoDB processar a exclusão
sleep 2

# 2. Cria a Tabela
echo "2. Criando a tabela '$TABELA'..."
aws dynamodb create-table \
    --table-name $TABELA \
    --attribute-definitions AttributeName=matricula,AttributeType=S \
    --key-schema AttributeName=matricula,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
    --endpoint-url $ENDPOINT \
    --region $REGION

# Espera 1 segundo para a tabela ficar ativa
sleep 1

# 3. Insere Dados de Teste (Seed)
echo "3. Inserindo funcionários de teste..."

# Funcionário 1 (Você)
aws dynamodb put-item \
    --table-name $TABELA \
    --endpoint-url $ENDPOINT \
    --item '{
        "matricula": {"S": "FUNC-001"},
        "nome": {"S": "Marcos Silva"},
        "qualtidade": {"S": "Dev Senior"},
        "data": {"S": "2025-12-09"}
    }'

# Funcionário 2 (Teste extra)
aws dynamodb put-item \
    --table-name $TABELA \
    --endpoint-url $ENDPOINT \
    --item '{
        "matricula": {"S": "FUNC-002"},
        "nome": {"S": "Maria Gerente"},
        "qualtidade": {"S": "Gestão"},
        "data": {"S": "2025-12-10"}
    }'

echo "--- CONCLUÍDO! ---"
echo "Listando dados atuais:"
aws dynamodb scan --table-name $TABELA --endpoint-url $ENDPOINT
