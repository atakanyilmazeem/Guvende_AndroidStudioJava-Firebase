package com.example.guvende;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class kullanicigiris1 extends AppCompatActivity {
//TextView textkullaniciad;

    DatabaseReference mdatabase;
    String kayittc;
Button butoninsan,buttondurumguncel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanicigiris1);
        mdatabase = FirebaseDatabase.getInstance("https://guvendeyiz-6bc2b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("kullanicilar");


        butoninsan = findViewById(R.id.buttoninsanarama);
        buttondurumguncel=findViewById(R.id.buttondurumguncel);

        buttondurumguncel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                startActivity(new Intent(kullanicigiris1.this,ProfileActivity.class));

            }
        });


        butoninsan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(kullanicigiris1.this,kullaniciinsanarama.class));
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


}