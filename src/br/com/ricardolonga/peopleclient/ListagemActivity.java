/**
 * 
 */
package br.com.ricardolonga.peopleclient;

import java.util.Arrays;
import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import br.com.ricardolonga.peopleclient.model.Pessoa;
import br.com.ricardolonga.peopleclient.util.HttpHelper;
import br.com.ricardolonga.peopleclient.util.PessoasAdapter;

import com.google.gson.Gson;

public class ListagemActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ConsultarNomes().execute();
    }

    private class ConsultarNomes extends AsyncTask<Void, Void, List<Pessoa>> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(ListagemActivity.this, "Aguarde", "Buscando as pessoas...");
        }

        @Override
        protected List<Pessoa> doInBackground(Void... params) {
            String pessoasJson = HttpHelper.doGet("http://172.16.200.216:8080/pessoas");

            return Arrays.asList(new Gson().fromJson(pessoasJson, Pessoa[].class));
        }

        @Override
        protected void onPostExecute(List<Pessoa> pessoas) {
            super.onPostExecute(pessoas);
            setListAdapter(new PessoasAdapter(ListagemActivity.this, pessoas));
            progressDialog.dismiss();
            Toast.makeText(ListagemActivity.this, "Retornou " + pessoas.size() + " nomes.", Toast.LENGTH_LONG).show();
        }
    }

}
