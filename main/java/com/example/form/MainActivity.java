package com.example.form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button login;
    TextView register;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        progressBar=findViewById(R.id.progressBar);
        fAuth= FirebaseAuth.getInstance();
        login=findViewById(R.id.login);
        register=findViewById(R.id.register);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String mail=email.getText().toString().trim();
                String pass=password.getText().toString().trim();

                if(TextUtils.isEmpty(mail)){
                    email.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(pass)){
                    password.setError("password is required");
                    return;
                }

                if (pass.length()<6){
                    password.setError("password must be of more then 6 character");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // Authanticate the user
                fAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "User Logged in successfully!!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Registration.class));
            }
        });



    }



}