
# EcoColeta ♻️

API REST para gerenciamento de coleta seletiva, desenvolvida com Java e Spring Boot como projeto acadêmico da FIAP — Global Solution 2026.

## 📌 Sobre o projeto

O EcoColeta tem como objetivo apoiar a organização da coleta seletiva por meio do gerenciamento de pontos de coleta, tipos de resíduos e registros de coletas.

A aplicação permite cadastrar, consultar, atualizar e excluir informações, mantendo o relacionamento entre pontos de coleta, resíduos e coletas realizadas.

## 🛠️ Tecnologias utilizadas

- Java 17
- Spring Boot 4.1.2
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database
- Flyway
- Maven
- Swagger / OpenAPI
- JUnit e Mockito

## 🏗️ Estrutura do projeto

```text
src/
├── main/
│   ├── java/br/com/fiap/ecocoleta/
│   │   ├── controller/
│   │   ├── model/
│   │   ├── repository/
│   │   ├── service/
│   │   └── EcocoletaApplication.java
│   └── resources/
│       ├── db/migration/
│       │   └── V1__criar_tabelas.sql
│       └── application.properties
└── test/
    └── java/br/com/fiap/ecocoleta/
        └── controller/
```

## ⚙️ Pré-requisitos

- JDK 17 ou superior
- Git
- Maven (opcional, pois o projeto possui Maven Wrapper)

## 🚀 Como executar

### 1. Clonar o repositório

```bash
git https://github.com/lzFelipee/EcoColeta
```

### 2. Entrar na pasta do projeto

```bash
cd ecocoleta
```

### 3. Executar a aplicação

**Windows PowerShell:**

```powershell
.\mvnw.cmd spring-boot:run
```

**Linux ou macOS:**

```bash
./mvnw spring-boot:run
```

A aplicação será iniciada em:

`http://localhost:8080`

## 📚 Documentação da API

A documentação interativa dos endpoints é disponibilizada pelo Swagger.

Após iniciar a aplicação, acesse:

- [Swagger UI](http://localhost:8080/swagger-ui.html)
- OpenAPI: `http://localhost:8080/v3/api-docs`

## 🔗 Endpoints disponíveis

### Pontos de coleta

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/pontos-coleta` | Listar pontos de coleta |
| GET | `/api/pontos-coleta/{id}` | Buscar ponto por ID |
| POST | `/api/pontos-coleta` | Cadastrar ponto |
| PUT | `/api/pontos-coleta/{id}` | Atualizar ponto |
| DELETE | `/api/pontos-coleta/{id}` | Excluir ponto |

### Resíduos

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/residuos` | Listar resíduos |
| GET | `/api/residuos/{id}` | Buscar resíduo por ID |
| POST | `/api/residuos` | Cadastrar resíduo |
| PUT | `/api/residuos/{id}` | Atualizar resíduo |
| DELETE | `/api/residuos/{id}` | Excluir resíduo |

### Coletas

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/coletas` | Listar coletas |
| GET | `/api/coletas/{id}` | Buscar coleta por ID |
| POST | `/api/coletas` | Registrar coleta |
| PUT | `/api/coletas/{id}` | Atualizar coleta |
| DELETE | `/api/coletas/{id}` | Excluir coleta |

## 🗄️ Banco de dados

O projeto utiliza o H2 Database com persistência em arquivo.

- URL: `jdbc:h2:file:./data/ecocoleta`
- Usuário: `sa`
- Senha: em branco

As migrações do banco são gerenciadas pelo Flyway.

### Acessar o H2 Console

Com a aplicação em execução:

`http://localhost:8080/h2-console`

Utilize a URL JDBC:

`jdbc:h2:file:./data/ecocoleta`

## 🧪 Testes automatizados

O projeto possui testes automatizados utilizando JUnit, Mockito e MockMvc.

Para executar:

**Windows:**

```powershell
.\mvnw.cmd clean test
```

**Linux/macOS:**

```bash
./mvnw clean test
```

### Resultado dos testes

| Classe | Testes aprovados |
|---|---:|
| ColetaControllerTest | 5 |
| PontoColetaControllerTest | 4 |
| ResiduoControllerTest | 4 |
| EcocoletaApplicationTests | 1 |
| **Total** | **14** |

## 👥 Integrantes

- Luiz Felipe Motta da Silva - 559126
- Nicolas Lorenzo Ferreira da Silva -557962
- Pedro Henrique Faim dos Santos - 557440


## 🎓 Instituição

**FIAP — Faculdade de Informática e Administração Paulista**

- Curso: Engenharia de Software
- Disciplina: Arquitetura Orientada a Serviços (SOA) e Web Services
- Projeto: Global Solution 2026 — CitizenTech
