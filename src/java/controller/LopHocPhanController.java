package controller;

import dal.LopHocPhanDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LopHocPhan;
import model.TaiKhoan;
import model.GiangVien;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "LopHocPhanController", urlPatterns = {"/lophocphan"})
public class LopHocPhanController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String maLHP = request.getParameter("maLHP");
        LopHocPhanDao dao = new LopHocPhanDao();

        // Get logged in user and role
        TaiKhoan account = (TaiKhoan) request.getSession().getAttribute("account");
        
        try {
            if (account != null && "2".equals(account.getQuyen())) { // Giảng viên role
                // Get lecturer name from session
                GiangVien giangVien = (GiangVien) request.getSession().getAttribute("giangvien");
                // Show only this lecturer's classes
                List<LopHocPhan> listLopHocPhan = dao.getLopHocPhanByGiangVien(giangVien.getHoTen());
                request.setAttribute("listLopHocPhan", listLopHocPhan);
            } else { // Admin or other roles
                if (maLHP != null && !maLHP.trim().isEmpty()) {
                    LopHocPhan lhp = dao.getLopHocPhanById(maLHP.trim());
                    if (lhp != null) {
                        List<LopHocPhan> searchResults = List.of(lhp);
                        request.setAttribute("listLopHocPhan", searchResults);
                    } else {
                        request.setAttribute("message", "Không tìm thấy lớp học phần với mã: " + maLHP);
                    }
                } else {
                    List<LopHocPhan> listLopHocPhan = dao.getAllLopHocPhan();
                    request.setAttribute("listLopHocPhan", listLopHocPhan);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Có lỗi xảy ra: " + e.getMessage());
        }
        
        request.getRequestDispatcher("LopHocPhan.jsp").forward(request, response);
    }
}