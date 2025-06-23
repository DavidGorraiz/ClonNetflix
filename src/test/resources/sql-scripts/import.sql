-- Tabla genre
INSERT INTO genre (nombre) VALUES ('Ciencia Ficción');
INSERT INTO genre (nombre) VALUES ('Acción');
INSERT INTO genre (nombre) VALUES ('Misterio');
INSERT INTO genre (nombre) VALUES ('Terror');

-- Tabla user
INSERT INTO "user" (email, password, nombre_completo, fecha_registro, rol) VALUES ('admin@netflixclone.com', 'admin123', 'Administrador', '2024-01-01', 'ADMIN');
INSERT INTO "user" (email, password, nombre_completo, fecha_registro, rol) VALUES ('john.doe@example.com', 'password123', 'John Doe', '2024-02-10', 'CLIENTE');
INSERT INTO "user" (email, password, nombre_completo, fecha_registro, rol) VALUES ('jane.smith@example.com', 'password456', 'Jane Smith', '2024-02-15', 'CLIENTE');

-- Tabla profile
INSERT INTO profile (nombre, idioma, user_id) VALUES ('John - Perfil 1', 'Español', 2);
INSERT INTO profile (nombre, idioma, user_id) VALUES ('John - Perfil Kids', 'Inglés', 2);
INSERT INTO profile (nombre, idioma, user_id) VALUES ('Jane - Perfil 1', 'Francés', 3);

-- Tabla subscription
INSERT INTO subscription (tipo, precio, duracion_meses, fecha_inicio, fecha_fin, activo, user_id) VALUES ('PREMIUM', 15.99, 12, '2024-02-10', '2025-02-10', TRUE, 2);
INSERT INTO subscription (tipo, precio, duracion_meses, fecha_inicio, fecha_fin, activo, user_id) VALUES ('ESTÁNDAR', 12.99, 6, '2024-02-15', '2024-08-15', TRUE, 3);

-- Tabla content
INSERT INTO content (titulo, descripcion, tipo, fecha_lanzamiento, duracion, clasificacion) VALUES ('The Matrix', 'Película de ciencia ficción', 'PELICULA', '1999-03-31', 136, '+16');
INSERT INTO content (titulo, descripcion, tipo, fecha_lanzamiento, duracion, clasificacion) VALUES ('Stranger Things', 'Serie de misterio y ciencia ficción', 'SERIE', '2016-07-15', NULL, '+13');

-- Insertar episodios
INSERT INTO episode (titulo, descripcion, numero_episodio, temporada, duracion, content_id) VALUES ('Capítulo 1: Vanishing', 'Primer episodio de Stranger Things', 1, 1, 48, 2);
INSERT INTO episode (titulo, descripcion, numero_episodio, temporada, duracion, content_id) VALUES ('Capítulo 2: The Weirdo', 'Segundo episodio', 2, 1, 45, 2);

-- Insertar genero de contenido

INSERT INTO content_genre (content_id, genre_id) VALUES (1, 1); -- The Matrix - Ciencia Ficción
INSERT INTO content_genre (content_id, genre_id) VALUES (1, 2); -- The Matrix - Acción
INSERT INTO content_genre (content_id, genre_id) VALUES (2, 1); -- Stranger Things - Ciencia Ficción
INSERT INTO content_genre (content_id, genre_id) VALUES (2, 3); -- Stranger Things - Misterio
INSERT INTO content_genre (content_id, genre_id) VALUES (2, 4); -- Stranger Things - Terror

-- Insertar historial de vistas
INSERT INTO watch_history (profile_id, content_id, fecha_visto, duracion_vista) VALUES (1, 1, TIMESTAMP '2024-05-01 20:00:00', 120);
INSERT INTO watch_history (profile_id, content_id, fecha_visto, duracion_vista) VALUES (1, 2, TIMESTAMP '2024-05-02 21:30:00', 45);
INSERT INTO watch_history (profile_id, content_id, fecha_visto, duracion_vista) VALUES (2, 2, TIMESTAMP '2024-05-03 19:00:00', 48);

-- Insertar Favoritos
INSERT INTO favorite (profile_id, content_id) VALUES (1, 1);
INSERT INTO favorite (profile_id, content_id) VALUES (1, 2);
INSERT INTO favorite (profile_id, content_id) VALUES (3, 2);

-- Insertar ratings
INSERT INTO rating (profile_id, content_id, valor, comentario)VALUES (1, 1, 5, '¡Increíble película!');
INSERT INTO rating (profile_id, content_id, valor, comentario)VALUES (1, 2, 4, 'Buena serie, me gusta el misterio.');
INSERT INTO rating (profile_id, content_id, valor, comentario)VALUES (3, 2, 5, 'Mi favorita');
