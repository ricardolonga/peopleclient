package br.com.ricardolonga.peopleclient.util;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.ricardolonga.peopleclient.FormularioActivity;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(ctx);

        textView.setText(pessoas.get(position).getNome());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeletarNome(pessoas.get(position).getNome()).execute();
            }
        });

        return textView;
    }

    private class DeletarNome extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog;

        private String nome;

        public DeletarNome(String nome) {
            this.nome = nome;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(ctx, "Aguarde", "Deletando...");
        }

        @Override
        protected Void doInBackground(Void... params) {
            HttpHelper.doDelete(FormularioActivity.SERVER_URL + "/pessoas/" + nome);
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            progressDialog.dismiss();
        }
    }

}
