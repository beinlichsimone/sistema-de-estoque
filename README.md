<h2>Estoque API Manager</h2>

O objetivo do projeto Estoque API Manager é disponibilizar uma API para cadastro de produtos e controle de estoque através de uma API REST.

Para executar o projeto no terminal, digite o seguinte comando:

```shell script
mvn spring-boot:run 
```

Após executar o comando acima, basta apenas abrir o seguinte endereço e visualizar a execução do projeto:

```
http://localhost:8080/api/produtos
http://localhost:8080/api/movimentoEstoque
```

Para abrir a documentação (disponibilizada através do Swagger 2) de todas as operações implementadas com o padrão arquitetural REST, acesse o seguinte link abaixo:

```
http://localhost:8080/swagger-ui.html#/
```

São necessários os seguintes pré-requisitos para a execução do projeto desenvolvido durante a aula:

* Java 17 ou versões superiores.
* Maven 3.8.1 ou versões superiores.
* SDKMan! para o gerenciamento de múltiplcas versões de Java, Maven e Spring Boot.
* Intellj IDEA Community Edition ou sua IDE favorita.
* Controle de versão GIT instalado na sua máquina.
* Swagger 2 para a documentação de todos os endpoints desenvolvidos dentro do projeto.
* Postman para execução de testes de integração para a validação de ponta a ponta da API.
* Banco de dados PostgreSQL 14.

