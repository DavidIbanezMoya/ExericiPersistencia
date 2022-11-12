package com.example.exercicipersistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText textinicial = (EditText) (findViewById(R.id.editText));
        Button boto = (Button) (findViewById(R.id.button));
        //Carregar el contingut de text de l arxiu de nou

        String datos = Arrays.toString(this.getFilesDir().list());
        //Si l arxiu no existeix crearem un
        if (!datos.contains("dades.txt")) {
            File rutaArxiu = new File(this.getFilesDir() + File.separator + "dades.txt");
            OutputStream os = null;
            try {
                os = openFileOutput("dades.txt", MODE_PRIVATE);
                String text = "Primer missatge";
                os.write(text.getBytes());
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        String rutaArxiu = this.getFilesDir() + File.separator + "dades.txt";




        boto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                try {

                    //Quan presiona el boto afegeix les dades a la ruta que rep
                    String arxiu = guardarString(rutaArxiu);
                    Log.v("Click",arxiu);
                    OutputStream os =  openFileOutput("dades.txt", MODE_PRIVATE);
                    arxiu += textinicial.getText();
                    os.write(arxiu.getBytes());
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private String guardarString(String rutaArxiu) {
        //Ho creem com a metode per a poder fer la crida mes facil desde el boto
        String arxiu = "";
        try {
            FileInputStream fis = new FileInputStream(rutaArxiu);
            InputStreamReader llegirArxiu = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(llegirArxiu);
            String lineaArxiu = br.readLine();
            arxiu = "";
            while (lineaArxiu != null) {
                arxiu = arxiu + lineaArxiu +"\n";

                lineaArxiu = br.readLine();
            }

            br.close();
            llegirArxiu.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return arxiu;
    }


}