package com.example.guvende;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class EditProfileActivity extends AppCompatActivity {
    EditText editYer,edittc;
    Button saveButton ,editcikis;
    String Yer, emailUser, kayittc, passwordUser;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        reference = FirebaseDatabase.getInstance("https://guvendeyiz-6bc2b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("kullanicilar");
        editYer = findViewById(R.id.editYer);
       editcikis=findViewById(R.id.editcikButton);
        saveButton = findViewById(R.id.saveButton);
       edittc=findViewById(R.id.edittc);
        showData();
        editcikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfileActivity.this,ProfileActivity.class));
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isYerChanged()){
                    Toast.makeText(EditProfileActivity.this, "Kaydedildi", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditProfileActivity.this, "Değişiklik Bulunamadı", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean isYerChanged() {
        if (!Yer.equals(editYer.getText().toString())){
            reference.child(kayittc).child("kayityer").setValue(editYer.getText().toString());
            Yer = editYer.getText().toString();
            return true;
        } else {
            return false;
        }
    }




    public void showData(){
        Intent intent = getIntent();
        Yer = intent.getStringExtra("kayityer");

    kayittc = intent.getStringExtra("kayittc");


        edittc.setText(kayittc);
        editYer.setText(Yer);
    }
}






