/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.sql.*;
import javax.swing.JOptionPane;

public class Main {
    
    public static void main(String[] args) throws ClassNotFoundException {
        String option;
        
        Connection conn = CriarConexao.conectar();
        Crud crud = new Crud();
        
        do{
            option = JOptionPane.showInputDialog("Sistema de cadastro de alunos\n\n1 - Cadastar\n2 - Atualizar\n3 - Excluir\n4 - Mostrar alunos\n\n0 - Sair");

            switch(option){
                case "0":
                    try{
                        conn.close();
                    } catch (SQLException e){}
                    System.exit(1);
                    break;
                case "1":
                    String nome, dept, cidade;
                    
                    nome = JOptionPane.showInputDialog("Insira o seu nome");
                    dept = JOptionPane.showInputDialog("Insira seu departamento");
                    cidade = JOptionPane.showInputDialog("Insira sua cidade");

                    Crud.cadastrar(conn, nome, dept, cidade);
                    break;
                case "2":
                    int id_up;
                    
                    id_up = Integer.parseInt(JOptionPane.showInputDialog("Insira o id do aluno que você quer atualizar"));
                    Crud.atualizar(conn, id_up);
                    break;
                case "3":
                    int id_del;
                    
                    id_del = Integer.parseInt(JOptionPane.showInputDialog("Insira o id do aluno que você quer excluir"));
                    Crud.deletar(conn, id_del);
                    break;
                case "4":
                    Crud.mostrarAlunos(conn);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção invalida", "Invalido", JOptionPane.ERROR_MESSAGE);
            }
            
        } while (!(option.equals("0")));
    }
}
