-- Criação da tabela usuario (que já está correta)
CREATE TABLE IF NOT EXISTS usuario (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       nome VARCHAR(255),
    rg VARCHAR(255),
    cpf VARCHAR(255),
    endereco VARCHAR(255),
    profissao VARCHAR(255),
    dtype VARCHAR(255)
    );

-- Criação da tabela entidades_empregadoras com relacionamento
CREATE TABLE IF NOT EXISTS entidades_empregadoras (
                                                      cliente_id BIGINT,
                                                      entidade_empregadora VARCHAR(255),
    FOREIGN KEY (cliente_id) REFERENCES usuario(id) ON DELETE CASCADE
    );

-- Criação da tabela usuario_rendimentos (supondo que esteja faltando)
CREATE TABLE IF NOT EXISTS usuario_rendimentos (
                                                   usuario_id BIGINT,
                                                   rendimentos FLOAT,
                                                   FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
    );

-- Criação da tabela pedido_aluguel (já existindo)
CREATE TABLE IF NOT EXISTS pedido_aluguel (
                                              id_pedido BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              cliente_id BIGINT,
                                              automovel_id BIGINT,
                                              data_inicio DATE,
                                              data_termino DATE,
                                              status VARCHAR(255),
    FOREIGN KEY (cliente_id) REFERENCES usuario(id) ON DELETE CASCADE
    );


-- Criação da tabela automovel (já existindo)
CREATE TABLE IF NOT EXISTS automovel (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         matricula VARCHAR(255) NOT NULL,
    ano INT NOT NULL,
    marca VARCHAR(255) NOT NULL,
    modelo VARCHAR(255) NOT NULL,
    placa VARCHAR(255) NOT NULL,
    cliente_id BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES usuario(id) ON DELETE SET NULL
    );
