
BEGIN;

-- Tabela Editora
INSERT INTO Editora (nome, sede, fundacao, dataCadastro) VALUES 
('Panini', 'São Paulo', '1990-01-15', '2025-04-24 19:00:00'),
('JBC', 'Rio de Janeiro', '1995-05-20', '2025-04-24 19:00:00'),
('New Pop', 'Curitiba', '2000-03-10', '2025-04-24 19:00:00'),
('Devir', 'Belo Horizonte', '1998-07-25', '2025-04-24 19:00:00'),
('Pipoca & Nanquim', 'Porto Alegre', '2010-11-05', '2025-04-24 19:00:00'),
('Darkside', 'Recife', '2012-09-12', '2025-04-24 19:00:00'),
('Intrínseca', 'Salvador', '2005-04-18', '2025-04-24 19:00:00'),
('Veneta', 'Fortaleza', '2008-06-30', '2025-04-24 19:00:00'),
('Pony Brasil', 'Brasília', '2003-02-14', '2025-04-24 19:00:00'),
('Conrad', 'Manaus', '1999-12-08', '2025-04-24 19:00:00');

-- Tabela Autor
INSERT INTO Autor (nome, nacionalidade, dataCadastro) VALUES
('Masashi Kishimoto', 'Japonesa', '2025-04-24 19:00:00'),
('Eiichiro Oda', 'Japonesa', '2025-04-24 19:00:00'),
('Hajime Isayama', 'Japonesa', '2025-04-24 19:00:00'),
('Gege Akutami', 'Japonesa', '2025-04-24 19:00:00'),
('Yusei Matsui', 'Japonesa', '2025-04-24 19:00:00'),
('Tite Kubo', 'Japonesa', '2025-04-24 19:00:00'),
('Akira Toriyama', 'Japonesa', '2025-04-24 19:00:00'),
('Naoko Takeuchi', 'Japonesa', '2025-04-24 19:00:00'),
('Rumiko Takahashi', 'Japonesa', '2025-04-24 19:00:00'),
('Hirohiko Araki', 'Japonesa', '2025-04-24 19:00:00');

-- Tabela Manga
INSERT INTO Manga (titulo, isbn, lancamento, preco, sinopse, estoque, genero, classificacao, id_editora, id_autor, dataCadastro) VALUES
('Naruto', '9784088732734', '2000-09-21', 29.90, 'Um jovem ninja que busca reconhecimento e sonha em se tornar Hokage.', 'DISPONIVEL', 'SHOUNEN', 'DEZ_ANOS', 1, 1, '2025-04-24 19:00:00'),
('One Piece', '9784088732735', '1997-07-22', 32.50, 'A tripulação dos Piratas do Chapéu de Palha em busca do One Piece.', 'DISPONIVEL', 'SHOUNEN', 'DEZ_ANOS', 2, 2, '2025-04-24 19:00:00'),
('Attack on Titan', '9784088732736', '2009-09-09', 35.00, 'Humanidade luta contra titãs devoradores de humanos.', 'INDISPONIVEL', 'SHOUNEN', 'DOZE_ANOS', 3, 3, '2025-04-24 19:00:00'),
('Jujutsu Kaisen', '9784088732737', '2018-03-05', 31.75, 'Estudantes de jujutsu combatendo maldições.', 'DISPONIVEL', 'SHOUNEN', 'DOZE_ANOS', 4, 4, '2025-04-24 19:00:00'),
('Assassination Classroom', '9784088732738', '2012-07-02', 28.90, 'Turma tenta assassinar seu professor alienígena.', 'DISPONIVEL', 'SHOUNEN', 'DOZE_ANOS', 5, 5, '2025-04-24 19:00:00'),
('Bleach', '9784088732739', '2001-08-07', 30.25, 'Ichigo Kurosaki se torna um Ceifeiro de Almas.', 'INDISPONIVEL', 'SHOUNEN', 'DOZE_ANOS', 6, 6, '2025-04-24 19:00:00'),
('Dragon Ball', '9784088732740', '1984-11-20', 27.50, 'Aventuras de Goku em busca das Esferas do Dragão.', 'DISPONIVEL', 'SHOUNEN', 'LIVRE', 7, 7, '2025-04-24 19:00:00'),
('Sailor Moon', '9784088732741', '1991-08-06', 33.00, 'Garotas com poderes mágicos protegem a Terra.', 'DISPONIVEL', 'SHOUJO', 'LIVRE', 8, 8, '2025-04-24 19:00:00'),
('Inuyasha', '9784088732742', '1996-11-13', 29.99, 'A jornada de Kagome e Inuyasha para reunir os fragmentos da Joia de Quatro Almas.', 'DISPONIVEL', 'SHOUJO', 'DOZE_ANOS', 9, 9, '2025-04-24 19:00:00'),
('JoJo''s Bizarre Adventure', '9784088732743', '1987-01-01', 36.50, 'Aventuras bizarras da família Joestar através das gerações.', 'DISPONIVEL', 'SHOUNEN', 'QUATORZE_ANOS', 10, 10, '2025-04-24 19:00:00');

