package com.fetel.linh.navmenu;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fetel.linh.database.Database;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    EditText ed1,ed2;
    Button btlg,btsg;
    private Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        if(savedInstanceState != null){
            ed1.setText(savedInstanceState.getString("user"));
            ed2.setText(savedInstanceState.getString("password"));

        }else {
            ed1 = (EditText) findViewById(R.id.edusername);
            ed2 = (EditText) findViewById(R.id.edpass);
            btlg = (Button) findViewById(R.id.login);
            btsg = (Button) findViewById(R.id.signup);
            btlg.setOnClickListener(this);
            btsg.setOnClickListener(this);
            ed1.setText(SignupActivity.u);
            ed2.setText(SignupActivity.p);
            db = new Database(this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                login();
                break;
            case R.id.signup:
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
                finish();
                break;
            default:
        }
    }
    private void login(){
        String username = ed1.getText().toString().trim();
        String password = ed2.getText().toString().trim();
        if(username.isEmpty()||password.isEmpty()){
            Toast.makeText(LoginActivity.this,"Error",Toast.LENGTH_SHORT).show();
            return;
        }
        if(db.Check(username,password)){
            Intent intent = new Intent(LoginActivity.this,ListRssActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(LoginActivity.this,"Wrong username/password",Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("user",ed1.getText().toString());
        outState.putString("password",ed2.getText().toString());
    }
}
