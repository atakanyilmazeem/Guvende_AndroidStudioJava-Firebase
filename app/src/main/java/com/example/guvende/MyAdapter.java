package com.example.guvende;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<kullanicilar> dataList;

    public MyAdapter(Context context, List<kullanicilar> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.recTitle.setText(dataList.get(position).getKayitad());
        holder.recDesc.setText(dataList.get(position).getKayitsoyad());
        holder.recPriority.setText(dataList.get(position).getKayittc());
       holder.recYer.setText(dataList.get(position).getKayityer());

        //holder.recLang.setText(dataList.get(position).getDataLang());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("kayitad", dataList.get(holder.getAdapterPosition()).getKayitad());
                intent.putExtra("kayitsoyad", dataList.get(holder.getAdapterPosition()).getKayitsoyad());
                intent.putExtra("kayittc", dataList.get(holder.getAdapterPosition()).getKayittc());
                intent.putExtra("kayityer",dataList.get(holder.getAdapterPosition()).getKayityer());

                intent.putExtra("kayittelefon",dataList.get(holder.getAdapterPosition()).getKayittelefon());
                intent.putExtra("kayitdogum",dataList.get(holder.getAdapterPosition()).getKayitdogum());
                intent.putExtra("kayitsifre",dataList.get(holder.getAdapterPosition()).getKayitsifre());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<kullanicilar> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView recTitle, recDesc, recLang, recYer,recPriority;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recCard = itemView.findViewById(R.id.recCard);
        recDesc = itemView.findViewById(R.id.recDesc);
        recTitle = itemView.findViewById(R.id.recTitle);
        recPriority = itemView.findViewById(R.id.recPriority);
        recYer=itemView.findViewById(R.id.recYer);
    }
}