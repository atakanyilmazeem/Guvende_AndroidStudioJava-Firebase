package com.example.guvende;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MainAdapter extends FirebaseRecyclerAdapter<kullanicilar,MainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<kullanicilar> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull kullanicilar model) {
    holder.adi.setText(model.getKayitad());
    holder.soyadi.setText(model.getKayitsoyad());
   holder.yeri.setText(model.getKayityer());

    holder.kimligi.setText((model.getKayittc()));

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.duzenleme_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
    //CircleImageView img;
    TextView adi,soyadi,kimligi,yeri;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

           // img=(CircleImageView)itemView.findViewById(R.id.img1);
            adi=(TextView)itemView.findViewById(R.id.kimlikad);
            soyadi=(TextView)itemView.findViewById(R.id.kimliksoyad);
           kimligi=(TextView)itemView.findViewById(R.id.kimlikbaslik);
            yeri=(TextView)itemView.findViewById(R.id.kimlikyer);


        }

    }


}
