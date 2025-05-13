/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class DangKyHocPhan {
    private String maDK;
    private String maSV;
    private String maLHP;
    private Date ngayDK;
        private String tinhTrang;
     


    public DangKyHocPhan() {
    }

    public DangKyHocPhan(String maDK, String maSV, String maLHP, Date ngayDK, String tinhTrang) {
        this.maDK = maDK;
        this.maSV = maSV;
        this.maLHP = maLHP;
        this.ngayDK = ngayDK;
        this.tinhTrang = tinhTrang;
    }

    public String getMaDK() {
        return maDK;
    }

    public void setMaDK(String maDK) {
        this.maDK = maDK;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getMaLHP() {
        return maLHP;
    }

    public void setMaLHP(String maLHP) {
        this.maLHP = maLHP;
    }

    public Date getNgayDK() {
        return ngayDK;
    }

    public void setNgayDK(Date ngayDK) {
        this.ngayDK = ngayDK;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    @Override
    public String toString() {
        return "DangKyHocPhan{" + "maDK=" + maDK + ", maSV=" + maSV + ", maLHP=" + maLHP + ", ngayDK=" + ngayDK + ", tinhTrang=" + tinhTrang + '}';
    }

  

    
    
    

}

