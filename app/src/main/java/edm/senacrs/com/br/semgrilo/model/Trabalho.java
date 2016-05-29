package edm.senacrs.com.br.semgrilo.model;

import java.io.Serializable;
import java.util.Date;

public class Trabalho implements Serializable {

    private Integer id;
    private String titulo;
    private String descricao;
    private String email;
    private Categoria categoria;
    private Date dtCadastro;
    private Date dtDesativacao;
    private Usuario usuario;

    public Trabalho() {
    }

    public Trabalho(Integer id, String titulo, String descricao, String email, Categoria categoria, Date dtCadastro, Date dtDesativacao, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.email = email;
        this.categoria = categoria;
        this.dtCadastro = dtCadastro;
        this.dtDesativacao = dtDesativacao;
        this.usuario = usuario;
    }



    public Trabalho(String titulo, String descricao, String email, Categoria categoria, Date dtCadastro, Date dtDesativacao, Usuario usuario) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.email = email;
        this.categoria = categoria;
        this.dtCadastro = dtCadastro;
        this.dtDesativacao = dtDesativacao;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDtDesativacao() {
        return dtDesativacao;
    }

    public void setDtDesativacao(Date dtDesativacao) {
        this.dtDesativacao = dtDesativacao;
    }
}
