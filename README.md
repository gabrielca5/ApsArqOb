# 🎧 SpotiFake - API REST Mini Spotify

API em Spring Boot que simula funcionalidades de streaming de música.

## ⚡ Como Rodar

```bash
cd ronaldo
./mvnw spring-boot:run
```

```powershell
Set-Location "C:\Users\gabri\Downloads\ronaldo"
.\mvnw.cmd spring-boot:run
```

API disponível em: `http://localhost:8080`

## 🗂️ Estrutura do Projeto

- Projeto Maven único na raiz
- Único código-fonte em `src/`
- Banco em memória H2 configurado em `src/main/resources/application.properties`
- Console H2 habilitado em `http://localhost:8080/h2-console`

## 📚 Entidades

- **Usuários** - Gerenciamento de contas (CRUD)
- **Artistas** - Cadastro de artistas (CRUD)
- **Álbuns** - Cadastro de álbuns (CRUD)
- **Músicas** - Cadastro de músicas com reprodução
- **Playlists** - Playlists do usuário com múltiplas músicas
- **Gêneros** - Classificação musical (CRUD)

## 🎯 Funcionalidades

- CRUD completo para todas as entidades
- Reprodução de músicas com contador (POST /musicas/{id}/reproduzir + header X-USER-ID)
- Top 10 músicas mais reproduzidas (GET /relatorios/top-musicas)
- Adicionar/remover músicas em playlists (apenas dono)
- Exclusão lógica de usuários
- Bloqueio de usuários inativos

## 📮 Postman

Importar: `SpotiFake-Postman.json` (38 rotas pré-configuradas)

## 🛠️ Stack

- Java 17
- Spring Boot 4.0.3
- Maven

**Pronto para entrega!** ✅
