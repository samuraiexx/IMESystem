CREATE TABLE `dummy`.`aluno` (
  `alunoId` INT NOT NULL UNIQUE,
   `usuario` VARCHAR(45) NOT NULL UNIQUE,
  `senha` VARCHAR(255) NOT NULL,
   `nome` VARCHAR(45) NOT NULL,
  `anoGrad` SMALLINT NULL,
   PRIMARY KEY (`alunoId`));


CREATE TABLE `dummy`.`nota` (
  `alunoId` INT NULL,
  `periodo` SMALLINT NULL,
  `disciplina` VARCHAR(45) NULL,
  `VE` FLOAT NULL,
  `VC` FLOAT NULL,
  `VF` FLOAT NULL,
  INDEX `alunoId_idx` (`alunoId` ASC),
  CONSTRAINT `alunoId`
    FOREIGN KEY (`alunoId`)
      REFERENCES `dummy`.`aluno` (`alunoId`)
        ON DELETE NO ACTION
          ON UPDATE NO ACTION);

CREATE TABLE `dummy`.`falta` (
  `alunoId` INT NULL,
  `periodo` VARCHAR(45) NULL,
  `pontos` SMALLINT NULL,
  INDEX `alunoId_idx` (`alunoId` ASC),
  CONSTRAINT `alunoId2`
    FOREIGN KEY (`alunoId`)
      REFERENCES `dummy`.`aluno` (`alunoId`)
        ON DELETE NO ACTION
          ON UPDATE NO ACTION);

CREATE TABLE `dummy`.`admin` (
  `usuario` VARCHAR(30) NOT NULL,
  `senha` VARCHAR(255) NOT NULL);


