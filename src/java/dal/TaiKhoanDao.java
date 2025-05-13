/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://.netbeans/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.*;
import model.*;

public class TaiKhoanDao extends DBConnect {

    public List<TaiKhoan> readTaiKhoan() {
        List<TaiKhoan> res = new ArrayList();
        String query = "Select * from TaiKhoan";
        try {
            PreparedStatement stmt = c.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TaiKhoan u = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3));
                res.add(u);
            }
        } catch (Exception e) {
        }
        return res;
    }

    public List<SinhVien> readSinhVien() {
        List<SinhVien> res = new ArrayList<>();
        String query = "SELECT * FROM SinhVien";
        try {
            PreparedStatement stmt = c.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SinhVien sv = new SinhVien(
                        rs.getString("maSV"),
                        rs.getString("hoTen"),
                        rs.getDate("ngaySinh"),
                        rs.getString("gioiTinh"),
                        rs.getString("email"),
                        rs.getString("sdt"),
                        rs.getString("diaChi"),
                        rs.getString("lopHC"),
                        rs.getString("tinhTrang")
                );
                res.add(sv);
            }
        } catch (Exception e) {
        }
        return res;
    }

    public List<GiangVien> readGiangVien() {
        List<GiangVien> res = new ArrayList<>();
        String query = "SELECT * FROM GiangVien";
        try {
            PreparedStatement stmt = c.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                GiangVien gv = new GiangVien(
                        rs.getString("maGV"),
                        rs.getString("hoTen"),
                        rs.getDate("ngaySinh"),
                        rs.getString("gioiTinh"),
                        rs.getString("email"),
                        rs.getString("sdt"),
                        rs.getString("hocVi"),
                        rs.getString("khoa")
                );
                res.add(gv);
            }
        } catch (Exception e) {
        }
        return res;
    }

    public List<NhanVienPhongDaoTao> readNhanVienPhongDaoTao() {
        List<NhanVienPhongDaoTao> res = new ArrayList<>();
        String query = "SELECT * FROM NhanVienPhongDaoTao";
        try {
            PreparedStatement stmt = c.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                NhanVienPhongDaoTao nv = new NhanVienPhongDaoTao(
                        rs.getString("maNV"),
                        rs.getString("hoTen"),
                        rs.getDate("ngaySinh"),
                        rs.getString("gioiTinh"),
                        rs.getString("email"),
                        rs.getString("sdt"),
                        rs.getString("chucVu"),
                        rs.getString("phongLV")
                );
                res.add(nv);
            }
        } catch (Exception e) {
            System.err.println("Lỗi readNhanVienPhongDaoTao: " + e.getMessage());
        }
        return res;
    }

    public void addTaiKhoan(TaiKhoan tk, Connection conn) throws SQLException {
        if (getTaiKhoan(tk.getTenDangNhap())) {
            throw new SQLException("Tên đăng nhập đã tồn tại: " + tk.getTenDangNhap());
        }
        String query = "INSERT INTO TaiKhoan (tenDangNhap, matKhau, quyen) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tk.getTenDangNhap());
            stmt.setString(2, tk.getMatKhau());
            stmt.setString(3, tk.getQuyen());
            stmt.executeUpdate();
        }
    }

    public void addSinhVien(SinhVien sv, String matKhau, Connection conn) throws SQLException {
        if (getTaiKhoan(sv.getMaSV())) {
            throw new SQLException("Mã sinh viên đã tồn tại: " + sv.getMaSV());
        }
        // First, add TaiKhoan
        TaiKhoan tk = new TaiKhoan();
        tk.setTenDangNhap(sv.getMaSV());
        tk.setMatKhau(matKhau);
        tk.setQuyen("1");
        addTaiKhoan(tk, conn);

        // Then, add SinhVien
        String query = "INSERT INTO SinhVien (maSV, hoTen, ngaySinh, gioiTinh, email, sdt, diaChi, lopHC, tinhTrang) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, sv.getMaSV());
            stmt.setString(2, sv.getHoTen());
            stmt.setDate(3, new java.sql.Date(sv.getNgaySinh().getTime()));
            stmt.setString(4, sv.getGioiTinh());
            stmt.setString(5, sv.getEmail());
            stmt.setString(6, sv.getSdt());
            stmt.setString(7, sv.getDiaChi());
            stmt.setString(8, sv.getLopHC());
            stmt.setString(9, sv.getTinhTrang());
            stmt.executeUpdate();
        }
    }

