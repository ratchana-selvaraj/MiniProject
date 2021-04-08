package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        final EditText etxt1 = (EditText)findViewById(R.id.etext1);
        Button bt = (Button)findViewById(R.id.button1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etxt1.getText().toString();
                Intent i = new Intent(MainActivity3.this,MainActivity2.class);
                i.putExtra("res","Hai " + name +" ");
                startActivity(i);
            }
        });

    }
}