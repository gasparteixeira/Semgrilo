package edm.senacrs.com.br.semgrilo.dao;


import java.util.List;

public interface GenericDao<T> {
    void salvar(T entidade);
    void excluir(T entidade);
    void atualizar(T entidade);
    List<T> listar();
    T procurarPorId(Integer id);

}
