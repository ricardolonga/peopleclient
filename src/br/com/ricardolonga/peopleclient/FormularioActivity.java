package br.com.ricardolonga.peopleclient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.com.ricardolonga.peopleclient.model.Pessoa;
import br.com.ricardolonga.peopleclient.util.ConnectionUtil;
import br.com.ricardolonga.peopleclient.util.HttpHelper;

import com.google.gson.Gson;

public class FormularioActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
    }

    public void cadastrar(View v) {
        if (!ConnectionUtil.isConnected(this)) {
            Toast.makeText(this, "Sem conexão!", Toast.LENGTH_LONG).show();
            return;
        }

        new CadastrarNome().execute();
    }

    public void verTodos(View v) {
        if (!ConnectionUtil.isConnected(this)) {
            Toast.makeText(this, "Sem conexão!", Toast.LENGTH_LONG).show();
            return;
        }

        startActivity(new Intent(this, ListagemActivity.class));
    }

    private class CadastrarNome extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(FormularioActivity.this, "Aguarde", "Cadastrando...");
        }

        @Override
        protected Void doInBackground(Void... params) {
            String nome = ((EditText) findViewById(R.id.campoNome)).getText().toString();
            HttpHelper.doPost("http://192.168.0.180:8080/pessoas", new Gson().toJson(new Pessoa(nome)));
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            progressDialog.dismiss();
            ((EditText) findViewById(R.id.campoNome)).setText("");
            Toast.makeText(FormularioActivity.this, "Cadastrado com sucesso!", Toast.LENGTH_LONG).show();
        }
    }

}
