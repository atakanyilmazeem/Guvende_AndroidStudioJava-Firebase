package com.example.guvende;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    DatabaseReference mDatabase= FirebaseDatabase.getInstance("https://guvendeyiz-6bc2b-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText kimlikgiristxt=findViewById(R.id.kimlikgiristxt);
        final EditText sifregiristxt=findViewById(R.id.sifregiristxt);
        final Button butongiristxt=findViewById(R.id.butongiristxt);
        final Button butonkayitoltxt=findViewById(R.id.butonkayitoltxt);
        final TextView kurumsal=findViewById(R.id.kurumsal);

        kurumsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,AdminGiris.class));
            }
        });


        butongiristxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String kimlikgiris=kimlikgiristxt.getText().toString();
                final String sifregiris=sifregiristxt.getText().toString();

                if ((kimlikgiris.isEmpty())|| sifregiris.isEmpty())
                {
                    Toast.makeText(LoginActivity.this,"Lütfen boşlukları doldurunuz.",Toast.LENGTH_SHORT).show();

                }

                else
                {
                 mDatabase.child("kullanicilar").addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot snapshot) {
                         if(snapshot.hasChild(kimlikgiris))
                         {
                             final String getPassword=snapshot.child(kimlikgiris).child("kayitsifre").getValue(String.class);
                             if(getPassword.equals(sifregiris))
                             {
                                 Toast.makeText(LoginActivity.this,"Giriş Başarılı",Toast.LENGTH_SHORT).show();
                                 startActivity(new Intent(LoginActivity.this,kullanicigiris1.class));


                             }
                             else
                             {
                                 Toast.makeText(LoginActivity.this,"Kimlik numarasın veya şifreyi tekrar kontrol ediniz.",Toast.LENGTH_SHORT).show();

                             }
                         }
                         else {
                             Toast.makeText(LoginActivity.this,"Kimlik numarasın veya şifreyi tekrar kontrol ediniz.",Toast.LENGTH_SHORT).show();

                         }
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError error) {

                     }
                 });

                }
            }
        });
       butonkayitoltxt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
           }
       });

    }
}

/*
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

public class LoginActivity extends AppCompatActivity {

    EditText loginUsername, loginPassword;
    Button loginButton,Signupbutton;

    TextView signupRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.kimlikgiristxt);
        loginPassword = findViewById(R.id.sifregiristxt);
        loginButton = findViewById(R.id.butongiristxt);
        signupRedirectText = findViewById(R.id.kurumsal);
        Signupbutton=findViewById(R.id.butonkayitoltxt);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!kimlik() | !sifre()) {

                } else {
                    checkUser();
                }
            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    public Boolean kimlik() {
        String val = loginUsername.getText().toString();
        if (val.isEmpty()) {
            loginUsername.setError("Username cannot be empty");
            return false;
        } else {
            loginUsername.setError(null);
            return true;
        }
    }

    public Boolean sifre(){
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Password cannot be empty");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }


    public void checkUser(){
        String kayittc = loginUsername.getText().toString().trim();
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

                        Intent intent = new Intent(LoginActivity.this, kullaniciinsanarama.class);

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
                    loginUsername.setError("User does not exist");
                    loginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}*/
