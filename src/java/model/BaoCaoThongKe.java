package model;

import java.util.Date;

public class BaoCaoThongKe {
    private String maBC;
    private String tenBC;
    private String loaiTK;
    private Date ngayTao;
    private String maNguoiTao;
    private String noiDung;

    public BaoCaoThongKe() {
    }

    public BaoCaoThongKe(String maBC, String tenBC, String loaiTK, Date ngayTao, String maNguoiTao, String noiDung) {
        this.maBC = maBC;
        this.tenBC = tenBC;
        this.loaiTK = loaiTK;
        this.ngayTao = ngayTao;
        this.maNguoiTao = maNguoiTao;
        this.noiDung = noiDung;
    }

    public String getMaBC() {
        return maBC;
    }

    public void setMaBC(String maBC) {
        this.maBC = maBC;
    }

    public String getTenBC() {
        return tenBC;
    }

    public void setTenBC(String tenBC) {
        this.tenBC = tenBC;
    }

    public String getLoaiTK() {
        return loaiTK;
    }

    public void setLoaiTK(String loaiTK) {
        this.loaiTK = loaiTK;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getMaNguoiTao() {
        return maNguoiTao;
    }

    public void setMaNguoiTao(String maNguoiTao) {
        this.maNguoiTao = maNguoiTao;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    @Override
    public String toString() {
        return "BaoCaoThongKe{" + "maBC=" + maBC + ", tenBC=" + tenBC + ", loaiTK=" + loaiTK + ", ngayTao=" + ngayTao + ", maNguoiTao=" + maNguoiTao + ", noiDung=" + noiDung + '}';
    }

    
}
