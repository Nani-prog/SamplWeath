package com.example.samplew.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.samplew.Interface.CityOnclick;
import com.example.samplew.R;
import com.example.samplew.model.CityModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.cityViewHolder> {
    Context context;
    List<CityModel> cityModelList;
    CityOnclick cityOnclick;

    public CityAdapter( List<CityModel> cityModelList,CityOnclick cityOnclick) {
//        context = this.context;
        this.cityModelList = cityModelList;
        this.cityOnclick = cityOnclick;
    }
    public void updatemovielist(List<CityModel> list) {
        this.cityModelList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CityAdapter.cityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_spinner_items, parent, false);
        cityViewHolder viewHolder = new cityViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull cityViewHolder holder, int position) {
        holder.cityTv.setText(cityModelList.get(position).getName());
        holder.stateTv.setText(cityModelList.get(position).getState());
        holder.coTv.setText("" + cityModelList.get(position).getLat() + "," + cityModelList.get(position).getLon());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityOnclick.OnItemClick(cityModelList.get(position).getLat(),cityModelList.get(position).getLon(),cityModelList.get(position).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityModelList.size();
    }

    public class cityViewHolder extends RecyclerView.ViewHolder {
        TextView cityTv,coTv,stateTv;
        public cityViewHolder(@NonNull View itemView) {
            super(itemView);
             cityTv = (TextView) itemView.findViewById(R.id.cityTv);
             coTv = (TextView) itemView.findViewById(R.id.coTv);
             stateTv = (TextView) itemView.findViewById(R.id.stateTv);
        }
    }
}
