package com.example.pmrtask;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ListaAdapter extends ArrayAdapter<Pasajeros> {
    private Activity mContext;
    private List<Pasajeros> pasajerosList;

    public ListaAdapter(Activity mContext, List<Pasajeros> pasajerosList) {
        super(mContext, R.layout.activity_listado_pax, pasajerosList);
        this.mContext = mContext;
        this.pasajerosList = pasajerosList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            listItemView = inflater.inflate(R.layout.activity_listado_pax, parent, false);
        }

        TextView vuelo = listItemView.findViewById(R.id.vuelo);
        TextView nombre = listItemView.findViewById(R.id.nombre);
        TextView info = listItemView.findViewById(R.id.info);
        TextView email = listItemView.findViewById(R.id.email); // Nuevo TextView para el correo electrónico
        Pasajeros pasajeros = pasajerosList.get(position);

        vuelo.setText(pasajeros.getVuelo());
        nombre.setText(pasajeros.getNombre());
        info.setText(pasajeros.getInfo());

        // Obtener el correo electrónico del usuario autenticado
        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        // Mostrar el correo electrónico solo si el usuario es admin
        if (userEmail != null && userEmail.equals("admin@gmx.es")) {
            email.setVisibility(View.VISIBLE);
            email.setText(pasajeros.getUserEmail());
        } else {
            email.setVisibility(View.GONE);
        }

        return listItemView;
    }
}