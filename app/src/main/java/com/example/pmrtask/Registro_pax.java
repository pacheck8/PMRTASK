package com.example.pmrtask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Registro_pax extends AppCompatActivity {
    EditText editTextnombre,editTextvuelo,editTextinfo;
    Button buttonregistropax;
    DatabaseReference dbpasajeros;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_pax);
        String userEmail = firebaseAuth.getCurrentUser().getEmail();


        Button buttonregistropax = findViewById(R.id.buttonregistropax);
        editTextnombre = findViewById(R.id.nombrepax);
        editTextvuelo = findViewById(R.id.vuelo);
        editTextinfo = findViewById(R.id.info);
        dbpasajeros = FirebaseDatabase.getInstance().getReference().child("pasajeros");

        buttonregistropax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre= editTextnombre.getText().toString();
                String vuelo= editTextvuelo.getText().toString();
                if (vuelo.isEmpty()) {
                    Toast.makeText(Registro_pax.this, "Introduzca Vuelo", Toast.LENGTH_SHORT).show();
                } else if (vuelo.length() < 4) {
                    Toast.makeText(Registro_pax.this, "Numero de vuelo no existe", Toast.LENGTH_SHORT).show();
                } else if (nombre.isEmpty()) {
                    Toast.makeText(Registro_pax.this, "Introduzca nombre", Toast.LENGTH_SHORT).show();
                } else if (nombre.length() < 3) {
                    Toast.makeText(Registro_pax.this, "Nombre demasiado corto", Toast.LENGTH_SHORT).show();
                } else {
                String userEmail = firebaseAuth.getCurrentUser().getEmail();
                insertPasajerosDatos(userEmail);
                Intent intent = new Intent(Registro_pax.this, DatosRecuperacion.class);
                startActivity(intent);
                editTextnombre.setText("");
                editTextvuelo.setText("");
                editTextinfo.setText("");
                }

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void insertPasajerosDatos(String userEmail){

        String nombre = editTextnombre.getText().toString();
        String vuelo=editTextvuelo.getText().toString();
        String info=editTextinfo.getText().toString();
        Pasajeros pasajeros = new Pasajeros(nombre,vuelo,info,userEmail);
        dbpasajeros.push().setValue(pasajeros);

    }
}