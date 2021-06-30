package com.example.guftgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class SignUpActivity extends AppCompatActivity {
     private FirebaseAuth mAuth;
     FirebaseFirestore database;
    Button oldacc,create;
    EditText email,password,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        mAuth = FirebaseAuth.getInstance();
        database=FirebaseFirestore.getInstance();
        create=findViewById(R.id.create);
        oldacc=findViewById(R.id.oldacc);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        name=findViewById(R.id.name);



        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String emailt, passwrd, namet;
                namet=name.getText().toString();
                emailt=email.getText().toString();
                passwrd=password.getText().toString();
                User user=new User();
                user.setName(namet);
                user.setEmail(emailt);
                user.setPass(passwrd);

                Toast.makeText(SignUpActivity.this, "WELCOME", Toast.LENGTH_SHORT).show();
                mAuth.createUserWithEmailAndPassword(emailt,passwrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            database.collection("Users").document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                                }
                            });
                            Toast.makeText(SignUpActivity.this, "Account is CREATED", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(SignUpActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


               oldacc.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                   }
               });


            }
        });
    }




}