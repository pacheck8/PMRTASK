package com.example.pmrtask;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatosRecuperacion extends AppCompatActivity {
    ListView miLista;
    List<Pasajeros> pasajerosList;
    DatabaseReference pasajerosDbRef;
    ListaAdapter adapter;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_recuperacion);
        miLista = findViewById(R.id.miLista);
        pasajerosList = new ArrayList<>();
        pasajerosDbRef = FirebaseDatabase.getInstance().getReference("pasajeros");

        adapter = new ListaAdapter(this, pasajerosList);
        miLista.setAdapter(adapter);

        // Obtener el email del usuario autenticado
        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        pasajerosDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pasajerosList.clear();

                for (DataSnapshot pasajerosDatasnap : snapshot.getChildren()) {
                    Pasajeros pasajeros = pasajerosDatasnap.getValue(Pasajeros.class);

                    if (userEmail != null && userEmail.equals("admin@gmx.es")) {
                        pasajerosList.add(pasajeros);
                    } else {
                        // Filtrar los pasajeros por el email del usuario
                        if (pasajeros != null && pasajeros.getUserEmail().equals(userEmail)) {
                            pasajerosList.add(pasajeros);
                        }
                    }
                }

                // Notificar al adaptador que los datos han cambiado
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Manejar errores de lectura de la base de datos
            }
        });
    }
}
