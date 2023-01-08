package com.if5b.komik_kamikaze;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class komikadapter extends  RecyclerView.Adapter<komikadapter.ViewHolder> {
        private List<komikmodel.Result> results = new ArrayList<>();
        private OnAdapterListener listener;
        public komikadapter(List<komikmodel.Result> results, OnAdapterListener listener) {
            this.results = results;
            this.listener = listener;
        }

        public void setData(List<komikmodel.Result> data) {
            results.clear();
            results.addAll(data);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public komikadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_komik, parent, false)
            );
        }

        @Override
        public void onBindViewHolder(@NonNull komikadapter.ViewHolder holder, int position) {
            komikmodel.Result result = results.get(position);
            holder.nm_komik.setText(result.getNm_komik());
            holder.genre.setText(result.getGenre());
            Glide.with(holder.itemView.getContext())
                    .load(result.getGbr_komik())
                    .fitCenter()
                    .into(holder.gbr_komik);
        }

        @Override
        public int getItemCount() {
            return results.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView nm_komik, genre;
            ImageView gbr_komik;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                nm_komik = itemView.findViewById(R.id.nama_komik);
                genre = itemView.findViewById(R.id.genre);
                gbr_komik = itemView.findViewById(R.id.gmbr_komik);
            }
        }

        public interface OnAdapterListener {
            void onClick(komikmodel.Result result);
        }
}
