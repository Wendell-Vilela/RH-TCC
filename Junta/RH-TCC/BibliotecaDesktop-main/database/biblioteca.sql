-- Sistema de Gestao de Biblioteca - MySQL 5.7/8.0
-- Execute todo este arquivo no MySQL Workbench.

CREATE DATABASE IF NOT EXISTS biblioteca
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

USE biblioteca;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS emprestimo;
DROP TABLE IF EXISTS exemplar;
DROP TABLE IF EXISTS livro_autor;
DROP TABLE IF EXISTS livro;
DROP TABLE IF EXISTS autor;
DROP TABLE IF EXISTS editora;
DROP TABLE IF EXISTS categoria;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS usuario;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE usuario (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    login VARCHAR(50) NOT NULL,
    senha_hash CHAR(64) NOT NULL,
    senha_salt CHAR(32) NOT NULL,
    perfil ENUM('ADMIN', 'ATENDENTE') NOT NULL,
    ativo TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    UNIQUE KEY uk_usuario_login (login)
) ENGINE=InnoDB;

CREATE TABLE cliente (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(120) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    email VARCHAR(120),
    telefone VARCHAR(20),
    endereco VARCHAR(200),
    data_cadastro DATE NOT NULL,
    ativo TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    UNIQUE KEY uk_cliente_cpf (cpf),
    KEY ix_cliente_nome (nome)
) ENGINE=InnoDB;

CREATE TABLE autor (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(120) NOT NULL,
    nacionalidade VARCHAR(80),
    PRIMARY KEY (id),
    KEY ix_autor_nome (nome)
) ENGINE=InnoDB;

CREATE TABLE editora (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(120) NOT NULL,
    cidade VARCHAR(100),
    PRIMARY KEY (id),
    KEY ix_editora_nome (nome)
) ENGINE=InnoDB;

CREATE TABLE categoria (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    descricao VARCHAR(250),
    PRIMARY KEY (id),
    UNIQUE KEY uk_categoria_nome (nome)
) ENGINE=InnoDB;

