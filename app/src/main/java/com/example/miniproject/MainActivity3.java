package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class MainActivity3 extends AppCompatActivity {
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    TextInputLayout pname,puname,ppassword,pmail,pphno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        pname = findViewById(R.id.pname);
        puname = findViewById(R.id.pusername);
        ppassword = findViewById(R.id.ppassword);
        pmail = findViewById(R.id.pmail);
        pphno = findViewById(R.id.pphnno);
        Button bt = (Button)findViewById(R.id.button1);
        rootnode = FirebaseDatabase.getInstance();
        reference = rootnode.getReference("PatientRegister");
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Objects.requireNonNull(pname.getEditText()).getText().toString();
                String phn = Objects.requireNonNull(pphno.getEditText()).getText().toString();
                String mail = Objects.requireNonNull(pmail.getEditText()).getText().toString();
                String username = Objects.requireNonNull(puname.getEditText()).getText().toString();
                String password = Objects.requireNonNull(ppassword.getEditText()).getText().toString();
                if (name.isEmpty() && phn.isEmpty() && mail.isEmpty() && username.isEmpty() && password.isEmpty()){
                    Toast.makeText(MainActivity3.this,"You are missing out certain fields,Please fill out",Toast.LENGTH_SHORT).show();
                }
                else{
                    RegisterHelper helper = new RegisterHelper(name,phn,mail,username,password);
                    reference.child(phn).setValue(helper);
                Intent i = new Intent(MainActivity3.this,MainActivity.class);
                startActivity(i);
            }
            }
        });

    }
}