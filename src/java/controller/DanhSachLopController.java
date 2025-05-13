package controller;

import dal.DanhSachLopDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GiangVien;
import model.LopHocPhan;
import model.SinhVien;
import java.io.IOException;
import java.util.List;

@WebServlet("/danhsachlop")
public class DanhSachLopController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        GiangVien gv = (GiangVien) session.getAttribute("giangVien");
        
        if (gv == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String maLHP = request.getParameter("maLHP");
        DanhSachLopDao dao = new DanhSachLopDao();
        
        if (maLHP != null && !maLHP.isEmpty()) {
            // Hiển thị danh sách sinh viên của một lớp cụ thể
            List<SinhVien> danhSachSV = dao.getSinhVienByLopHocPhan(maLHP);
            request.setAttribute("danhSachSV", danhSachSV);
            request.setAttribute("maLHP", maLHP);
        }
        
        // Lấy danh sách tất cả các lớp của giảng viên
        List<LopHocPhan> danhSachLop = dao.getLopHocPhanByGiangVien(gv.getHoTen());
        request.setAttribute("danhSachLop", danhSachLop);
        for (LopHocPhan lopHocPhan : danhSachLop) {
            System.out.println(lopHocPhan + "?");
        }
        
        request.getRequestDispatcher("DanhSachLop.jsp").forward(request, response);
    }
    
    
}