//    public void addGiangVien(GiangVien gv, String matKhau, Connection conn) throws SQLException {
//        if (getTaiKhoan(gv.getMaGV())) {
//            throw new SQLException("Mã giảng viên đã tồn tại: " + gv.getMaGV());
//        }
//        // First, add TaiKhoan
//        TaiKhoan tk = new TaiKhoan();
//        tk.setTenDangNhap(gv.getMaGV());
//        tk.setMatKhau(matKhau);
//        tk.setQuyen("2");
//        addTaiKhoan(tk, conn);
//
//        // Then, add GiangVien
//        String query = "INSERT INTO GiangVien (maGV, hoTen, ngaySinh, gioiTinh, email, sdt, hocVi, khoa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//        try (PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setString(1, gv.getMaGV());
//            stmt.setString(2, gv.getHoTen());
//            stmt.setDate(3, new java.sql.Date(gv.getNgaySinh().getTime()));
//            stmt.setString(4, gv.getGioiTinh());
//            stmt.setString(5, gv.getEmail());
//            stmt.setString(6, gv.getSdt());
//
//            stmt.setString(7, gv.getHocVi());
//            stmt.setString(8, gv.getKhoa());
//            stmt.executeUpdate();
//        }
//    }
//
//    public void addNhanVienPhongDaoTao(NhanVienPhongDaoTao nv, String matKhau, Connection conn) throws SQLException {
//        if (getTaiKhoan(nv.getMaNV())) {
//            throw new SQLException("Mã nhân viên đã tồn tại: " + nv.getMaNV());
//        }
//        // First, add TaiKhoan
//        TaiKhoan tk = new TaiKhoan();
//        tk.setTenDangNhap(nv.getMaNV());
//        tk.setMatKhau(matKhau);
//        tk.setQuyen("3");
//        addTaiKhoan(tk, conn);
//
//        // Then, add NhanVienPhongDaoTao
//        String query = "INSERT INTO NhanVienPhongDaoTao (maNV, hoTen, ngaySinh, gioiTinh, email, sdt, chucVu, phongLV) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//        try (PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setString(1, nv.getMaNV());
//            stmt.setString(2, nv.getHoTen());
//            stmt.setDate(3, new java.sql.Date(nv.getNgaySinh().getTime()));
//            stmt.setString(4, nv.getGioiTinh());
//            stmt.setString(5, nv.getEmail());
//            stmt.setString(6, nv.getSdt());
//            stmt.setString(7, nv.getChucVu());
//            stmt.setString(8, nv.getPhongLV());
//            stmt.executeUpdate();
//        }
//    }
public void addGiangVien(GiangVien gv, String matKhau, Connection conn) throws SQLException {
    try {
        conn.setAutoCommit(false);
        if (getTaiKhoan(gv.getMaGV())) {
            throw new SQLException("Mã giảng viên " + gv.getMaGV() + " đã tồn tại.");
        }
        if (gv.getMaGV() == null || gv.getHoTen() == null || gv.getNgaySinh() == null) {
            throw new IllegalArgumentException("Thông tin giảng viên không đầy đủ.");
        }

        // Thêm tài khoản
        TaiKhoan tk = new TaiKhoan();
        tk.setTenDangNhap(gv.getMaGV());
        tk.setMatKhau(matKhau);
        tk.setQuyen("2");
        addTaiKhoan(tk, conn);

        // Thêm giảng viên
        String query = "INSERT INTO GiangVien (maGV, hoTen, ngaySinh, gioiTinh, email, sdt, hocVi, khoa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, gv.getMaGV());
            stmt.setString(2, gv.getHoTen());
            stmt.setDate(3, new java.sql.Date(gv.getNgaySinh().getTime()));
            stmt.setString(4, gv.getGioiTinh());
            stmt.setString(5, gv.getEmail());
            stmt.setString(6, gv.getSdt());
            stmt.setString(7, gv.getHocVi());
            stmt.setString(8, gv.getKhoa());
            stmt.executeUpdate();
        }
        conn.commit();
    } catch (SQLException e) {
        System.err.println("Lỗi SQL trong addGiangVien: " + e.getMessage());
        conn.rollback();
        throw e;
    } finally {
        conn.setAutoCommit(true);
    }
}

