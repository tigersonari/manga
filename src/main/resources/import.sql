BEGIN;

-- Inserir Editoras
INSERT INTO Editora (nome, sede, fundacao, dataCadastro) VALUES
('Panini', 'Brasil', '1990-01-15', CURRENT_TIMESTAMP),
('JBC', 'Brasil', '1995-05-20', CURRENT_TIMESTAMP),
('New Pop', 'Brasil', '2000-03-10', CURRENT_TIMESTAMP),
('Devir', 'Brasil', '1998-07-25', CURRENT_TIMESTAMP),
('Pipoca & Nanquim', 'Brasil', '2010-11-05', CURRENT_TIMESTAMP),
('Darkside', 'Brasil', '2012-09-12', CURRENT_TIMESTAMP),
('Intrínseca', 'Brasil', '2005-04-18', CURRENT_TIMESTAMP),
('Veneta', 'Brasil', '2008-06-30', CURRENT_TIMESTAMP);

-- Inserir Autores
INSERT INTO Autor (nome, nacionalidade, dataCadastro) VALUES
('Masashi Kishimoto', 'Japonesa', CURRENT_TIMESTAMP),
('Eiichiro Oda', 'Japonesa', CURRENT_TIMESTAMP),
('Hajime Isayama', 'Japonesa', CURRENT_TIMESTAMP),
('Gege Akutami', 'Japonesa', CURRENT_TIMESTAMP),
('Yusei Matsui', 'Japonesa', CURRENT_TIMESTAMP),
('Tite Kubo', 'Japonesa', CURRENT_TIMESTAMP),
('Akira Toriyama', 'Japonesa', CURRENT_TIMESTAMP),
('Naoko Takeuchi', 'Japonesa', CURRENT_TIMESTAMP);

-- Inserir Mangas
INSERT INTO Manga (titulo, isbn, lancamento, preco, sinopse, estoque, genero, classificacao, id_editora, id_autor, dataCadastro) VALUES
('Naruto', '9784088732734', '2000-09-21', 29.90, 'Um jovem ninja que busca reconhecimento e sonha em se tornar Hokage.', 1, 1, 2, 1, 1, CURRENT_TIMESTAMP),
('One Piece', '9784088732735', '1997-07-22', 32.50, 'A tripulação dos Piratas do Chapéu de Palha em busca do One Piece.', 1, 1, 2, 2, 2, CURRENT_TIMESTAMP),
('Attack on Titan', '9784088732736', '2009-09-09', 35.00, 'Humanidade luta contra titãs devoradores de humanos.', 2, 1, 3, 3, 3, CURRENT_TIMESTAMP),
('Jujutsu Kaisen', '9784088732737', '2018-03-05', 31.75, 'Estudantes de jujutsu combatendo maldições.', 1, 1, 3, 4, 4, CURRENT_TIMESTAMP),
('Assassination Classroom', '9784088732738', '2012-07-02', 28.90, 'Turma tenta assassinar seu professor alienígena.', 1, 1, 3, 5, 5, CURRENT_TIMESTAMP),
('Bleach', '9784088732739', '2001-08-07', 30.25, 'Ichigo Kurosaki se torna um Ceifeiro de Almas.', 2, 1, 3, 6, 6, CURRENT_TIMESTAMP),
('Dragon Ball', '9784088732740', '1984-11-20', 27.50, 'Aventuras de Goku em busca das Esferas do Dragão.', 1, 1, 1, 7, 7, CURRENT_TIMESTAMP),
('Sailor Moon', '9784088732741', '1991-08-06', 33.00, 'Garotas com poderes mágicos protegem a Terra.', 1, 2, 1, 8, 8, CURRENT_TIMESTAMP);

