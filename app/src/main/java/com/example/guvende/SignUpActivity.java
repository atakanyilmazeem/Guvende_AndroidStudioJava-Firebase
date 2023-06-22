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

public class SignUpActivity extends AppCompatActivity{
     DatabaseReference mDatabase= FirebaseDatabase.getInstance("https://guvendeyiz-6bc2b-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

    //EditText kayitad,kayitsoyad,kayitkimlik,kayitdogum,kayittelefon,kayitsifre,kayitannetc;
    //Button kayitbuton;

    //TextView hesapacma;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

       final EditText kayitad,kayitsoyad,kayitkimlik,kayitdogum,kayittelefon,kayitsifre,kayitannetc;
       final Button kayitbuton;

       final TextView hesapacma;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        kayitad=findViewById(R.id.kayit_ad);
        kayitsoyad=findViewById(R.id.kayit_soyad);
        kayitkimlik=findViewById(R.id.kayit_kimlik);
        kayitdogum=findViewById(R.id.kayit_dogum);
        kayittelefon=findViewById(R.id.kayit_telefon);
        kayitsifre=findViewById(R.id.kayit_sifre);
        kayitannetc=findViewById(R.id.kayit_annetc);
        kayitbuton=findViewById(R.id.kayit_buton);
        hesapacma=findViewById(R.id.hesap_acma);
      //  mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://guvendeyiz-6bc2b-default-rtdb.europe-west1.firebasedatabase.app/");


        kayitbuton.setOnClickListener( new   View.OnClickListener() {
            @Override
            public void onClick(View v) {

               final String ad=kayitad.getText().toString().trim();
               final String soyad=kayitsoyad.getText().toString().trim();
               final String tc=kayitkimlik.getText().toString().trim();
               final String dogum=kayitdogum.getText().toString().trim();
               final String telefon=kayittelefon.getText().toString().trim();
               final String sifre=kayitsifre.getText().toString().trim();
               final String annetc=kayitannetc.getText().toString().trim();

                if (ad.isEmpty()||soyad.isEmpty()||tc.isEmpty()||dogum.isEmpty()||telefon.isEmpty()||sifre.isEmpty()||annetc.isEmpty()){
                  Toast.makeText(SignUpActivity.this,"Lütfen boşlukları doldurunuz.",Toast.LENGTH_SHORT).show();
                }
               else {
                    mDatabase.child("kullanicilar").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(tc)){
                                Toast.makeText(SignUpActivity.this,"Bu kimlik numarası daha önceden kayıtlı!.",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                mDatabase.child("kullanicilar").child(tc).child("kayitad").setValue(ad);
                                mDatabase.child("kullanicilar").child(tc).child("kayitsoyad").setValue(soyad);
                                mDatabase.child("kullanicilar").child(tc).child("kayitdogum").setValue(dogum);
                                mDatabase.child("kullanicilar").child(tc).child("kayittelefon").setValue(telefon);
                                mDatabase.child("kullanicilar").child(tc).child("kayitsifre").setValue(sifre);
                                mDatabase.child("kullanicilar").child(tc).child("kayitannetc").setValue(annetc);
                                Toast.makeText(SignUpActivity.this,"Kayıt başarılı.",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(SignUpActivity.this,LoginActivity2.class));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }

            }
        });
        //kayıt ekranı kapanıyor oturum açma açılıyor
        hesapacma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}