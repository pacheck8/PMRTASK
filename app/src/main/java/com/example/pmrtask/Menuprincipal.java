package com.example.pmrtask;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import android.view.WindowInsets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;

public class Menuprincipal extends AppCompatActivity {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    TextView textViewSaludo;
    Button buttonregistropaxhome;
    Button listapaxhome;
    Button logoutpaxhome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal);
        textViewSaludo = findViewById(R.id.textViewSaludo);
        String userEmail = firebaseAuth.getCurrentUser().getEmail();
        textViewSaludo.setText("Hola, " + userEmail);
        Button buttonregistropaxhome = findViewById(R.id.registropaxhome);
        Button buttonlistapaxhome=findViewById(R.id.listapaxhome);
        Button logoutpaxhome=findViewById(R.id.logoutpaxhome);

        buttonregistropaxhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menuprincipal.this, Registro_pax.class);
                startActivity(intent);
            }
        });
        buttonlistapaxhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menuprincipal.this, DatosRecuperacion.class);
                startActivity(intent);
            }
        });
        logoutpaxhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent intent = new Intent(Menuprincipal.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
