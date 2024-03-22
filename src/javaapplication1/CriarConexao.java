package javaapplication1;

import java.sql.*;

public class CriarConexao {

    CriarConexao(){}
    
    public static Connection conectar() throws ClassNotFoundException{
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/escola","root","");
            
            System.out.println("Connected");
            return connection;
        } catch(SQLException e){
            System.out.println("Erro: "+e);
            return null;
        }
    }
    
    
}
