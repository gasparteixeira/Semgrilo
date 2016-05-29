package edm.senacrs.com.br.semgrilo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.List;

import edm.senacrs.com.br.semgrilo.dao.CategoriaDao;
import edm.senacrs.com.br.semgrilo.dao.db.CategoriaDaoBd;
import edm.senacrs.com.br.semgrilo.model.Categoria;


public class PostFragment extends Fragment{


    public PostFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_post, container, false);
        CategoriaDao categorias = new CategoriaDaoBd(this.getActivity());
        List<Categoria> valores = categorias.listar();
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, valores);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        return v;
    }




}
