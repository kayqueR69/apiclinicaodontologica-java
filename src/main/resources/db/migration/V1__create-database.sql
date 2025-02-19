CREATE TABLE cliente (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         nome VARCHAR(50) NOT NULL,
                         email VARCHAR(200) NOT NULL UNIQUE,
                         senha VARCHAR(200) NOT NULL
);

CREATE TABLE dentista (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          nome VARCHAR(50) NOT NULL,
                          especialidade VARCHAR(50),
                          email VARCHAR(200) NOT NULL UNIQUE,
                          senha VARCHAR(200) NOT NULL,
                          cro VARCHAR(200) NOT NULL
);

CREATE TABLE agendamento (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             idDentista INT NOT NULL,
                             idCliente INT NOT NULL,
                             dataAgendamento DATE NOT NULL,
                             motivo VARCHAR(200),
                             estado VARCHAR(50),
                             FOREIGN KEY (idDentista) REFERENCES dentista(id),
                             FOREIGN KEY (idCliente) REFERENCES cliente(id)
);