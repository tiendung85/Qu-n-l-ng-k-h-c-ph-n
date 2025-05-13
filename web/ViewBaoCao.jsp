<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.TaiKhoan"%>
<%@page import="model.BaoCaoThongKe"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chi tiết báo cáo</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <style>
            body {
                background-color: #f5f5f5;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }
            .container {
                margin-top: 20px;
                background-color: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            .report-header {
                background-color: #f8f9fa;
                padding: 15px;
                border-radius: 5px;
                margin-bottom: 20px;
            }
            .report-content {
                background-color: #fff;
                padding: 20px;
                border: 1px solid #dee2e6;
                border-radius: 5px;
                min-height: 200px;
            }
        </style>
    </head>
    <body>
        <%
            TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("SessionLogin");
            if (taiKhoan == null || !"3".equals(taiKhoan.getQuyen())) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        
        <div class="container">
            <h2 class="text-center mb-4">Chi tiết báo cáo</h2>
            
            <div class="report-header">
                <div class="row">
                    <div class="col-md-6">
                        <p><strong>Mã báo cáo:</strong> ${baoCao.maBC}</p>
                        <p><strong>Tên báo cáo:</strong> ${baoCao.tenBC}</p>
                        <p><strong>Loại thống kê:</strong> ${baoCao.loaiTK}</p>
                    </div>
                    <div class="col-md-6">
                        <p><strong>Ngày tạo:</strong> <fmt:formatDate value="${baoCao.ngayTao}" pattern="dd/MM/yyyy"/></p>
                        <p><strong>Người tạo:</strong> ${baoCao.maNguoiTao}</p>
                    </div>
                </div>
            </div>
            
            <div class="report-content">
                <h5 class="mb-3">Nội dung báo cáo:</h5>
                <p style="white-space: pre-wrap;">${baoCao.noiDung}</p>
            </div>
            
            <div class="mt-4">
                <a href="baocao" class="btn btn-secondary">
                    <i class="fas fa-arrow-left"></i> Quay lại danh sách
                </a>
                <button onclick="window.print()" class="btn btn-primary">
                    <i class="fas fa-print"></i> In báo cáo
                </button>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>