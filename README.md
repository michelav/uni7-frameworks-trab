# Introdução

O texto abaixo trata do trabalho de conclusão da disciplina de _Programação com Frameworks e Componentes para Internet_
do Centro Universitário 7 de Setembro.


## RockNRollers SA
A RockNRollers SA é a mais nova startup com foco no público mais irado e gente boa do mundo: os fã de Rock. Ela pretende disseminar a cultura _headbanger_ por meio da distribuição de camisas estampadas com a temática Rock 'n Roll para lojas
de e-commerce que farão a revenda para o usuário final.

Sua equipe foi destacada para implementar uma parte do Sistema de Controle de Distribuição da empresa. Vocês
serão responsáveis por implementar os seguintes processos: o pedido de distribuição e encaminhamento para produção de novas
camisas.

### Pedido de distribuição
No pedido de distribuição, uma loja pode solicitar o envio de blusas estampadas diversas. O pedido deve informar:

- O nome da loja cliente (Texto alfanumérico com 20 caracteres de tamanho máximo)
- A Banda da estampa (Texto alfanumérico com 20 caracteres de tamanho máximo)
- O tamanho da camisa (P, M ou G)
- A quantidade de camisas

Durante a criação do pedido, o sistema deve checar se há camisas suficientes em estoque para o tamanho assinalado. Caso
as camisas já estejam no estoque o pedido deve ser _Processado_ . Do contrário, o pedido será _Criado_ com algumas
pendências (basicamente a lista de camisas faltantes para completar o pedido). A lista de pendências é encaminhada
para a Produção de novas camisas. O controle de estoque aguarda a confirmação das camisas produzidas para baixar as
pendências e _Confirmar_ o pedido. Após a confirmação do Pedido, o estoque é atualizado.

### Produção de Novas Camisas
A Produção de Novas Camisas é um processo complexo e longo que é disparado durante o Pedido de Distribuição. Após receber
a lista de pendências, a produção de novas camisas é iniciada. Ela retorna a confirmação de camisas produzidas para o
controle de estoque.

## Principais 'Conceitos'

**Camisa**

Camisas que serão controladas no Estoque.

Info | Descrição
---- |---------------------------------------
nome | Nome da banda da estampa (20 caracteres)
quantidade | quantidade de camisas em estoque (inteiro)
tamanho | Tamanho da camisa (p, m ou g)

**Pedido**

A requisição de distriuição oriunda das lojas de revenda.

Info | Descrição
------|----------------------------------------
Loja cliente | Nome da loja (20 caracteres)
camisa | Noma da banda da estampa (20 caracteres)
quantidade | quantidade de camisas do pedido (inteiro)
tamanho | Tamanho da camisa (p, m ou g)
situação | Situação do Pedido (Criado, Cancelado, Processado)

**Pendencia**

Pendências que impedem o processamento do pedido. No caso, a quantidade de camisas faltantes.

Info | Descrição
-----|-----------------
Pedido | Identificador do Pedido
camisa | estampa a ser produzida
tamanho | Tamanho da camisa (p, m ou g)
situacao | Situação da Pendência (aberta, resolvida)

**Producao**

Info | Descrição
-----|-----------------
Pedido | Identificador do Pedido
camisa | estampa a ser produzida
tamanho | Tamanho da camisa (p, m ou g)
quantidade | quantidade de camisas que devem ser produzidas
situacao | Situação da Produção (Solicitado, Cancelado, Produzido)


## Trabalho
#### Descrição

Implementar utilizando as tecnologias JavaEE os Processos: Pedido de Distribuição e Controle de Produção.
Siga as recomendações abaixo:

**1. Pedido de Distriuição:**
Uma aplicação Web implementada na plataforma JavaEE e disponível na URL: **http://<URL>:8080/dist-api/** .
A criação e listagem de pedidos serão expostos como serviços REST conforme a tabela abaixo:

URI             | Método | Descrição
----------------|--------|---------------------------------------------------
pedidos         | POST   | Submissão de pedido por meio de um documento JSON
pedidos         | GET    | Lista de todos os pedidos por meio de um documento JSON
pedidos/{id}    | GET    | Listagem de um pedido identificado por seu ID
pedidos/{id}/cancelar  | POST   | Cancelar um pedido criado. Pedidos processados não podem ser cancelados

O retorno da listagem deve ser um documento JSON com a informação do pedido.
O encaminhamento das pendências do pedido será realizado por meio de uma fila JMS indicando o que deve ser produzido.
O controle de produção confirmará a Produção por meio de uma fila de retorno.

