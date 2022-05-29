# AccountsDailySyncWorker
Simulacro de um serviço diário de sincronização de contas com o Banco Central do Brasil

## Descrição
Utilitário de linha de comando feito em Java que recebe um arquivo CSV com dados fictícios de contas de banco e envia as mesmas para um serviço também fictício de sincronização do Banco Central.
Após a validação de cada conta por este serviço será gerado um novo CSV na pasta _home_ do usuário informando quais contas obtiveram sucesso e quais falharam na sincronização.

## Tecnologias Utilizadas
- Java 13
- Maven 3.8.5

## Utilização

### Compilar
Para compilar o projeto e gerar o .jar basta executar o comando abaixo na raiz do projeto:
```shell
mvn package
```
Ao compilar o projeto irá também executar os testes atuomatizados da aplicação.

### Executar
Para executar o projeto basta invocar o .jar gerado na pasta _bin_ passado como único parâmetro o arquivo CSV desejado.
```shell
java -jar bin\AccountsDailySyncWorker-1.0.jar <arquivo_csv>
```
Na pasta _data_ existem arquivos CSV de exemplo com casos de sucesso e falha ao sincronizar as contas.

