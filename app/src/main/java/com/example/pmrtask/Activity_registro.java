package com.example.pmrtask;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Activity_registro extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    Button registro_button;
    Button inicio_button;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        registro_button = findViewById(R.id.registro_button);
        inicio_button = findViewById(R.id.Inicio_yatengocuenta);

        inicio_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_registro.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        });
        registro_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Activity_registro.this, "Introduzca email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!email.contains("@")) {
                    Toast.makeText(Activity_registro.this, "Error: El correo electrónico debe contener un '@'", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (email.length() <= 8) {
                    Toast.makeText(Activity_registro.this, "Error: El correo electrónico debe tener más de 8 caracteres", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() <= 8) {
                    Toast.makeText(Activity_registro.this, "Error: la contraseña tiene que tener 8 caracteres", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Activity_registro.this, "Introduzca contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Activity_registro.this, "Registro correcto", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Activity_registro.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Activity_registro.this, "Fallo el registro", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });


        View registroView = findViewById(R.id.registro);

        ViewCompat.setOnApplyWindowInsetsListener(registroView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}