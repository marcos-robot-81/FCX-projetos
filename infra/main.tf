# Configuração do Provider (Onde o Terraform vai atuar)
provider "aws" {
  region                      = "us-east-1"
  access_key                  = "fake"     # Senha falsa pois é local
  secret_key                  = "fake"
  skip_credentials_validation = true       # Não validar senha na AWS real
  skip_requesting_account_id  = true       # Não pedir ID da conta real
  
  # O SEGREDO: Apontar para o seu Docker
  endpoints {
    dynamodb = "http://localhost:8000"
  }
}
