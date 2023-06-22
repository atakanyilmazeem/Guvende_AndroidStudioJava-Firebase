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

public class AdminGiris extends AppCompatActivity {
    DatabaseReference mDatabase= FirebaseDatabase.getInstance("https://guvendeyiz-6bc2b-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_giris);
         final Button admingiris=findViewById(R.id.admingiris);
         final EditText admingirisad=findViewById(R.id.admingirisad);
         final EditText adminsifre=findViewById(R.id.adminsifre);
         final TextView Loginekranı=findViewById((R.id.Loginekranı));

         Loginekranı.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(AdminGiris.this,LoginActivity2.class));
             }
         });


      admingiris.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              final String kurumad=admingirisad.getText().toString();
              final String kurumsifre=adminsifre.getText().toString();

              if ((kurumad.isEmpty())|| kurumsifre.isEmpty())
              {
                  Toast.makeText(AdminGiris.this,"Lütfen boşlukları doldurunuz.",Toast.LENGTH_SHORT).show();

              }

              else
              {
                  mDatabase.child("kurumsal").addListenerForSingleValueEvent(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                          if(snapshot.hasChild(kurumad))
                          {
                              final String kurumunsifre=snapshot.child(kurumad).child("kurumsifre").getValue(String.class);
                              if(kurumunsifre.equals(kurumsifre))
                              {
                                  Toast.makeText(AdminGiris.this,"Giriş Başarılı",Toast.LENGTH_SHORT).show();
                                  startActivity(new Intent(AdminGiris.this,AMainActivity.class));
                              }
                              else
                              {
                                  Toast.makeText(AdminGiris.this,"Kurum adı ve Şifreyi tekrar kontrol ediniz.",Toast.LENGTH_SHORT).show();

                              }
                          }
                          else {
                              Toast.makeText(AdminGiris.this,"Kurum adı ve Şifreyi tekrar kontrol ediniz.",Toast.LENGTH_SHORT).show();

                          }
                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError error) {

                      }
                  });

              }
          }
      });



    }
}