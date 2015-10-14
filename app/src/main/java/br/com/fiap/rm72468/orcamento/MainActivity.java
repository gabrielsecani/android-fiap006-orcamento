package br.com.fiap.rm72468.orcamento;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;


public class MainActivity extends Activity {

    EditText txtCliente;
    EditText txtValordaNota;
    EditText txtCarga;

    TextView lblValorTotaldoFrete;

    Spinner spnEstado;
    Spinner spnCodigoCarga;

    RadioGroup rgIncluirSeguro;

    Button btnCalcularFrete;

    String[] estado = {"Rio de Janeiro", "Minas Gerais", "Paraná", "São Paulo"};
    String[] codigoCargas = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "31", "32", "33", "34", "35", "36", "37", "38", "39", "40"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCliente = (EditText) findViewById(R.id.txtCliente);
        txtValordaNota = (EditText) findViewById(R.id.txtValordaNota);
        txtCarga = (EditText) findViewById(R.id.txtCarga);

        lblValorTotaldoFrete = (TextView) findViewById(R.id.lblValorTotaldoFrete);

        // preeenche spinner Estado
        spnEstado = (Spinner) findViewById(R.id.spnEstado);
        spnEstado.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estado));

        // preeenche spinner Codigo da Carga
        spnCodigoCarga = (Spinner) findViewById(R.id.spnCodigoCarga);
        spnCodigoCarga.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, codigoCargas));

        rgIncluirSeguro = (RadioGroup) findViewById(R.id.rgIncluirSeguro);

        btnCalcularFrete = (Button) findViewById(R.id.btnCalcularFrete);
        btnCalcularFrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularFrete();
            }
        });

    }

    private double getDoubleTextView(TextView v) {
        try {
            return Double.parseDouble(String.valueOf(v.getText()));
        } catch (Exception e) {
            return 0.0;
        }
    }

    private void calcularFrete() {
        double valorNota = getDoubleTextView(txtValordaNota);
        double carga = getDoubleTextView(txtCarga);
        double valorFrete;
        double imposto = 0;
        String estadoSelecionado = spnEstado.getSelectedItem().toString();
        switch (estadoSelecionado) {
            case "Rio de Janeiro":
                imposto = 0.25 * valorNota;
                break;
            case "Minas Gerais":
                imposto = 0.20 * valorNota;
                break;
            case "Paraná":
                imposto = 0.15 * valorNota;
                break;
            case "São Paulo":
                imposto = 0.12 * valorNota;
                break;
        }
        double pesoCarga = 0;
        int codigoCargaSelecionado = Integer.parseInt(String.valueOf(spnCodigoCarga.getSelectedItem()));
        if (codigoCargaSelecionado >= 1 && codigoCargaSelecionado <= 10)
            pesoCarga = carga * 120;
        else if (codigoCargaSelecionado >= 11 && codigoCargaSelecionado <= 20)
            pesoCarga = carga * 200;
        else if (codigoCargaSelecionado >= 31 && codigoCargaSelecionado <= 40)
            pesoCarga = carga * 280;

        // Frete = Imposto_Sobre_Nota + Código_da_Carga*Preço_Por_Quilo
        valorFrete = imposto + pesoCarga;

        if ((rgIncluirSeguro.getCheckedRadioButtonId()) == R.id.rbSim) {
            valorFrete += valorNota * 0.1;
        }

        NumberFormat nf = NumberFormat.getCurrencyInstance();
        lblValorTotaldoFrete.setText(nf.format(valorFrete));
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
