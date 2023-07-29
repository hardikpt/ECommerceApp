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

public class LoginActivity extends AppCompatActivity {

    EditText email,pass;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
    }


    public void singup(View view)
    {

        startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
    }

    public void singin(View view)
    {
        String userEmail = email.getText().toString();
        String userPass = pass.getText().toString();

        if(TextUtils.isEmpty(userEmail))
        {
            Toast.makeText(this, "📩 Please Enter Email!!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(userPass))
        {
            Toast.makeText(this, "🔑 Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        if(userPass.length()<6)
        {
            Toast.makeText(this, "🔑 Password Too Short-Enter Minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(userEmail,userPass)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isComplete())
                                {
                                    Toast.makeText(LoginActivity.this, "😊 Login SuccessFull", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(LoginActivity.this, "❌ Login Error"+task.getException()  , Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

        startActivity(new Intent(LoginActivity.this,MainActivity.class));
    }
}