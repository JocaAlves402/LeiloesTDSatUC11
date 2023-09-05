
package leiloestdsatDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jocaa
 */
public class ProdutosDAO {
    Connection conn;
    PreparedStatement st;
    ResultSet rs;
    
    /**
     * Conexão com o banco de dados MySQL
     */
    public boolean connectDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11","root", "1505Jc109Sil");
            return true;
           
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }

   // Método para cadastrar filmes no bamco de dados
    public int cadastrar(ProdutosDTO produtos){
        int status;
        try {
            st = conn.prepareStatement("INSERT INTO produtos VALUES(?,?,?,?)");
            st.setString(1,produtos.getId());
            st.setString(2,produtos.getNome());
            st.setDouble(3,produtos.getValor());
            st.setString(4,produtos.getStatusProd());
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
