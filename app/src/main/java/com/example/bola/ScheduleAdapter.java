package com.example.bola;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter .MahasiswaViewHolder> {
    private ArrayList<ModelJadwal> dataList;

   public ScheduleAdapter(ArrayList<ModelJadwal> dataList) {
           this.dataList = dataList;
       }
    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.itemview, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position) {
        holder.textaway.setText(dataList.get(position).getStrAwayTeam());
        holder.txthome.setText(dataList.get(position).getStrHomeTeam());
        holder.txtdate.setText(dataList.get(position).getStrDate());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private TextView txthome, textaway, txtdate;

        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            txthome = (TextView) itemView.findViewById(R.id.txtteamhome);
            textaway = (TextView) itemView.findViewById(R.id.txtteamaway);
            txtdate = (TextView) itemView.findViewById(R.id.txtdate);
        }
    }
}
