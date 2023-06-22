package com.example.guvende;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

public class UpdateActivity extends AppCompatActivity {

    ImageView updateImage;
    Button updateButton;
    EditText updateDesc, updateTitle, updateLang,updateDogum,updateSifre,updateTelefon,updatetc;
    String kayitad, kayitsoyad, kayityer,kayitdogum,kayitsifre,kayittelefon;
    String imageUrl;
    String key="";
    String kayittc;
    Uri uri;
    DatabaseReference mdatabase;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updateButton = findViewById(R.id.updateButton);
        updateDesc = findViewById(R.id.updateDesc);
        updateLang = findViewById(R.id.updateLang);
        updateTitle = findViewById(R.id.updateTitle);
         updatetc=findViewById(R.id.updateTc);
        updateDogum=findViewById(R.id.updateDogum);
        updateSifre=findViewById(R.id.updateSifre);
        updateTelefon=findViewById(R.id.updateTelefon);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                    }
                }
        );
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){

            updateTitle.setText(bundle.getString("kayitad"));
            updateDesc.setText(bundle.getString("kayitsoyad"));
            updateLang.setText(bundle.getString("kayityer"));
             updatetc.setText(bundle.getString("kayittc"));
            updateDogum.setText(bundle.getString("kayitdogum"));
            updateSifre.setText(bundle.getString("kayitsifre"));
            updateTelefon.setText(bundle.getString("kayittelefon"));
            kayittc = bundle.getString("kayittc");
        }
        mdatabase = FirebaseDatabase.getInstance("https://guvendeyiz-6bc2b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("kullanicilar").child(kayittc);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent intent = new Intent(UpdateActivity.this, AMainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void saveData(){
       // mdatabase = FirebaseDatabase.getInstance("https://guvendeyiz-6bc2b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("kullanicilar");

        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        updateData();
    }
    public void updateData(){

        kayitad = updateTitle.getText().toString().trim();
        kayitsoyad = updateDesc.getText().toString().trim();
        kayityer = updateLang.getText().toString();

        kayitdogum=updateDogum.getText().toString();
        kayitsifre=updateSifre.getText().toString();
        kayittelefon=updateTelefon.getText().toString();

        kullanicilar dataClass = new kullanicilar(kayitad, kayitsoyad,kayittc,kayitdogum,kayittelefon,kayitsifre, kayityer);

        mdatabase.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(UpdateActivity.this, "Güncellendi", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}