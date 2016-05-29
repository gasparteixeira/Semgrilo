package edm.senacrs.com.br.semgrilo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edm.senacrs.com.br.semgrilo.adapter.PagerAdapter;
import edm.senacrs.com.br.semgrilo.dao.CategoriaDao;
import edm.senacrs.com.br.semgrilo.dao.TrabalhoDao;
import edm.senacrs.com.br.semgrilo.dao.db.CategoriaDaoBd;
import edm.senacrs.com.br.semgrilo.dao.db.TrabalhoDaoBd;
import edm.senacrs.com.br.semgrilo.model.Categoria;
import edm.senacrs.com.br.semgrilo.model.Trabalho;
import edm.senacrs.com.br.semgrilo.model.Usuario;

public class MainActivity extends AppCompatActivity {

    private CategoriaDao categoriaDao;
    private TrabalhoDao trabalhoDao;
    private List<Categoria> categorias;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        /*ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/

        /*NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/


        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Buscar"));
        tabLayout.addTab(tabLayout.newTab().setText("Publicar"));
        tabLayout.addTab(tabLayout.newTab().setText("Registre-se"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        categoriaDao = new CategoriaDaoBd(this);
        trabalhoDao = new TrabalhoDaoBd(this);
        autoCategoria();


    }

    public void openDialog(View view) {
        String text = "<p>Sem grilo é uma gíria que significa sem problema!<p>" +
                "<p>Com este objetivo resolvi criar um APP para facilitar profissionais de TI em fazer um trabalho 'EXTRA' e ganhar algum dinheiro com isso!<p>" +
                "<p><b>SEM FINS LUCRATIVOS!</b><br/>Este aplicativo apenas incentiva os profissionais a terem mais autonomia e conhecer as demandas que o mercado de TI necessida.</p>" +
                "<p><b>FUNCIONA ASSIM:</b><br/>O cliente entra e posta um JOB. O desenvolvedor interessado, entra em contato com o cliente e oferece seus serviços para fazer a demanda.</p>";
        new AlertDialog.Builder(this)
                .setTitle("O QUE É SEMGRILO?")
                .setMessage(Html.fromHtml(text))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onCadastro(View view) {

        TextView titulo = (TextView) findViewById(R.id.input_titulo);
        TextView descricao = (TextView) findViewById(R.id.input_descricao);
        TextView email = (TextView) findViewById(R.id.input_email);
        Categoria categoria = (Categoria) ((Spinner) findViewById(R.id.spinner)).getSelectedItem();
        Usuario usuario = new Usuario("Gaspar","78797586072","gaspar.teixeira@gmail.com","5182272599","inter", new Date());

        if(TextUtils.isEmpty(titulo.getText().toString().trim())){
            titulo.setError("O título está inválido.");
            return;
        }

        if(TextUtils.isEmpty(descricao.getText().toString().trim())) {
            descricao.setError("Informe uma descrição.");
            return;
        }

        if(TextUtils.isEmpty(email.getText().toString().trim())) {
            email.setError("Informe um endereço de e-mail.");
            return;
        }

        Trabalho trabalho = new Trabalho(titulo.getText().toString().trim(), descricao.getText().toString().trim(), email.getText().toString().trim(), categoria, new Date(), new Date(), usuario);
        try {
            trabalhoDao.salvar(trabalho);
            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
            descricao.setText("");
            email.setText("");
            titulo.setText("");
        } catch (SQLiteException exception) {
            Log.i("erro ao inserir.", exception.getMessage().toString());
            Toast.makeText(this, "Erro ao inserir. Tente novamente!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onSearch(View view) {
        EditText search = (EditText)findViewById(R.id.input_search);

        if(TextUtils.isEmpty(search.getText().toString().trim())) {
            search.setError("Informe uma palavra chave.");
            return;
        }

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("palavra", search.getText().toString().trim());
        startActivity(intent);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    private void autoCategoria(){

        List<Categoria> valores = categoriaDao.listar();
        if(valores.size() < 1) {
            categoriaDao.limparCategorias();
            categorias = new ArrayList<Categoria>();
            categorias.add(new Categoria("CATEGORIA GERAL"));
            categorias.add(new Categoria("Android"));
            categorias.add(new Categoria("IOS"));
            categorias.add(new Categoria("Java"));
            categorias.add(new Categoria("PHP"));
            categorias.add(new Categoria("HTMl"));
            categorias.add(new Categoria("Oracle"));
            categorias.add(new Categoria("Outra"));
            for (Categoria c : categorias) {
                categoriaDao.salvar(c);
            }
        }
    }

}
