package controller;

import dal.LopHocPhanDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.LopHocPhan;
import model.TaiKhoan;

@WebServlet(name = "QuanLiLopHocPhanController", urlPatterns = {"/quanlilophocphan"})
public class QuanLiLopHocPhanController extends HttpServlet {

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

        try {
            LopHocPhanDao lhpDao = new LopHocPhanDao();
String action = request.getParameter("action");
            
            if ("edit".equals(action)) {
                String maLHP = request.getParameter("id");
                LopHocPhan lhp = lhpDao.getLopHocPhanById(maLHP);
                if (lhp != null) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    // Convert LopHocPhan to JSON
                    String json = String.format(
                        "{\"maLHP\":\"%s\",\"tenHP\":\"%s\",\"tenGV\":\"%s\",\"maMH\":\"%s\","
                        + "\"hocKy\":\"%s\",\"namHoc\":\"%s\",\"soLuongSVTD\":%d,"
                        + "\"thoiGianHoc\":\"%s\",\"phongHoc\":\"%s\",\"mocDK\":\"%s\"}",
                        lhp.getMaLHP(), lhp.getTenHP(), lhp.getTenGV(), lhp.getMaMH(),
                        lhp.getHocKy(), lhp.getNamHoc(), lhp.getSoLuongSVTD(),
                        lhp.getThoiGianHoc(), lhp.getPhongHoc(), 
                        new SimpleDateFormat("yyyy-MM-dd").format(lhp.getMocDK())
                    );
                    response.getWriter().write(json);
                    return;
                }
} else if ("delete".equals(action)) {
                String maLHP = request.getParameter("id");
                                        response.setContentType("application/json");
response.setCharacterEncoding("UTF-8");
                
                try {
                    LopHocPhanDao dao = new LopHocPhanDao();
                    boolean success = dao.deleteLopHocPhan(maLHP);
                    
String jsonResponse;
                    if (success) {
                        jsonResponse = "{\"success\": true}";
                    } else {
                                                jsonResponse = "{\"success\": false, \"message\": \"Không thể xóa lớp học phần\"}";
                    }
response.getWriter().write(jsonResponse);
                                    } catch (SQLException e) {
String errorJson = "{\"success\": false, \"message\": \"Lỗi khi xóa: " + 
                        e.getMessage().replace("\"", "'") + "\"}";
                                        response.getWriter().write(errorJson);
                                    }
return;
            } else {            
            String searchTerm = request.getParameter("search");
                        List<LopHocPhan> listLHP;
            if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                listLHP = lhpDao.searchLopHocPhan(searchTerm);
            } else {
                listLHP = lhpDao.getAllLopHocPhan();
            }
            
            request.setAttribute("listLHP", listLHP);
            request.getRequestDispatcher("QuanLiLopHocPhan.jsp").forward(request, response);
}            
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

        String action = request.getParameter("action");
        if ("update".equals(action)) {
            try {
                LopHocPhan lhp = new LopHocPhan();
                lhp.setMaLHP(request.getParameter("maLHP"));
                lhp.setTenHP(request.getParameter("tenHP"));
                lhp.setTenGV(request.getParameter("tenGV"));
                lhp.setMaMH(request.getParameter("maMH"));
                lhp.setHocKy(request.getParameter("hocKy"));
                lhp.setNamHoc(request.getParameter("namHoc"));
                lhp.setSoLuongSVTD(Integer.parseInt(request.getParameter("soLuongSVTD")));
                lhp.setThoiGianHoc(request.getParameter("thoiGianHoc"));
                lhp.setPhongHoc(request.getParameter("phongHoc"));
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date mocDK = sdf.parse(request.getParameter("mocDK"));
                lhp.setMocDK(mocDK);

                LopHocPhanDao dao = new LopHocPhanDao();
                if (dao.updateLopHocPhan(lhp)) {
                    response.sendRedirect("quanlilophocphan");
                } else {
                    request.setAttribute("error", "Cập nhật thất bại");
                    request.setAttribute("lhp", lhp);
                    request.setAttribute("editMode", true);
                    request.getRequestDispatcher("QuanLiLopHocPhan.jsp").forward(request, response);
                }
            } catch (SQLException | ParseException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
} else if ("add".equals(action)) {
            try {
                LopHocPhan lhp = new LopHocPhan();
                lhp.setMaLHP(request.getParameter("maLHP"));
                lhp.setTenHP(request.getParameter("tenHP"));
                lhp.setTenGV(request.getParameter("tenGV"));
                lhp.setMaMH(request.getParameter("maMH"));
                lhp.setHocKy(request.getParameter("hocKy"));
                lhp.setNamHoc(request.getParameter("namHoc"));
                lhp.setSoLuongSVTD(Integer.parseInt(request.getParameter("soLuongSVTD")));
                lhp.setThoiGianHoc(request.getParameter("thoiGianHoc"));
                lhp.setPhongHoc(request.getParameter("phongHoc"));
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date mocDK = sdf.parse(request.getParameter("mocDK"));
                lhp.setMocDK(mocDK);

                LopHocPhanDao dao = new LopHocPhanDao();
                if (dao.createLopHocPhan(lhp)) {
                    response.sendRedirect("quanlilophocphan");
                } else {
                    request.setAttribute("error", "Thêm mới thất bại");
                    request.getRequestDispatcher("QuanLiLopHocPhan.jsp").forward(request, response);
                }
            } catch (SQLException | ParseException e) {
                e.printStackTrace();
                request.setAttribute("error", "Lỗi: " + e.getMessage());
                request.getRequestDispatcher("QuanLiLopHocPhan.jsp").forward(request, response);
            }
        } else {
        doGet(request, response);
    }
}
}