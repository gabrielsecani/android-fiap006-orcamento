package br.com.fiap.rm72468.orcamento;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends Activity {

    EditText txtCliente;
    EditText txtValordaNota;
    EditText txtCarga;

    TextView txtValorTotaldoFrete;

    Spinner spnEstado;
    Spinner spnCodigoCarga;

    RadioGroup rgIncluirSeguro;

    Button btnCalcularFrete;

    String[] estado = {"Rio de Janeiro", "Minas Gerais", "Paraná", "São Paulo"};
    String[] cargas = {"1","2","3","4","5","6","7","8","9","10",
            "11","12","13","14","15","16","17","18","19","20",
            "31","32","33","34","35","36","37","38","39","40"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCliente = (EditText) findViewById(R.id.txtCliente);
        txtValordaNota = (EditText) findViewById(R.id.txtValordaNota);
        txtCarga = (EditText) findViewById(R.id.txtCarga);
        txtValorTotaldoFrete = (TextView) findViewById(R.id.txtValorTotaldoFrete);

        spnEstado = (Spinner) findViewById(R.id.spnEstado);
        spnEstado.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estado));

        spnCodigoCarga = (Spinner) findViewById(R.id.spnCodigoCarga);
        spnCodigoCarga.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cargas));

        rgIncluirSeguro = (RadioGroup) findViewById(R.id.rgIncluirSeguro);

        btnCalcularFrete = (Button) findViewById(R.id.btnCalcularFrete);
        btnCalcularFrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularFrete();
            }
        });

    }

    private void calcularFrete() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
