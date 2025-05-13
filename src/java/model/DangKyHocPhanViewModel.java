package model;

import java.util.Date;

public class DangKyHocPhanViewModel {

    private String maDK;
    private String maLHP;
    private String maSV;
    private String tenHP;
    private int soTC;
    private String tenGV;
    private String thoiGianHoc;
    private String phongHoc;
    private String hocKy;
    private String namHoc;
    private Date ngayDK;
    private Date mocDK;
    private String tinhTrang;
private int soLuongSVTD;

    public DangKyHocPhanViewModel() {
    }

    public DangKyHocPhanViewModel(String maDK, String maLHP, String maSV, String tenHP, int soTC, String tenGV, String thoiGianHoc, String phongHoc, String hocKy, String namHoc, Date ngayDK, Date mocDK, String tinhTrang) {
        this.maDK = maDK;
        this.maLHP = maLHP;
        this.maSV = maSV;
        this.tenHP = tenHP;
        this.soTC = soTC;
        this.tenGV = tenGV;
        this.thoiGianHoc = thoiGianHoc;
        this.phongHoc = phongHoc;
        this.hocKy = hocKy;
        this.namHoc = namHoc;
        this.ngayDK = ngayDK;
        this.mocDK = mocDK;
        this.tinhTrang = tinhTrang;
    }

    public String getMaDK() {
        return maDK;
    }

    public void setMaDK(String maDK) {
        this.maDK = maDK;
    }

    public String getMaLHP() {
        return maLHP;
    }

    public void setMaLHP(String maLHP) {
        this.maLHP = maLHP;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenHP() {
        return tenHP;
    }

    public void setTenHP(String tenHP) {
        this.tenHP = tenHP;
    }

    public int getSoTC() {
        return soTC;
    }

    public void setSoTC(int soTC) {
        this.soTC = soTC;
    }

    public String getTenGV() {
        return tenGV;
    }

    public void setTenGV(String tenGV) {
        this.tenGV = tenGV;
    }

    public String getThoiGianHoc() {
        return thoiGianHoc;
    }

    public void setThoiGianHoc(String thoiGianHoc) {
        this.thoiGianHoc = thoiGianHoc;
    }

    public String getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }

    public String getHocKy() {
        return hocKy;
    }

    public void setHocKy(String hocKy) {
        this.hocKy = hocKy;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    public Date getNgayDK() {
        return ngayDK;
    }

    public void setNgayDK(Date ngayDK) {
        this.ngayDK = ngayDK;
    }

    public Date getMocDK() {
        return mocDK;
    }

    public void setMocDK(Date mocDK) {
        this.mocDK = mocDK;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getSoLuongSVTD() {
        return soLuongSVTD;
    }

    public void setSoLuongSVTD(int soLuongSVTD) {
        this.soLuongSVTD = soLuongSVTD;
    }

    @Override
    public String toString() {
        return "DangKyHocPhanViewModel{" + "maDK=" + maDK + ", maLHP=" + maLHP + ", maSV=" + maSV + ", tenHP=" + tenHP + ", soTC=" + soTC + ", tenGV=" + tenGV + ", thoiGianHoc=" + thoiGianHoc + ", phongHoc=" + phongHoc + ", hocKy=" + hocKy + ", namHoc=" + namHoc + ", ngayDK=" + ngayDK + ", mocDK=" + mocDK + ", tinhTrang=" + tinhTrang + '}';
    }

    

}
