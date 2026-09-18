package com.example.quanlysanpham; // CHÚ Ý: Giữ package của bạn

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder> {

    private ArrayList<SanPham> danhSachSP;

    public SanPhamAdapter(ArrayList<SanPham> danhSachSP) {
        this.danhSachSP = danhSachSP;
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_san_pham, parent, false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
        SanPham sp = danhSachSP.get(position);
        holder.tvTenSP.setText(sp.getTenSP());
        holder.tvMaSP.setText("Mã SP: " + sp.getMaSP());
        holder.tvGia.setText("Giá: " + sp.getGia());
        holder.tvSoLuong.setText("SL: " + sp.getSoLuong());
        holder.tvTongTien.setText("Tổng tiền: " + sp.tinhTongTien());
    }

    @Override
    public int getItemCount() {
        return danhSachSP.size();
    }

    public static class SanPhamViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSP, tvMaSP, tvGia, tvSoLuong, tvTongTien;

        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSP = itemView.findViewById(R.id.tvTenSP_Item);
            tvMaSP = itemView.findViewById(R.id.tvMaSP_Item);
            tvGia = itemView.findViewById(R.id.tvGia_Item);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong_Item);
            tvTongTien = itemView.findViewById(R.id.tvTongTien_Item);
        }
    }
}
