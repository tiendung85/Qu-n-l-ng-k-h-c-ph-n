package controller;

import dal.BaoCaoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import model.BaoCaoThongKe;
import model.TaiKhoan;

@WebServlet(name = "BaoCaoController", urlPatterns = { "/baocao" })
public class BaoCaoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("SessionLogin");

        if (taiKhoan == null || !"3".equals(taiKhoan.getQuyen())) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if ("view".equals(action)) {
            try {
                String maBC = request.getParameter("id");
                BaoCaoDao dao = new BaoCaoDao();
                BaoCaoThongKe baoCao = dao.getBaoCaoById(maBC);

                if (baoCao != null) {
                    request.setAttribute("baoCao", baoCao);
                    request.getRequestDispatcher("ViewBaoCao.jsp").forward(request, response);
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
                return;
            }
        }

        try {
            BaoCaoDao baoCaoDao = new BaoCaoDao();
            List<BaoCaoThongKe> listBaoCao = baoCaoDao.getAllBaoCao();
            request.setAttribute("listBaoCao", listBaoCao);
            request.getRequestDispatcher("BaoCao.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("SessionLogin");

        if (taiKhoan == null || !"3".equals(taiKhoan.getQuyen())) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if ("add".equals(action)) {
            try {
                BaoCaoThongKe baoCao = new BaoCaoThongKe();
                baoCao.setMaBC(request.getParameter("maBC"));
                baoCao.setTenBC(request.getParameter("tenBC"));
                baoCao.setLoaiTK(request.getParameter("loaiTK"));
                baoCao.setNgayTao(new Date());
                baoCao.setMaNguoiTao(taiKhoan.getTenDangNhap());
                baoCao.setNoiDung(request.getParameter("noiDung"));

                BaoCaoDao dao = new BaoCaoDao();
                if (dao.createBaoCao(baoCao)) {
                    response.sendRedirect("baocao");
                } else {
                    request.setAttribute("error", "Thêm báo cáo thất bại");
                    request.getRequestDispatcher("BaoCao.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Lỗi: " + e.getMessage());
                request.getRequestDispatcher("BaoCao.jsp").forward(request, response);
            }
        }
    }
}