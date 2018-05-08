package com.example.estagiario_01.httpteste;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.estagiario_01.httpteste.Utilidades.RedeUtil;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText editPesquisa;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editPesquisa = findViewById(R.id.editText);
        result = findViewById(R.id.textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.buscarMenu){
            buscaHttp();
            return true;
        }else
            return false;
    }

    private void buscaHttp() {
        String pesquisa = editPesquisa.getText().toString();
        URL urlDaPesquisa = RedeUtil.buildUrl(pesquisa);
        new HttpTask().execute(urlDaPesquisa);
    }

    public class HttpTask extends AsyncTask<URL,Void,String>{

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String resultados = "";
            try {
                resultados = RedeUtil.pegarRespostaHttp(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return resultados;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null | !s.isEmpty()) {
                result.setText(s);
            }
        }
    }
}
