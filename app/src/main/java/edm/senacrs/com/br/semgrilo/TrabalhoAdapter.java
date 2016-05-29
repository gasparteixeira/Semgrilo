package edm.senacrs.com.br.semgrilo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import edm.senacrs.com.br.semgrilo.model.Trabalho;

public class TrabalhoAdapter extends BaseAdapter {

    private List<Trabalho> listaTrabalhos;
    private Context contexto;

    public TrabalhoAdapter(Context contexto, List<Trabalho> listaTrabalho) {
        this.contexto = contexto;
        this.listaTrabalhos = listaTrabalho;
    }

    @Override
    public int getCount() {
        return listaTrabalhos.size();
    }

    @Override
    public Object getItem(int position) {
        return (listaTrabalhos.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Trabalho trabalho = listaTrabalhos.get(position);
        LayoutInflater inflater = (LayoutInflater)contexto.getSystemService(
                contexto.LAYOUT_INFLATER_SERVICE
        );

        View viewInflate = inflater.inflate(R.layout.list_item,null);
        TextView textNome = (TextView)viewInflate.findViewById(R.id.item_titulo);
        textNome.setText(trabalho.getTitulo());

        TextView textTelefone = (TextView)viewInflate.findViewById(R.id.item_categoria);
        textTelefone.setText(trabalho.getCategoria().getNome());

        return viewInflate;
    }
}
