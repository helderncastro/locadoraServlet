CREATE TABLE FILME 
(
    idFilme INTEGER,
    nomeFilme VARCHAR2(100),
    dataFilme DATE,
    qtdFilme INTEGER,
    classFilme VARCHAR2(50),
    valorFilme NUMBER(5,2),
    
    CONSTRAINT PK_FILME PRIMARY KEY (idFilme)    
    
);


CREATE TABLE CLIENTE
(
    idCliente	INTEGER,	
    nomeCliente	VARCHAR2(100),
    cpfCliente	VARCHAR2(14),
    tpLogCliente VARCHAR2(10),	
    logCliente	VARCHAR2(100), 
    numeroCliente	INTEGER,	
    compCliente	VARCHAR2(50),
    bairroCliente	VARCHAR2(50),
    cidadeCliente	VARCHAR2(100),
    cepCliente	CHAR(10),
    ufCliente	CHAR(2),
    telefoneCliente	VARCHAR2(14),
    emailCliente	VARCHAR2(100),

    CONSTRAINT PK_CLIENTE PRIMARY KEY (idCliente)    
    
);

CREATE TABLE LOCACAO (
    idLocacao	INTEGER PRIMARY KEY,
    dataLocacao	DATE,
    idCliLocacao	INTEGER,
    dataDevLocacao	DATE,
    finalizadoLocacao	CHAR(1),
    
    CONSTRAINT FK_CLIENTE FOREIGN KEY(idCliLocacao) REFERENCES CLIENTE(idCliente)
    
);

CREATE TABLE ITEM_LOCACAO (
    idItLocacao	INTEGER PRIMARY KEY,
    idFilmeItLocacao	INTEGER,
    idLocacaoItLocacao	INTEGER,
   
    CONSTRAINT FK_FILME FOREIGN KEY(idFilmeItLocacao) REFERENCES FILME(idFilme),
    CONSTRAINT FK_LOCACAO FOREIGN KEY (idLocacaoItLocacao) REFERENCES LOCACAO (idLocacao)
);



CREATE SEQUENCE filme_id_seq;

CREATE SEQUENCE cliente_id_seq;

CREATE SEQUENCE locacao_id_seq;

CREATE SEQUENCE itLocacao_id_seq;


CREATE TRIGGER cliente_bi
BEFORE INSERT ON cliente
FOR EACH ROW
BEGIN
  SELECT cliente_id_seq.nextval
  INTO :new.idCliente
  FROM dual;
END;

CREATE TRIGGER filme_bi
BEFORE INSERT ON filme
FOR EACH ROW
BEGIN
  SELECT filme_id_seq.nextval
  INTO :new.idFilme
  FROM dual;
END;

CREATE TRIGGER locacao_bi
BEFORE INSERT ON locacao
FOR EACH ROW
BEGIN
  SELECT locacao_id_seq.nextval
  INTO :new.idLocacao
  FROM dual;
END;

CREATE TRIGGER itemLocacao_bi
BEFORE INSERT ON ITEM_LOCACAO
FOR EACH ROW
BEGIN
  SELECT itLocacao_id_seq.nextval
  INTO :new.idItLocacao	
  FROM dual;
END;