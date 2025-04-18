-- Configuração inicial para garantir UTF-8 e transação
SET client_encoding = 'UTF8';
BEGIN;

-- Tabela Editora (10 registros)
INSERT INTO Editora (id, nome, sede, fundacao, dataCadastro, dataAlteracao) VALUES 
(1, 'Panini', 'São Paulo', '1990-01-15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'JBC', 'Rio de Janeiro', '1995-05-20', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'New Pop', 'Curitiba', '2000-03-10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Devir', 'Belo Horizonte', '1998-07-25', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Pipoca & Nanquim', 'Porto Alegre', '2010-11-05', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'Darkside', 'Recife', '2012-09-12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'Intrínseca', 'Salvador', '2005-04-18', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'Veneta', 'Fortaleza', '2008-06-30', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 'Pony Brasil', 'Brasília', '2003-02-14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'Conrad', 'Manaus', '1999-12-08', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Tabela Autor (10 registros)
INSERT INTO Autor (id, nome, nacionalidade, dataCadastro, dataAlteracao) VALUES
(1, 'Masashi Kishimoto', 'Japonesa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Eiichiro Oda', 'Japonesa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Hajime Isayama', 'Japonesa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Gege Akutami', 'Japonesa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Yusei Matsui', 'Japonesa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'Tite Kubo', 'Japonesa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'Akira Toriyama', 'Japonesa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'Naoko Takeuchi', 'Japonesa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 'Rumiko Takahashi', 'Japonesa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'Hirohiko Araki', 'Japonesa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Tabela Manga (10 registros)
INSERT INTO Manga (id, titulo, isbn, lancamento, preco, sinopse, estoque, genero, classificacao, id_editora, id_autor, dataCadastro, dataAlteracao) VALUES
(1, 'Naruto', '9784088732734', '2000-09-21', 29.90, 'Um jovem ninja que busca reconhecimento e sonha em se tornar Hokage.', 'DISPONIVEL', 'SHOUNEN', 'DEZ_ANOS', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'One Piece', '9784088732735', '1997-07-22', 32.50, 'A tripulação dos Piratas do Chapéu de Palha em busca do One Piece.', 'DISPONIVEL', 'SHOUNEN', 'DEZ_ANOS', 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Attack on Titan', '9784088732736', '2009-09-09', 35.00, 'Humanidade luta contra titãs devoradores de humanos.', 'INDISPONIVEL', 'SHOUNEN', 'DOZE_ANOS', 3, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Jujutsu Kaisen', '9784088732737', '2018-03-05', 31.75, 'Estudantes de jujutsu combatendo maldições.', 'DISPONIVEL', 'SHOUNEN', 'DOZE_ANOS', 4, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Assassination Classroom', '9784088732738', '2012-07-02', 28.90, 'Turma tenta assassinar seu professor alienígena.', 'DISPONIVEL', 'SHOUNEN', 'DOZE_ANOS', 5, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'Bleach', '9784088732739', '2001-08-07', 30.25, 'Ichigo Kurosaki se torna um Ceifeiro de Almas.', 'INDISPONIVEL', 'SHOUNEN', 'DOZE_ANOS', 6, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'Dragon Ball', '9784088732740', '1984-11-20', 27.50, 'Aventuras de Goku em busca das Esferas do Dragão.', 'DISPONIVEL', 'SHOUNEN', 'LIVRE', 7, 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'Sailor Moon', '9784088732741', '1991-08-06', 33.00, 'Garotas com poderes mágicos protegem a Terra.', 'DISPONIVEL', 'SHOUJO', 'LIVRE', 8, 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 'Inuyasha', '9784088732742', '1996-11-13', 29.99, 'A jornada de Kagome e Inuyasha para reunir os fragmentos da Joia de Quatro Almas.', 'DISPONIVEL', 'SHOUJO', 'DOZE_ANOS', 9, 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'JoJo''s Bizarre Adventure', '9784088732743', '1987-01-01', 36.50, 'Aventuras bizarras da família Joestar através das gerações.', 'DISPONIVEL', 'SHOUNEN', 'QUATORZE_ANOS', 10, 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Tabela Edicao (10 registros - 2 por mangá)
INSERT INTO Edicao (id, titulo, volume, idioma, lancamento, dimensao, formato, tipoCapa, status, id_manga, dataCadastro, dataAlteracao) VALUES
(1, 'Naruto Vol. 1', 1, 'Português', '2002-03-03', '13x18cm', 'FISICO', 'MOLE', 'FINALIZADO', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Naruto Vol. 2', 2, 'Português', '2002-04-04', '13x18cm', 'FISICO', 'MOLE', 'FINALIZADO', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'One Piece Vol. 1', 1, 'Português', '2001-05-05', '13x18cm', 'FISICO', 'MOLE', 'FINALIZADO', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'One Piece Vol. 2', 2, 'Português', '2001-06-06', '13x18cm', 'FISICO', 'MOLE', 'FINALIZADO', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Attack on Titan Vol. 1', 1, 'Português', '2010-07-07', '13x18cm', 'FISICO', 'DURA', 'FINALIZADO', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'Attack on Titan Vol. 2', 2, 'Português', '2010-08-08', '13x18cm', 'FISICO', 'DURA', 'FINALIZADO', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'Jujutsu Kaisen Vol. 1', 1, 'Português', '2019-09-09', '13x18cm', 'FISICO', 'MOLE', 'EM_ANDAMENTO', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'Jujutsu Kaisen Vol. 2', 2, 'Português', '2019-10-10', '13x18cm', 'FISICO', 'MOLE', 'EM_ANDAMENTO', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 'Dragon Ball Vol. 1', 1, 'Português', '1986-11-11', '13x18cm', 'FISICO', 'MOLE', 'FINALIZADO', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'Dragon Ball Vol. 2', 2, 'Português', '1986-12-12', '13x18cm', 'FISICO', 'MOLE', 'FINALIZADO', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Tabela Usuario (10 registros)
INSERT INTO Usuario (id, nome, email, senhaHash, endereco, tipoUsuario, dataCadastro, dataAlteracao) VALUES
(1, 'João Silva', 'joao@email.com', 'hash123', 'Rua A, 123', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Maria Souza', 'maria@email.com', 'hash456', 'Av B, 456', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Carlos Oliveira', 'carlos@email.com', 'hash789', 'Rua C, 789', 'ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Ana Santos', 'ana@email.com', 'hash101', 'Av D, 101', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Pedro Costa', 'pedro@email.com', 'hash112', 'Rua E, 112', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'Lucia Pereira', 'lucia@email.com', 'hash131', 'Av F, 131', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'Marcos Rocha', 'marcos@email.com', 'hash415', 'Rua G, 415', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'Fernanda Lima', 'fernanda@email.com', 'hash161', 'Av H, 161', 'ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 'Ricardo Alves', 'ricardo@email.com', 'hash718', 'Rua I, 718', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'Juliana Martins', 'juliana@email.com', 'hash192', 'Av J, 192', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Tabela Pedido (10 registros)
INSERT INTO Pedido (id, numeroPedido, data, status, valorTotal, usuario_id, dataCadastro, dataAlteracao) VALUES
(1, 1001, '2023-01-01', 'ENTREGUE', 62.40, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 1002, '2023-01-02', 'ENVIADO', 65.00, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 1003, '2023-01-03', 'PROCESSANDO', 70.00, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 1004, '2023-01-04', 'ENTREGUE', 63.50, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 1005, '2023-01-05', 'CANCELADO', 57.80, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 1006, '2023-01-06', 'ENTREGUE', 60.50, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 1007, '2023-01-07', 'ENVIADO', 55.00, 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 1008, '2023-01-08', 'ENTREGUE', 66.00, 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 1009, '2023-01-09', 'PROCESSANDO', 59.98, 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 1010, '2023-01-10', 'ENTREGUE', 73.00, 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Tabela Pagamento (10 registros - 1 por pedido)
INSERT INTO Pagamento (id, metodoPagamento, status, dataConfirmacao, pedido_id, dataCadastro, dataAlteracao) VALUES
(1, 'CARTAO_CREDITO', 'CONFIRMADO', '2023-01-01', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'PIX', 'CONFIRMADO', '2023-01-02', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'BOLETO', 'PENDENTE', '2023-01-03', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'CARTAO_DEBITO', 'CONFIRMADO', '2023-01-04', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'CARTAO_CREDITO', 'CANCELADO', '2023-01-05', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'PIX', 'CONFIRMADO', '2023-01-06', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'BOLETO', 'CONFIRMADO', '2023-01-07', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'CARTAO_CREDITO', 'CONFIRMADO', '2023-01-08', 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 'PIX', 'PENDENTE', '2023-01-09', 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'CARTAO_DEBITO', 'CONFIRMADO', '2023-01-10', 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Tabela Entrega (10 registros - 1 por pedido)
INSERT INTO Entrega (id, endereco, codigoRastreio, status, pedido_id, dataCadastro, dataAlteracao) VALUES
(1, 'Rua A, 123', 'BR123456789', 'ENTREGUE', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Av B, 456', 'BR987654321', 'EM_TRANSITO', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Rua C, 789', 'BR456123789', 'PREPARANDO', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Av D, 101', 'BR789456123', 'ENTREGUE', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Rua E, 112', 'BR321654987', 'CANCELADA', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'Av F, 131', 'BR654987321', 'ENTREGUE', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'Rua G, 415', 'BR147258369', 'EM_TRANSITO', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'Av H, 161', 'BR369258147', 'ENTREGUE', 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 'Rua I, 718', 'BR258369147', 'PREPARANDO', 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'Av J, 192', 'BR951753852', 'ENTREGUE', 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Tabela Avaliacao (10 registros - 1 por mangá)
INSERT INTO Avaliacao (id, nota, comentario, id_manga, dataCadastro, dataAlteracao) VALUES
(1, 9.5, 'Excelente mangá!', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 10.0, 'Obra prima do Kishimoto', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 8.5, 'Muito bom, mas poderia ter mais ação', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 9.0, 'História envolvente', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 7.5, 'Bom, mas não é meu favorito', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 8.0, 'Arte incrível', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 9.5, 'Clássico absoluto', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 8.5, 'Gostei bastante', 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 7.0, 'Interessante', 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 9.0, 'Muito criativo', 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Tabela pedido_manga (20 registros - 2 por pedido) - ÚLTIMA A SER INSERIDA
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

-- Finaliza a transação
COMMIT;