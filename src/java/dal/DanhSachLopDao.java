package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.DangKyHocPhan;
import model.LopHocPhan;
import model.SinhVien;

public class DanhSachLopDao extends DBConnect {
    
    public List<LopHocPhan> getLopHocPhanByGiangVien(String tenGV) {
        List<LopHocPhan> danhSachLop = new ArrayList<>();
// Modified query to only get classes with registered students
     String sql = "SELECT DISTINCT l.*, mh.soTC FROM LopHocPhan l " +
             "INNER JOIN DangKyHocPhan dk ON l.maLHP = dk.maLHP " +
             "INNER JOIN MonHoc mh ON l.maMH = mh.maMH " +
             "WHERE l.tenGV = ? AND dk.tinhTrang = N'Đã đăng ký'";

        
        try (PreparedStatement ps = c.prepareStatement(sql)) {
                        ps.setString(1, tenGV);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                LopHocPhan lhp = new LopHocPhan();
                lhp.setMaLHP(rs.getString("maLHP"));
                lhp.setTenHP(rs.getString("tenHP"));
                lhp.setTenGV(rs.getString("tenGV"));
                lhp.setMaMH(rs.getString("maMH"));
                lhp.setHocKy(rs.getString("hocKy"));
                lhp.setNamHoc(rs.getString("namHoc"));
                lhp.setSoLuongSVTD(rs.getInt("soLuongSVTD"));
                lhp.setThoiGianHoc(rs.getString("thoiGianHoc"));
                lhp.setPhongHoc(rs.getString("phongHoc"));
                lhp.setMocDK(rs.getDate("mocDK"));
                lhp.setSoTC(rs.getInt("soTC"));
                danhSachLop.add(lhp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachLop;
    }
    
    public List<SinhVien> getSinhVienByLopHocPhan(String maLHP) {
        List<SinhVien> danhSachSV = new ArrayList<>();
        String sql = "SELECT sv.*, dk.ngayDK " +
                    "FROM SinhVien sv " +
                    "JOIN DangKyHocPhan dk ON sv.maSV = dk.maSV " +
                    "WHERE dk.maLHP = ? AND dk.tinhTrang = N'Đã đăng ký' " +
                    "ORDER BY sv.hoTen";  
        
        try (PreparedStatement ps = c.prepareStatement(sql)) {
                        ps.setString(1, maLHP);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                SinhVien sv = new SinhVien();
                sv.setMaSV(rs.getString("maSV"));
                sv.setHoTen(rs.getString("hoTen"));
                sv.setNgaySinh(rs.getDate("ngaySinh"));
                sv.setGioiTinh(rs.getString("gioiTinh"));
                sv.setEmail(rs.getString("email"));
                sv.setSdt(rs.getString("sdt"));
                  sv.setDiaChi(rs.getString("diachi"));
                sv.setLopHC(rs.getString("LopHC"));
                sv.setTinhTrang(rs.getString("tinhTrang"));       
                danhSachSV.add(sv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachSV;
    }

    // Add method to get total number of registered students for a class
    public int getSoLuongSinhVienDangKy(String maLHP) {
        String sql = "SELECT COUNT(*) as total FROM DangKyHocPhan " +
                    "WHERE maLHP = ? AND tinhTrang = N'Đã đăng ký'";
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, maLHP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
}