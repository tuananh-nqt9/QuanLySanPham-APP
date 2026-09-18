package com.example.quanlysanpham; // Đổi lại theo package của bạn nếu khác

public class SanPham {
    private String maSP;
    private String tenSP;
    private double gia;
    private int soLuong;

    public SanPham(String maSP, String tenSP, double gia, int soLuong) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public String getMaSP() { return maSP; }
    public String getTenSP() { return tenSP; }
    public double getGia() { return gia; }
    public int getSoLuong() { return soLuong; }

    // Phương thức tính tổng tiền
    public double tinhTongTien() {
        return this.gia * this.soLuong;
    }

    // Trả về chuỗi hiển thị thông tin sản phẩm
    public String getThongTin() {
        return "Mã SP: " + maSP + "\n" +
                "Tên SP: " + tenSP + "\n" +
                "Giá: " + gia + " | Số lượng: " + soLuong + "\n" +
                "Tổng tiền: " + tinhTongTien();
    }
}
