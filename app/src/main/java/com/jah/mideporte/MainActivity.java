package com.jah.mideporte;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgTipoDeporte;
    RadioButton rbIndividual;
    RadioButton rbPareja;
    RadioButton rbGrupo;
    Spinner spDeportes;
    TextView txtMostrar;
    ArrayAdapter<CharSequence> adaptador;
    String mostrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rgTipoDeporte = findViewById(R.id.rgTipoDeporte);
        rbIndividual = findViewById(R.id.rbIndividual);
        rbPareja = findViewById(R.id.rbPareja);
        rbGrupo = findViewById(R.id.rbGrupo);
        spDeportes = findViewById(R.id.spDeportes);
        txtMostrar = findViewById(R.id.txtMostrar);

        rgTipoDeporte.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == -1) {
                Log.i("DAM", "no hay nada seleccionado");
            } else {
                if (rbIndividual.isChecked()) {
                    adaptador = ArrayAdapter.createFromResource(this, R.array.deporte_individual, android.R.layout.simple_spinner_item);
                } else if (rbPareja.isChecked()) {
                    adaptador = ArrayAdapter.createFromResource(this, R.array.deporte_pareja, android.R.layout.simple_spinner_item);
                } else {
                    adaptador = ArrayAdapter.createFromResource(this, R.array.deporte_grupo, android.R.layout.simple_spinner_item);
                }
                adaptador.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
                spDeportes.setAdapter(adaptador);


                spDeportes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mostrar = parent.getItemAtPosition(position).toString();
                        txtMostrar.setText(mostrar);
                        Log.i("DAM", "Seleccionaste: " + mostrar);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                mostrar = spDeportes.getSelectedItem().toString();
                txtMostrar.setText(mostrar);
                Log.i("DAM", "Esta seleccionado: " + ((RadioButton) group.findViewById(checkedId)).getText());
            }
        });
    }
}