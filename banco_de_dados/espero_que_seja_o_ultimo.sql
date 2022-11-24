-- MySQL Script generated by MySQL Workbench
-- Wed Nov  9 11:48:58 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`estados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`estados` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(75) NULL DEFAULT NULL,
  `uf` VARCHAR(2) NULL DEFAULT NULL,
  `ibge` INT(2) NULL DEFAULT NULL,
  `pais` INT(3) NULL DEFAULT NULL,
  `ddd` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 100
DEFAULT CHARACTER SET = utf8
COMMENT = 'Unidades Federativas';


-- -----------------------------------------------------
-- Table `mydb`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`endereco` (
  `id_endereco` INT NOT NULL AUTO_INCREMENT,
  `bairro` VARCHAR(45) NULL,
  `rua` VARCHAR(45) NULL,
  `cidade` VARCHAR(45) NULL,
  `cep` VARCHAR(45) NULL,
  `id_estado` INT(11) NOT NULL,
  `numero` INT NULL,
  PRIMARY KEY (`id_endereco`),
  INDEX `fk_endereco_estados1_idx` (`id_estado` ASC),
  CONSTRAINT `fk_endereco_estados1`
    FOREIGN KEY (`id_estado`)
    REFERENCES `mydb`.`estados` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cliente` (
  `id_cliente` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(200) NOT NULL,
  `cpf` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `data_de_nasc` DATE NULL DEFAULT NULL,
  `id_endereco` INT NOT NULL,
  PRIMARY KEY (`id_cliente`),
  INDEX `fk_cliente_endereco1_idx` (`id_endereco` ASC),
  CONSTRAINT `fk_cliente_endereco1`
    FOREIGN KEY (`id_endereco`)
    REFERENCES `mydb`.`endereco` (`id_endereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`fornecedor` (
  `nome_empresa` VARCHAR(200) NOT NULL,
  `telefone` VARCHAR(30) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL,
  `id_endereco` INT NOT NULL,
  `cnpj` VARCHAR(45) NOT NULL,
  PRIMARY KEY ( `cnpj`),
  INDEX `fk_fornecedor_endereco1_idx` (`id_endereco` ASC),
  CONSTRAINT `fk_fornecedor_endereco1`
    FOREIGN KEY (`id_endereco`)
    REFERENCES `mydb`.`endereco` (`id_endereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`produto` (
  `id_produto` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_produto` VARCHAR(200) NOT NULL,
  `preco_produto` FLOAT NOT NULL,
  `estoque` INT(11) NOT NULL,
  `cnpj_fornecedor` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_produto`),
  INDEX `fk_fornecedor_idx` (`cnpj_fornecedor` ASC),
  CONSTRAINT `fk_fornecedor`
    FOREIGN KEY (`cnpj_fornecedor`)
    REFERENCES `mydb`.`fornecedor` (`cnpj`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`historico_produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`historico_produto` (
  `id_historico_produto` INT NOT NULL AUTO_INCREMENT,
  `id_produto` INT NOT NULL,
  `data_atualizacao` DATE NULL,
  `preco_novo` FLOAT NULL,
  `preco_antigo` FLOAT NULL,
  `cnpj_fornecedor` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_historico_produto`),
  INDEX `fk_historico_produto_fornecedor1_idx` (`cnpj_fornecedor` ASC),
  CONSTRAINT `fk_historico_produto_fornecedor1`
    FOREIGN KEY (`cnpj_fornecedor`)
    REFERENCES `mydb`.`fornecedor` (`cnpj`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuarios` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(30) NOT NULL,
  `nome` VARCHAR(200) NOT NULL,
  `senha` VARCHAR(10) NOT NULL,
  `cpf` VARCHAR(16) NULL DEFAULT NULL,
  `data_nascimento` DATE NULL DEFAULT NULL,
  `idade` INT NULL DEFAULT NULL,
  `permissao` TINYINT(4) NULL DEFAULT NULL,
  `ativo` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`venda` (
  `id_venda` INT NOT NULL AUTO_INCREMENT,
  `data_venda` DATE NOT NULL,
  `comissao_vendedor` FLOAT NULL DEFAULT NULL,
  `lucro` FLOAT NOT NULL,
  `id_cliente` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_venda`),
  INDEX `fk_Venda_idCliente` (`id_cliente` ASC),
  INDEX `fk_Venda_idUsuario` (`id_usuario` ASC),
  CONSTRAINT `fk_Venda_idCliente`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `mydb`.`cliente` (`id_cliente`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_Venda_idUsuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `mydb`.`usuarios` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`venda_produtos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`venda_produtos` (
  `id_venda_produto` INT NOT NULL AUTO_INCREMENT,
  `id_venda` INT NOT NULL,
  `quantidade_produto` INT NOT NULL,
  `id_historico_produto` INT NOT NULL,
  INDEX `fk_Venda_has_Produto_Venda1_idx` (`id_venda` ASC),
  INDEX `fk_vendaprodutos_historico_produto1_idx` (`id_historico_produto` ASC),
  PRIMARY KEY (`id_venda_produto`),
  CONSTRAINT `fk_Venda_has_Produto_Venda1`
    FOREIGN KEY (`id_venda`)
    REFERENCES `mydb`.`venda` (`id_venda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vendaprodutos_historico_produto1`
    FOREIGN KEY (`id_historico_produto`)
    REFERENCES `mydb`.`historico_produto` (`id_historico_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `mydb`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`produto_AFTER_INSERT` AFTER INSERT ON `produto` FOR EACH ROW
BEGIN
	insert into historico_produto values (null, new.id_produto, now(), new.preco_produto, new.preco_produto, new.cnpj_fornecedor);
END$$

USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`produto_AFTER_UPDATE` AFTER UPDATE ON `produto` FOR EACH ROW
BEGIN
	if new.preco_produto <> old.preco_produto then
		insert into historico_produto values (null, new.id_produto, now(), new.preco_produto, old.preco_produto, new.cnpj_fornecedor);
	end if;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO Estados(id, nome, uf, ibge, pais, ddd) VALUES
(1, 'Acre', 'AC', 12, 1, '68'),
(2, 'Alagoas', 'AL', 27, 1, '82'),
(3, 'Amazonas', 'AM', 13, 1, '97,92'),
(4, 'Amapá', 'AP', 16, 1, '96'),
(5, 'Bahia', 'BA', 29, 1, '77,75,73,74,71'),
(6, 'Ceará', 'CE', 23, 1, '88,85'),
(7, 'Distrito Federal', 'DF', 53, 1, '61'),
(8, 'Espírito Santo', 'ES', 32, 1, '28,27'),
(9, 'Goiás', 'GO', 52, 1, '62,64,61'),
(10, 'Maranhão', 'MA', 21, 1, '99,98'),
(11, 'Minas Gerais', 'MG', 31, 1, '34,37,31,33,35,38,32'),
(12, 'Mato Grosso do Sul', 'MS', 50, 1, '67'),
(13, 'Mato Grosso', 'MT', 51, 1, '65,66'),
(14, 'Pará', 'PA', 15, 1, '91,94,93'),
(15, 'Paraíba', 'PB', 25, 1, '83'),
(16, 'Pernambuco', 'PE', 26, 1, '81,87'),
(17, 'Piauí', 'PI', 22, 1, '89,86'),
(18, 'Paraná', 'PR', 41, 1, '43,41,42,44,45,46'),
(19, 'Rio de Janeiro', 'RJ', 33, 1, '24,22,21'),
(20, 'Rio Grande do Norte', 'RN', 24, 1, '84'),
(21, 'Rondônia', 'RO', 11, 1, '69'),
(22, 'Roraima', 'RR', 14, 1, '95'),
(23, 'Rio Grande do Sul', 'RS', 43, 1, '53,54,55,51'),
(24, 'Santa Catarina', 'SC', 42, 1, '47,48,49'),
(25, 'Sergipe', 'SE', 28, 1, '79'),
(26, 'São Paulo', 'SP', 35, 1, '11,12,13,14,15,16,17,18,19'),
(27, 'Tocantins', 'TO', 17, 1, '63'),
(99, 'Exterior', 'EX', 99, NULL, NULL);

insert into Usuarios (login, nome, senha, data_nascimento, cpf, idade, permissao, ativo) values ( 'arthur01' , 'Arthur' , '123' , '2004-10-14' , '08607179926' , 18, 1, 1);
insert into Usuarios (login, nome, senha, data_nascimento, cpf, idade, permissao, ativo) values ( 'gabi' , 'Gabrieli' , '123' , '2004-10-14' , '08607179926' , 18, 1, 1);
insert into Usuarios (login, nome, senha, data_nascimento, cpf, idade, permissao, ativo) values ( 'duda' , 'Eduarda' , '123' , '2004-10-14' , '08607179926' , 18, 1, 1);




insert into endereco ( bairro, rua, cidade, cep, numero, id_estado) values ('b','r','c','5465',30 ,1);
insert into cliente (nome, cpf, email, data_de_nasc, id_endereco)values ('Gabrieli','132456','e','2000-01-01',1);


insert into endereco ( bairro, rua, cidade, cep, numero,id_estado) values ('cajkb','fbai','cvaskb','1634', 15,1);
insert into fornecedor (nome_empresa, telefone,email, id_endereco,cnpj)values ( 'Nicolas' , '(47) 99999-9999' , 'e' , 2 , '1564' );
insert into endereco ( bairro, rua, cidade, cep, id_estado) values ('b','r','c','123456',24);
insert into fornecedor (nome_empresa, telefone,email, id_endereco,cnpj)values ( 'Marcao do gas' , '(47) 99999-9999' , 'e' , 3 , '987654' );


insert into produto (nome_produto, preco_produto, estoque,  cnpj_fornecedor) values ( 'Bolsa' , 3.0 , 5 ,  '1564' );
insert into produto (nome_produto, preco_produto, estoque,  cnpj_fornecedor) values ( 'Pote' , 3.0 , 5 ,  '1564' );
insert into produto (nome_produto, preco_produto, estoque,  cnpj_fornecedor) values ( 'Agua' , 3.0 , 5 ,  '1564' );
insert into produto (nome_produto, preco_produto, estoque,  cnpj_fornecedor) values ( 'Uva' , 3.0 , 5 ,  '1564' );
insert into produto (nome_produto, preco_produto, estoque,  cnpj_fornecedor) values ( 'Limao' , 3.0 , 5 ,  '1564' );


insert into venda (data_venda, comissao_vendedor, lucro, id_cliente, id_usuario)values ('2022-11-09',0.15,3.0,1,1);
insert into venda_produtos (id_venda, quantidade_produto, id_historico_produto)values (1,1,1);
insert into venda (data_venda, comissao_vendedor, lucro, id_cliente, id_usuario)values ('2022-11-09',0.3,6.0,1,1);
insert into venda_produtos (id_venda, quantidade_produto, id_historico_produto)values (2,2,1);