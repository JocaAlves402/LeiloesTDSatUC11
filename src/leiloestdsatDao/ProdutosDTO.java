
package leiloestdsatDao;

/**
 *
 * @author jocaa
 */
public class ProdutosDTO {
  private String id;
    private String nome;
    private Double valor;
    private String statusProd;

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getValor() {
        return valor;
    }

    public String getStatusProd() {
        return statusProd;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setStatusProd(String statusProd) {
        this.statusProd = statusProd;
    }  
}
