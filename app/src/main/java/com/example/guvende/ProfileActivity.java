package com.example.guvende;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    TextView profileName, profilesoyad, profiletc, profiledogum,profilyer,profiletel;
    Button butoninsan;
    Button editProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileName = findViewById(R.id.Profilad);
        profiletel=findViewById(R.id.Profiltel);
        profilesoyad= findViewById(R.id.Profilsoyad);
        profiletc= findViewById(R.id.Profiltc);
        profiledogum = findViewById(R.id.Profildogumyili);
       profilyer=findViewById(R.id.Profilyerbilgisi);
        editProfile = findViewById(R.id.ProfileditButton);


        butoninsan = findViewById(R.id.buttoninsanarama);
        showAllUserData();
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passUserData();
            }
        });
        butoninsan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,kullaniciinsanarama.class));
            }
        });

        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.bal, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.deprem, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.sel, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.hava, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

    }



    public void showAllUserData(){
        Intent intent = getIntent();
        String pad = intent.getStringExtra("kayitad");
        String psoyad = intent.getStringExtra("kayitsoyad");
        String ptc= intent.getStringExtra("kayittc");
        String pdogum = intent.getStringExtra("kayitdogum");
        String pyer = intent.getStringExtra("kayityer");
        String ptel = intent.getStringExtra("kayittelefon");
        profileName.setText(pad);
        profilesoyad.setText(psoyad);
        profiletc.setText(ptc);
        profiledogum.setText(pdogum);
        profilyer.setText(pyer);
        profiletel.setText(ptel);
    }
    public void passUserData(){
        String kayittc = profiletc.getText().toString().trim();
        DatabaseReference mdatabase = FirebaseDatabase.getInstance("https://guvendeyiz-6bc2b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("kullanicilar");
        Query checkUserDatabase = mdatabase.orderByChild("kayittc").equalTo(kayittc);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String nameFromDB = snapshot.child(kayittc).child("kayitad").getValue(String.class);
                    String emailFromDB = snapshot.child(kayittc).child("kayitsoyad").getValue(String.class);
                   String usernameFromDB = snapshot.child(kayittc).child("kayittc").getValue(String.class);
                    String passwordFromDB = snapshot.child(kayittc).child("kayitsifre").getValue(String.class);
                    String yerFromDB=snapshot.child(kayittc).child("kayityer").getValue(String.class);
                    Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                    intent.putExtra("kayitad", nameFromDB);
                    intent.putExtra("kayitsoyad", emailFromDB);
                    intent.putExtra("kayittc", usernameFromDB);
                    intent.putExtra("kayitsifre", passwordFromDB);
                    intent.putExtra("kayityer",yerFromDB);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ProfileActivity.this, "Başarısız", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}