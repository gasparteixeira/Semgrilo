package edm.senacrs.com.br.semgrilo.dao.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edm.senacrs.com.br.semgrilo.dao.CategoriaDao;
import edm.senacrs.com.br.semgrilo.model.Categoria;

public class CategoriaDaoBd implements CategoriaDao {
    private BancoDadosOpenHelper bdOpenHelper;

    public CategoriaDaoBd(Context contexto) {
        this.bdOpenHelper = new BancoDadosOpenHelper(contexto);
    }

    @Override
    public List<Categoria> procurarPorNome(String palavra_chave) {
        return null;
    }

    @Override
    public void limparCategorias() {
        SQLiteDatabase banco = bdOpenHelper.getWritableDatabase();
        banco.delete("categoria", null, null);
        banco.close();
    }

    @Override
    public void salvar(Categoria categoria) {
        SQLiteDatabase banco = bdOpenHelper.getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("nome", categoria.getNome());
        long id = banco.insert("categoria", null, dados);
        categoria.setId((int) id);
        banco.close();

    }

    @Override
    public void excluir(Categoria entidade) {


    }

    @Override
    public void atualizar(Categoria entidade) {

    }

    @Override
    public List<Categoria> listar() {
        List<Categoria> listaCategorias = new ArrayList<Categoria>();

        SQLiteDatabase banco = bdOpenHelper.getReadableDatabase();
        Cursor cursor = banco.query("categoria",
                new String[]{"id","nome"},
                null, null, null, null, null);

        while(cursor.moveToNext()){
            Categoria categoria = new Categoria((cursor.getInt(cursor.getColumnIndex("id"))),
                    cursor.getString(cursor.getColumnIndex("nome")));

            listaCategorias.add(categoria);
        }
        return(listaCategorias);
    }

    @Override
    public Categoria procurarPorId(Integer id) {

        SQLiteDatabase banco = bdOpenHelper.getReadableDatabase();
        Cursor cursor = banco.query("categoria",
                new String[]{"id","nome"},
                "id=?", new String[]{id.toString()},
                null, null, null);

        if(cursor.moveToNext()){
            Categoria categoria = new Categoria((cursor.getInt(cursor.getColumnIndex("id"))),
                    cursor.getString(cursor.getColumnIndex("nome")));

            return(categoria);
        }
        return(null);
    }


}
