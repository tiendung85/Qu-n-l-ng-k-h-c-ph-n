/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://.netbeans/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.DBConnect;
import dal.TaiKhoanDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.GiangVien;
import model.NhanVienPhongDaoTao;
import model.SinhVien;

@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    private TaiKhoanDao taiKhoanDao;

    @Override
    public void init() throws ServletException {
        taiKhoanDao = new TaiKhoanDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String quyen = request.getParameter("Quyen");
        String matKhau = request.getParameter("MatKhau");
        String confirmMatKhau = request.getParameter("ConfirmMatKhau");

        // Validate password match
        if (!matKhau.equals(confirmMatKhau)) {
            request.setAttribute("error", "Mật khẩu xác nhận không khớp!");
            forwardToJsp(quyen, request, response);
            return;
        }

        DBConnect dbConnect = null;
        try {
            dbConnect = new DBConnect();
            Connection conn = dbConnect.c; // Get connection from DBConnect
            conn.setAutoCommit(false);

            switch (quyen) {
                case "1": // SinhVien
                    SinhVien sv = createSinhVien(request);
                    taiKhoanDao.addSinhVien(sv, matKhau, conn);
                    break;
                case "2": // GiangVien
                    GiangVien gv = createGiangVien(request);
                    taiKhoanDao.addGiangVien(gv, matKhau, conn);
                    break;
                case "3": // NhanVienPhongDaoTao
                    NhanVienPhongDaoTao nv = createNhanVienPhongDaoTao(request);
                    taiKhoanDao.addNhanVienPhongDaoTao(nv, matKhau, conn);
                    break;
                default:
                    throw new ServletException("Quyền không hợp lệ!");
            }

            conn.commit();
            request.setAttribute("msg", "Đăng ký thành công! Vui lòng đăng nhập.");
            forwardToJsp(quyen, request, response);

        } catch (SQLException e) {
            if (dbConnect != null && dbConnect.c != null) {
                try {
                    dbConnect.c.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            request.setAttribute("error", e.getMessage());
            forwardToJsp(quyen, request, response);
        } catch (ParseException e) {
            request.setAttribute("error", "Định dạng ngày sinh không hợp lệ!");
            forwardToJsp(quyen, request, response);
        } finally {
            if (dbConnect != null) {
                dbConnect.closeConnection();
            }
        }
    }

    private SinhVien createSinhVien(HttpServletRequest request) throws ParseException {
        String maSV = request.getParameter("MaSV");
        String hoTen = request.getParameter("HoTen");
        String ngaySinhStr = request.getParameter("NgaySinh");
        String gioiTinh = request.getParameter("GioiTinh");
        String email = request.getParameter("Email");
        String sdt = request.getParameter("SDT");
        String diaChi = request.getParameter("DiaChi");
        String lopHC = request.getParameter("LopHC");
        String tinhTrang = request.getParameter("TinhTrang");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date ngaySinh = sdf.parse(ngaySinhStr);

        return new SinhVien(maSV, hoTen, ngaySinh, gioiTinh, email, sdt, diaChi, lopHC, tinhTrang);
    }

    private GiangVien createGiangVien(HttpServletRequest request) throws ParseException {
        String maGV = request.getParameter("MaGV");
        String hoTen = request.getParameter("HoTen");
        String ngaySinhStr = request.getParameter("NgaySinh");
        String gioiTinh = request.getParameter("GioiTinh");
        String email = request.getParameter("Email");
        String sdt = request.getParameter("SDT");
    
        String hocVi = request.getParameter("HocVi");
        String khoa = request.getParameter("Khoa");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date ngaySinh = sdf.parse(ngaySinhStr);

        return new GiangVien(maGV, hoTen, ngaySinh, gioiTinh, email, sdt, hocVi, khoa);
    }

    private NhanVienPhongDaoTao createNhanVienPhongDaoTao(HttpServletRequest request) throws ParseException {
        String maNV = request.getParameter("MaNV");
        String hoTen = request.getParameter("HoTen");
        String ngaySinhStr = request.getParameter("NgaySinh");
        String gioiTinh = request.getParameter("GioiTinh");
        String email = request.getParameter("Email");
        String sdt = request.getParameter("SDT");
        String chucVu = request.getParameter("ChucVu");
        String phongLV = request.getParameter("PhongLV");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date ngaySinh = sdf.parse(ngaySinhStr);

        return new NhanVienPhongDaoTao(maNV, hoTen, ngaySinh, gioiTinh, email, sdt, chucVu, phongLV);
    }

    private void forwardToJsp(String quyen, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jspPage;
        switch (quyen) {
            case "1":
                jspPage = "registerSinhVien.jsp";
                break;
            case "2":
                jspPage = "registerGiangVien.jsp";
                break;
            case "3":
                jspPage = "registerNhanVien.jsp";
                break;
            default:
                jspPage = "error.jsp";
        }
        request.getRequestDispatcher(jspPage).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("registerSinhVien.jsp");
    }
}