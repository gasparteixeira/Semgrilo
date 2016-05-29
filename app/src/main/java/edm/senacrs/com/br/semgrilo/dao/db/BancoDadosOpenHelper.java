package edm.senacrs.com.br.semgrilo.dao.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDadosOpenHelper extends SQLiteOpenHelper {

    private static String nome ="semgrilo.db";

    private static String categoria = "CREATE TABLE categoria" +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nome VARCHAR(200))";

    private static String usuario = "CREATE TABLE usuario" +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nome VARCHAR(200)," +
            "documento VARCHAR(14)," +
            "email VARCHAR(100)," +
            "telefone VARCHAR(32)," +
            "senha VARCHAR(40)," +
            "dt_cadastro DATE)";

    private static String trabalho ="CREATE TABLE trabalho" +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "titulo VARCHAR(200), " +
            "descricao TEXT, " +
            "email VARCHAR(100), " +
            "dt_cadastro DATE, " +
            "id_categoria INTEGER, " +
            "dt_desativacao DATE, " +
            "id_usuario INTEGER )";

    public BancoDadosOpenHelper(Context contexto) {
        super(contexto, nome, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase banco) {
        banco.execSQL(categoria);
        banco.execSQL(usuario);
        banco.execSQL(trabalho);
    }

    @Override
    public void onUpgrade(SQLiteDatabase banco, int versaoAntiga, int versaoNova) {
        banco.execSQL("DROP TABLE categoria");
        banco.execSQL("DROP TABLE usuario");
        banco.execSQL("DROP TABLE trabalho");
        onCreate(banco);
    }

}
