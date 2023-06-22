package com.example.guvende;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
//import android.widget.SearchView;
import androidx.appcompat.widget.SearchView;
//import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class DuzenlemeEkran extends AppCompatActivity {
 RecyclerView recyclerView;
 MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duzenleme_ekran);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        recyclerView = (RecyclerView) findViewById(R.id.liste);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<kullanicilar> options=
                new FirebaseRecyclerOptions.Builder<kullanicilar>()
                        .setQuery(FirebaseDatabase.getInstance("https://guvendeyiz-6bc2b-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("kullanicilar"),kullanicilar.class)
                        .build();
        mainAdapter=new MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText) {
                mysearch(newText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mysearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void mysearch(String newText) {

        FirebaseRecyclerOptions<kullanicilar> options=
                new FirebaseRecyclerOptions.Builder<kullanicilar>()
                        .setQuery(FirebaseDatabase.getInstance("https://guvendeyiz-6bc2b-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("kullanicilar").orderByChild("kayitad").startAt("newText").endAt(newText+"\uf8ff"),kullanicilar.class)
                        .build();

        mainAdapter=new MainAdapter(options);
        mainAdapter.startListening();
        recyclerView.setAdapter(mainAdapter);
    }

}