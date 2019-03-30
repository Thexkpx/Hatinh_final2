package com.example.mikasaloli.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {

    private EditText email,pwd;
    private Button btn;
    private FirebaseAuth fbauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email= (EditText) findViewById(R.id.reg_email);
        pwd = (EditText) findViewById(R.id.reg_pwd);
        btn = (Button) findViewById(R.id.regbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String iemail = email.getText().toString().trim();
                String ipassword = pwd.getText().toString().trim();

                fbauth=FirebaseAuth.getInstance();
                fbauth.createUserWithEmailAndPassword(iemail, ipassword)
                        .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                Toast.makeText(register.this, "ສະຫມັກສໍາເລັດ:" , Toast.LENGTH_SHORT).show();

                                if (!task.isSuccessful()) {
                                    Toast.makeText(register.this, "ຜິດພາດ." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent myIntent = new Intent(register.this, MainActivity.class);
                                    startActivity(myIntent);

                                    finish();
                                }
                            }
                        });
            }
        });

    }
    private void savetofb()
    {

    }
}