-- Inserir Edições
INSERT INTO edicao (volume, idioma, lancamento, dimensao, titulo, formato, tipoCapa, status, id_manga, dataCadastro) VALUES
(1, 'Português', '2002-03-03', '13x18cm', 'Naruto Vol. 1', 1, 2, 1, 1, CURRENT_TIMESTAMP),
(2, 'Português', '2002-04-04', '13x18cm', 'Naruto Vol. 2', 1, 2, 1, 1, CURRENT_TIMESTAMP),
(1, 'Português', '2001-05-05', '13x18cm', 'One Piece Vol. 1', 1, 2, 1, 2, CURRENT_TIMESTAMP),
(2, 'Português', '2001-06-06', '13x18cm', 'One Piece Vol. 2', 1, 2, 1, 2, CURRENT_TIMESTAMP),
(1, 'Português', '2010-07-07', '13x18cm', 'Attack on Titan Vol. 1', 1, 1, 1, 3, CURRENT_TIMESTAMP),
(2, 'Português', '2010-08-08', '13x18cm', 'Attack on Titan Vol. 2', 1, 1, 1, 3, CURRENT_TIMESTAMP),
(1, 'Português', '2019-09-09', '13x18cm', 'Jujutsu Kaisen Vol. 1', 1, 2, 2, 4, CURRENT_TIMESTAMP),
(2, 'Português', '2019-10-10', '13x18cm', 'Jujutsu Kaisen Vol. 2', 1, 2, 2, 4, CURRENT_TIMESTAMP);

-- Inserir Usuários e Admins na tabela UsuarioBase
INSERT INTO UsuarioBase (nome, email, senhaHash, endereco, tipo, dataCadastro) VALUES
('João Silva', 'joao@email.com', 'hash123', 'Rua A, 123', 'USER', CURRENT_TIMESTAMP),
('Maria Souza', 'maria@email.com', 'hash456', 'Av B, 456', 'USER', CURRENT_TIMESTAMP),
('Ana Santos', 'ana@email.com', 'hash101', 'Av D, 101', 'USER', CURRENT_TIMESTAMP),
('Pedro Costa', 'pedro@email.com', 'hash112', 'Rua E, 112', 'USER', CURRENT_TIMESTAMP),
('Carlos Oliveira', 'carlos@email.com', 'hash789', 'Rua C, 789', 'ADMIN', CURRENT_TIMESTAMP),
('Fernanda Lima', 'fernanda@email.com', 'hash161', 'Av H, 161', 'ADMIN', CURRENT_TIMESTAMP),
('Lucia Pereira', 'lucia@email.com', 'hash131', 'Av F, 131', 'ADMIN', CURRENT_TIMESTAMP),
('Marcos Rocha', 'marcos@email.com', 'hash415', 'Rua G, 415', 'ADMIN', CURRENT_TIMESTAMP);

-- Atualizar permissao para Admins
UPDATE UsuarioBase SET permissao = 'FULL_ACCESS' WHERE id IN (5, 6);
UPDATE UsuarioBase SET permissao = 'READ_ONLY' WHERE id IN (7, 8);

-- Inserir Pedidos
INSERT INTO Pedido (numeroPedido, data, status, valorTotal, usuario_id, dataCadastro) VALUES
(1001, '2023-01-01', 'ENTREGUE', 62.40, 1, CURRENT_TIMESTAMP),
(1002, '2023-01-02', 'ENVIADO', 65.00, 2, CURRENT_TIMESTAMP),
(1003, '2023-01-03', 'PROCESSANDO', 70.00, 3, CURRENT_TIMESTAMP),
(1004, '2023-01-04', 'ENTREGUE', 63.50, 4, CURRENT_TIMESTAMP),
(1005, '2023-01-05', 'CANCELADO', 57.80, 2, CURRENT_TIMESTAMP),
(1006, '2023-01-06', 'ENTREGUE', 60.50, 1, CURRENT_TIMESTAMP),
(1007, '2023-01-07', 'ENVIADO', 55.00, 4, CURRENT_TIMESTAMP),
(1008, '2023-01-08', 'ENTREGUE', 66.00, 3, CURRENT_TIMESTAMP);

