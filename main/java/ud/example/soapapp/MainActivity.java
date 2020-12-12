package ud.example.soapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.nio.channels.AsynchronousChannelGroup;

import Clases.WebService;


public class MainActivity extends AppCompatActivity {

    private Button miboton;
    private TextView salidad;
    private TextView titulo;
    private TextView valoreuro;
    private EditText entrada;
    private RadioButton cambioP;
    private String editText;
    private String displayText;
    private String displayEuro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cambioP = findViewById(R.id.radioButton);
        cambioP.setChecked(true);
        entrada = findViewById(R.id.editTextTextNumber);
        valoreuro = findViewById(R.id.textView2);
        salidad = findViewById(R.id.textView4);
        titulo  = findViewById(R.id.textView);
        miboton = findViewById(R.id.button);
        miboton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View view){
                if (entrada.getText().length() != 0 && entrada.getText().toString() != "")
                {
                    editText = entrada.getText().toString();
                    CambioEuroWS task = new CambioEuroWS();
                    task.execute();
                    salidad.setVisibility(view.VISIBLE);
                }
            }
        });

        LLamadoInicialWS capini = new LLamadoInicialWS();
        capini.execute();

        AsyncCallWS capini1 = new AsyncCallWS();
        capini1.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class LLamadoInicialWS extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings){
            double temp = WebService.CaptureEurosWS("getEuro");
            int covt = (int) temp;
             displayEuro = String.valueOf(covt);


            return null;
        }
        @Override
        protected void onPostExecute(Void result){
            valoreuro.setText(displayEuro);
        }

         @Override
        protected void onPreExecute() {
            valoreuro.setText("Comenzo");
        }
    }

    private class CambioEuroWS extends AsyncTask<String,Void,Void>{
        @Override
        protected Void doInBackground(String... strings){
            double temp;
            int covt;
            if(cambioP.isChecked()){
                temp = WebService.CambioEuroWS("Euro2Pero",editText);
                covt = (int) temp;
                displayText = "PESOS  $" + String.valueOf(covt);
            } else {
                temp = WebService.CambioEuroWS("peso2Euro",editText);
                covt = (int) temp;
                displayText = "EUROS  €" + String.valueOf(covt);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            salidad.setText(displayText);}
        @Override
        protected void  onPreExecute() {
            salidad.setText("Conversion");
        }
    }

    private class AsyncCallWS extends  AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... params){
            displayText = WebService.HolaMundoWS(editText, "hello");
            return null;
        }
        @Override
        protected void onPostExecute(Void result){
            titulo.setText(displayText);
        }
        @Override
        protected void onPreExecute(){

        }
        @Override
        protected  void onProgressUpdate(Void... values){

        }

    }

}

