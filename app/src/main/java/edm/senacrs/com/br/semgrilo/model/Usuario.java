package edm.senacrs.com.br.semgrilo.model;

import java.io.Serializable;
import java.util.Date;

public class Usuario  implements Serializable{

    private Integer id;
    private String nome;
    private String documento;
    private String email;
    private String telefone;
    private String senha;
    private Date dtcadastro;

    public Usuario() {
    }

    public Usuario(String nome, String documento, String email, String telefone, String senha, Date dtcadastro) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.dtcadastro = dtcadastro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDtcadastro() {
        return dtcadastro;
    }

    public void setDtcadastro(Date dtcadastro) {
        this.dtcadastro = dtcadastro;
    }
}
