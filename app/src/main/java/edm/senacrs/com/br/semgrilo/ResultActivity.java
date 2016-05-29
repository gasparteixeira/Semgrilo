package edm.senacrs.com.br.semgrilo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import edm.senacrs.com.br.semgrilo.dao.TrabalhoDao;
import edm.senacrs.com.br.semgrilo.dao.db.TrabalhoDaoBd;
import edm.senacrs.com.br.semgrilo.model.Categoria;
import edm.senacrs.com.br.semgrilo.model.Trabalho;

public class ResultActivity extends AppCompatActivity {

    private TrabalhoDao trabalhoDao;
    private Trabalho trabalhoSelecionado;
    private ListView listView;
    private List<Trabalho> listaTrabalhos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        trabalhoDao = new TrabalhoDaoBd(this);

        Intent intent = getIntent();
        String palavra = intent.getStringExtra("palavra");

        TrabalhoAdapter adapter = new TrabalhoAdapter(this, trabalhoDao.procurarPorNome(palavra));

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent it = new Intent(ResultActivity.this,MainActivity.class);
                it.putExtra("trabalho",(Trabalho) adapterView.getItemAtPosition(pos));
                startActivity(it);
            }
        });


    }
}
