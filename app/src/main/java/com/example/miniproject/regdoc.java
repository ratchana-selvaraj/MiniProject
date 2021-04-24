package com.example.miniproject;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class regdoc extends AppCompatActivity {
    FirebaseDatabase rootnode;
    TextInputLayout docname,docphn,docuname,docpass,docmail;
    DatabaseReference reference ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regdoc);
        docname = findViewById(R.id.dname);
        docphn = findViewById(R.id.dphnno);
        docuname = findViewById(R.id.dusername);
        docpass = findViewById(R.id.dpassword);
        docmail = findViewById(R.id.dmail);
        Button bt = (Button)findViewById(R.id.dbutton);
        rootnode = FirebaseDatabase.getInstance();
        reference = rootnode.getReference("DocterRegister");
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Objects.requireNonNull(docname.getEditText()).getText().toString();
                String phn = Objects.requireNonNull(docphn.getEditText()).getText().toString();
                String mail = Objects.requireNonNull(docmail.getEditText()).getText().toString();
                String username = Objects.requireNonNull(docuname.getEditText()).getText().toString();
                String password = Objects.requireNonNull(docpass.getEditText()).getText().toString();
                if (name.isEmpty() && phn.isEmpty() && mail.isEmpty() && username.isEmpty() && password.isEmpty()){
                    Toast.makeText(regdoc.this,"You are missing out certain fields,Please fill out",Toast.LENGTH_SHORT).show();
                }
                else{
                    RegisterHelper helper = new RegisterHelper(name,phn,mail,username,password);
                    reference.child(phn).setValue(helper);
                    Intent i = new Intent(regdoc.this,MainActivity.class);
                    startActivity(i);
                }


            }
        });
    }
}