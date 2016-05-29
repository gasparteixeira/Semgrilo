package edm.senacrs.com.br.semgrilo.dao;


import java.util.List;

import edm.senacrs.com.br.semgrilo.model.Categoria;

public interface CategoriaDao extends  GenericDao<Categoria> {

    List<Categoria> procurarPorNome(String palavra_chave);
    void limparCategorias();

}
