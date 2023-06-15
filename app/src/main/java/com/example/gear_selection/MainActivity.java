package com.example.gear_selection;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    Spinner spnModulo, spnNumeroDientes;
    Button btnCalcular;
    EditText edtNumeroDientesConducido, edtDiametroPasoPinon, edtNumeroDientesPinon, edtDiametroPasoConducido, edtRelacionTransision, edtPasoCircular, edtDistanciaEntreCentros;

    int i_numero_dientes;
    float f_modulo, f_diametro_paso_pinon,f_diametro_paso_conducido, f_numero_dientes_conducido, f_relacion_transision, f_paso_circular, f_distancia_entre_centros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.my_light_background)));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.my_light_icon));
        //editText.setEnabled(false);
        //String selec=spinner1.getSelectedItem().toString();
        spnModulo = findViewById(R.id.spnModulo);
        btnCalcular = findViewById(R.id.btnCalcular);
        spnNumeroDientes = findViewById(R.id.spnNumeroDientes);
        edtNumeroDientesConducido = findViewById(R.id.edtNumeroDientesConducido);
        edtDiametroPasoPinon = findViewById(R.id.edtDiametroPasoPinon);
        edtNumeroDientesPinon = findViewById(R.id.edtNumeroDientesPinon);
        edtDiametroPasoConducido = findViewById(R.id.edtDiametroPasoConducido);
        edtRelacionTransision = findViewById(R.id.edtRelacionTransision);
        edtPasoCircular = findViewById(R.id.edtPasoCircular);
        edtDistanciaEntreCentros = findViewById(R.id.edtDistanciaEntreCentros);

        ArrayAdapter<CharSequence> adapterModulo=ArrayAdapter.createFromResource(this, R.array.modulos, R.layout.spinner_item_modulo_and_numero_diente);
        adapterModulo.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnModulo.setAdapter(adapterModulo);

        ArrayAdapter<CharSequence> adapterNumeroDientes = ArrayAdapter.createFromResource(this, R.array.numero_dientes, R.layout.spinner_item_modulo_and_numero_diente);
        adapterNumeroDientes.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnNumeroDientes.setAdapter(adapterNumeroDientes);




        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_modulo = (String) spnModulo.getSelectedItem();
                String s_numero_dientes = (String) spnNumeroDientes.getSelectedItem();
                String s_relacion_transision = edtRelacionTransision.getText().toString();

                if (s_modulo.equals("m")) {
                    Toast.makeText(MainActivity.this, "Selecciona un módulo", Toast.LENGTH_LONG).show();
                } else if (s_numero_dientes.equals("Np")) {
                    Toast.makeText(MainActivity.this, "Selecciona número de dientes", Toast.LENGTH_LONG).show();
                }else if (s_relacion_transision.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Relación de Transmisión no puede estar vacio", Toast.LENGTH_LONG).show();
                } else if (Integer.parseInt(s_relacion_transision) <= 0) {
                    Toast.makeText(MainActivity.this, "Relación de Transmisión no puede ser cero ni negativo", Toast.LENGTH_LONG).show();
                }else {
                    i_numero_dientes = Integer.parseInt(s_numero_dientes);
                    f_modulo = Float.parseFloat(s_modulo);
                    f_relacion_transision = Float.parseFloat(s_relacion_transision);

                    // N° de diente del piñon
                    edtNumeroDientesPinon.setText("np = " + s_numero_dientes);

                    //N° de diente del conducido
                    f_numero_dientes_conducido = i_numero_dientes * f_relacion_transision;
                    edtNumeroDientesConducido.setText("ng = " + f_numero_dientes_conducido);

                    //Diametro de paso del piñon
                    f_diametro_paso_pinon = i_numero_dientes * f_modulo;
                    edtDiametroPasoPinon.setText("dp = " + f_diametro_paso_pinon);

                    //Diametro de paso del conducido
                    f_diametro_paso_conducido = f_modulo * f_numero_dientes_conducido;
                    edtDiametroPasoConducido.setText("dp = " + f_diametro_paso_conducido);

                    //Paso circular
                    f_paso_circular = (float) (Math.PI * f_modulo);
                    edtPasoCircular.setText( "p = "+ f_paso_circular);

                    //Distancia entre centros
                    f_distancia_entre_centros = (f_diametro_paso_pinon / 2) + (f_diametro_paso_conducido / 2);
                    edtDistanciaEntreCentros.setText("c = "+ f_distancia_entre_centros);


                }
            }
        });
    }
}