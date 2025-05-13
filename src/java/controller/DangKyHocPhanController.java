package controller;

import dal.DangKyHocPhanDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DangKyHocPhanViewModel;


import java.io.IOException;
import java.util.List;

@WebServlet("/dangkyhocphan")
public class DangKyHocPhanController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String maSV = request.getParameter("maSV");
        String maLHP = request.getParameter("maLHP");

        if (maSV == null || maSV.isEmpty()) {
            response.getWriter().write("Vui lòng cung cấp mã sinh viên (maSV).");
            return;
        }

        DangKyHocPhanDao dao = new DangKyHocPhanDao();
        List<DangKyHocPhanViewModel> dsDangKy;
        
        if (maLHP != null && !maLHP.isEmpty()) {
            dsDangKy = dao.searchByMaLHP(maLHP, maSV);
        } else {
            dsDangKy = dao.getDangKyHocPhanByMaSV(maSV);
        }

        request.setAttribute("dsDangKy", dsDangKy);
        request.setAttribute("maSV", maSV);
        request.setAttribute("maLHP", maLHP);
        request.getRequestDispatcher("DangKyHocPhan.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String maDK = request.getParameter("maDK");
        String maSV = request.getParameter("maSV");
        String maLHP = request.getParameter("maLHP");
        
        DangKyHocPhanDao dao = new DangKyHocPhanDao();
        String message = "";

        if ("dangky".equals(action)) {
                        if (!dao.isThoiGianDangKyHopLe(maLHP)) {
                message = "Đã hết thời gian đăng ký!";
} else if (!dao.kiemTraSoLuongSVToiDa(maLHP)) {
                message = "Lớp học phần đã đủ số lượng sinh viên tối đa (30 SV)!";
            } else if (dao.kiemTraTrungLich(maSV, maLHP)) {
                message = "Trùng lịch học với học phần khác!";
            } else if (dao.getTongSoTinChi(maSV, maLHP) > 6) { 
                message = "Vượt quá số tín chỉ cho phép (tối đa 6 TC)!";
            } else if (dao.dangKyHocPhan(maDK)) {
dao.tangSoLuongSV(maLHP); 
                message = "Đăng ký thành công!";
            } else {
                message = "Đăng ký thất bại!";
            }
        } else if ("huy".equals(action)) {
            if (!dao.isThoiGianDangKyHopLe(maLHP)) {
                message = "Đã hết thời gian hủy đăng ký!";
            } else if (dao.huyDangKy(maDK)) {
dao.giamSoLuongSV(maLHP); 
                message = "Hủy đăng ký thành công!";
            } else {
                message = "Hủy đăng ký thất bại!";
            }
        }

        response.sendRedirect("dangkyhocphan?maSV=" + maSV + "&message=" + 
            java.net.URLEncoder.encode(message, "UTF-8"));
    }    
}
