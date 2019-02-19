package com.example.r2d2.activityexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle extras = this.getIntent().getExtras();
        String theString = extras.getString("theString","");
        int theInteger = extras.getInt("theInteger",-1);
        float theFloat = this.getIntent().getFloatExtra("theFloat",-1.0f);

        //Para mostrar en la interfaz
        TextView tvString = findViewById(R.id.tvString);
        TextView tvInteger = findViewById(R.id.tvInteger);
        TextView tvFloat = findViewById(R.id.tvFloat);

        tvString.setText(theString);
        //tvInteger.setText(""+theInteger);
        tvInteger.setText(String.format("%d",theInteger));
        //tvFloat.setText(""+theFloat);
        tvFloat.setText(String.format("%.4f",theFloat));

        Button btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
