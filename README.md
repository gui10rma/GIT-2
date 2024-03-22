# Java-usando-JDBC
## Descrição 
Projeto criado em aula para realizar as operações CRUD em um banco de dados dentro de uma aplicação java, usando o JDBC do mySQL

## Tecnologias utilizadas
<a href="#"> <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"> </a>
<a href="#"> <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white"> </a> <br>
Para criar um servidor local MySQL foi utilizado o pacote XAMPP. O banco foi criado utilizando uma query curta, apenas a criação de um banco e uma tabela, que pode ser feito em uma ferramenta para criação de banco de dados.
O código está compilado na versão 1.8.0_381 do Java SE. 

## Como rodar o projeto
Usando o XAMPP como localhost na porta 3306, basta criar um banco chamado "escola" e uma tabela chamada "estudantes", da seguinte forma:
```mysql
CREATE DATABASE escola;
USE escola;

CREATE TABLE estudantes (
  id int primary key auto_increment,
  nome_aluno varchar(150),
  curso varchar(150),
  cidade varchar(100)
);
```
ou mudar o codigo para o banco que você quiser utilizar. Na classe CriarConexao, é só mudar a String dentro do getConnection() 
```java
public static Connection conectar() throws ClassNotFoundException{
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            // mude a linha abaixo
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/escola","root","root"); 
            
            System.out.println("Connected");
            return connection;
        } catch(SQLException e){
            System.out.println("Erro: "+e);
            return null;
        }
    }
```
