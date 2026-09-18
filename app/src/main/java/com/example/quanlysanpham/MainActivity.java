package com.example.quanlysanpham;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText edtMaSP, edtTenSP, edtGia, edtSoLuong;
    private Button btnThem, btnTimMax;
    private RecyclerView recyclerViewSP;

    private ArrayList<SanPham> danhSachSP;
    private SanPhamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo list và adapter
        danhSachSP = new ArrayList<>();
        adapter = new SanPhamAdapter(danhSachSP);

        // Ánh xạ
        edtMaSP = findViewById(R.id.edtMaSP);
        edtTenSP = findViewById(R.id.edtTenSP);
        edtGia = findViewById(R.id.edtGia);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        btnThem = findViewById(R.id.btnThem);
        btnTimMax = findViewById(R.id.btnTimMax);
        recyclerViewSP = findViewById(R.id.recyclerViewSP);

        // Cấu hình RecyclerView
        recyclerViewSP.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSP.setAdapter(adapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themSanPham();
            }
        });

        btnTimMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timSanPhamGiaCaoNhat();
            }
        });
    }

    private void themSanPham() {
        String ma = edtMaSP.getText().toString().trim();
        String ten = edtTenSP.getText().toString().trim();
        String giaStr = edtGia.getText().toString().trim();
        String soLuongStr = edtSoLuong.getText().toString().trim();

        if (ma.isEmpty() || ten.isEmpty() || giaStr.isEmpty() || soLuongStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double gia = Double.parseDouble(giaStr);
            int soLuong = Integer.parseInt(soLuongStr);

            SanPham sp = new SanPham(ma, ten, gia, soLuong);
            danhSachSP.add(0, sp); // Thêm lên đầu danh sách

            // Báo adapter cập nhật giao diện
            adapter.notifyItemInserted(0);
            recyclerViewSP.scrollToPosition(0);

            // Xóa form
            edtMaSP.setText("");
            edtTenSP.setText("");
            edtGia.setText("");
            edtSoLuong.setText("");

            // Trỏ chuột lại ô Mã SP
            edtMaSP.requestFocus();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Giá hoặc số lượng không hợp lệ!", Toast.LENGTH_SHORT).show();
        }
    }

    private void timSanPhamGiaCaoNhat() {
        if (danhSachSP.isEmpty()) {
            Toast.makeText(this, "Chưa có sản phẩm nào!", Toast.LENGTH_SHORT).show();
            return;
        }

        SanPham maxSP = danhSachSP.get(0);
        for (SanPham sp : danhSachSP) {
            if (sp.getGia() > maxSP.getGia()) {
                maxSP = sp;
            }
        }

        // Dùng AlertDialog hiện thông báo cho sang trọng
        new AlertDialog.Builder(this)
                .setTitle("🏆 SẢN PHẨM GIÁ CAO NHẤT")
                .setMessage("Tên: " + maxSP.getTenSP() + "\n" +
                        "Mã SP: " + maxSP.getMaSP() + "\n" +
                        "Giá: " + maxSP.getGia() + "\n" +
                        "Số lượng: " + maxSP.getSoLuong())
                .setPositiveButton("ĐÓNG", null)
                .show();
    }
}
