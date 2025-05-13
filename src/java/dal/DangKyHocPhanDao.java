package dal;

import dal.DBConnect;
import model.DangKyHocPhanViewModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DangKyHocPhanDao extends DBConnect {

    public List<DangKyHocPhanViewModel> getDangKyHocPhanByMaSV(String maSV) {
        List<DangKyHocPhanViewModel> list = new ArrayList<>();
        String sql = "SELECT dk.MaDK, dk.MaLHP, l.TenHP, mh.SoTC, l.TenGV, l.ThoiGianHoc, l.PhongHoc, "
                + "l.HocKy, l.NamHoc, dk.NgayDK, l.MocDK, dk.TinhTrang, l.SoLuongSVTD "
                + "FROM DangKyHocPhan dk "
                + "JOIN LopHocPhan l ON dk.MaLHP = l.MaLHP "
                + "JOIN MonHoc mh ON l.MaMH = mh.MaMH "
                + "WHERE dk.MaSV = ?";
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, maSV);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DangKyHocPhanViewModel dkhp = new DangKyHocPhanViewModel();
                dkhp.setMaDK(rs.getString("MaDK"));
                dkhp.setMaLHP(rs.getString("MaLHP"));
                dkhp.setTenHP(rs.getString("TenHP"));
                dkhp.setSoTC(rs.getInt("SoTC"));
                dkhp.setTenGV(rs.getString("TenGV"));
                dkhp.setThoiGianHoc(rs.getString("ThoiGianHoc"));
                dkhp.setPhongHoc(rs.getString("PhongHoc"));
                dkhp.setHocKy(rs.getString("HocKy"));
                dkhp.setNamHoc(rs.getString("NamHoc"));
                dkhp.setNgayDK(rs.getDate("NgayDK"));
                dkhp.setMocDK(rs.getDate("MocDK"));
                dkhp.setTinhTrang(rs.getString("TinhTrang"));
dkhp.setSoLuongSVTD(rs.getInt("SoLuongSVTD"));
                list.add(dkhp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<DangKyHocPhanViewModel> searchByMaLHP(String maLHP, String maSV) {
        List<DangKyHocPhanViewModel> list = new ArrayList<>();
        String sql = "SELECT dk.MaDK, dk.MaLHP, l.TenHP, mh.SoTC, l.TenGV, l.ThoiGianHoc, l.PhongHoc, "
                + "l.HocKy, l.NamHoc, dk.NgayDK, l.MocDK, dk.TinhTrang, l.SoLuongSVTD "
                + "FROM DangKyHocPhan dk "
                + "JOIN LopHocPhan l ON dk.MaLHP = l.MaLHP "
                + "JOIN MonHoc mh ON l.MaMH = mh.MaMH "
                + "WHERE dk.MaSV = ? AND dk.MaLHP LIKE ?";

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, maSV);
            ps.setString(2, "%" + maLHP + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DangKyHocPhanViewModel dkhp = new DangKyHocPhanViewModel();
                dkhp.setMaDK(rs.getString("MaDK"));
                dkhp.setMaLHP(rs.getString("MaLHP"));
                dkhp.setTenHP(rs.getString("TenHP"));
                dkhp.setSoTC(rs.getInt("SoTC"));
                dkhp.setTenGV(rs.getString("TenGV"));
                dkhp.setThoiGianHoc(rs.getString("ThoiGianHoc"));
                dkhp.setPhongHoc(rs.getString("PhongHoc"));
                dkhp.setHocKy(rs.getString("HocKy"));
                dkhp.setNamHoc(rs.getString("NamHoc"));
                dkhp.setNgayDK(rs.getDate("NgayDK"));
                dkhp.setMocDK(rs.getDate("MocDK"));
                dkhp.setTinhTrang(rs.getString("TinhTrang"));
dkhp.setSoLuongSVTD(rs.getInt("SoLuongSVTD"));
                list.add(dkhp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean isThoiGianDangKyHopLe(String maLHP) {
        String sql = "SELECT CASE WHEN GETDATE() <= MocDK THEN 1 ELSE 0 END as ConHan FROM LopHocPhan WHERE MaLHP = ?";
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, maLHP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("ConHan");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean kiemTraTrungLich(String maSV, String maLHP) {
        String sql = "SELECT COUNT(*) as TrungLich FROM DangKyHocPhan dk1 " +
                    "JOIN LopHocPhan l1 ON dk1.MaLHP = l1.MaLHP " +
                    "JOIN LopHocPhan l2 ON l1.ThoiGianHoc = l2.ThoiGianHoc " +
                    "WHERE dk1.MaSV = ? AND l2.MaLHP = ? AND dk1.TinhTrang = N'Đã đăng ký'";
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, maSV);
            ps.setString(2, maLHP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("TrungLich") > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getTongSoTinChi(String maSV) {
        String sql = "SELECT SUM(mh.SoTC) as TongTC " +
                    "FROM DangKyHocPhan dk " +
                    "JOIN LopHocPhan l ON dk.MaLHP = l.MaLHP " +
                    "JOIN MonHoc mh ON l.MaMH = mh.MaMH " +
                    "WHERE dk.MaSV = ? AND dk.TinhTrang = N'Đã đăng ký'";
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, maSV);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("TongTC");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTongSoTinChi(String maSV, String maLHP) {
        String sql = "SELECT (COALESCE((SELECT SUM(mh.SoTC) " +
                    "FROM DangKyHocPhan dk " +
                    "JOIN LopHocPhan l ON dk.MaLHP = l.MaLHP " +
                    "JOIN MonHoc mh ON l.MaMH = mh.MaMH " +
                    "WHERE dk.MaSV = ? AND dk.TinhTrang = N'Đã đăng ký'), 0) + " +
                    "COALESCE((SELECT mh.SoTC " +
                    "FROM LopHocPhan l " +
                    "JOIN MonHoc mh ON l.MaMH = mh.MaMH " +
                    "WHERE l.MaLHP = ?), 0)) as TongTC";
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, maSV);
            ps.setString(2, maLHP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("TongTC");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean dangKyHocPhan(String maDK) {
        String sql = "UPDATE DangKyHocPhan SET TinhTrang = N'Đã đăng ký' WHERE MaDK = ?";
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, maDK);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean huyDangKy(String maDK) {
        String sql = "UPDATE DangKyHocPhan SET TinhTrang = N'Đã hủy' WHERE MaDK = ?";
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, maDK);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean kiemTraSoLuongSVToiDa(String maLHP) {
        String sql = "SELECT SoLuongSVTD FROM LopHocPhan WHERE MaLHP = ?";
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, maLHP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int soLuongHienTai = rs.getInt("SoLuongSVTD");
                return soLuongHienTai < 30; // Return true if still has space
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean tangSoLuongSV(String maLHP) {
        String sql = "UPDATE LopHocPhan SET SoLuongSVTD = SoLuongSVTD + 1 " +
                     "WHERE MaLHP = ? AND SoLuongSVTD < 30";
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, maLHP);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean giamSoLuongSV(String maLHP) {
        String sql = "UPDATE LopHocPhan SET SoLuongSVTD = SoLuongSVTD - 1 WHERE MaLHP = ?";
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, maLHP);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
