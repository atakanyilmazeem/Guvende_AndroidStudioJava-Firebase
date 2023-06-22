package com.example.guvende;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sign2 extends AppCompatActivity {

    EditText signupName, signupkimlik, signupsoyad, signupPassword,signupdogum,signuptelefon,signupyer;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign2);
         signupyer=findViewById(R.id.signup_yer);
        signupName = findViewById(R.id.signup_name);
        signupsoyad= findViewById(R.id.signup_email);
        signupkimlik = findViewById(R.id.signup_kimlik);
        signupPassword = findViewById(R.id.signup_password);

       signupdogum=findViewById(R.id.signup_dogum);
       signuptelefon=findViewById(R.id.signup_telefon);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton = findViewById(R.id.signup_button);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance("https://guvendeyiz-6bc2b-default-rtdb.europe-west1.firebasedatabase.app/");
                reference = database.getReference("kullanicilar");

                String kayitad = signupName.getText().toString();
                String kayitsoyad = signupsoyad.getText().toString();
                String kayittc = signupkimlik.getText().toString();
                String kayitsifre = signupPassword.getText().toString();

                String kayitdogum=signupdogum.getText().toString();
                String kayittelefon=signuptelefon.getText().toString();
                String kayityer=signupyer.getText().toString();

                if (kayitad.isEmpty()||kayitsoyad.isEmpty()||kayittc.isEmpty()||kayitdogum.isEmpty()||kayittelefon.isEmpty()||kayitsifre.isEmpty()){
                    Toast.makeText(Sign2.this,"Lütfen boşlukları doldurunuz.",Toast.LENGTH_SHORT).show();
                }
                else {
                kullanicilar helperClass = new kullanicilar(kayitad, kayitsoyad, kayittc,kayitdogum,kayittelefon,kayitsifre);
                reference.child(kayittc).setValue(helperClass);

                Toast.makeText(Sign2.this, "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Sign2.this, LoginActivity2.class);
                startActivity(intent);
            }}
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sign2.this, LoginActivity2.class);
                startActivity(intent);
            }
        });
    }
}