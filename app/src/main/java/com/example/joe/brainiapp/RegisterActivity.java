package com.example.joe.brainiapp;

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
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText userEmail, userPass, userPass2;
    private Button regBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialise views
        userEmail = findViewById(R.id.regEmail);
        userPass = findViewById(R.id.regPass);
        userPass2 = findViewById(R.id.regPassConf);
        regBtn = findViewById(R.id.BtnConfirm);

        mAuth = FirebaseAuth.getInstance();

        //get user details
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = userEmail.getText().toString();
                final String password = userPass.getText().toString();
                final String password2 = userPass2.getText().toString();

                //check fields if empty
                if (email.isEmpty() || password.isEmpty() || password2.isEmpty() || !password.equals(password2)) {
                    showMessage("Please Verify Fields");

                } else {
                    CreateUserAccount(email, password);
                }


            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }


    private void CreateUserAccount(final String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Then create user account
                            showMessage("Account created");
                            updateUI();
                            // updateUserInfo(email,mAuth.getCurrentUser());
                        } else {
                            showMessage("account creation failed" + task.getException().getMessage());
                            updateUI2();
                        }
                    }
                });
    }

    private void updateUI() {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);

    }

    private void updateUI2() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
