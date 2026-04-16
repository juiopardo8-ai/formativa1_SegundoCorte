package com.example.formativa1_segundocorte;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nombre, edad;
    Spinner spinner;
    Button btnGuardado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.nombre);
        edad = findViewById(R.id.edad);
        spinner = findViewById(R.id.spinner);
        btnGuardado = findViewById(R.id.btnGuardado);

        // Llenar el Spinner con las categorías
        String[] categorias = {"Deportes", "Música", "Cine"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btnGuardado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = MainActivity.this.nombre.getText().toString();
                String edad = MainActivity.this.edad.getText().toString();
                String categoria = spinner.getSelectedItem().toString();

                // valida que no esten vacios
                if (nombre.isEmpty() || edad.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // y lo guarda en sharedpreferences
                SharedPreferences preferences = getSharedPreferences("MisDatos", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("nombre", nombre);
                editor.putString("edad", edad);
                editor.putString("categoria", categoria);
                editor.apply();


                if (categoria.equals("Deportes")) {
                    Intent intent = new Intent(MainActivity.this, activity_DeportesP2.class);
                    startActivity(intent);
                } else if (categoria.equals("Música")) {
                    Intent intent = new Intent(MainActivity.this, activity_MusicasP1.class);
                    startActivity(intent);
                } else if (categoria.equals("Cine")) {
                    Intent intent = new Intent(MainActivity.this, activity_CineP3.class);
                    startActivity(intent);
                }
            }
        });
    }
}