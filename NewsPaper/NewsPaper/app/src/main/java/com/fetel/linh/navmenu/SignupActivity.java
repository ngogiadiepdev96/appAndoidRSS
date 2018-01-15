package com.fetel.linh.navmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fetel.linh.database.Database;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    static int check = 0;
    static String u= "";
    static String p="";
    EditText ed1,ed2,ed3;
    Button btnsg,btncancel;
    private Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        ed1 = (EditText)findViewById(R.id.edtusername);
        ed2 = (EditText)findViewById(R.id.edtpass);
        ed3 = (EditText)findViewById(R.id.edtrelpass);
        btnsg = (Button)findViewById(R.id.btnsignup);
        btncancel = (Button)findViewById(R.id.btncancel);
        btnsg.setOnClickListener(this);
        btncancel.setOnClickListener(this);
        db = new Database(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnsignup:
                signup();
                break;
            case R.id.btncancel:
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                break;
            default:
        }
    }
    private void signup(){
        String username = ed1.getText().toString().trim().toLowerCase();
        String password = ed2.getText().toString().trim();
        String relpassword = ed3.getText().toString().trim();
        if(!relpassword.equals(password)){
            Toast.makeText(SignupActivity.this,"Error password",Toast.LENGTH_SHORT).show();
            return;
        }else
        if(username.length()==0||password.length()==0){
            Toast.makeText(SignupActivity.this,"Error",Toast.LENGTH_SHORT).show();
            return;
        }else
        if(!db.CheckUsername(username)){
            Intent intent = new Intent(this, LoginActivity.class);
            check = 1;
            u = username;
            p = password;
//            intent.putExtra("username",username);
//            intent.putExtra("password",password);
            Toast.makeText(SignupActivity.this,"Signup success",Toast.LENGTH_SHORT).show();
            db.addAccount(username,password);
            startActivity(intent);
        }
        else {
            Toast.makeText(SignupActivity.this," Username exits", Toast.LENGTH_SHORT).show();
            return;
        }

                }
                }
