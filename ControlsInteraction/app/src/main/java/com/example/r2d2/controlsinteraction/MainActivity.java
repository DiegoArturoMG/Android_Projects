package com.example.r2d2.controlsinteraction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView output = findViewById(R.id.miTextView1);
        output.setText("Hola: Diego Arturo");

        final EditText input = findViewById(R.id.input);
        input.setText("Texto del EditText");

        Button boton = findViewById(R.id.boton);
        boton.setText("Este es un boton");

        View.OnClickListener clickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Log.d("Tag","Hola Mundo desde un boton"); //imprime en consola
                String text = input.getText().toString();
                output.setText(text);
                input.setText(null);
            }
        };

        boton.setOnClickListener(clickListener);

        final Switch swSwitch = findViewById(R.id.swSwitch);

        swSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    Log.e("Tag","El elemento esta: Prendido");
                else
                    Log.e("Tag","El elemento esta: Apagado");
            }
        });

        CheckBox checkBox = findViewById(R.id.cbCheckbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                swSwitch.setChecked(!isChecked);

                if (isChecked)
                    input.setEnabled(false);
                else
                    input.setEnabled(true);

            }
        });

        final TextView tvOption = findViewById(R.id.tvOption);
        RadioGroup rgRadioGroup = findViewById(R.id.radioGroup);
        rgRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int selectedId) {
                switch (selectedId){
                    case R.id.rb1:
                        tvOption.setText("Opcion 1");
                        break;
                    case R.id.rb2:
                        tvOption.setText("Opcion 2");
                        break;
                    case R.id.rb3:
                        tvOption.setText("Opcion 3");
                        break;
                }
            }
        });

        final TextView tvProgress = findViewById(R.id.tvProgress);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        SeekBar sbSlider = findViewById(R.id.sbSlider);
        sbSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean wasUser) {
                tvProgress.setText("Progreso: "+seekBar.getProgress());
                progressBar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
