package com.example.exercicipersistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button boto = (findViewById(R.id.button));
        try {
            OutputStream os = this.openFileOutput("dades.txt", MODE_PRIVATE);
            String text = "Missatge de proba";
            os.write(text.getBytes());
            os.close();

            File arxiu = this.getFilesDir();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}