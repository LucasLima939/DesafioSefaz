CREATE TABLE Usuario (
  email VARCHAR2(60) NOT NULL,
  nome VARCHAR2(60) NOT NULL,
  senha VARCHAR2(60) NOT NULL,
  PRIMARY KEY (EMAIL)
);

CREATE TABLE Telefone (
  numero VARCHAR2(11) NOT NULL,
  ddd NUMBER(3) NOT NULL,
  tipo VARCHAR(10) NOT NULL,
  usuario_email VARCHAR2(60) NOT NULL,
  PRIMARY KEY (NUMERO)
);

ALTER TABLE Telefone
ADD CONSTRAINT fk_telefone_usuario
FOREIGN KEY (usuario_email)
REFERENCES Usuario(email) ON DELETE CASCADE;




