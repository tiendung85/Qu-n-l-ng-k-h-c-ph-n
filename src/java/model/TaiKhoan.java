package model;

public class TaiKhoan {
    private String tenDangNhap;
    private String matKhau;
    private String quyen; // 1: Sinh viên, 2: Giảng viên, 3: Nhân viên PĐT

    public TaiKhoan() {
    }

    public TaiKhoan(String tenDangNhap, String matKhau, String quyen) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.quyen = quyen;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" + "tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau + ", quyen=" + quyen + '}';
    }

    
}
