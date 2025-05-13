package controller;

import dal.TaiKhoanDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.TaiKhoan;
import model.SinhVien;
import model.GiangVien;
import model.NhanVienPhongDaoTao;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            String TenDangNhap = request.getParameter("TenDangNhap");
            String MatKhau = request.getParameter("MatKhau");

            // Kiểm tra dữ liệu nhập
            if (TenDangNhap == null || MatKhau == null || TenDangNhap.trim().isEmpty() || MatKhau.trim().isEmpty()) {
                request.setAttribute("msg", "Vui lòng nhập tên đăng nhập và mật khẩu.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            // Gọi DAO để xử lý đăng nhập
            TaiKhoanDao dao = new TaiKhoanDao();
            TaiKhoan taiKhoan = dao.login(TenDangNhap.trim(), MatKhau.trim());

            if (taiKhoan != null) {
                session.setAttribute("SessionLogin", taiKhoan);
                session.setAttribute("dataTaiKhoan", taiKhoan);

                // Phân loại quyền
                switch (taiKhoan.getQuyen()) {
                    case "1": // Sinh viên
                        SinhVien sv = dao.getByTenDangNhap(taiKhoan.getTenDangNhap());
                        if (sv != null) {
                            session.setAttribute("sinhVien", sv);
                        }
                        break;
                 case "2": // Giảng viên
    GiangVien gv = dao.getGiangVienByTenDangNhap(taiKhoan.getTenDangNhap());
    if (gv != null) {
        session.setAttribute("giangVien", gv);
    } else {
        System.out.println("GiangVien not found for: " + taiKhoan.getTenDangNhap());
    }
    break;
                    case "3": // Nhân viên
                        NhanVienPhongDaoTao nv = dao.getNhanVienByTenDangNhap(taiKhoan.getTenDangNhap());
                        if (nv != null) {
                            session.setAttribute("nhanVien", nv);
                        }
                        break;
                }

                response.sendRedirect("home.jsp");
            } else {
                request.setAttribute("msg", "Sai tên đăng nhập hoặc mật khẩu.");
                request.setAttribute("TenDangNhap", TenDangNhap);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "Đã xảy ra lỗi khi đăng nhập.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Login Controller for TaiKhoan system";
    }
}