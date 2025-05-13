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

@WebServlet(name = "ResetPasswordController", urlPatterns = {"/reset-password"})
public class ResetPasswordController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = (String) request.getSession().getAttribute("reset_email");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validate inputs
        if (email == null || newPassword == null || confirmPassword == null) {
            request.setAttribute("error", "Dữ liệu không hợp lệ.");
            request.getRequestDispatcher("reset-password.jsp").forward(request, response);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu xác nhận không khớp.");
            request.getRequestDispatcher("reset-password.jsp").forward(request, response);
            return;
        }

        // Find TaiKhoan by email
        TaiKhoanDao dao = new TaiKhoanDao();
        TaiKhoan taiKhoan = null;
        SinhVien sv = dao.getSinhVienByEmail(email);
        if (sv != null) {
            taiKhoan = dao.getTaiKhoanById(sv.getMaSV(), "1");
        } else {
            GiangVien gv = dao.getGiangVienByEmail(email);
            if (gv != null) {
                taiKhoan = dao.getTaiKhoanById(gv.getMaGV(), "2");
            } else {
                NhanVienPhongDaoTao nv = dao.getNhanVienByEmail(email);
                if (nv != null) {
                    taiKhoan = dao.getTaiKhoanById(nv.getMaNV(), "3");
                }
            }
        }

        if (taiKhoan != null) {
            // Update password
            taiKhoan.setMatKhau(newPassword);
            boolean updated = dao.updateTaiKhoanPassword(taiKhoan);
            if (updated) {
                // Clear session attribute and redirect to login
                request.getSession().removeAttribute("reset_email");
                request.setAttribute("msg", "Mật khẩu đã được đặt lại thành công. Vui lòng đăng nhập.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Lỗi khi đặt lại mật khẩu. Vui lòng thử lại.");
                request.getRequestDispatcher("reset-password.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Tài khoản không tồn tại.");
            request.getRequestDispatcher("reset-password.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Reset Password Controller for TaiKhoan system";
    }
}