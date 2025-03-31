-- Inserir Clientes
INSERT INTO usuario (id, nome, rg, cpf, endereco, profissao, dtype)
VALUES (1000, 'João Silva', 'MG-12345678', '123.456.789-01', 'Rua das Flores, 123', 'Engenheiro', 'Cliente'),
       (2000, 'Maria Oliveira', 'MG-87654321', '987.654.321-09', 'Av. Central, 456', 'Médica', 'Cliente'),
       (3000, 'Pedro Souza', 'MG-11223344', '223.344.556-77', 'Rua Nova, 789', 'Advogado', 'Cliente');

-- Inserir Entidades Empregadoras (clientes)
INSERT INTO entidades_empregadoras (cliente_id, entidade_empregadora)
VALUES (1000, 'Empresa A'),
       (1000, 'Empresa B'),
       (2000, 'Hospital XYZ'),
       (3000, 'Escritório Souza e Associados');

-- Inserir Rendimentos (clientes)
INSERT INTO usuario_rendimentos (usuario_id, rendimentos)
VALUES (1000, 5500.00),
       (1000, 4500.00),
       (2000, 10000.00),
       (3000, 7500.00);

-- Inserir Automóveis
INSERT INTO automovel (id, matricula, ano, marca, modelo, placa, cliente_id)
VALUES (1000, 'ABC1234', 2020, 'Toyota', 'Corolla', 'XYZ-1234', 1000),
       (2000, 'DEF5678', 2019, 'Honda', 'Civic', 'ABC-5678', 2000),
       (3000, 'GHI9012', 2021, 'Ford', 'Focus', 'DEF-9012', 3000);

-- Inserir Pedidos de Aluguel
INSERT INTO pedido_aluguel (id_pedido, cliente_id, automovel_id, data_inicio, data_termino, status)
VALUES (1000, 1000, 1000, '2023-09-01', '2023-09-10', 'PENDENTE'),
       (2000, 2000, 2000, '2023-09-05', '2023-09-12', 'APROVADO'),
       (3000, 3000, 3000, '2023-09-08', '2023-09-15', 'RECUSADO');

