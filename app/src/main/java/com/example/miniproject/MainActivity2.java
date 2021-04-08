package com.example.miniproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView tt = (TextView)findViewById(R.id.atxt1);
        Button bt = (Button)findViewById(R.id.button);
        Button bt2 = (Button)findViewById(R.id.button2);
        Intent intent = getIntent();
        String st = (String)intent.getSerializableExtra("res");
        tt.setText(st);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                String url = "https://www.apollohospitals.com/";
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    bt2.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            PopupMenu popupMenu = new PopupMenu(MainActivity2.this,view);
            popupMenu.setOnMenuItemClickListener(MainActivity2.this);
            popupMenu.inflate(R.menu.main_activity2);
            popupMenu.show();
        }
    });
    }
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        Toast.makeText(getApplicationContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
        switch (menuItem.getItemId()) {
            case R.id.msg:
                BottomModel bottomModel = new BottomModel();
                bottomModel.show(getSupportFragmentManager(),"ModelFragment");
                break;
            case R.id.mail:
                Toast.makeText(getApplicationContext()," redirecting to mail",Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
}