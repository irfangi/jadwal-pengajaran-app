package com.ero.jadwalpengajaran.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ero.jadwalpengajaran.R;
import com.ero.jadwalpengajaran.model.DataListJadwal;
import com.ero.jadwalpengajaran.model.ResultJadwal;
import com.ero.jadwalpengajaran.model.ValueJadwal;

import java.util.ArrayList;
import java.util.List;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder> {

    List<DataListJadwal> jadwal;
    Context context;

    public JadwalAdapter(List<DataListJadwal> jadwal, Context context) {
        this.jadwal = jadwal;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_event, viewGroup, false);
        ViewHolder itemViewHolder = new ViewHolder(view);
        return itemViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.tvNama.setText(jadwal.get(i).getNama());
        holder.tvJudul.setText(jadwal.get(i).getJudul());
        holder.tvNim.setText(jadwal.get(i).getNim());
        holder.tvWaktu.setText(jadwal.get(i).getWaktu()+", ");
        holder.tvRuang.setText(jadwal.get(i).getRuang());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog detailEvent = new BottomSheetDialog(context);
                detailEvent.setContentView(View.inflate(context, R.layout.detail_event, null));
                detailEvent.show();

                TextView tvJudul = detailEvent.findViewById(R.id.tv_judul);
                TextView tvNama = detailEvent.findViewById(R.id.tv_nama);
                TextView tvNim = detailEvent.findViewById(R.id.tv_nim);
                TextView tvWaktu = detailEvent.findViewById(R.id.tv_waktu);
                TextView tvRuang = detailEvent.findViewById(R.id.tv_ruang);
                TextView tvPemateri1 = detailEvent.findViewById(R.id.tv_narasumber1);
                TextView tvPemateri2 = detailEvent.findViewById(R.id.tv_narasumber2);
                TextView tvPemateri3 = detailEvent.findViewById(R.id.tv_narasumber3);

                tvNama.setText(jadwal.get(i).getNama());
                tvJudul.setText(jadwal.get(i).getJudul());
                tvNim.setText(jadwal.get(i).getNim());
                tvWaktu.setText(jadwal.get(i).getWaktu()+", ");
                tvRuang.setText(jadwal.get(i).getRuang());
                tvPemateri1.setText(jadwal.get(i).getNarasumber1());
                tvPemateri2.setText(jadwal.get(i).getNarasumber2());
                tvPemateri3.setText(jadwal.get(i).getNarasumber3());
            }
        });
    }

    @Override
    public int getItemCount() {
        return (jadwal == null) ? 0 : jadwal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul, tvNim, tvNama, tvWaktu, tvRuang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvNim = itemView.findViewById(R.id.tv_nim);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvWaktu = itemView.findViewById(R.id.tv_waktu);
            tvRuang = itemView.findViewById(R.id.tv_ruang);
        }
    }
}
