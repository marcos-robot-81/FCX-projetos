#!/bin/bash

# 1. Define onde os dados vão ficar (Caminho do Host)
# ATENÇÃO: Confirme se esse é o caminho que você criou no Passo 1
PASTA_DADOS="/home/marcos/Documents/MeusProgetos/java/FCX/dynamo-data"

# 2. Garante permissão total na pasta (para o Docker conseguir escrever)
# Isso evita o erro "Permission Denied" comum no Linux
chmod 777 "$PASTA_DADOS"

# 3. Remove qualquer versão antiga ou travada do container
echo "Limpando containers antigos..."
docker stop dynamo-banco 2>/dev/null
docker rm dynamo-banco 2>/dev/null

# 4. Inicia o novo container persistente
echo "Iniciando DynamoDB Persistente em: $PASTA_DADOS"

docker run -d \
  --name dynamo-banco \
  --restart always \
  -p 8000:8000 \
  -v "$PASTA_DADOS":/home/dynamodblocal/data \
  amazon/dynamodb-local \
  -jar DynamoDBLocal.jar -sharedDb -dbPath /home/dynamodblocal/data

echo "Sucesso! O banco está rodando e salvando em $PASTA_DADOS"
