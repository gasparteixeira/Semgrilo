package edm.senacrs.com.br.semgrilo.dao;


import java.util.List;

import edm.senacrs.com.br.semgrilo.model.Trabalho;

public interface TrabalhoDao extends  GenericDao<Trabalho> {

    List<Trabalho> procurarPorNome(String palavra_chave);
}