CREATE TABLE livro (
    id INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(180) NOT NULL,
    isbn VARCHAR(20) NOT NULL,
    ano_publicacao INT NOT NULL,
    id_editora INT NOT NULL,
    id_categoria INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_livro_isbn (isbn),
    KEY ix_livro_titulo (titulo),
    CONSTRAINT fk_livro_editora FOREIGN KEY (id_editora)
        REFERENCES editora (id) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_livro_categoria FOREIGN KEY (id_categoria)
        REFERENCES categoria (id) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE livro_autor (
    id_livro INT NOT NULL,
    id_autor INT NOT NULL,
    PRIMARY KEY (id_livro, id_autor),
    CONSTRAINT fk_livro_autor_livro FOREIGN KEY (id_livro)
        REFERENCES livro (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_livro_autor_autor FOREIGN KEY (id_autor)
        REFERENCES autor (id) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE exemplar (
    id INT NOT NULL AUTO_INCREMENT,
    id_livro INT NOT NULL,
    codigo VARCHAR(30) NOT NULL,
    status ENUM('DISPONIVEL', 'EMPRESTADO', 'INATIVO') NOT NULL DEFAULT 'DISPONIVEL',
    PRIMARY KEY (id),
    UNIQUE KEY uk_exemplar_codigo (codigo),
    KEY ix_exemplar_livro_status (id_livro, status),
    CONSTRAINT fk_exemplar_livro FOREIGN KEY (id_livro)
        REFERENCES livro (id) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE emprestimo (
    id INT NOT NULL AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    id_exemplar INT NOT NULL,
    data_emprestimo DATE NOT NULL,
    data_prevista_devolucao DATE NOT NULL,
    data_devolucao DATE,
    status ENUM('ABERTO', 'DEVOLVIDO', 'ATRASADO') NOT NULL DEFAULT 'ABERTO',
    PRIMARY KEY (id),
    KEY ix_emprestimo_cliente (id_cliente),
    KEY ix_emprestimo_exemplar_status (id_exemplar, status),
    CONSTRAINT fk_emprestimo_cliente FOREIGN KEY (id_cliente)
        REFERENCES cliente (id) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_emprestimo_exemplar FOREIGN KEY (id_exemplar)
        REFERENCES exemplar (id) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

-- As senhas em texto puro aparecem somente no README para o primeiro acesso.
-- O banco recebe exclusivamente salt e hash SHA-256.
INSERT INTO usuario (nome, login, senha_hash, senha_salt, perfil, ativo) VALUES
('Administrador', 'admin', '0212b518b03b50cc62a0dadc9e897f48190e21e0af3ba93665b4b0085885e265', 'a1b2c3d4e5f60718293a4b5c6d7e8f90', 'ADMIN', 1),
('Atendente Biblioteca', 'atendente', 'dfa058b75dd5fcbb24720d30f5ed42e8e2e547451b2c6ab6e7156c2afa0530e2', '1029384756abcdef1029384756abcdef', 'ATENDENTE', 1);

INSERT INTO cliente (nome, cpf, email, telefone, endereco, data_cadastro, ativo) VALUES
('Maria Silva', '111.111.111-11', 'maria@exemplo.com', '(11) 99999-1111', 'Rua das Flores, 10', '2026-08-01', 1),
('Joao Santos', '222.222.222-22', 'joao@exemplo.com', '(11) 99999-2222', 'Avenida Central, 20', '2026-08-02', 1),
('Ana Oliveira', '333.333.333-33', 'ana@exemplo.com', '(11) 99999-3333', 'Rua do Sol, 30', '2026-08-03', 1),
('Carlos Souza', '444.444.444-44', 'carlos@exemplo.com', '(11) 99999-4444', 'Rua das Palmeiras, 40', '2026-08-04', 1),
('Luciana Costa', '555.555.555-55', 'luciana@exemplo.com', '(11) 99999-5555', 'Praca da Matriz, 50', '2026-08-05', 1);

INSERT INTO autor (nome, nacionalidade) VALUES
('Robert C. Martin', 'Estadunidense'),
('Deitel e Deitel', 'Estadunidense'),
('Machado de Assis', 'Brasileira'),
('George Orwell', 'Britanica'),
('Antoine de Saint-Exupery', 'Francesa');

INSERT INTO editora (nome, cidade) VALUES
('Alta Books', 'Rio de Janeiro'),
('Pearson', 'Sao Paulo'),
('Companhia das Letras', 'Sao Paulo');

INSERT INTO categoria (nome, descricao) VALUES
('Literatura', 'Romances, contos e obras literarias'),
('Tecnologia', 'Computacao e desenvolvimento de sistemas'),
('Historia', 'Obras de historia e sociedade'),
('Ciencia', 'Divulgacao e fundamentos cientificos'),
('Educacao', 'Didatica, aprendizagem e formacao docente');

INSERT INTO livro (titulo, isbn, ano_publicacao, id_editora, id_categoria) VALUES
('Codigo Limpo', '9788576082675', 2009, 1, 2),
('Java: Como Programar', '9788543004792', 2015, 2, 2),
('Dom Casmurro', '9788535910663', 1899, 3, 1),
('Memorias Postumas de Bras Cubas', '9788535910250', 1881, 3, 1),
('1984', '9788535914849', 1949, 3, 1),
('A Revolucao dos Bichos', '9788535909555', 1945, 3, 1),
('O Pequeno Principe', '9788595081512', 1943, 3, 1),
('Arquitetura Limpa', '9788550804606', 2019, 1, 2),
('Fundamentos de Bancos de Dados', '9788543005872', 2011, 2, 2),
('Metodologias Ativas', '9788584291168', 2018, 3, 5);

INSERT INTO livro_autor (id_livro, id_autor) VALUES
(1, 1), (2, 2), (3, 3), (4, 3), (5, 4), (6, 4), (7, 5), (8, 1), (9, 2), (10, 2);

INSERT INTO exemplar (id_livro, codigo, status) VALUES
(1, 'EX0001', 'DISPONIVEL'), (1, 'EX0002', 'DISPONIVEL'),
(2, 'EX0003', 'DISPONIVEL'), (2, 'EX0004', 'DISPONIVEL'),
(3, 'EX0005', 'DISPONIVEL'), (4, 'EX0006', 'DISPONIVEL'),
(5, 'EX0007', 'DISPONIVEL'), (6, 'EX0008', 'DISPONIVEL'),
(7, 'EX0009', 'DISPONIVEL'), (8, 'EX0010', 'DISPONIVEL'),
(9, 'EX0011', 'DISPONIVEL'), (10, 'EX0012', 'DISPONIVEL');