-- Tabela Edicao
INSERT INTO Edicao (titulo, volume, idioma, lancamento, dimensao, formato, tipoCapa, status, id_manga, dataCadastro) VALUES
('Naruto Vol. 1', 1, 'Português', '2002-03-03', '13x18cm', 'FISICO', 'MOLE', 'FINALIZADO', 1, '2025-04-24 19:00:00'),
('Naruto Vol. 2', 2, 'Português', '2002-04-04', '13x18cm', 'FISICO', 'MOLE', 'FINALIZADO', 1, '2025-04-24 19:00:00'),
('One Piece Vol. 1', 1, 'Português', '2001-05-05', '13x18cm', 'FISICO', 'MOLE', 'FINALIZADO', 2, '2025-04-24 19:00:00'),
('One Piece Vol. 2', 2, 'Português', '2001-06-06', '13x18cm', 'FISICO', 'MOLE', 'FINALIZADO', 2, '2025-04-24 19:00:00'),
('Attack on Titan Vol. 1', 1, 'Português', '2010-07-07', '13x18cm', 'FISICO', 'DURA', 'FINALIZADO', 3, '2025-04-24 19:00:00'),
('Attack on Titan Vol. 2', 2, 'Português', '2010-08-08', '13x18cm', 'FISICO', 'DURA', 'FINALIZADO', 3, '2025-04-24 19:00:00'),
('Jujutsu Kaisen Vol. 1', 1, 'Português', '2019-09-09', '13x18cm', 'FISICO', 'MOLE', 'EM_ANDAMENTO', 4, '2025-04-24 19:00:00'),
('Jujutsu Kaisen Vol. 2', 2, 'Português', '2019-10-10', '13x18cm', 'FISICO', 'MOLE', 'EM_ANDAMENTO', 4, '2025-04-24 19:00:00'),
('Dragon Ball Vol. 1', 1, 'Português', '1986-11-11', '13x18cm', 'FISICO', 'MOLE', 'FINALIZADO', 7, '2025-04-24 19:00:00'),
('Dragon Ball Vol. 2', 2, 'Português', '1986-12-12', '13x18cm', 'FISICO', 'MOLE', 'FINALIZADO', 7, '2025-04-24 19:00:00');

-- Tabela Usuario
INSERT INTO Usuario (nome, email, senhaHash, endereco, tipoUsuario, dataCadastro) VALUES
('João Silva', 'joao@email.com', 'hash123', 'Rua A, 123', 'USER', '2025-04-24 19:00:00'),
('Maria Souza', 'maria@email.com', 'hash456', 'Av B, 456', 'USER', '2025-04-24 19:00:00'),
('Carlos Oliveira', 'carlos@email.com', 'hash789', 'Rua C, 789', 'ADMIN', '2025-04-24 19:00:00'),
('Ana Santos', 'ana@email.com', 'hash101', 'Av D, 101', 'USER', '2025-04-24 19:00:00'),
('Pedro Costa', 'pedro@email.com', 'hash112', 'Rua E, 112', 'USER', '2025-04-24 19:00:00'),
('Lucia Pereira', 'lucia@email.com', 'hash131', 'Av F, 131', 'USER', '2025-04-24 19:00:00'),
('Marcos Rocha', 'marcos@email.com', 'hash415', 'Rua G, 415', 'USER', '2025-04-24 19:00:00'),
('Fernanda Lima', 'fernanda@email.com', 'hash161', 'Av H, 161', 'ADMIN', '2025-04-24 19:00:00'),
('Ricardo Alves', 'ricardo@email.com', 'hash718', 'Rua I, 718', 'USER', '2025-04-24 19:00:00'),
('Juliana Martins', 'juliana@email.com', 'hash192', 'Av J, 192', 'USER', '2025-04-24 19:00:00');

-- Tabela Pedido
INSERT INTO Pedido (numeroPedido, data, status, valorTotal, usuario_id, dataCadastro) VALUES
(1001, '2023-01-01', 'ENTREGUE', 62.40, 1, '2025-04-24 19:00:00'),
(1002, '2023-01-02', 'ENVIADO', 65.00, 2, '2025-04-24 19:00:00'),
(1003, '2023-01-03', 'PROCESSANDO', 70.00, 3, '2025-04-24 19:00:00'),
(1004, '2023-01-04', 'ENTREGUE', 63.50, 4, '2025-04-24 19:00:00'),
(1005, '2023-01-05', 'CANCELADO', 57.80, 5, '2025-04-24 19:00:00'),
(1006, '2023-01-06', 'ENTREGUE', 60.50, 6, '2025-04-24 19:00:00'),
(1007, '2023-01-07', 'ENVIADO', 55.00, 7, '2025-04-24 19:00:00'),
(1008, '2023-01-08', 'ENTREGUE', 66.00, 8, '2025-04-24 19:00:00'),
(1009, '2023-01-09', 'PROCESSANDO', 59.98, 9, '2025-04-24 19:00:00'),
(1010, '2023-01-10', 'ENTREGUE', 73.00, 10, '2025-04-24 19:00:00');

