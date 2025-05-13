
package controller;

import dal.TaiKhoanDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import model.GiangVien;
import model.NhanVienPhongDaoTao;
import model.SinhVien;
import model.TaiKhoan;

@WebServlet(name = "QuanLiNguoiDungController", urlPatterns = {"/quanlinguoidung"})
public class QuanLiNguoiDungController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TaiKhoan taiKhoan = (TaiKhoan) request.getSession().getAttribute("SessionLogin");
        if (taiKhoan == null || !taiKhoan.getQuyen().equals("3")) {
            response.sendRedirect("login.jsp");
            return;
        }

        TaiKhoanDao dao = new TaiKhoanDao();
        String searchName = request.getParameter("searchName");

        List<SinhVien> sinhViens;
        List<GiangVien> giangViens;
        List<NhanVienPhongDaoTao> nhanViens;

        if (searchName != null && !searchName.trim().isEmpty()) {
            // Tìm kiếm theo tên
            sinhViens = dao.searchSinhVienByName(searchName);
            giangViens = dao.searchGiangVienByName(searchName);
            nhanViens = dao.searchNhanVienPhongDaoTaoByName(searchName);
            request.setAttribute("searchName", searchName);
        } else {
            // Lấy toàn bộ danh sách nếu không có tìm kiếm
            sinhViens = dao.readSinhVien();
            giangViens = dao.readGiangVien();
            nhanViens = dao.readNhanVienPhongDaoTao();
        }

        request.setAttribute("sinhViens", sinhViens);
        request.setAttribute("giangViens", giangViens);
        request.setAttribute("nhanViens", nhanViens);

        request.getRequestDispatcher("QuanLiNguoiDung.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TaiKhoan taiKhoan = (TaiKhoan) request.getSession().getAttribute("SessionLogin");
        if (taiKhoan == null || !taiKhoan.getQuyen().equals("3")) {
            response.sendRedirect("login.jsp");
            return;
        }

        TaiKhoanDao dao = new TaiKhoanDao();
        String action = request.getParameter("action");
        if ("edit".equals(action)) {
            String userType = request.getParameter("userType");
            String id = request.getParameter("id");
            String hoTen = request.getParameter("hoTen");
            String ngaySinhStr = request.getParameter("ngaySinh");
            String gioiTinh = request.getParameter("gioiTinh");
            String email = request.getParameter("email");
            String sdt = request.getParameter("sdt");

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date ngaySinh = sdf.parse(ngaySinhStr);

                if ("sinhvien".equals(userType)) {
                    SinhVien sv = dao.getSinhVienById(id);
                    if (sv != null) {
                        sv.setHoTen(hoTen);
                        sv.setNgaySinh(ngaySinh);
                        sv.setGioiTinh(gioiTinh);
                        sv.setEmail(email);
                        sv.setSdt(sdt);
                        dao.updateSinhVien(sv);
                    }
                } else if ("giangvien".equals(userType)) {
                    GiangVien gv = dao.getGiangVienById(id);
                    if (gv != null) {
                        gv.setHoTen(hoTen);
                        gv.setNgaySinh(ngaySinh);
                        gv.setGioiTinh(gioiTinh);
                        gv.setEmail(email);
                        gv.setSdt(sdt);
                        dao.updateGiangVien(gv);
                    }
                } else if ("nhanvien".equals(userType)) {
                    NhanVienPhongDaoTao nv = dao.getNhanVienById(id);
                    if (nv != null) {
                        nv.setHoTen(hoTen);
                        nv.setNgaySinh(ngaySinh);
                        nv.setGioiTinh(gioiTinh);
                        nv.setEmail(email);
                        nv.setSdt(sdt);
                        dao.updateNhanVien(nv);
                    }
                }
                response.sendRedirect("quanlinguoidung");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Lỗi khi sửa người dùng: " + e.getMessage());
                doGet(request, response);
            }
        } else if ("delete".equals(action)) {
            String userType = request.getParameter("userType");
            String id = request.getParameter("id");

            try {
                boolean success = false;
                if ("sinhvien".equals(userType)) {
                    success = dao.deleteSinhVien(id);
                } else if ("giangvien".equals(userType)) {
                    success = dao.deleteGiangVien(id);
                } else if ("nhanvien".equals(userType)) {
                    success = dao.deleteNhanVien(id);
                }

                if (success) {
                    response.sendRedirect("quanlinguoidung");
                } else {
                    request.setAttribute("errorMessage", "Không thể xóa người dùng. Vui lòng thử lại.");
                    doGet(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Lỗi hệ thống: " + e.getMessage());
                doGet(request, response);
            }
        } else if ("add".equals(action)) {
            String userType = request.getParameter("userType");
            String id = request.getParameter("id");
            String hoTen = request.getParameter("hoTen");
            String ngaySinhStr = request.getParameter("ngaySinh");
            String gioiTinh = request.getParameter("gioiTinh");
            String email = request.getParameter("email");
            String sdt = request.getParameter("sdt");
            String matKhau = request.getParameter("matKhau");

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date ngaySinh = sdf.parse(ngaySinhStr);

                if ("sinhvien".equals(userType)) {
                    String diaChi = request.getParameter("diaChi");
                    String lopHC = request.getParameter("lopHC");
                    String tinhTrang = request.getParameter("tinhTrang");

                    SinhVien sv = new SinhVien(id, hoTen, ngaySinh, gioiTinh, email, sdt, diaChi, lopHC, tinhTrang);
                    dao.addSinhVien(sv, matKhau, dao.c);
                } else if ("giangvien".equals(userType)) {
                    String hocVi = request.getParameter("hocVi");
                    String khoa = request.getParameter("khoa");

                    GiangVien gv = new GiangVien(id, hoTen, ngaySinh, gioiTinh, email, sdt, hocVi, khoa);
                    dao.addGiangVien(gv, matKhau, dao.c);
                } else if ("nhanvien".equals(userType)) {
                    String chucVu = request.getParameter("chucVu");
                    String phongLV = request.getParameter("phongLV");

                    NhanVienPhongDaoTao nv = new NhanVienPhongDaoTao(id, hoTen, ngaySinh, gioiTinh, email, sdt, chucVu, phongLV);
                    dao.addNhanVienPhongDaoTao(nv, matKhau, dao.c);
                }
                response.sendRedirect("quanlinguoidung");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Lỗi khi thêm người dùng: " + e.getMessage());
                doGet(request, response);
            }
        } else {
            response.sendRedirect("quanlinguoidung");
        }
    }
}
