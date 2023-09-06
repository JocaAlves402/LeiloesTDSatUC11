
package leiloestdsatDao;

/**
 *
 * @author jocaa
 */
import java.util.List;
import java.util.ArrayList;

public class ListaProdutos {

    private static final List<ProdutosDTO> lista = new ArrayList<>();
    
    public static List<ProdutosDTO> Listar(){
        return lista;
    
    }
    
    public static void Adicionar(ProdutosDTO produto){
        lista.add(produto);
    }
    
}