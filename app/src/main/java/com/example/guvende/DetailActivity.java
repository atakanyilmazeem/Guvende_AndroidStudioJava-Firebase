package com.example.guvende;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.clans.fab.FloatingActionButton;

public class DetailActivity extends AppCompatActivity {

    TextView detailDesc, detailTitle, detailLang,textannetc,textdogum,texttelefon,textsifre;
    ImageView detailImage;
    FloatingActionButton deleteButton, editButton;
    String kayittc= "";
    String imageUrl = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailDesc = findViewById(R.id.detailDesc);
        detailTitle = findViewById(R.id.detailTitle);
        textannetc=findViewById(R.id.textannetc);
        textdogum=findViewById(R.id.textdogum);
        textsifre=findViewById(R.id.textsifre);
       texttelefon=findViewById(R.id.texttelefon);


        editButton = findViewById(R.id.editButton);
        detailLang = findViewById(R.id.detailLang);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailDesc.setText(bundle.getString("kayitsoyad"));
            detailTitle.setText(bundle.getString("kayitad"));
            detailLang.setText(bundle.getString("kayityer"));
            texttelefon.setText(bundle.getString("kayittelefon"));
            textdogum.setText(bundle.getString("kayitdogum"));
            textannetc.setText(bundle.getString("kayitannetc"));
            textsifre.setText(bundle.getString("kayitsifre"));
            kayittc=bundle.getString("kayittc");
        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, UpdateActivity.class)
                        .putExtra("kayitad", detailTitle.getText().toString())
                        .putExtra("kayitsoyad", detailDesc.getText().toString())
                        .putExtra("kayityer", detailLang.getText().toString())
                        .putExtra("kayittelefon",texttelefon.getText().toString())
                        .putExtra("kayitannetc",textannetc.getText().toString())
                        .putExtra("kayitsifre",textsifre.getText().toString())
                        .putExtra("kayitdogum",textdogum.getText().toString())


                        .putExtra("kayittc",kayittc);


                startActivity(intent);
            }
        });
    }
}