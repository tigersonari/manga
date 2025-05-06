BEGIN;


INSERT INTO Editora (id, nome, sede, fundacao, dataCadastro, dataAlteracao) VALUES
(1, 'Panini', 'Brasil', '1990-01-15', CURRENT_TIMESTAMP, NULL),
(2, 'JBC', 'Brasil', '1995-05-20', CURRENT_TIMESTAMP, NULL),
(3, 'New Pop', 'Brasil', '2000-03-10', CURRENT_TIMESTAMP, NULL),
(4, 'Devir', 'Brasil', '1998-07-25', CURRENT_TIMESTAMP, NULL),
(5, 'Pipoca & Nanquim', 'Brasil', '2010-11-05', CURRENT_TIMESTAMP, NULL),
(6, 'Darkside', 'Brasil', '2012-09-12', CURRENT_TIMESTAMP, NULL),
(7, 'Intrínseca', 'Brasil', '2005-04-18', CURRENT_TIMESTAMP, NULL),
(8, 'Veneta', 'Brasil', '2008-06-30', CURRENT_TIMESTAMP, NULL);


INSERT INTO Autor (id, nome, nacionalidade, dataCadastro, dataAlteracao) VALUES
(1, 'Masashi Kishimoto', 'Japonesa', CURRENT_TIMESTAMP, NULL),
(2, 'Eiichiro Oda', 'Japonesa', CURRENT_TIMESTAMP, NULL),
(3, 'Hajime Isayama', 'Japonesa', CURRENT_TIMESTAMP, NULL),
(4, 'Gege Akutami', 'Japonesa', CURRENT_TIMESTAMP, NULL),
(5, 'Yusei Matsui', 'Japonesa', CURRENT_TIMESTAMP, NULL),
(6, 'Tite Kubo', 'Japonesa', CURRENT_TIMESTAMP, NULL),
(7, 'Akira Toriyama', 'Japonesa', CURRENT_TIMESTAMP, NULL),
(8, 'Naoko Takeuchi', 'Japonesa', CURRENT_TIMESTAMP, NULL);


INSERT INTO Manga (id, titulo, isbn, lancamento, preco, sinopse, estoque, genero, classificacao, id_editora, id_autor, dataCadastro, dataAlteracao) VALUES
(1, 'Naruto', '9784088732734', '2000-09-21', 29.90, 'Um jovem ninja que busca reconhecimento e sonha em se tornar Hokage.', 0, 0, 2, 1, 1, CURRENT_TIMESTAMP, NULL),
(2, 'One Piece', '9784088732735', '1997-07-22', 32.50, 'A tripulação dos Piratas do Chapéu de Palha em busca do One Piece.', 0, 0, 2, 2, 2, CURRENT_TIMESTAMP, NULL),
(3, 'Attack on Titan', '9784088732736', '2009-09-09', 35.00, 'Humanidade luta contra titãs devoradores de humanos.', 0, 2, 3, 3, 3, CURRENT_TIMESTAMP, NULL),
(4, 'Jujutsu Kaisen', '9784088732737', '2018-03-05', 31.75, 'Estudantes de jujutsu combatendo maldições.', 0, 0, 3, 4, 4, CURRENT_TIMESTAMP, NULL),
(5, 'Assassination Classroom', '9784088732738', '2012-07-02', 28.90, 'Turma tenta assassinar seu professor alienígena.', 0, 0, 3, 5, 5, CURRENT_TIMESTAMP, NULL),
(6, 'Bleach', '9784088732739', '2001-08-07', 30.25, 'Ichigo Kurosaki se torna um Ceifeiro de Almas.', 0, 0, 3, 6, 6, CURRENT_TIMESTAMP, NULL),
(7, 'Dragon Ball', '9784088732740', '1984-11-20', 27.50, 'Aventuras de Goku em busca das Esferas do Dragão.', 0, 0, 1, 7, 7, CURRENT_TIMESTAMP, NULL),
(8, 'Sailor Moon', '9784088732741', '1991-08-06', 33.00, 'Garotas com poderes mágicos protegem a Terra.', 0, 1, 1, 8, 8, CURRENT_TIMESTAMP, NULL);


