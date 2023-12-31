package leiloestdsatDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jocaa
 */
public class ProdutosDAO {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;
    ArrayList<ProdutosDTO> lista = new ArrayList<>();

    /**
     * Conexão com o banco de dados MySQL
     */
    public boolean connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11", "root", "1505Jc109Sil");
            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }

    // Método para cadastrar filmes no bamco de dados
    public int cadastrar(ProdutosDTO produtos) {
        int status;
        try {
            st = conn.prepareStatement("INSERT INTO produtos VALUES(?,?,?,?)");
            st.setString(1, produtos.getId());
            st.setString(2, produtos.getNome());
            st.setDouble(3, produtos.getValor());
            st.setString(4, produtos.getStatusProd());
            status = st.executeUpdate();
            return status; //retornar 1
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }

    }

    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {
            //pode-se deixar vazio para evitar uma mensagem de erro desnecessária ao usuário
        }
    }

    public ProdutosDTO listarProdutos() throws SQLException {
        ProdutosDTO produto = new ProdutosDTO();

        try {
            st = conn.prepareStatement("SELECT * FROM produtos");

            rs = st.executeQuery();
            if (rs.next()) {
                produto.setId(rs.getString("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getDouble("valor"));
                produto.setStatusProd(rs.getString("status"));
                return produto;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Algo deu errado " + ex.getMessage());
            return null;
        } finally {
            conn.close();
        }
    }

    public int statusVendido(ProdutosDTO produto) throws SQLException {
        int status;
        try {
            st = conn.prepareStatement("UPDATE produtos SET status = ? where id = ?");
            st.setString(1, produto.getStatusProd());
            st.setString(2, produto.getId());
            status = st.executeUpdate();
            return status; //retornar 1
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            return ex.getErrorCode();
        } finally {
            conn.close();
        }
    }

    public ProdutosDTO listarProdutosVendidos(String statusProd) {
        ProdutosDTO produto = new ProdutosDTO();

        try {
            st = conn.prepareStatement("select * from filmes where status = ?");
            st.setString(1, statusProd);
            rs = st.executeQuery();
            if (rs.next()) {
                produto.setId(rs.getString("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(Double.parseDouble(rs.getString("valor")));
                produto.setStatusProd(rs.getString("Status"));
                return produto;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Algo deu errado, confira os dados digitados: " + ex.getMessage());
            return null;
        }

    } 
    public List<ProdutosDTO> ListaProdutosVendidos(String statusProd) {
      try {
            st = conn.prepareStatement("SELECT * FROM produtos WHERE status = ?");
        
          st.setString(1,statusProd);
          rs = st.executeQuery();            
          
          List<ProdutosDTO> lista = new ArrayList<>();
          //percorrer o resultSet e salvar as informações dentro de uma variável "ProdutoDTO"
          //Depois salva essa variavel dentro da lista
          
          //Estrutura de repetição While
          while (rs.next()) { //.next retorna verdadeiro caso exista uma próxima posição dentro do array
              ProdutosDTO produto = new ProdutosDTO();
              //Salvar dentro da variavel empresa, as informações            
              produto.setId(rs.getString("id"));
              produto.setNome(rs.getString("nome"));
              produto.setValor(rs.getDouble("valor"));
              produto.setStatusProd(rs.getString("status"));
              //Adicionando os elementos na lista criada
              lista.add(produto);
          }
          //Após finalizar o while, o retorno será a listaEmpresas, onde cada posição é um registro do banco de dados
          return lista;
          
          //Se o método entrar no "Catch" quer dizer que não encontrou nenhuma empresa, então damos um "return null"
      } catch (Exception e) {
          return null;
      }
      
  }
}
