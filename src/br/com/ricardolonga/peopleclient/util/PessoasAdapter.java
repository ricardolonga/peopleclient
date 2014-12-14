package br.com.ricardolonga.peopleclient.util;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.ricardolonga.peopleclient.model.Pessoa;

public class PessoasAdapter extends BaseAdapter {

    private Context ctx;
    private List<Pessoa> pessoas;

    public PessoasAdapter(Context ctx, List<Pessoa> pessoas) {
        this.ctx = ctx;
        this.pessoas = pessoas;
    }

    @Override
    public int getCount() {
        return pessoas.size();
    }

    @Override
    public Object getItem(int location) {
        return pessoas.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(ctx);
        textView.setText(pessoas.get(position).getNome());
        return textView;
    }

}
