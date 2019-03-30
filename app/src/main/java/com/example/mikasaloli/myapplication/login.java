package com.example.mikasaloli.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private EditText email1;
    private EditText pws;
    private Button login;
    private Button register;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthlistener;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth =FirebaseAuth.getInstance();
        email1 = (EditText) findViewById(R.id.emailinput);
        pws = (EditText) findViewById(R.id.pwdinput);
        register = (Button) findViewById(R.id.reg);
        login = (Button) findViewById(R.id.loginbtn);
        mAuthlistener = new FirebaseAuth.AuthStateListener()
        {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null)
                {
                    startActivity(new Intent(login.this, MainActivity.class));
                }

            }
        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startsignin();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(login.this, register.class);
                startActivity(myIntent);

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthlistener);
    }
    private void startsignin()
    {
        String email = email1.getText().toString();
        String password = pws.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) )
        {

            Toast.makeText(login.this,"no input",Toast.LENGTH_LONG).show();
        }else {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful())
                    {
                        Toast.makeText(login.this,"error",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
}
