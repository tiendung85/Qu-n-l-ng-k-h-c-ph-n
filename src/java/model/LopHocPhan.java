package model;

import java.util.Date;

public class LopHocPhan {
    private String maLHP;
    private String tenHP;
    private String tenGV;
    private String maMH;
    private String hocKy;
    private String namHoc;
    private int soLuongSVTD;
    private String thoiGianHoc;
    private String phongHoc;
    private Date mocDK;
private int soTC;
   
    
    public LopHocPhan() {
    }

    public LopHocPhan(String maLHP, String tenHP, String tenGV, String maMH, String hocKy, 
String namHoc, int soLuongSVTD, String thoiGianHoc, String phongHoc, 
Date mocDK, int soTC) {
        this.maLHP = maLHP;
        this.tenHP = tenHP;
        this.tenGV = tenGV;
        this.maMH = maMH;
        this.hocKy = hocKy;
        this.namHoc = namHoc;
        this.soLuongSVTD = soLuongSVTD;
        this.thoiGianHoc = thoiGianHoc;
        this.phongHoc = phongHoc;
        this.mocDK = mocDK;
this.soTC = soTC;
    
    }

    public String getMaLHP() {
        return maLHP;
    }

    public void setMaLHP(String maLHP) {
        this.maLHP = maLHP;
    }

    public String getTenHP() {
        return tenHP;
    }

    public void setTenHP(String tenHP) {
        this.tenHP = tenHP;
    }

    public String getTenGV() {
        return tenGV;
    }

    public void setTenGV(String tenGV) {
        this.tenGV = tenGV;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
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

    public int getSoLuongSVTD() {
        return soLuongSVTD;
    }

    public void setSoLuongSVTD(int soLuongSVTD) {
        this.soLuongSVTD = soLuongSVTD;
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

    public Date getMocDK() {
        return mocDK;
    }

    public void setMocDK(Date mocDK) {
        this.mocDK = mocDK;
    }

    public int getSoTC() {
        return soTC;
    }

    public void setSoTC(int soTC) {
        this.soTC = soTC;
    }

    @Override
    public String toString() {
        return "LopHocPhan{" + "maLHP=" + maLHP + ", tenHP=" + tenHP + ", tenGV=" + tenGV + ", maMH=" + maMH + ", hocKy=" + hocKy + ", namHoc=" + namHoc + ", soLuongSVTD=" + soLuongSVTD + ", thoiGianHoc=" + thoiGianHoc + ", phongHoc=" + phongHoc + ", mocDK=" + mocDK + ", soTC=" + soTC + '}';
    }


}
