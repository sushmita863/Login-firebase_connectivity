package com.example.form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    EditText Username,Email,Rollno,password,confirm_password;
    Button submit;
    TextView rlogin;
   private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Username=findViewById(R.id.Username);
        Email=findViewById(R.id.email);
        Rollno=findViewById(R.id.roll);
        password=findViewById(R.id.password);
        confirm_password=findViewById(R.id.confirmpassword);
        submit=findViewById(R.id.submit);
        rlogin=findViewById(R.id.rlogin);
        fAuth=FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();

        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=Email.getText().toString().trim();
                String pass=password.getText().toString().trim();

                if(TextUtils.isEmpty(mail)){
                    Email.setError("Email is required");
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

                fAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Registration.this, "User created successfulli!!!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else {
                            Toast.makeText(Registration.this, "Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        rlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));


            }
        });

    }
}