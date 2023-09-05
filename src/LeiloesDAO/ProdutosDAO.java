package LeiloesDAO;

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
  Connection conn;
    PreparedStatement st;
    ResultSet rs;
    
    /**
     * Conexão com o banco de dados MySQL
     */
    public boolean conectar(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11","root", "1505Jc109Sil");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }
    /**
     * Desconexão com o banco de dados MySQL
     * @param produtos
     */
     
    // Método para cadastrar filmes no bamco de dados
    public int cadastrar(ProdutosDTO produtos){
        int status;
        try {
            st = conn.prepareStatement("INSERT INTO produtos VALUES(?,?,?)");
           
            st.setString(1,produtos.getNome());
            st.setDouble(2,produtos.getValor());
            st.setString(3,produtos.getStatusProd());
            status = st.executeUpdate();
            return status; //retornar 1
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
public void desconectar(){
        try {
            conn.close();
        } catch (SQLException ex) {
            //pode-se deixar vazio para evitar uma mensagem de erro desnecessária ao usuário
        }
    }
}

