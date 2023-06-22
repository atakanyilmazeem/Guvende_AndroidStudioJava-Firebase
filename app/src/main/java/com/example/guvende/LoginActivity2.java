package com.example.guvende;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity2 extends AppCompatActivity {

    EditText loginUsername, loginPassword;
    Button loginButton;
    TextView signupRedirectText,kurumsalgir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        loginUsername = findViewById(R.id.login_kimlik);
        loginPassword = findViewById(R.id.login_sifre);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signupRedirectText);
        kurumsalgir=findViewById(R.id.kurumsalgir);

kurumsalgir.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(LoginActivity2.this,AdminGiris.class));
    }
});

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword()) {

                } else {
                    checkUser();
                }
            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity2.this, Sign2.class);
                startActivity(intent);
            }
        });

    }

    public Boolean validateUsername() {
        String val = loginUsername.getText().toString();
        if (val.isEmpty()) {
            loginUsername.setError("Kimlik kısmı boş olamaz.");
            return false;
        } else {
            loginUsername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Şifre kısmı boş olamaz.");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }


    public void checkUser(){
        String kayittc= loginUsername.getText().toString().trim();
        String kayitsifre = loginPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance("https://guvendeyiz-6bc2b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("kullanicilar");
        Query checkUserDatabase = reference.orderByChild("kayittc").equalTo(kayittc);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){

                    loginUsername.setError(null);
                    String passwordFromDB = snapshot.child(kayittc).child("kayitsifre").getValue(String.class);

                    if (passwordFromDB.equals(kayitsifre)) {
                        loginUsername.setError(null);

                        String nameFromDB = snapshot.child(kayittc).child("kayitad").getValue(String.class);
                        String emailFromDB = snapshot.child(kayittc).child("kayitsoyad").getValue(String.class);
                        String usernameFromDB = snapshot.child(kayittc).child("kayittc").getValue(String.class);

                        Intent intent = new Intent(LoginActivity2.this, ProfileActivity.class);

                        intent.putExtra("kayitad", nameFromDB);
                        intent.putExtra("kayitsoyad", emailFromDB);
                        intent.putExtra("kayittc", usernameFromDB);
                        intent.putExtra("kayitsifre", passwordFromDB);


                       startActivity(intent);

                    } else {
                        loginPassword.setError("Invalid Credentials");
                        loginPassword.requestFocus();
                    }
                } else {
                    loginUsername.setError("Kimlik Bulunamadı");
                    loginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}