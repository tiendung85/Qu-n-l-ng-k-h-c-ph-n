package model;

public class MonHoc {
    private String maMH;
    private String tenMH;
    private int soTC;
    private int soTiet;
    private String khoaPT;

    public MonHoc() {
    }

    public MonHoc(String maMH, String tenMH, int soTC, int soTiet, String khoaPT) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.soTC = soTC;
        this.soTiet = soTiet;
        this.khoaPT = khoaPT;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public int getSoTC() {
        return soTC;
    }

    public void setSoTC(int soTC) {
        this.soTC = soTC;
    }

    public int getSoTiet() {
        return soTiet;
    }

    public void setSoTiet(int soTiet) {
        this.soTiet = soTiet;
    }

    public String getKhoaPT() {
        return khoaPT;
    }

    public void setKhoaPT(String khoaPT) {
        this.khoaPT = khoaPT;
    }

    @Override
    public String toString() {
        return "MonHoc{" + "maMH=" + maMH + ", tenMH=" + tenMH + ", soTC=" + soTC + ", soTiet=" + soTiet + ", khoaPT=" + khoaPT + '}';
    }

    
}
