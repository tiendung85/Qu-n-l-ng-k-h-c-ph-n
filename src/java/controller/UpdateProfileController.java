package controller;

import dal.TaiKhoanDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.SinhVien;
import model.GiangVien;
import model.NhanVienPhongDaoTao;
import model.TaiKhoan;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "UpdateProfileController", urlPatterns = {"/updateProfile"})
public class UpdateProfileController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Starting profile update...");

        HttpSession session = request.getSession();
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("SessionLogin");
        System.out.println("User role: " + (taiKhoan != null ? taiKhoan.getQuyen() : "null"));

        TaiKhoanDao dao = new TaiKhoanDao();

        try {
            String hoTen = request.getParameter("hoTen");
            String email = request.getParameter("email");
            String ngaySinhStr = request.getParameter("ngaySinh");
            String gioiTinh = request.getParameter("gioiTinh");
            String sdt = request.getParameter("sdt");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date ngaySinh = sdf.parse(ngaySinhStr);

            if (taiKhoan != null) {
                String quyen = taiKhoan.getQuyen();
                if ("1".equals(quyen)) {
                    SinhVien sv = (SinhVien) session.getAttribute("sinhVien");
                    if (sv != null) {
                        sv.setHoTen(hoTen);
                        sv.setEmail(email);
                        sv.setNgaySinh(ngaySinh);
                        sv.setGioiTinh(gioiTinh);
                        sv.setSdt(sdt);
                        sv.setDiaChi(request.getParameter("diaChi"));
                        sv.setLopHC(request.getParameter("lopHC"));
                        sv.setTinhTrang(request.getParameter("tinhTrang"));
                        dao.updateSinhVien(sv);
                        session.setAttribute("sinhVien", sv);
                    }
                } else if ("2".equals(quyen)) {
                   
                    GiangVien gv = (GiangVien) session.getAttribute("giangVien");
                    

                    if (gv != null) {
                        gv.setHoTen(hoTen);
                        gv.setEmail(email);
                        gv.setNgaySinh(ngaySinh);
                        gv.setGioiTinh(gioiTinh);
                        gv.setSdt(sdt);
                 
                        gv.setHocVi(request.getParameter("hocVi"));
                        gv.setKhoa(request.getParameter("khoa"));
                        dao.updateGiangVien(gv);
                        session.setAttribute("giangVien", gv);
                    }
                } else if ("3".equals(quyen)) {
                    NhanVienPhongDaoTao nv = (NhanVienPhongDaoTao) session.getAttribute("nhanVien");
                    if (nv != null) {
                        nv.setHoTen(hoTen);
                        nv.setEmail(email);
                        nv.setNgaySinh(ngaySinh);
                        nv.setGioiTinh(gioiTinh);
                        nv.setSdt(sdt);
                        nv.setChucVu(request.getParameter("chucVu"));
                        nv.setPhongLV(request.getParameter("phongLV"));
                        dao.updateNhanVienPhongDaoTao(nv);
                        session.setAttribute("nhanVien", nv);
                    }
                }
            }
            response.sendRedirect("home.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi cập nhật thông tin: " + e.getMessage());
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Update Profile Controller for TaiKhoan system";
    }
}
