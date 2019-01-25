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

public class LoginActivity extends AppCompatActivity {

    private EditText userEmail, userPassword;
    private FirebaseAuth mAuth;
    private Button Btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Btnlogin = findViewById(R.id.BtnEnter);
        Btnlogin.setOnClickListener(new View.OnClickListener() { // If Clicked
            @Override
            public void onClick(View v) {
                userEmail = findViewById(R.id.TxtUsername);
                userPassword = findViewById(R.id.TxtPassword);
                Btnlogin = findViewById(R.id.BtnEnter);
                mAuth = FirebaseAuth.getInstance();

                Btnlogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //get inputs from user
                        final String Email = userEmail.getText().toString();
                        final String password = userPassword.getText().toString();

                        if (Email.isEmpty() || password.isEmpty()) {
                            showMessage("Please Verify all fields");
                        } else {
                            signIn(Email, password);
                        }
                    }
                });
            }
        });
    }

    //sign in user
    private void signIn(String Email, String Password) {

        mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    updateUI();
                }
                else
                {
                    showMessage(task.getException().getMessage());
                }
            }
        });
    }

    // Go To Activity
    private void updateUI() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void showMessage(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user !=null) {
            // the users already connected so move past login screen
            updateUI();

        }
    }
}

