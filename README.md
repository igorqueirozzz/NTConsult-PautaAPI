# NTConsult Pauta API REST
## _API REST Para votação de pautas._

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)]()

## Recursos

- Cadastre Pautas com uma breve descrição.
- Abra sessões de votos nas pautas cadastradas definindo um tempo de duração.
- Consulte o status de cada pauta e a quantidade de votos recebidos.
- Persista a quatidade de votos recebidos em cada pauta no banco de dados.
## Escolha técnica

Tecnologias utilizadas no projeto.

- [Java](https://docs.oracle.com/en/java/) - linguagem de programação.
- [Spring Boot](https://spring.io/projects/spring-boot) - Gerenciador do projeto.
- [Maven](https://maven.apache.org/) - Gerenciador de dependências.
- [Hibernate + JPA](https://hibernate.org/) - Persistência de dados.
- [Swagger](https://swagger.io/) - Documentação da API.
- [PostgreSQL](https://www.postgresql.org/) - Banco de dados.

## Requisitos

O projeto requer:
[Java JDK ](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html) 11.
[Apache Maven](https://maven.apache.org/),
[PostgreSQL](https://www.postgresql.org/download/) 11+

Crie um banco de dados no postgreSQL vá para src/main/resources e edite o arquivo "application-dev.properties" inserindo as informações do banco de dados postgreSQL como o exemplo abaixo:

```sh
spring.datasource.url=jdbc:postgresql://localhost:5432/SEUBANCO
spring.datasource.username=**USUARIO**
spring.datasource.password=**SENHA**
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.initialization-mode=always
# Hibernate properties
hibernate.hbm2ddl.auto=update;
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
```

Acesse a pasta raiz do projeto utilizando o prompt de comando e execute o projeto com o comando ```mvn spring-boot:run```

## Utilização

Acesse a documentação em ```http://localhost:8080/swagger-ui.html``` e veja todos os métodos disponíveis para uso.
