package controller;

import dal.TaiKhoanDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TaiKhoan;
import model.SinhVien;
import model.GiangVien;
import model.NhanVienPhongDaoTao;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ForgotPasswordController", urlPatterns = {"/forgot-password"})
public class ForgotPasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("verify".equals(action)) {
            String email = request.getParameter("email");
            TaiKhoanDao dao = new TaiKhoanDao();
            TaiKhoan taiKhoan = null;
            String hoTen = null;
            String quyen = null;

            // Check email in SinhVien, GiangVien, NhanVienPhongDaoTao
            SinhVien sv = dao.getSinhVienByEmail(email);
            if (sv != null) {
                taiKhoan = dao.getTaiKhoanById(sv.getMaSV(), "1");
                hoTen = sv.getHoTen();
                quyen = "1";
            } else {
                GiangVien gv = dao.getGiangVienByEmail(email);
                if (gv != null) {
                    taiKhoan = dao.getTaiKhoanById(gv.getMaGV(), "2");
                    hoTen = gv.getHoTen();
                    quyen = "2";
                } else {
                    NhanVienPhongDaoTao nv = dao.getNhanVienByEmail(email);
                    if (nv != null) {
                        taiKhoan = dao.getTaiKhoanById(nv.getMaNV(), "3");
                        hoTen = nv.getHoTen();
                        quyen = "3";
                    }
                }
            }

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            if (taiKhoan != null && hoTen != null) {
                // Manually construct JSON, escape quotes in hoTen
                hoTen = hoTen.replace("\"", "\\\"");
                out.print("{\"found\":true,\"TenDangNhap\":\"" + taiKhoan.getTenDangNhap() + "\",\"HoTen\":\"" + hoTen + "\"}");
            } else {
                out.print("{\"found\":false}");
            }
            out.flush();
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        TaiKhoanDao dao = new TaiKhoanDao();
        TaiKhoan taiKhoan = null;
        String quyen = null;

        // Check email in SinhVien, GiangVien, NhanVienPhongDaoTao
        SinhVien sv = dao.getSinhVienByEmail(email);
        if (sv != null) {
            taiKhoan = dao.getTaiKhoanById(sv.getMaSV(), "1");
            quyen = "1";
        } else {
            GiangVien gv = dao.getGiangVienByEmail(email);
            if (gv != null) {
                taiKhoan = dao.getTaiKhoanById(gv.getMaGV(), "2");
                quyen = "2";
            } else {
                NhanVienPhongDaoTao nv = dao.getNhanVienByEmail(email);
                if (nv != null) {
                    taiKhoan = dao.getTaiKhoanById(nv.getMaNV(), "3");
                    quyen = "3";
                }
            }
        }

        if (taiKhoan != null) {
            // Store the email in session for verification in reset password page
            request.getSession().setAttribute("reset_email", email);
            response.sendRedirect("reset-password.jsp");
        } else {
            request.setAttribute("error", "Email không tồn tại trong hệ thống.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Forgot Password Controller for TaiKhoan system";
    }
}