INSERT INTO edicao (id, volume, idioma, lancamento, dimensao, titulo, formato, tipoCapa, status, id_manga, dataCadastro, dataAlteracao) VALUES
(1, 1, 'Português', '2002-03-03', '13x18cm', 'Naruto Vol. 1', 0, 1, 1, 1, CURRENT_TIMESTAMP, NULL),
(2, 2, 'Português', '2002-04-04', '13x18cm', 'Naruto Vol. 2', 0, 1, 1, 1, CURRENT_TIMESTAMP, NULL),
(3, 1, 'Português', '2001-05-05', '13x18cm', 'One Piece Vol. 1', 0, 1, 1, 2, CURRENT_TIMESTAMP, NULL),
(4, 2, 'Português', '2001-06-06', '13x18cm', 'One Piece Vol. 2', 0, 1, 1, 2, CURRENT_TIMESTAMP, NULL),
(5, 1, 'Português', '2010-07-07', '13x18cm', 'Attack on Titan Vol. 1', 0, 0, 1, 3, CURRENT_TIMESTAMP, NULL),
(6, 2, 'Português', '2010-08-08', '13x18cm', 'Attack on Titan Vol. 2', 0, 0, 1, 3, CURRENT_TIMESTAMP, NULL),
(7, 1, 'Português', '2019-09-09', '13x18cm', 'Jujutsu Kaisen Vol. 1', 0, 1, 1, 4, CURRENT_TIMESTAMP, NULL),
(8, 2, 'Português', '2019-10-10', '13x18cm', 'Jujutsu Kaisen Vol. 2', 0, 1, 1, 4, CURRENT_TIMESTAMP, NULL);


INSERT INTO UsuarioBase (id, nome, email, senhaHash, endereco, tipo, permissao, dataCadastro, dataAlteracao) VALUES
(1, 'João Silva', 'joao@email.com', 'hash123', 'Rua A, 123', 'USER', NULL, CURRENT_TIMESTAMP, NULL),
(2, 'Maria Souza', 'maria@email.com', 'hash456', 'Av B, 456', 'USER', NULL, CURRENT_TIMESTAMP, NULL),
(3, 'Ana Santos', 'ana@email.com', 'hash101', 'Av D, 101', 'USER', NULL, CURRENT_TIMESTAMP, NULL),
(4, 'Pedro Costa', 'pedro@email.com', 'hash112', 'Rua E, 112', 'USER', NULL, CURRENT_TIMESTAMP, NULL),
(5, 'Carlos Oliveira', 'carlos@email.com', 'hash789', 'Rua C, 789', 'ADMIN', 'FULL_ACCESS', CURRENT_TIMESTAMP, NULL),
(6, 'Fernanda Lima', 'fernanda@email.com', 'hash161', 'Av H, 161', 'ADMIN', 'FULL_ACCESS', CURRENT_TIMESTAMP, NULL),
(7, 'Lucia Pereira', 'lucia@email.com', 'hash131', 'Av F, 131', 'ADMIN', 'READ_ONLY', CURRENT_TIMESTAMP, NULL),
(8, 'Marcos Rocha', 'marcos@email.com', 'hash415', 'Rua G, 415', 'ADMIN', 'READ_ONLY', CURRENT_TIMESTAMP, NULL);


INSERT INTO Pedido (id, numeroPedido, data, status, valorTotal, usuario_id, dataCadastro, dataAlteracao) VALUES
(1, 1001, '2023-01-01', 'ENTREGUE', 62.40, 1, CURRENT_TIMESTAMP, NULL),
(2, 1002, '2023-01-02', 'ENVIADO', 65.00, 2, CURRENT_TIMESTAMP, NULL),
(3, 1003, '2023-01-03', 'PENDENTE', 70.00, 3, CURRENT_TIMESTAMP, NULL),
(4, 1004, '2023-01-04', 'ENTREGUE', 63.50, 4, CURRENT_TIMESTAMP, NULL),
(5, 1005, '2023-01-05', 'CANCELADO', 57.80, 2, CURRENT_TIMESTAMP, NULL),
(6, 1006, '2023-01-06', 'ENTREGUE', 60.50, 1, CURRENT_TIMESTAMP, NULL),
(7, 1007, '2023-01-07', 'ENVIADO', 55.00, 4, CURRENT_TIMESTAMP, NULL),
(8, 1008, '2023-01-08', 'ENTREGUE', 66.00, 3, CURRENT_TIMESTAMP, NULL);