-- Inserir Pagamentos
INSERT INTO Pagamento (metodoPagamento, status, dataConfirmacao, pedido_id, dataCadastro) VALUES
('CARTAO_CREDITO', 'CONFIRMADO', '2023-01-01', 1, CURRENT_TIMESTAMP),
('PIX', 'CONFIRMADO', '2023-01-02', 2, CURRENT_TIMESTAMP),
('BOLETO', 'PENDENTE', '2023-01-03', 3, CURRENT_TIMESTAMP),
('CARTAO_DEBITO', 'CONFIRMADO', '2023-01-04', 4, CURRENT_TIMESTAMP),
('CARTAO_CREDITO', 'CANCELADO', '2023-01-05', 5, CURRENT_TIMESTAMP),
('PIX', 'CONFIRMADO', '2023-01-06', 6, CURRENT_TIMESTAMP),
('BOLETO', 'CONFIRMADO', '2023-01-07', 7, CURRENT_TIMESTAMP),
('CARTAO_CREDITO', 'CONFIRMADO', '2023-01-08', 8, CURRENT_TIMESTAMP);

-- Inserir Entregas
INSERT INTO Entrega (endereco, codigoRastreio, status, pedido_id, dataCadastro) VALUES
('Rua A, 123', 'BR123456789', 'ENTREGUE', 1, CURRENT_TIMESTAMP),
('Av B, 456', 'BR987654321', 'EM_TRANSITO', 2, CURRENT_TIMESTAMP),
('Rua C, 789', 'BR456123789', 'PREPARANDO', 3, CURRENT_TIMESTAMP),
('Av D, 101', 'BR789456123', 'ENTREGUE', 4, CURRENT_TIMESTAMP),
('Rua E, 112', 'BR321654987', 'CANCELADA', 5, CURRENT_TIMESTAMP),
('Av F, 131', 'BR654987321', 'ENTREGUE', 6, CURRENT_TIMESTAMP),
('Rua G, 415', 'BR147258369', 'EM_TRANSITO', 7, CURRENT_TIMESTAMP),
('Av H, 161', 'BR369258147', 'ENTREGUE', 8, CURRENT_TIMESTAMP);

-- Inserir Avaliações
INSERT INTO Avaliacao (nota, comentario, id_manga, dataCadastro) VALUES
(9.5, 'Excelente mangá!', 1, CURRENT_TIMESTAMP),
(10.0, 'Obra prima do Kishimoto', 1, CURRENT_TIMESTAMP),
(8.5, 'Muito bom, mas poderia ter mais ação', 2, CURRENT_TIMESTAMP),
(9.0, 'História envolvente', 3, CURRENT_TIMESTAMP),
(7.5, 'Bom, mas não é meu favorito', 4, CURRENT_TIMESTAMP),
(8.0, 'Arte incrível', 5, CURRENT_TIMESTAMP),
(9.5, 'Clássico absoluto', 6, CURRENT_TIMESTAMP),
(8.5, 'Gostei bastante', 7, CURRENT_TIMESTAMP);

-- Inserir PedidoManga
INSERT INTO pedido_manga (id_pedido, id_manga) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8);

-- Ajustar sequências
SELECT setval('editora_id_seq', (SELECT MAX(id) FROM Editora));
SELECT setval('autor_id_seq', (SELECT MAX(id) FROM Autor));
SELECT setval('manga_id_seq', (SELECT MAX(id) FROM Manga));
SELECT setval('edicao_id_seq', (SELECT MAX(id) FROM edicao));
SELECT setval('usuariobase_id_seq', (SELECT MAX(id) FROM UsuarioBase));
SELECT setval('pedido_id_seq', (SELECT MAX(id) FROM Pedido));
SELECT setval('pagamento_id_seq', (SELECT MAX(id) FROM Pagamento));
SELECT setval('entrega_id_seq', (SELECT MAX(id) FROM Entrega));
SELECT setval('avaliacao_id_seq', (SELECT MAX(id) FROM Avaliacao));

COMMIT;