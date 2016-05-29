package edm.senacrs.com.br.semgrilo.dao.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import edm.senacrs.com.br.semgrilo.dao.CategoriaDao;
import edm.senacrs.com.br.semgrilo.dao.TrabalhoDao;
import edm.senacrs.com.br.semgrilo.model.Categoria;
import edm.senacrs.com.br.semgrilo.model.Trabalho;

public class TrabalhoDaoBd implements TrabalhoDao {

    private CategoriaDao categoriaDao;
    private Context context;
    private BancoDadosOpenHelper bdOpenHelper;
    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.ENGLISH);

    public TrabalhoDaoBd(Context contexto) {
        this.bdOpenHelper = new BancoDadosOpenHelper(contexto);
        this.context = contexto;
    }

    @Override
    public List<Trabalho> procurarPorNome(String filtro) {

        List<Trabalho> listaContatos = new ArrayList<Trabalho>();

        SQLiteDatabase banco = bdOpenHelper.getReadableDatabase();
        Cursor cursor = banco.query("trabalho",
                new String[]{"id","titulo","descricao", "email", "dt_cadastro", "id_categoria", "dt_desativacao", "id_usuario"},
                "titulo LIKE ?", new String[]{"%"+filtro+"%"},
                null, null, null);

        while(cursor.moveToNext()){
            Trabalho trabalho = new Trabalho();
            trabalho.setId(cursor.getInt(cursor.getColumnIndex("id")));
            trabalho.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
            categoriaDao = new CategoriaDaoBd(this.context);
            Categoria c = categoriaDao.procurarPorId(cursor.getInt(cursor.getColumnIndex("id_categoria")));
            trabalho.setCategoria(c);
            listaContatos.add(trabalho);
        }
        return(listaContatos);
    }

    @Override
    public void salvar(Trabalho trabalho) {
        SQLiteDatabase banco = bdOpenHelper.getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("titulo", trabalho.getTitulo());
        dados.put("descricao", trabalho.getDescricao());
        dados.put("email", trabalho.getEmail());
        dados.put("id_categoria", trabalho.getCategoria().getId());
        dados.put("dt_cadastro", formatter.format(trabalho.getDtCadastro()));
        dados.put("dt_desativacao", formatter.format(addDias(trabalho.getDtDesativacao(), 90)));
        dados.put("id_usuario", (trabalho.getUsuario().getId() != null ? trabalho.getUsuario().getId() : 1));
        long id = banco.insert("trabalho", null, dados);
        trabalho.setId((int) id);
        banco.close();

    }

    @Override
    public void excluir(Trabalho entidade) {

    }

    @Override
    public void atualizar(Trabalho entidade) {

    }

    @Override
    public List<Trabalho> listar() {
        return null;
    }

    @Override
    public Trabalho procurarPorId(Integer id) {
        return null;
    }

    private static Date addDias(Date data , int dias) {
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        c.add(Calendar.DATE, dias);
        return c.getTime();
    }
}
