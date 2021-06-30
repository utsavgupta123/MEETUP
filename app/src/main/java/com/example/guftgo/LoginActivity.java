package com.example.guftgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {
    Button login,create;
    EditText email,password;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        create=findViewById(R.id.create);
        login=findViewById(R.id.login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        auth=FirebaseAuth.getInstance();
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailt, passwrd, namet;
                emailt=email.getText().toString();
                passwrd=password.getText().toString();

                auth.signInWithEmailAndPassword(emailt,passwrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this, "LOGGED IN", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,Dashboard.class));
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }
}