package model;

import java.util.Date;

public class NhanVienPhongDaoTao {
    private String maNV;
    private String hoTen;
    private Date ngaySinh;
    private String gioiTinh;
    private String email;
    private String sdt;
    private String chucVu;
    private String phongLV;

    public NhanVienPhongDaoTao() {
    }

    public NhanVienPhongDaoTao(String maNV, String hoTen, Date ngaySinh, String gioiTinh, String email, String sdt, String chucVu, String phongLV) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.sdt = sdt;
        this.chucVu = chucVu;
        this.phongLV = phongLV;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getPhongLV() {
        return phongLV;
    }

    public void setPhongLV(String phongLV) {
        this.phongLV = phongLV;
    }

    @Override
    public String toString() {
        return "NhanVienPhongDaoTao{" + "maNV=" + maNV + ", hoTen=" + hoTen + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh + ", email=" + email + ", sdt=" + sdt + ", chucVu=" + chucVu + ", phongLV=" + phongLV + '}';
    }

   
}
