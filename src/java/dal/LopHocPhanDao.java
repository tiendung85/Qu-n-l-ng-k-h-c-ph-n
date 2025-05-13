package dal;

import model.LopHocPhan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LopHocPhanDao extends DBConnect {

    public List<LopHocPhan> getAllLopHocPhan() throws SQLException {
        List<LopHocPhan> list = new ArrayList<>();
        String sql = "SELECT lhp.*, mh.SoTC FROM LopHocPhan lhp " +
                    "JOIN MonHoc mh ON lhp.MaMH = mh.MaMH";

try (PreparedStatement stmt = c.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            LopHocPhan lhp = mapResultSetToLopHocPhan(rs);
            list.add(lhp);
}
        }
        return list;
    }

    public LopHocPhan getLopHocPhanById(String maLHP) throws SQLException {
        String sql = "SELECT lhp.*, mh.SoTC FROM LopHocPhan lhp " +
                    "JOIN MonHoc mh ON lhp.MaMH = mh.MaMH " +
                    "WHERE lhp.MaLHP = ?";

try (PreparedStatement stmt = c.prepareStatement(sql)) {
        stmt.setString(1, maLHP);
try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
return mapResultSetToLopHocPhan(rs);
}
                }
            } catch (SQLException e) {
            System.out.println("Error searching LopHocPhan by ID: " + e.getMessage());
            throw e;
        }
        return null;
    }

    public List<LopHocPhan> searchLopHocPhan(String searchTerm) throws SQLException {
        List<LopHocPhan> list = new ArrayList<>();
        String sql = "SELECT lhp.*, mh.SoTC FROM LopHocPhan lhp " +
                    "JOIN MonHoc mh ON lhp.MaMH = mh.MaMH " +
                    "WHERE lhp.MaLHP LIKE ? OR lhp.TenHP LIKE ?";
        
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            String pattern = "%" + searchTerm + "%";
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToLopHocPhan(rs));
                }
            }
        }
        return list;
    }

    public List<LopHocPhan> getLopHocPhanByGiangVien(String tenGV) throws SQLException {
        List<LopHocPhan> list = new ArrayList<>();
String sql = "SELECT lhp.*, mh.SoTC, mh.TenHP FROM LopHocPhan lhp " +
             "JOIN MonHoc mh ON lhp.MaMH = mh.MaMH " +
             "WHERE lhp.TenGV = ?";

                try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, tenGV);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToLopHocPhan(rs));
                }
            }
        }
        return list;
    }

    public boolean updateLopHocPhan(LopHocPhan lhp) throws SQLException {
        String sql = "UPDATE LopHocPhan SET TenHP=?, TenGV=?, MaMH=?, HocKy=?, "
                + "NamHoc=?, SoLuongSVTD=?, ThoiGianHoc=?, PhongHoc=?, MocDK=? "
                + "WHERE MaLHP=?";
        
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, lhp.getTenHP());
            stmt.setString(2, lhp.getTenGV());
            stmt.setString(3, lhp.getMaMH());
            stmt.setString(4, lhp.getHocKy());
            stmt.setString(5, lhp.getNamHoc());
            stmt.setInt(6, lhp.getSoLuongSVTD());
            stmt.setString(7, lhp.getThoiGianHoc());
            stmt.setString(8, lhp.getPhongHoc());
            stmt.setDate(9, new java.sql.Date(lhp.getMocDK().getTime()));
            stmt.setString(10, lhp.getMaLHP());
            
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deleteLopHocPhan(String maLHP) throws SQLException {
     
        PreparedStatement stmtDangKy = null;
        PreparedStatement stmtLopHP = null;
        
        try {
       
            c.setAutoCommit(false); // Start transaction
            
            // First delete related records in DangKyHocPhan
            String sqlDangKy = "DELETE FROM DangKyHocPhan WHERE MaLHP = ?";
            stmtDangKy = c.prepareStatement(sqlDangKy);
            stmtDangKy.setString(1, maLHP);
            stmtDangKy.executeUpdate();
            
            // Then delete the LopHocPhan record
            String sqlLopHP = "DELETE FROM LopHocPhan WHERE MaLHP = ?";
            stmtLopHP = c.prepareStatement(sqlLopHP);
            stmtLopHP.setString(1, maLHP);
            int result = stmtLopHP.executeUpdate();

            c.commit(); // Commit transaction
            return result > 0;

} catch (SQLException e) {
if (c != null) {
                try {
                    c.rollback(); // Rollback on error
                } catch (SQLException ex) {
                    System.out.println("Error rolling back: " + ex.getMessage());
                }
            }
            System.out.println("Error deleting LopHocPhan: " + e.getMessage());
            throw new SQLException("Không thể xóa lớp học phần vì có dữ liệu liên quan");
        } finally {
            if (stmtDangKy != null) {
                stmtDangKy.close();
            }
            if (stmtLopHP != null) {
                stmtLopHP.close();
            }
            if (c != null) {
                c.setAutoCommit(true);
            }
        }
    }

    public boolean createLopHocPhan(LopHocPhan lhp) throws SQLException {
        String sql = "INSERT INTO LopHocPhan (MaLHP, TenHP, TenGV, MaMH, HocKy, " +
                     "NamHoc, SoLuongSVTD, ThoiGianHoc, PhongHoc, MocDK) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, lhp.getMaLHP());
            stmt.setString(2, lhp.getTenHP());
            stmt.setString(3, lhp.getTenGV());
            stmt.setString(4, lhp.getMaMH());
            stmt.setString(5, lhp.getHocKy());
            stmt.setString(6, lhp.getNamHoc());
            stmt.setInt(7, lhp.getSoLuongSVTD());
            stmt.setString(8, lhp.getThoiGianHoc());
            stmt.setString(9, lhp.getPhongHoc());
            stmt.setDate(10, new java.sql.Date(lhp.getMocDK().getTime()));
            
            return stmt.executeUpdate() > 0;
        }
    }

    private LopHocPhan mapResultSetToLopHocPhan(ResultSet rs) throws SQLException {
            LopHocPhan lhp = new LopHocPhan();
            lhp.setMaLHP(rs.getString("MaLHP"));
            lhp.setTenHP(rs.getString("TenHP"));
            lhp.setTenGV(rs.getString("TenGV"));
            lhp.setMaMH(rs.getString("MaMH"));
            lhp.setHocKy(rs.getString("HocKy"));
            lhp.setNamHoc(rs.getString("NamHoc"));
            lhp.setSoLuongSVTD(rs.getInt("SoLuongSVTD"));
            lhp.setThoiGianHoc(rs.getString("ThoiGianHoc"));
            lhp.setPhongHoc(rs.getString("PhongHoc"));
            lhp.setMocDK(rs.getDate("MocDK"));
lhp.setSoTC(rs.getInt("SoTC")); // Add this line to get SoTC
            return lhp;
            }
}
