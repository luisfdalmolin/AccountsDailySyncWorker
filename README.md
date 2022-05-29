# AccountsDailySyncWorker
Simulacro de um serviço diário de sincronização de contas com o Banco Central do Brasil

## Descrição
Utilitário de linha de comando feito em Java que recebe um arquivo CSV com dados fictícios de contas de banco e envia as mesmas para um serviço também fictício de sincronização do Banco Central.
Após a validação de cada conta por este serviço será gerado um novo CSV na pasta _home_ do usuário, informando quais contas obtiveram sucesso e quais falharam na sincronização.

## Tecnologias Utilizadas
- Java 13
- Maven 3.8.5

## Utilização

### Compilar
Para compilar o projeto e gerar o .jar basta executar o comando abaixo na raiz do projeto:
```shell
mvn package
```
Ao compilar, o projeto irá também executar os testes automatizados da aplicação.

### Executar
Para executar o projeto basta invocar o .jar gerado na pasta _bin_ passando como único parâmetro o arquivo CSV desejado.
```shell
java -jar bin\AccountsDailySyncWorker-1.0.jar <arquivo_csv>
```
Na pasta _data_ existem arquivos CSV de exemplo com casos de sucesso e falha ao sincronizar as contas.

## Conceitos Utilizados
- Implementada camada de domínio onde fica a representação em objetos das entidades pertinentes e também as interfaces que correspondem às regras de negócio da aplicação.
- Adicionados os serviços que implementam a camada de domínio.
- Implementado controlador que recebe como dependência os serviços responsáveis por ler e escrever os arquivos CSV e simular a sincronização de contas.
- Adicionado _factory_ para facilitar a injeção de dependências no controlador.
- Criadas classes utilitárias para padronizar ações genéricas.
- Adicionados testes do serviço de sincronização de contas.

## Resultado
Seguindo os conceitos citados foi possível construir uma aplicação que atende o requisito proposto e que ao mesmo tempo, devido à forma como foi estruturada, é pássivel de receber novas implementações sem perder o seu real propósito. 

A camada de domínio permite que uma mesma regra de negócio possa ser implementada em vários serviços, cada um deles operando de uma forma diferente porém sempre gerando um resultado final similar, como por exemplo mudar o formato de leitura e atualização das contas para um arquivo XLS/XLSX/JSON/XML ou até mesmo buscando e atualizando as contas a partir de um serviço de _storage_.

Usando um controlador para delegar as atribuções de cada serviço, fica mais fácil o tratamento da resposta que cada serviço pode nos devolver. Além disso, o uso dos _factories_ facilita a geração de instâncias de um mesmo controlador com diferentes implementações de uma mesma dependência.

As classes utilitárias responsáveis por formatações, _parsers_ e conversões, facilitam as situações onde é necessária alguma mudança no padrão de algum dado, pois todo tratamento que este dado recebe está centralizado em um mesmo lugar.

Por fim, o uso de testes dá maior segurança em momentos onde é necessária alguma refatoração muito grande ou alguma nova implementação que necessita a realização de mudanças no que já está em produção.