-- Tabela Pagamento
INSERT INTO Pagamento (metodoPagamento, status, dataConfirmacao, pedido_id, dataCadastro) VALUES
('CARTAO_CREDITO', 'CONFIRMADO', '2023-01-01', 1, '2025-04-24 19:00:00'),
('PIX', 'CONFIRMADO', '2023-01-02', 2, '2025-04-24 19:00:00'),
('BOLETO', 'PENDENTE', '2023-01-03', 3, '2025-04-24 19:00:00'),
('CARTAO_DEBITO', 'CONFIRMADO', '2023-01-04', 4, '2025-04-24 19:00:00'),
('CARTAO_CREDITO', 'CANCELADO', '2023-01-05', 5, '2025-04-24 19:00:00'),
('PIX', 'CONFIRMADO', '2023-01-06', 6, '2025-04-24 19:00:00'),
('BOLETO', 'CONFIRMADO', '2023-01-07', 7, '2025-04-24 19:00:00'),
('CARTAO_CREDITO', 'CONFIRMADO', '2023-01-08', 8, '2025-04-24 19:00:00'),
('PIX', 'PENDENTE', '2023-01-09', 9, '2025-04-24 19:00:00'),
('CARTAO_DEBITO', 'CONFIRMADO', '2023-01-10', 10, '2025-04-24 19:00:00');

-- Tabela Entrega
INSERT INTO Entrega (endereco, codigoRastreio, status, pedido_id, dataCadastro) VALUES
('Rua A, 123', 'BR123456789', 'ENTREGUE', 1, '2025-04-24 19:00:00'),
('Av B, 456', 'BR987654321', 'EM_TRANSITO', 2, '2025-04-24 19:00:00'),
('Rua C, 789', 'BR456123789', 'PREPARANDO', 3, '2025-04-24 19:00:00'),
('Av D, 101', 'BR789456123', 'ENTREGUE', 4, '2025-04-24 19:00:00'),
('Rua E, 112', 'BR321654987', 'CANCELADA', 5, '2025-04-24 19:00:00'),
('Av F, 131', 'BR654987321', 'ENTREGUE', 6, '2025-04-24 19:00:00'),
('Rua G, 415', 'BR147258369', 'EM_TRANSITO', 7, '2025-04-24 19:00:00'),
('Av H, 161', 'BR369258147', 'ENTREGUE', 8, '2025-04-24 19:00:00'),
('Rua I, 718', 'BR258369147', 'PREPARANDO', 9, '2025-04-24 19:00:00'),
('Av J, 192', 'BR951753852', 'ENTREGUE', 10, '2025-04-24 19:00:00');

-- Tabela Avaliacao
INSERT INTO Avaliacao (nota, comentario, id_manga, dataCadastro) VALUES
(9.5, 'Excelente mangá!', 1, '2025-04-24 19:00:00'),
(10.0, 'Obra prima do Kishimoto', 1, '2025-04-24 19:00:00'),
(8.5, 'Muito bom, mas poderia ter mais ação', 2, '2025-04-24 19:00:00'),
(9.0, 'História envolvente', 3, '2025-04-24 19:00:00'),
(7.5, 'Bom, mas não é meu favorito', 4, '2025-04-24 19:00:00'),
(8.0, 'Arte incrível', 5, '2025-04-24 19:00:00'),
(9.5, 'Clássico absoluto', 7, '2025-04-24 19:00:00'),
(8.5, 'Gostei bastante', 8, '2025-04-24 19:00:00'),
(7.0, 'Interessante', 9, '2025-04-24 19:00:00'),
(9.0, 'Muito criativo', 10, '2025-04-24 19:00:00');

-- Tabela pedido_manga
INSERT INTO pedido_manga (id_pedido, id_manga) VALUES
(1, 1), (1, 2),
(2, 3), (2, 4),
(3, 5), (3, 6),
(4, 7), (4, 8),
(5, 9), (5, 10),
(6, 1), (6, 3),
(7, 2), (7, 4),
(8, 5), (8, 7),
(9, 6), (9, 8),
(10, 9), (10, 10);

SELECT setval('autor_id_seq', (SELECT MAX(id) FROM Autor));
SELECT setval('editora_id_seq', (SELECT MAX(id) FROM Editora));
SELECT setval('manga_id_seq', (SELECT MAX(id) FROM Manga));
SELECT setval('edicao_id_seq', (SELECT MAX(id) FROM Edicao));
SELECT setval('usuario_id_seq', (SELECT MAX(id) FROM Usuario));
SELECT setval('pedido_id_seq', (SELECT MAX(id) FROM Pedido));
SELECT setval('pagamento_id_seq', (SELECT MAX(id) FROM Pagamento));
SELECT setval('entrega_id_seq', (SELECT MAX(id) FROM Entrega));
SELECT setval('avaliacao_id_seq', (SELECT MAX(id) FROM Avaliacao));

COMMIT;