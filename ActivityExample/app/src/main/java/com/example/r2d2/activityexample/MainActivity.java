package com.example.r2d2.activityexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnNavegar = this.findViewById(R.id.btnNavegar);
        btnNavegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAG","Boton presionado!");
                Intent intent = new Intent(MainActivity.this,SecondActivity.class); //Operacion de resolucion de alcance

                intent.putExtra("theString","HolaMundo");
                intent.putExtra("theInteger",5);
                intent.putExtra("theFloat",5.5f);

                startActivity(intent); //Debe ir hasta el final para que pueda enviar todos los datos
            }
        });

    }

}