INSERT INTO Pagamento (id, metodoPagamento, status, dataConfirmacao, pedido_id, dataCadastro, dataAlteracao) VALUES
(1, 'CARTAO_CREDITO', 'CONFIRMADO', '2023-01-01', 1, CURRENT_TIMESTAMP, NULL),
(2, 'PIX', 'CONFIRMADO', '2023-01-02', 2, CURRENT_TIMESTAMP, NULL),
(3, 'BOLETO', 'PENDENTE', '2023-01-03', 3, CURRENT_TIMESTAMP, NULL),
(4, 'CARTAO_DEBITO', 'CONFIRMADO', '2023-01-04', 4, CURRENT_TIMESTAMP, NULL),
(5, 'CARTAO_CREDITO', 'CANCELADO', '2023-01-05', 5, CURRENT_TIMESTAMP, NULL),
(6, 'PIX', 'CONFIRMADO', '2023-01-06', 6, CURRENT_TIMESTAMP, NULL),
(7, 'BOLETO', 'CONFIRMADO', '2023-01-07', 7, CURRENT_TIMESTAMP, NULL),
(8, 'CARTAO_CREDITO', 'CONFIRMADO', '2023-01-08', 8, CURRENT_TIMESTAMP, NULL);


INSERT INTO Entrega (id, endereco, codigoRastreio, status, pedido_id, dataCadastro, dataAlteracao) VALUES
(1, 'Rua A, 123', 'BR123456789', 'ENTREGUE', 1, CURRENT_TIMESTAMP, NULL),
(2, 'Av B, 456', 'BR987654321', 'EM_TRANSITO', 2, CURRENT_TIMESTAMP, NULL),
(3, 'Rua C, 789', 'BR456123789', 'PREPARANDO', 3, CURRENT_TIMESTAMP, NULL),
(4, 'Av D, 101', 'BR789456123', 'ENTREGUE', 4, CURRENT_TIMESTAMP, NULL),
(5, 'Rua E, 112', 'BR321654987', 'CANCELADA', 5, CURRENT_TIMESTAMP, NULL),
(6, 'Av F, 131', 'BR654987321', 'ENTREGUE', 6, CURRENT_TIMESTAMP, NULL),
(7, 'Rua G, 415', 'BR147258369', 'EM_TRANSITO', 7, CURRENT_TIMESTAMP, NULL),
(8, 'Av H, 161', 'BR369258147', 'ENTREGUE', 8, CURRENT_TIMESTAMP, NULL);


INSERT INTO Avaliacao (id, nota, comentario, id_manga, dataCadastro, dataAlteracao) VALUES
(1, 9.5, 'Excelente mangá!', 1, CURRENT_TIMESTAMP, NULL),
(2, 10.0, 'Obra prima do Kishimoto', 1, CURRENT_TIMESTAMP, NULL),
(3, 8.5, 'Muito bom, mas poderia ter mais ação', 2, CURRENT_TIMESTAMP, NULL),
(4, 9.0, 'História envolvente', 3, CURRENT_TIMESTAMP, NULL),
(5, 7.5, 'Bom, mas não é meu favorito', 4, CURRENT_TIMESTAMP, NULL),
(6, 8.0, 'Arte incrível', 5, CURRENT_TIMESTAMP, NULL),
(7, 9.5, 'Clássico absoluto', 6, CURRENT_TIMESTAMP, NULL),
(8, 8.5, 'Gostei bastante', 7, CURRENT_TIMESTAMP, NULL);


INSERT INTO pedido_manga (id_pedido, id_manga, quantidade) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(6, 6, 1),
(7, 7, 1),
(8, 8, 1);


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