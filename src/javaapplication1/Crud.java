package javaapplication1;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Crud {
    
    public static void cadastrar(Connection co, String nome, String curso, String cidade){
        PreparedStatement ps = null;
        
        try{
            ps = co.prepareStatement("insert into estudantes values(null, ?,?,?)");
            
            ps.setString(1, nome);
            ps.setString(2, curso);
            ps.setString(3, cidade);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            try{
                ps.close();
            } catch(SQLException e){}
        }
    }
    
    public static void atualizar(Connection co, int id){
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            String old_nome="", old_curso="", old_cidade="";
            String novo_nome="", novo_curso="", novo_cidade="";
            
            ps = co.prepareStatement("Select nome_aluno, curso, cidade from estudantes where id=?");
            ps.setInt(1,id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                old_nome = rs.getString("nome_aluno");
                old_curso = rs.getString("curso");
                old_cidade = rs.getString("cidade");
            }
            
            if(rs.next() == true){
                novo_nome = JOptionPane.showInputDialog("Nome antigo: "+old_nome+"\n\nInsira o novo nome");
                novo_curso = JOptionPane.showInputDialog("Curso antigo: "+old_curso+"\n\nInsira o novo curso");
                novo_cidade = JOptionPane.showInputDialog("Cidade antiga: "+old_cidade+"\n\nInsira a nova cidade");
                
                ps = co.prepareStatement("update estudantes set nome_aluno=?, curso=?, cidade=? where id=?");
                ps.setString(1,novo_nome);
                ps.setString(2,novo_curso);
                ps.setString(3,novo_cidade);
                ps.setInt(4, id);

                ps.executeUpdate();
                System.out.println("id "+id+" atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "id não existe", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            try{
                ps.close();
                rs.close();
            } catch(SQLException e){}
        }

    }
    
    public static void deletar(Connection co, int id){
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = co.prepareStatement("Select nome_aluno, curso, cidade from estudantes where id=?");
            ps.setInt(1,id);
            
            rs = ps.executeQuery();
            
            if(rs.next() == true){
                ps = co.prepareStatement("delete from estudantes where id=?");
                ps.setInt(1, id);

                ps.execute();
                JOptionPane.showMessageDialog(null, "Aluno excluido com sucesso", "Exclusao", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Id não existe:", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            try{
                ps.close();
                rs.close();
            } catch(SQLException e){}
        }
    }
    
    public static void mostrarAlunos(Connection co){
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            String lista_alunos = "";
            
            ps = co.prepareStatement("Select * from estudantes");
            rs = ps.executeQuery();
            
            while(rs.next()){
                lista_alunos += rs.getInt("id")+", "+rs.getString("nome_aluno")+", "
                        +rs.getString("curso")+", "+rs.getString("cidade")+"\n";
            }
            
            JOptionPane.showMessageDialog(null, "Alunos cadastrados:\n\n"+lista_alunos, "Alunos", JOptionPane.INFORMATION_MESSAGE);

        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao buscar: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            try{
                ps.close();
                rs.close();
            } catch(SQLException e){}
        }
    }
}
