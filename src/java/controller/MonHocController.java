package controller;

import dal.MonHocDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MonHoc;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "MonHocController", urlPatterns = {"/monhoc"})
public class MonHocController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            MonHocDao dao = new MonHocDao();
            String maMH = request.getParameter("maMH");
            
            if (maMH != null && !maMH.trim().isEmpty()) {
                // Tìm kiếm theo mã môn học
                MonHoc monHoc = dao.getSubjectById(maMH);
                if (monHoc != null) {
                    List<MonHoc> list = new ArrayList<>();
                    list.add(monHoc);
                    request.setAttribute("listMonHoc", list);
                } else {
                    request.setAttribute("message", "Không tìm thấy môn học với mã: " + maMH);
                }
            } else {
                // Lấy tất cả môn học
                List<MonHoc> list = dao.getAllSubjects();
                request.setAttribute("listMonHoc", list);
            }
            
            request.getRequestDispatcher("MonHoc.jsp").forward(request, response);
            
        } catch (SQLException e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}