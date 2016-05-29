package edm.senacrs.com.br.semgrilo.dao;


import java.util.List;

import edm.senacrs.com.br.semgrilo.model.Usuario;

public interface UsuarioDao extends GenericDao<Usuario> {

    List<Usuario> procurarPorNome(String palavra_chave);
}