public void addNhanVienPhongDaoTao(NhanVienPhongDaoTao nv, String matKhau, Connection conn) throws SQLException {
    try {
        conn.setAutoCommit(false);
        if (getTaiKhoan(nv.getMaNV())) {
            throw new SQLException("Mã nhân viên " + nv.getMaNV() + " đã tồn tại.");
        }
        if (nv.getMaNV() == null || nv.getHoTen() == null || nv.getNgaySinh() == null) {
            throw new IllegalArgumentException("Thông tin nhân viên không đầy đủ.");
        }

        // Thêm tài khoản
        TaiKhoan tk = new TaiKhoan();
        tk.setTenDangNhap(nv.getMaNV());
        tk.setMatKhau(matKhau);
        tk.setQuyen("3");
        addTaiKhoan(tk, conn);

        // Thêm nhân viên
        String query = "INSERT INTO NhanVienPhongDaoTao (maNV, hoTen, ngaySinh, gioiTinh, email, sdt, chucVu, phongLV) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nv.getMaNV());
            stmt.setString(2, nv.getHoTen());
            stmt.setDate(3, new java.sql.Date(nv.getNgaySinh().getTime()));
            stmt.setString(4, nv.getGioiTinh());
            stmt.setString(5, nv.getEmail());
            stmt.setString(6, nv.getSdt());
            stmt.setString(7, nv.getChucVu());
            stmt.setString(8, nv.getPhongLV());
            stmt.executeUpdate();
        }
        conn.commit();
    } catch (SQLException e) {
        System.err.println("Lỗi SQL trong addNhanVienPhongDaoTao: " + e.getMessage());
        conn.rollback();
        throw e;
    } finally {
        conn.setAutoCommit(true);
    }
}
    public TaiKhoan login(String TenDangNhap, String MatKhau) {
        String query = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ? AND MatKhau = ?";
        try {
            if (TenDangNhap == null || MatKhau == null || TenDangNhap.trim().isEmpty() || MatKhau.trim().isEmpty()) {
                return null;
            }
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1, TenDangNhap.trim());
            stmt.setString(2, MatKhau.trim());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new TaiKhoan(
                        rs.getString("TenDangNhap"),
                        rs.getString("MatKhau"),
                        rs.getString("Quyen")
                );
            }
        } catch (Exception e) {
        }
        return null;
    }

    public boolean getTaiKhoan(String tenDangNhap) {
        String query = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ?";
        try {
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1, tenDangNhap);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
        }
        return false;
    }

    public void updateTaiKhoan(TaiKhoan tk) {
        String query = "UPDATE TaiKhoan SET MatKhau = ?, Quyen = ? WHERE TenDangNhap = ?";
        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, tk.getMatKhau());
            stmt.setString(2, tk.getQuyen());
            stmt.setString(3, tk.getTenDangNhap());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật tài khoản: " + e.getMessage());
        }
    }

    public void updateSinhVien(SinhVien sv) {
        String query = "UPDATE SinhVien SET hoTen = ?, ngaySinh = ?, gioiTinh = ?, email = ?, sdt = ?, diaChi = ?, lopHC = ?, tinhTrang = ? WHERE maSV = ?";
        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, sv.getHoTen());
            stmt.setDate(2, new java.sql.Date(sv.getNgaySinh().getTime()));
            stmt.setString(3, sv.getGioiTinh());
            stmt.setString(4, sv.getEmail());
            stmt.setString(5, sv.getSdt());
            stmt.setString(6, sv.getDiaChi());
            stmt.setString(7, sv.getLopHC());
            stmt.setString(8, sv.getTinhTrang());
            stmt.setString(9, sv.getMaSV());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật sinh viên: " + e.getMessage());
        }
    }

    public void updateGiangVien(GiangVien gv) {
        String query = "UPDATE GiangVien SET hoTen = ?, ngaySinh = ?, gioiTinh = ?, "
                + "email = ?, sdt = ?, hocVi = ?, khoa = ? WHERE maGV = ?";
        try {
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1, gv.getHoTen());
            stmt.setDate(2, new java.sql.Date(gv.getNgaySinh().getTime()));
            stmt.setString(3, gv.getGioiTinh());
            stmt.setString(4, gv.getEmail());
            stmt.setString(5, gv.getSdt());
            stmt.setString(6, gv.getHocVi());
            stmt.setString(7, gv.getKhoa());
            stmt.setString(8, gv.getMaGV());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi updateGiangVien: " + e.getMessage());
        }
    }

    public void updateNhanVienPhongDaoTao(NhanVienPhongDaoTao nv) {
        String query = "UPDATE NhanVienPhongDaoTao SET hoTen = ?, ngaySinh = ?, gioiTinh = ?, email = ?, sdt = ?, chucVu = ?, phongLV = ? WHERE maNV = ?";
        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, nv.getHoTen());
            stmt.setDate(2, new java.sql.Date(nv.getNgaySinh().getTime()));
            stmt.setString(3, nv.getGioiTinh());
            stmt.setString(4, nv.getEmail());
            stmt.setString(5, nv.getSdt());
            stmt.setString(6, nv.getChucVu());
            stmt.setString(7, nv.getPhongLV());
            stmt.setString(8, nv.getMaNV());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật nhân viên: " + e.getMessage());
        }
    }

    public void updateNhanVien(NhanVienPhongDaoTao nv) {
        String query = "UPDATE NhanVienPhongDaoTao SET hoTen = ?, ngaySinh = ?, gioiTinh = ?, "
                + "email = ?, sdt = ?, chucVu = ?, phongLV = ? WHERE maNV = ?";
        try {
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1, nv.getHoTen());
            stmt.setDate(2, new java.sql.Date(nv.getNgaySinh().getTime()));
            stmt.setString(3, nv.getGioiTinh());
            stmt.setString(4, nv.getEmail());
            stmt.setString(5, nv.getSdt());
            stmt.setString(6, nv.getChucVu());
            stmt.setString(7, nv.getPhongLV());
            stmt.setString(8, nv.getMaNV());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi updateNhanVien: " + e.getMessage());
        }
    }

    public TaiKhoan getTaiKhoanById(String tenDangNhap) {
        String query = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ?";
        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, tenDangNhap);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new TaiKhoan(rs.getString("TenDangNhap"), rs.getString("MatKhau"), rs.getString("Quyen"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi getTaiKhoanById: " + e.getMessage());
        }
        return null;
    }

    public boolean deleteTaiKhoan(String tenDangNhap) {
        String query = "DELETE FROM TaiKhoan WHERE TenDangNhap = ?";
        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, tenDangNhap);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi deleteTaiKhoan: " + e.getMessage());
        }
        return false;
    }

    public SinhVien getSinhVienById(String maSV) {
        String query = "SELECT * FROM SinhVien WHERE maSV = ?";
        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, maSV);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SinhVien(
                        rs.getString("maSV"),
                        rs.getString("hoTen"),
                        rs.getDate("ngaySinh"),
                        rs.getString("gioiTinh"),
                        rs.getString("email"),
                        rs.getString("sdt"),
                        rs.getString("diaChi"),
                        rs.getString("lopHC"),
                        rs.getString("tinhTrang")
                );
            }
        } catch (SQLException e) {
            System.err.println("Lỗi getSinhVienById: " + e.getMessage());
        }
        return null;
    }

    public boolean deleteSinhVien(String maSV) {

        try {
            // hoặc sử dụng connection c của class
            c.setAutoCommit(false);

            // 1. Xóa các bản ghi liên quan trong các bảng khác 
            String deleteRelatedQuery = "DELETE FROM DangKyHocPhan WHERE maSV = ?";
            PreparedStatement deleteRelatedStmt = c.prepareStatement(deleteRelatedQuery);
            deleteRelatedStmt.setString(1, maSV);
            deleteRelatedStmt.executeUpdate();

            // 2. Xóa sinh viên
            String deleteSVQuery = "DELETE FROM SinhVien WHERE maSV = ?";
            PreparedStatement deleteSVStmt = c.prepareStatement(deleteSVQuery);
            deleteSVStmt.setString(1, maSV);
            deleteSVStmt.executeUpdate();

            // 3. Xóa tài khoản
            String deleteTKQuery = "DELETE FROM TaiKhoan WHERE TenDangNhap = ?";
            PreparedStatement deleteTKStmt = c.prepareStatement(deleteTKQuery);
            deleteTKStmt.setString(1, maSV);
            deleteTKStmt.executeUpdate();

            c.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Lỗi deleteSinhVien: " + e.getMessage());
            if (c != null) {
                try {
                    c.rollback();
                } catch (SQLException ex) {
                    System.err.println("Lỗi rollback: " + ex.getMessage());
                }
            }
            return false;
        } finally {
            if (c != null) {
                try {
                    c.setAutoCommit(true);
                    c.close();
                } catch (SQLException e) {
                    System.err.println("Lỗi đóng kết nối: " + e.getMessage());
                }
            }
        }
    }

    public GiangVien getGiangVienById(String maGV) {
        String query = "SELECT * FROM GiangVien WHERE maGV = ?";
        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, maGV);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new GiangVien(
                        rs.getString("maGV"),
                        rs.getString("hoTen"),
                        rs.getDate("ngaySinh"),
                        rs.getString("gioiTinh"),
                        rs.getString("email"),
                        rs.getString("sdt"),
                        rs.getString("hocVi"),
                        rs.getString("khoa")
                );
            }
        } catch (SQLException e) {
            System.err.println("Lỗi getGiangVienById: " + e.getMessage());
        }
        return null;
    }

    public boolean deleteGiangVien(String maGV) {
        String query = "DELETE FROM GiangVien WHERE maGV = ?";
        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, maGV);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi deleteGiangVien: " + e.getMessage());
        }
        return false;
    }

    public NhanVienPhongDaoTao getNhanVienById(String maNV) {
        String query = "SELECT * FROM NhanVienPhongDaoTao WHERE maNV = ?";
        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, maNV);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new NhanVienPhongDaoTao(
                        rs.getString("maNV"),
                        rs.getString("hoTen"),
                        rs.getDate("ngaySinh"),
                        rs.getString("gioiTinh"),
                        rs.getString("email"),
                        rs.getString("sdt"),
                        rs.getString("chucVu"),
                        rs.getString("phongLV")
                );
            }
        } catch (SQLException e) {
            System.err.println("Lỗi getNhanVienById: " + e.getMessage());
        }
        return null;
    }

    public boolean deleteNhanVien(String maNV) {
        String query = "DELETE FROM NhanVienPhongDaoTao WHERE maNV = ?";
        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, maNV);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi deleteNhanVien: " + e.getMessage());
        }
        return false;
    }

    public boolean updateTaiKhoanPassword(TaiKhoan tk) {
        String query = "UPDATE TaiKhoan SET MatKhau = ? WHERE TenDangNhap = ?";
        try {
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1, tk.getMatKhau());
            stmt.setString(2, tk.getTenDangNhap());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Lỗi trong updateTaiKhoanPassword: " + e.getMessage());
            return false;
        }
    }

    public SinhVien getSinhVienByEmail(String email) {
        String query = "SELECT * FROM SinhVien WHERE Email = ?";
        try {
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SinhVien(
                        rs.getString("MaSV"),
                        rs.getString("HoTen"),
                        rs.getDate("NgaySinh"),
                        rs.getString("GioiTinh"),
                        rs.getString("Email"),
                        rs.getString("Sdt"),
                        rs.getString("DiaChi"),
                        rs.getString("LopHC"),
                        rs.getString("TinhTrang")
                );
            }
        } catch (Exception e) {
            System.out.println("Lỗi trong getSinhVienByEmail: " + e.getMessage());
        }
        return null;
    }

    public GiangVien getGiangVienByEmail(String email) {
        String query = "SELECT * FROM GiangVien WHERE Email = ?";
        try {
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new GiangVien(
                        rs.getString("MaGV"),
                        rs.getString("HoTen"),
                        rs.getDate("NgaySinh"),
                        rs.getString("GioiTinh"),
                        rs.getString("Email"),
                        rs.getString("Sdt"),
                        rs.getString("HocVi"),
                        rs.getString("Khoa")
                );
            }
        } catch (Exception e) {
            System.out.println("Lỗi trong getGiangVienByEmail: " + e.getMessage());
        }
        return null;
    }

    public NhanVienPhongDaoTao getNhanVienByEmail(String email) {
        String query = "SELECT * FROM NhanVienPhongDaoTao WHERE Email = ?";
        try {
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new NhanVienPhongDaoTao(
                        rs.getString("MaNV"),
                        rs.getString("HoTen"),
                        rs.getDate("NgaySinh"),
                        rs.getString("GioiTinh"),
                        rs.getString("Email"),
                        rs.getString("Sdt"),
                        rs.getString("ChucVu"),
                        rs.getString("PhongLV")
                );
            }
        } catch (Exception e) {
            System.out.println("Lỗi trong getNhanVienByEmail: " + e.getMessage());
        }
        return null;
    }

    public SinhVien getByTenDangNhap(String tenDangNhap) {
        String query = "SELECT sv.* FROM SinhVien sv JOIN TaiKhoan tk ON sv.maSV = tk.tenDangNhap WHERE tk.tenDangNhap = ?";
        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, tenDangNhap);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SinhVien(
                        rs.getString("maSV"),
                        rs.getString("hoTen"),
                        rs.getDate("ngaySinh"),
                        rs.getString("gioiTinh"),
                        rs.getString("email"),
                        rs.getString("sdt"),
                        rs.getString("diaChi"),
                        rs.getString("lopHC"),
                        rs.getString("tinhTrang")
                );
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public GiangVien getGiangVienByTenDangNhap(String tenDangNhap) {
        String sql = "SELECT gv.* FROM GiangVien gv JOIN TaiKhoan tk ON gv.maGV = tk.tenDangNhap WHERE tk.tenDangNhap = ?";
        try {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, tenDangNhap);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                GiangVien gv = new GiangVien();
                gv.setMaGV(rs.getString("maGV"));
                gv.setHoTen(rs.getString("hoTen"));
                gv.setNgaySinh(rs.getDate("ngaySinh"));
                gv.setGioiTinh(rs.getString("gioiTinh"));
                gv.setEmail(rs.getString("email"));
                gv.setSdt(rs.getString("sdt"));

                gv.setHocVi(rs.getString("hocVi"));
                gv.setKhoa(rs.getString("khoa"));
                return gv; // Return the constructed object
            }
        } catch (SQLException e) {
            System.err.println("Lỗi getGiangVienByTenDangNhap: " + e.getMessage());
        }
        return null;
    }

    public NhanVienPhongDaoTao getNhanVienByTenDangNhap(String tenDangNhap) {
        String query = "SELECT nv.* FROM NhanVienPhongDaoTao nv JOIN TaiKhoan tk ON nv.maNV = tk.tenDangNhap WHERE tk.tenDangNhap = ?";
        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, tenDangNhap);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new NhanVienPhongDaoTao(
                        rs.getString("MaNV"),
                        rs.getString("HoTen"),
                        rs.getDate("NgaySinh"),
                        rs.getString("GioiTinh"),
                        rs.getString("Email"),
                        rs.getString("Sdt"),
                        rs.getString("ChucVu"),
                        rs.getString("PhongLV")
                );
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public TaiKhoan getTaiKhoanById(String id, String quyen) {
        String query = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ? AND Quyen = ?";
        try {
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1, id);
            stmt.setString(2, quyen);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new TaiKhoan(
                        rs.getString("TenDangNhap"),
                        rs.getString("MatKhau"),
                        rs.getString("Quyen")
                );
            }
        } catch (Exception e) {
        }
        return null;
    }
    
       public List<SinhVien> searchSinhVienByName(String hoTen) {
        List<SinhVien> res = new ArrayList<>();
        String query = "SELECT * FROM SinhVien WHERE hoTen LIKE ?";
        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, "%" + hoTen + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SinhVien sv = new SinhVien(
                        rs.getString("maSV"),
                        rs.getString("hoTen"),
                        rs.getDate("ngaySinh"),
                        rs.getString("gioiTinh"),
                        rs.getString("email"),
                        rs.getString("sdt"),
                        rs.getString("diaChi"),
                        rs.getString("lopHC"),
                        rs.getString("tinhTrang")
                );
                res.add(sv);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi searchSinhVienByName: " + e.getMessage());
        }
        return res;
    }

    public List<GiangVien> searchGiangVienByName(String hoTen) {
        List<GiangVien> res = new ArrayList<>();
        String query = "SELECT * FROM GiangVien WHERE hoTen LIKE ?";
        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, "%" + hoTen + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                GiangVien gv = new GiangVien(
                        rs.getString("maGV"),
                        rs.getString("hoTen"),
                        rs.getDate("ngaySinh"),
                        rs.getString("gioiTinh"),
                        rs.getString("email"),
                        rs.getString("sdt"),
                        rs.getString("hocVi"),
                        rs.getString("khoa")
                );
                res.add(gv);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi searchGiangVienByName: " + e.getMessage());
        }
        return res;
    }

    public List<NhanVienPhongDaoTao> searchNhanVienPhongDaoTaoByName(String hoTen) {
        List<NhanVienPhongDaoTao> res = new ArrayList<>();
        String query = "SELECT * FROM NhanVienPhongDaoTao WHERE hoTen LIKE ?";
        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, "%" + hoTen + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                NhanVienPhongDaoTao nv = new NhanVienPhongDaoTao(
                        rs.getString("maNV"),
                        rs.getString("hoTen"),
                        rs.getDate("ngaySinh"),
                        rs.getString("gioiTinh"),
                        rs.getString("email"),
                        rs.getString("sdt"),
                        rs.getString("chucVu"),
                        rs.getString("phongLV")
                );
                res.add(nv);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi searchNhanVienPhongDaoTaoByName: " + e.getMessage());
        }
        return res;
    }
}
