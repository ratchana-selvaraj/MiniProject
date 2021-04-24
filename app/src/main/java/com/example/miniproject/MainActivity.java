package com.example.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextInputLayout username,password;
    String path = "PatientRegister";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = (Button)findViewById(R.id.login);
        Button bt2 = (Button)findViewById(R.id.register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton rb = (RadioButton)findViewById(R.id.radioButton1);
                RadioButton rb1 = (RadioButton)findViewById(R.id.radioButton2);
                if (rb.isChecked()){
                    loginUser(view);
                }
                if (rb1.isChecked()) {
                    path = "DocterRegister";
                    loginUser(view);
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
    public void loginUser(View view){
        if (!validateUsername() | !validatePassword()) {
            return;
        }
        else{
            isUser();
        }
    }
    private Boolean validateUsername() {
        String val = username.getEditText().getText().toString();
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
    private void isUser(){
        final String userEnteredUsername = username.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(path);
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    username.setError(null);
                    username.setErrorEnabled(false);
                    String passwordFromDB = userEnteredUsername;
                if (passwordFromDB!= null && passwordFromDB.equals(userEnteredPassword)){
                    username.setError(null);
                    username.setErrorEnabled(false);
                    if(path.equals("PatientRegister")){
                    Intent i = new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(i);
                    }
                    else{
                        Intent i = new Intent(MainActivity.this,bottom_tab.class);
                        startActivity(i);
                    }
                }
                else{
                    password.setError("Wrong password");
                }
            }else{
                    username.setError("No such user");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}