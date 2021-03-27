package com.example.miniproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
        Integer index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText etxt1 = (EditText)findViewById(R.id.etext1);
        Button bt = (Button)findViewById(R.id.button1);
        Button bt2 = (Button)findViewById(R.id.button3);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etxt1.getText().toString();
                RadioButton rb = (RadioButton)findViewById(R.id.radioButton1);
                RadioButton rb1 = (RadioButton)findViewById(R.id.radioButton2);
                if (rb.isChecked()){
                    Intent i = new Intent(MainActivity.this,MainActivity3.class);
                    startActivity(i);
                }
                if (rb1.isChecked()) {
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    i.putExtra("res", "Hai " + name + " ");
                    startActivity(i);
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   final String [] listitems = new String[]{"Doctor","Patient"};
                   AlertDialog.Builder mBuilder=new AlertDialog.Builder(MainActivity.this);
                   mBuilder.setTitle("Select the type of user");//set title of AlertDialog
                   mBuilder.setSingleChoiceItems(listitems, -1, new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           if (listitems[i].equals("Doctor")){
                               Intent in = new Intent(MainActivity.this,regdoc.class);
                               startActivity(in);
                           }
                           else {
                               Intent in = new Intent(MainActivity.this,MainActivity3.class);
                               startActivity(in);
                           }
                           dialogInterface.dismiss();
                       }
                   });
                   //show Alert Dialog
                   AlertDialog mDialog=mBuilder.create();
                   mDialog.show();
               }
        });

    }
}