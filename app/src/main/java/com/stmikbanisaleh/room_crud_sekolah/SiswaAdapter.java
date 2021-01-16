package com.stmikbanisaleh.room_crud_sekolah;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SiswaAdapter extends RecyclerView.Adapter <SiswaAdapter.SiswaviewHolder> implements Filterable {
    private Context context;
    private List<Siswa> list = new ArrayList<>();
    private List<Siswa> filteredList = new ArrayList<>();
    private Map<String, String> initialColor = new HashMap<>();
    private View.OnClickListener listener;

    public SiswaAdapter(Context context, List<Siswa> list, View.OnClickListener onClickListener) {
        this.context = context;
        this.list = list;
        this.filteredList = list;
        listener = onClickListener;
        initialColor.put("M", "#0000FF");
        initialColor.put("J", "#008000");
        initialColor.put("N", "#FFFF00");
        initialColor.put("R", "#FFD700");
        initialColor.put("I", "#FF0000");
        initialColor.put("A", "#FFA500");
        initialColor.put("a", "#FFA500");
    }

    @NonNull
    @Override
    public SiswaviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_siswa, parent, false);
        view.setOnClickListener(listener);
        SiswaviewHolder holder = new SiswaviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SiswaviewHolder holder, int position) {
        Siswa siswa = filteredList.get(position);
        String initial = siswa.getNama().substring(0, 1);
        holder.textNama.setText(siswa.getNama());
        holder.textTgl_lahir.setText(siswa.getTanggal_lahir());
        holder.textJenis_kelamin.setText(siswa.getJenis_kelamin());
        holder.textAlamat.setText(siswa.getAlamat());
        holder.viewInitial.getBackground().setColorFilter(Color.parseColor(initialColor.get(initial)), PorterDuff.Mode.SRC_ATOP);;
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Siswa> listFilter = new ArrayList<>();
                String search = constraint.toString();
                for (Siswa siswa : list) {
                    if(siswa.getNama().toLowerCase().contains(search.toLowerCase())) {
                        listFilter.add(siswa);
                    }
                }
                filteredList = listFilter;
                FilterResults results = new FilterResults();
                results.values = listFilter;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (List<Siswa>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class SiswaviewHolder extends RecyclerView.ViewHolder {
        LinearLayout viewInitial = null;
        TextView textNama, textTgl_lahir, textJenis_kelamin, textAlamat, textInitial;
        View viewStatus = null;

        public SiswaviewHolder(@NonNull View view) {
            super(view);
            viewInitial = view.findViewById(R.id.viewInitial);
            textNama = view.findViewById(R.id.textNama);
            textTgl_lahir = view.findViewById(R.id.textTanggalLahir);
            textJenis_kelamin = view.findViewById(R.id.textJenisKelamin);
            textAlamat = view.findViewById(R.id.textAlamat);
            textInitial = view.findViewById(R.id.textInitial);
        }
    }
}