package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {

    EditText name,email,password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);



        auth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        
    }

    public void singin(View view)
    {
        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
    }

    public void singup(View view)
    {
        String userName = name.getText().toString();
        String userEmail = email.getText().toString();
        String userPass = password.getText().toString();

        if(TextUtils.isEmpty(userName))
        {
            Toast.makeText(this, "📛 Please Enter eeName!!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(userEmail))
        {
            Toast.makeText(this, "📩 Please Enter Email!!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(userPass))
        {
            Toast.makeText(this, "🔑 Please Enter PassWord!!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(userPass.length()<6)
        {
            Toast.makeText(this, "🔑 Password Too Short-Enter Minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(userEmail,userPass)
                        .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isComplete())
                                {
                                    Toast.makeText(RegistrationActivity.this, "🎯 Successfully Register", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                                }
                                else
                                {
                                    Toast.makeText(RegistrationActivity.this, "❌ Registration Failed"+task.getException(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });


    }
}