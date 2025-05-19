package dal;

import model.BaoCaoThongKe;
import java.sql.*;
import java.util.*;

public class BaoCaoDao extends DBConnect {
    
    public Map<String, Integer> getThongKeDangKyTheoHocKy(String hocKy, String namHoc) throws SQLException {
        Map<String, Integer> thongKe = new HashMap<>();
        String sql = "SELECT lhp.TenHP, COUNT(dk.MaDK) as SoLuongDK " +
                    "FROM LopHocPhan lhp " +
                    "LEFT JOIN DangKyHocPhan dk ON lhp.MaLHP = dk.MaLHP " +
                    "WHERE lhp.HocKy = ? AND lhp.NamHoc = ? " +
                    "GROUP BY lhp.TenHP";
        
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, hocKy);
            stmt.setString(2, namHoc);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                thongKe.put(rs.getString("TenHP"), rs.getInt("SoLuongDK"));
            }
        }
        return thongKe;
    }
    // anvbaefgh
    public List<Map<String, Object>> getThongKeSinhVienTheoLop() throws SQLException {
        List<Map<String, Object>> thongKe = new ArrayList<>();
        String sql = "SELECT lhp.MaLHP, lhp.TenHP, COUNT(dk.MaSV) as SoLuongSV, lhp.SoLuongSVTD " +
                    "FROM LopHocPhan lhp " +
                    "LEFT JOIN DangKyHocPhan dk ON lhp.MaLHP = dk.MaLHP " +
                    "GROUP BY lhp.MaLHP, lhp.TenHP, lhp.SoLuongSVTD";
        
        try (Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("maLHP", rs.getString("MaLHP"));
                row.put("tenHP", rs.getString("TenHP"));
                row.put("soLuongSV", rs.getInt("SoLuongSV"));
                row.put("soLuongSVTD", rs.getInt("SoLuongSVTD"));
                thongKe.add(row);
            }
        }
        return thongKe;
    }

    public List<BaoCaoThongKe> getAllBaoCao() throws SQLException {
        List<BaoCaoThongKe> list = new ArrayList<>();
        String sql = "SELECT * FROM BaoCaoThongKe ORDER BY NgayTao DESC";
        
        try (Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                BaoCaoThongKe baoCao = new BaoCaoThongKe();
                baoCao.setMaBC(rs.getString("MaBC"));
                baoCao.setTenBC(rs.getString("TenBC"));
                baoCao.setLoaiTK(rs.getString("LoaiTK"));
                baoCao.setNgayTao(rs.getDate("NgayTao"));
                baoCao.setMaNguoiTao(rs.getString("MaNguoiTao"));
                baoCao.setNoiDung(rs.getString("NoiDung"));
                list.add(baoCao);
            }
        }
        return list;
    }

    public boolean createBaoCao(BaoCaoThongKe baoCao) throws SQLException {
        String sql = "INSERT INTO BaoCaoThongKe (MaBC, TenBC, LoaiTK, NgayTao, MaNguoiTao, NoiDung) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, baoCao.getMaBC());
            stmt.setString(2, baoCao.getTenBC());
            stmt.setString(3, baoCao.getLoaiTK());
            stmt.setDate(4, new java.sql.Date(baoCao.getNgayTao().getTime()));
            stmt.setString(5, baoCao.getMaNguoiTao());
            stmt.setString(6, baoCao.getNoiDung());
            
            return stmt.executeUpdate() > 0;
        }
    }

    public BaoCaoThongKe getBaoCaoById(String maBC) throws SQLException {
        String sql = "SELECT * FROM BaoCaoThongKe WHERE MaBC = ?";
        
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, maBC);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    BaoCaoThongKe baoCao = new BaoCaoThongKe();
                    baoCao.setMaBC(rs.getString("MaBC"));
                    baoCao.setTenBC(rs.getString("TenBC"));
                    baoCao.setLoaiTK(rs.getString("LoaiTK"));
                    baoCao.setNgayTao(rs.getDate("NgayTao"));
                    baoCao.setMaNguoiTao(rs.getString("MaNguoiTao"));
                    baoCao.setNoiDung(rs.getString("NoiDung"));
                    return baoCao;
                }
            }
        }
        return null;
    }
}