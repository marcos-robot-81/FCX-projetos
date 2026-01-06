# v -0.1.5
terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}


# --- 1. Security Group ---
resource "aws_security_group" "permitir_acesso" {
  name        = "permitir_ssh_http_java"
  description = "Libera SSH e HTTP"

  ingress {
    description = "SSH"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "Aplicacao Java"
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    description = "Saida para Internet (Necessario para falar com DynamoDB)"
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# --- 2. IAM Role (O Crachá) ---
resource "aws_iam_role" "role_ec2_admin_dynamo" {
  name = "role_java_cria_tabelas"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [{
      Action = "sts:AssumeRole"
      Effect = "Allow"
      Principal = {
        Service = "ec2.amazonaws.com"
      }
    }]
  })
}

# --- 3. Permissões (Policy) ---
# AQUI MUDOU: Damos permissão para CRIAR tabelas também
resource "aws_iam_role_policy" "politica_admin_dynamo" {
  name = "politica_full_dynamo"
  role = aws_iam_role.role_ec2_admin_dynamo.id

  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect = "Allow"
        Action = [
          "dynamodb:*" # PERIGO: Isso da acesso total ao DynamoDB. Para dev ok.
        ]
        Resource = "*" # Como a tabela nao existe ainda, permitimos em todas
      }
    ]
  })
}

# --- 4. Instance Profile ---
resource "aws_iam_instance_profile" "perfil_ec2_admin" {
  name = "perfil_ec2_java_admin"
  role = aws_iam_role.role_ec2_admin_dynamo.name
}

# --- 5. Dados da Imagem (Amazon Linux) ---
data "aws_ami" "amazon_linux" {
  most_recent = true
  owners      = ["amazon"]
  filter {
    name   = "name"
    values = ["al2023-ami-2023.*-x86_64"]
  }
}

# --- 6. Chave SSH ---
resource "aws_key_pair" "chave_deploy" {
  key_name   = "key_fcx-server-v2" # Mudei o nome caso o anterior ja exista
  public_key = file("/home/marcos/.ssh/id_ed25519.pub")
}

# --- 7. Instância EC2 ---
resource "aws_instance" "CN1" {
  ami           = data.aws_ami.amazon_linux.id
  instance_type = "t2.micro"
  key_name      = aws_key_pair.chave_deploy.key_name
  
  vpc_security_group_ids = [aws_security_group.permitir_acesso.id]
  iam_instance_profile   = aws_iam_instance_profile.perfil_ec2_admin.name

  tags = {
    Name = "Server_Java_FCX" 
  }

  # Não tem mais depends_on, pois a tabela não existe no Terraform
  
  user_data = <<-EOF
              #!/bin/bash
              dnf update -y
              dnf install -y docker
              systemctl start docker
              systemctl enable docker
              usermod -aG docker ec2-user
              EOF
}

output "ip_publico" {
  value = aws_instance.CN1.public_ip
}