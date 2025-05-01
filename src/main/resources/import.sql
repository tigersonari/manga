-- Inserir Editoras
INSERT INTO Editora (nome, sede, fundacao, data_cadastro) VALUES
('Panini', 'São Paulo, Brasil', '1990-01-15', '2025-01-01 10:00:00'),
('JBC', 'Rio de Janeiro, Brasil', '1995-05-20', '2025-01-01 10:00:00');

-- Inserir Autores
INSERT INTO Autor (nome, nacionalidade, data_cadastro) VALUES
('Masashi Kishimoto', 'Japonesa', '2025-01-01 10:00:00'),
('Eiichiro Oda', 'Japonesa', '2025-01-01 10:00:00');

-- Ajustar sequências
SELECT setval('editora_id_seq', (SELECT MAX(id) FROM Editora));
SELECT setval('autor_id_seq', (SELECT MAX(id) FROM Autor));