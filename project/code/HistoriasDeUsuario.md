# Descrição das Histórias do Usuário

## Introdução
O sistema de aluguel de automóveis é projetado para permitir que clientes e agentes interajam com o sistema de forma eficiente. Os clientes podem gerenciar pedidos de aluguel, enquanto os agentes e bancos podem avaliar e associar contratos de crédito a esses pedidos.

## Histórias do Usuário

### 1. Cadastro de Usuário
**Como** um novo usuário,  
**Eu quero** me cadastrar no sistema,  
**Para que** eu possa acessar e utilizar os serviços oferecidos.

#### Critérios de Aceitação:
- O usuário deve fornecer informações básicas, como nome, e-mail e senha.
- O sistema deve validar a informação e criar uma conta para o usuário.
- O usuário deve receber uma confirmação de cadastro por e-mail.

### 2. Login no Sistema
**Como** um usuário registrado,  
**Eu quero** fazer login no sistema,  
**Para que** eu possa acessar minhas funcionalidades e dados pessoais.

#### Critérios de Aceitação:
- O usuário deve fornecer e-mail e senha para autenticação.
- O sistema deve validar as credenciais e permitir o acesso se forem corretas.
- O usuário deve receber uma mensagem de erro se as credenciais estiverem incorretas.

### 3. Introdução de Pedido de Aluguel
**Como** um cliente,  
**Eu quero** introduzir um pedido de aluguel,  
**Para que** eu possa solicitar um veículo para alugar.

#### Critérios de Aceitação:
- O cliente deve preencher um formulário com os detalhes do pedido, como tipo de veículo e período de aluguel.
- O sistema deve criar um pedido de aluguel e associá-lo ao cliente.
- O cliente deve receber uma confirmação do pedido.

### 4. Modificação de Pedido de Aluguel
**Como** um cliente,  
**Eu quero** modificar um pedido de aluguel existente,  
**Para que** eu possa alterar os detalhes do veículo ou o período de aluguel.

#### Critérios de Aceitação:
- O cliente deve poder visualizar e alterar os detalhes do pedido.
- O sistema deve atualizar o pedido com as novas informações.
- O cliente deve receber uma confirmação da modificação.

### 5. Consulta de Pedido de Aluguel
**Como** um cliente,  
**Eu quero** consultar o status do meu pedido de aluguel,  
**Para que** eu possa verificar se o pedido foi processado e qual é o seu status atual.

#### Critérios de Aceitação:
- O cliente deve poder acessar uma lista de seus pedidos e visualizar os detalhes e status.
- O sistema deve fornecer informações atualizadas sobre o estado do pedido.

### 6. Cancelamento de Pedido de Aluguel
**Como** um cliente,  
**Eu quero** cancelar um pedido de aluguel,  
**Para que** eu não precise mais do veículo ou do serviço.

#### Critérios de Aceitação:
- O cliente deve poder solicitar o cancelamento de um pedido.
- O sistema deve atualizar o status do pedido para "cancelado".
- O cliente deve receber uma confirmação do cancelamento.

### 7. Análise de Pedido de Aluguel
**Como** um agente,  
**Eu quero** analisar pedidos de aluguel,  
**Para que** eu possa avaliar a viabilidade financeira e decidir se o pedido deve ser aprovado.

#### Critérios de Aceitação:
- O agente deve poder visualizar os detalhes dos pedidos de aluguel.
- O sistema deve permitir que o agente analise e faça uma recomendação sobre o pedido.
- O agente deve ter a opção de aprovar ou rejeitar o pedido.

### 8. Registro de Automóveis
**Como** um agente,  
**Eu quero** registrar automóveis no sistema,  
**Para que** os veículos estejam disponíveis para aluguel.

#### Critérios de Aceitação:
- O agente deve fornecer informações detalhadas sobre o veículo, como matrícula, ano, marca, modelo e placa.
- O sistema deve adicionar o veículo ao inventário e associá-lo ao proprietário (cliente, empresa ou banco).

### 9. Associação de Contrato de Crédito ao Aluguel
**Como** um banco,  
**Eu quero** associar um contrato de crédito a um pedido de aluguel,  
**Para que** o cliente possa financiar o aluguel do veículo.

#### Critérios de Aceitação:
- O banco deve poder criar e associar um contrato de crédito ao pedido de aluguel.
- O sistema deve vincular o contrato ao pedido e atualizar o status conforme necessário.

## Conclusão
Essas histórias de usuário fornecem uma visão clara dos principais recursos e funcionalidades que o sistema de aluguel de automóveis deve oferecer para atender às necessidades dos clientes, agentes e bancos.
