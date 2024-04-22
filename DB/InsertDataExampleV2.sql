-- add missing data for old users
UPDATE user
SET birth_date = '1980-01-01', phone = '3101234567'
WHERE id = '1';

UPDATE user
SET birth_date = '1995-04-12', phone = '3159876543'
WHERE id = '2';

UPDATE user
SET birth_date = '1988-07-25', phone = '3206543218'
WHERE id = '3';

UPDATE user
SET birth_date = '1992-10-18', phone = '3107894561'
WHERE id = '5';

UPDATE user
SET birth_date = '1990-03-30', phone = '3185678943'
WHERE id = '6';

-- New tables example data
INSERT INTO Resources (resource_id, type, title, quantity, quantity_loaned, author_id)
VALUES 
(1, 'SONG', 'Bailando', 20, 5, '1'),
(2, 'SONG', 'Colombia tierra querida', 15, 3, '4'),
(3, 'SONG', 'La camisa negra', 25, 8, '2'),
(4, 'ESSAY', 'Historia de Colombia', 30, 10, '3'),
(5, 'ESSAY', 'Economía Global', 25, 5, '6'),
(6, 'ESSAY', 'Literatura Universal', 35, 15, '6'),
(7, 'VIDEO_RECORDING', 'Introduction to Biology', 10, 3, '1'),
(8, 'VIDEO_RECORDING', 'History of Art', 8, 2, '5'),
(9, 'VIDEO_RECORDING', 'Programming Fundamentals', 12, 5, '1');


INSERT INTO Songs (resource_id, duration)
VALUES 
(1, 3.5),
(2, 4.2),
(3, 5.0);

INSERT INTO Essays (resource_id, academic_level)
VALUES 
(4, 'Pregrado'),
(5, 'Maestría'),
(6, 'Pregrado');

INSERT INTO Video_recordings (resource_id, resolution)
VALUES 
(7, '720p'),
(8, '1080p'),
(9, '4K');

INSERT INTO Loans (request_date, return_date, status, user_id)
VALUES 
('2024-04-15', '2024-04-30', 'REQUESTED', '2'),
('2024-04-16', '2024-04-30', 'COMPLETED', '5'),
('2024-04-17', '2024-04-30', 'FINISHED', '2'),
('2024-04-18', '2024-04-30', 'REQUESTED', '5'),
('2024-04-19', '2024-04-30', 'FINISHED', '2');

INSERT INTO loan_resources (loan_id, resource_id, type)
VALUES 
(1, 1, 'SONG'),
(2, 2, 'SONG'),
(3, 3, 'SONG'),
(4, 7, 'VIDEO_RECORDING'),
(5, 8, 'VIDEO_RECORDING');