CREATE DATABASE IF NOT EXISTS  netflix_clone CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE netflix_clone;

-- Tabla: USER
CREATE TABLE IF NOT EXISTS  user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nombre_completo VARCHAR(255) NOT NULL,
    fecha_registro DATE NOT NULL,
    rol ENUM('ADMIN', 'CLIENTE') NOT NULL
);

-- Tabla: PROFILE
CREATE TABLE IF NOT EXISTS  profile (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    idioma VARCHAR(50),
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

-- Tabla: SUBSCRIPTION
CREATE TABLE IF NOT EXISTS  subscription (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('BÁSICO', 'ESTÁNDAR', 'PREMIUM') NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    duracion_meses INT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE,
    activo BOOLEAN DEFAULT TRUE,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

-- Tabla: CONTENT
CREATE TABLE IF NOT EXISTS  content (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descripcion TEXT,
    tipo ENUM('PELICULA', 'SERIE') NOT NULL,
    fecha_lanzamiento DATE,
    duracion INT, -- En minutos
    clasificacion VARCHAR(20)
);

-- Tabla: EPISODE
CREATE TABLE IF NOT EXISTS  episode (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descripcion TEXT,
    numero_episodio INT NOT NULL,
    temporada INT NOT NULL,
    duracion INT,
    content_id INT NOT NULL,
    FOREIGN KEY (content_id) REFERENCES content(id)
);

-- Tabla: GENRE
CREATE TABLE IF NOT EXISTS  genre (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE NOT NULL
);

-- Tabla intermedia: CONTENT_GENRE
CREATE TABLE IF NOT EXISTS content_genre (
    content_id INT NOT NULL,
    genre_id INT NOT NULL,
    PRIMARY KEY (content_id, genre_id),
    FOREIGN KEY (content_id) REFERENCES content(id),
    FOREIGN KEY (genre_id) REFERENCES genre(id)
);

-- Tabla: WATCH_HISTORY
CREATE TABLE IF NOT EXISTS  watch_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    profile_id INT NOT NULL,
    content_id INT NOT NULL,
    fecha_visto DATETIME NOT NULL,
    duracion_vista INT,
    FOREIGN KEY (profile_id) REFERENCES profile(id),
    FOREIGN KEY (content_id) REFERENCES content(id)
);

-- Tabla: FAVORITE
CREATE TABLE IF NOT EXISTS  favorite (
    id INT AUTO_INCREMENT PRIMARY KEY,
    profile_id INT NOT NULL,
    content_id INT NOT NULL,
    FOREIGN KEY (profile_id) REFERENCES profile(id),
    FOREIGN KEY (content_id) REFERENCES content(id)
);

-- Tabla: RATING
CREATE TABLE IF NOT EXISTS  rating (
    id INT AUTO_INCREMENT PRIMARY KEY,
    profile_id INT NOT NULL,
    content_id INT NOT NULL,
    valor INT CHECK (valor BETWEEN 1 AND 5),
    comentario TEXT,
    FOREIGN KEY (profile_id) REFERENCES profile(id),
    FOREIGN KEY (content_id) REFERENCES content(id)
);

-- Insertar usuairos
INSERT INTO user (email, password, nombre_completo, fecha_registro, rol) VALUES
('admin@netflixclone.com', 'admin123', 'Administrador', '2024-01-01', 'ADMIN'),
('john.doe@example.com', 'password123', 'John Doe', '2024-02-10', 'CLIENTE'),
('jane.smith@example.com', 'password456', 'Jane Smith', '2024-02-15', 'CLIENTE');

-- Insertar perfiles
INSERT INTO profile (nombre, idioma, user_id) VALUES
('John - Perfil 1', 'Español', 2),
('John - Perfil Kids', 'Inglés', 2),
('Jane - Perfil 1', 'Francés', 3);

-- Insertar subscription
INSERT INTO subscription (tipo, precio, duracion_meses, fecha_inicio, fecha_fin, activo, user_id) VALUES
('PREMIUM', 15.99, 12, '2024-02-10', '2025-02-10', TRUE, 2),
('ESTÁNDAR', 12.99, 6, '2024-02-15', '2024-08-15', TRUE, 3);

-- Insertar contenido
INSERT INTO content (titulo, descripcion, tipo, fecha_lanzamiento, duracion, clasificacion) VALUES
('The Matrix', 'Película de ciencia ficción', 'PELICULA', '1999-03-31', 136, '+16'),
('Stranger Things', 'Serie de misterio y ciencia ficción', 'SERIE', '2016-07-15', NULL, '+13');

-- Insertar epidodios
INSERT INTO episode (titulo, descripcion, numero_episodio, temporada, duracion, content_id) VALUES
('Capítulo 1: Vanishing', 'Primer episodio de Stranger Things', 1, 1, 48, 2),
('Capítulo 2: The Weirdo', 'Segundo episodio', 2, 1, 45, 2);

-- Insertar Genero
INSERT INTO genre (nombre) VALUES
('Ciencia Ficción'),
('Acción'),
('Misterio'),
('Terror');

-- Insertar genero de contenido
INSERT INTO content_genre (content_id, genre_id) VALUES
(1, 1), -- The Matrix - Ciencia Ficción
(1, 2), -- The Matrix - Acción
(2, 1), -- Stranger Things - Ciencia Ficción
(2, 3), -- Stranger Things - Misterio
(2, 4); -- Stranger Things - Terror

-- Insertar HIstorial de vistas
INSERT INTO watch_history (profile_id, content_id, fecha_visto, duracion_vista) VALUES
(1, 1, '2024-05-01 20:00:00', 120),
(1, 2, '2024-05-02 21:30:00', 45),
(2, 2, '2024-05-03 19:00:00', 48);

-- Insertar Favoritos
INSERT INTO favorite (profile_id, content_id) VALUES
(1, 1),
(1, 2),
(3, 2);

-- Insertar ratings
INSERT INTO rating (profile_id, content_id, valor, comentario) VALUES
(1, 1, 5, '¡Increíble película!'),
(1, 2, 4, 'Buena serie, me gusta el misterio.'),
(3, 2, 5, 'Mi favorita');