**2. Controle de Produção de Novas Camisas**
O controle de Produção será implementado como uma outra aplicação Java EE que receberá um pedido de Produção por
meio de uma fila e o salvará em BD. Para simular a execução do processo de produção, a aplicação exporá uma API REST
permitindo o processamento do pedido: **http://prd-api:8080/---**.

URI             | Método | Descrição
----------------|--------|---------------------------------------------------
producao         | POST   | Submissão de pedido de produção por meio de um documento JSON
producao         | GET    | Lista de todos os pedidos de produção por meio de um documento JSON
producao/{id}    | GET    | Listagem de um pedido de produção identificado por seu ID
producao/{id}/cancelar  | POST   | Cancelar um pedido criado. Pedidos produzidos não podem ser cancelados
producao/{id}/executar  | POST   | Produzir as camisas do pedido.

A execução de um pedido de produção deve ser sucedido por uma notificação à aplicação de distribuição informando que
as camisas foram produzidas.

### Detalhes Técnicos

As aplicações estão parcialmente escritas e serão completadas pela equipe. A pasta [rnrprojs](/rnrprojs) contém um
conjunto de projetos MAVEN organizados conforme a tabela abaixo:

Pasta      | Descrição
-----------|------------------------------------------------
rnrprojs   | Projeto raiz Maven
dist-api   | Projeto dinâmico Web onde está a API para a aplicação Distribuídora
dist-biz   | Projeto EJB onde estão as regras de negócio e entidades para a aplicação Distribuidora
dist-app   | Projeto EAR da aplicação distribuidora
prd-api    | Projeto dinâmico Web onde está a API para a aplicação de Produção
prd-biz    | Projeto EJB onde estão as regras de negócio e entidades para a aplicação de Produção
prd-app    | Projeto EAR da aplicação de Produção
conf       | Arquivos de configuração e inicialização dos BDs e Servidor de aplicação

Cada aplicação (que pode ser implantada no mesmo servidor de aplicação) possui um banco de dados específico.
Para facilitar testes e validação do que foi implementado, há um conjunto de arquivos de definição de container e
composição (_Dockerfiles e docker-compose_) de forma que o ambiente possa ser reproduzido por qualquer equipe sem
dificuldade. Caso desejem utilizar esse recurso, instalem o Docker CE e Docker Compose seguindo as instruções disponíveis
no [site do produto](https://www.docker.com/).

#### Importante

+ *Usuários e Senha:*  As configurações de usuário, senha do BD estão disponíveis como arquivos de configuração do Docker
(_Dockerfile-dist-db_ e _Dockerfile-prd-db_) na forma de variáveis (_POSTGRES_USER_ e _POSTGRES_PASSWORD_). Caso desejem
alterá-las, lembrem-se de reproduzir a mudança no arquivo de configuração do servidor de aplicação que está no caminho
[rnrprojs/dist/](rnrprojs/dist/).

+ *Início do Ambiente, Construção e Deploy:* Use o Docker Compose para iniciar o ambiente configurado e o Maven para
preparar os binários. O deploy acontecerá por meio de cópia de arquivos no sistema de arquivos. Você deverá escolher uma
pasta onde copiará os binários para testar e informá-la ao Compose por meio do arquivo de configuração
(_docker-compose.yml_). No exemplo abaixo, você está mapeando a área de deployments do container docker
(_/opt/wildfly/standalone/deployments_) para uma área do seu disco local fora do container (_/tmp/deployments_). Essa
será a área de deploy pós-construção.

```yaml
volumes:
  - "/tmp/deployments:/opt/wildfly/standalone/deployments:rw"
depends_on:
  - dist-db
  - prd-db
```
Segue Um exemplo básico do ciclo de vida de construção e deploy da aplicação:

```bash
# Feito apenas uma vez para criar a area de deploy
mkdir -p /tmp/deployments

mvn clean package

cp dist-app/target/dist-app.ear /tmp/deployments
```

O processo de deploy informa por meio de um arquivo se houve sucesso (deployed) ou não (failed).

### Entrega
A Entrega final consistirá:

1. Do código-fonte atualizado na pasta dos projetos;
2. Do arquivo DISCUSSAO.md atualizado com as respostas da equipe para a discussão.

A entrega deverá ser realizada no repositório até 05/11 23:59:59. Qualquer atraso implicará em redução da nota final.

### Avaliação
- Pedido de Distribuição (4,5 pt);
- Controle de Produção (4,5 pt);
- Discussão (1 pt).