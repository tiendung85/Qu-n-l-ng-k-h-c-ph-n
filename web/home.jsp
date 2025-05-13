<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.TaiKhoan"%>
<%@page import="model.SinhVien"%>
<%@page import="model.GiangVien"%>
<%@page import="model.NhanVienPhongDaoTao"%>
<%@page import="dal.TaiKhoanDao"%>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Home-N5</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Animate.css -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <style>
            body {
                background-color: #003087;
                color: #FFA500;
                font-family: "Segoe UI", sans-serif;
                padding-top: 120px; /* Increased padding for header */
                min-height: 100vh;
                display: flex;
                flex-direction: column;
            }

            .header-banner {
                background: linear-gradient(45deg, #002470, #003087);
                position: fixed;
                top: 0;
                width: 100%;
                z-index: 1000;
                padding: 1rem;
                box-shadow: 0 4px 15px rgba(0,0,0,0.2);
            }

            .user-info {
                background: rgba(255, 255, 255, 0.95);
                border-radius: 15px;
                padding: 2rem;
                margin: auto; /* Changed from 2rem auto to auto */
                margin-top: 15vh; /* Add space from top, 15% of viewport height */
                max-width: 800px;
                text-align: center;
                display: flex;
                align-items: center;
                justify-content: center;
                gap: 2rem;
                box-shadow: 0 0 20px rgba(0,0,0,0.1);
                animation: slideUp 0.5s ease;
            }

            @keyframes slideUp {
                from {
                    opacity: 0;
                    transform: translateY(100px); /* Increased distance */
                }
                to {
                    opacity: 1;
                    transform: translateY(0);
                }
            }
            .user-avatar {
                width: 80px;
                height: 80px;
                border-radius: 50%;
                border: 3px solid #003087;
            }
            .user-details {
                text-align: left;
                color: #003087;
            }
            .nav-menu {
                background: rgba(255, 255, 255, 0.95);
                padding: 1rem;
                border-radius: 0 0 15px 15px;
                display: flex;
                justify-content: center;
                gap: 1rem;
                flex-wrap: wrap;
            }
            .nav-link {
                color: #003087;
                padding: 0.8rem 1.5rem;
                border-radius: 8px;
                transition: all 0.3s ease;
                text-decoration: none;
                display: inline-flex;
                align-items: center;
                gap: 0.5rem;
            }
            .nav-link:hover, .nav-link.active {
                background-color: #003087;
                color: #FFA500;
                transform: translateY(-2px);
            }
            .btn-group {
                display: flex;
                gap: 1rem;
                justify-content: center;
                margin-top: 1rem;
            }
            .btn-edit, .btn-logout {
                padding: 8px 20px;
                border-radius: 5px;
                transition: all 0.3s ease;
                margin: 5px;
                border: none;
            }
            .btn-edit {
                background-color: #003087;
                color: #FFA500;
            }
            .btn-logout {
                background-color: #dc3545;
                color: white;
            }
            .btn-edit:hover, .btn-logout:hover {
                transform: translateY(-2px);
                box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            }
        </style>
    </head>
    <body>
        <%
          TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("SessionLogin");
          if (taiKhoan == null) {
            response.sendRedirect("login.jsp"); 
            return;
          }
        %>
        <div class="header-banner">
            <h1 class="text-center mb-3">QUẢN LÝ HỌC SINH VIÊN</h1>

            <!-- Navigation Menu -->
            <div class="nav-menu">
                <%
                  if ("1".equals(taiKhoan.getQuyen())) {
                  SinhVien sv = (SinhVien) session.getAttribute("sinhVien");
            String maSV = sv != null ? sv.getMaSV() : "";
                %>
                <a href="${pageContext.request.contextPath}/dangkyhocphan?maSV=<%= maSV %>" 
                   class="nav-link ${pageContext.request.servletPath.endsWith('DangKyHocPhan.jsp') ? 'active' : ''}">
                    <i class="fas fa-edit"></i> Đăng ký học phần
                </a>
                <a href="LopHocPhan.jsp" class="nav-link ${pageContext.request.servletPath.endsWith('LopHocPhan.jsp') ? 'active' : ''}">
                    <i class="fas fa-chalkboard"></i> Lớp học phần
                </a>   
                <a href="MonHoc.jsp" class="nav-link ${pageContext.request.servletPath.endsWith('MonHoc.jsp') ? 'active' : ''}">
                    <i class="fas fa-book"></i> Môn học
                </a>
                <%
                        } else if ("2".equals(taiKhoan.getQuyen())) {
                %>
                <a href="LopHocPhan.jsp" class="nav-link ${pageContext.request.servletPath.endsWith('LopHocPhan.jsp') ? 'active' : ''}">
                    <i class="fas fa-chalkboard"></i> Lớp học phần
                </a>
                <a href="danhsachlop" class="nav-link ${pageContext.request.servletPath.endsWith('DanhSachLop.jsp') ? 'active' : ''}">
                    <i class="fas fa-users"></i> Danh sách lớp 
                </a>
                <%
                   } else if ("3".equals(taiKhoan.getQuyen())) {
                %>
               
                <a href="quanlinguoidung" class="nav-link ${pageContext.request.servletPath.endsWith('QuanLiNguoiDung.jsp') ? 'active' : ''}">
                    <i class="fas fa-users-cog"></i> Quản lí người dùng
                </a>
                <a href="quanlilophocphan" class="nav-link ${pageContext.request.servletPath.endsWith('QuanLiLopHocPhan.jsp') ? 'active' : ''}">
                    <i class="fas fa-chalkboard-teacher"></i> Quản lí lớp học phần
                </a>

                <a href="baocao" class="nav-link ${pageContext.request.servletPath.endsWith('BaoCao.jsp') ? 'active' : ''}">
                    <i class="fas fa-chart-bar"></i> Báo cáo thống kê
                </a>
                <%
                  }
                %>
            </div>
        </div>

        <!-- User Info -->
        <div class="user-info animate__animated animate__fadeIn">
            <img src="<%= request.getContextPath() %>/img/avt.jpg" alt="User Avatar" class="user-avatar">
            <div class="user-details">
                <%
    String quyen = taiKhoan.getQuyen();
    String hoTen = "";
    String role = "";

    if ("1".equals(quyen)) {
        SinhVien sv = (SinhVien) session.getAttribute("sinhVien");
        hoTen = sv != null ? sv.getHoTen() : "Không tìm thấy thông tin sinh viên";
        role = "Sinh viên";
    } else if ("2".equals(quyen)) {
        GiangVien gv = (GiangVien) session.getAttribute("giangVien");
        hoTen = gv != null ? gv.getHoTen() : "Không tìm thấy thông tin giảng viên";
        role = "Giảng viên";
    } else if ("3".equals(quyen)) {
        NhanVienPhongDaoTao nv = (NhanVienPhongDaoTao) session.getAttribute("nhanVien");
        hoTen = nv != null ? nv.getHoTen() : "Không tìm thấy thông tin nhân viên";
        role = "Nhân viên phòng đào tạo";
    }
                %>
                <h5 class="mb-1" style="color: #003087;"><%= hoTen %></h5>
                <p class="mb-1" style="color: #003087;"><%= taiKhoan.getTenDangNhap() %></p>
                <p class="mb-1" style="color: #003087;"><%= role %></p>
            </div>
            <div class="btn-group">
                <a href="editProfile.jsp" class="btn-edit">
                    <i class="fas fa-edit"></i> Chỉnh sửa
                </a>
                <a href="logout" class="btn-logout">
                    <i class="fas fa-sign-out-alt"></i> Đăng xuất
                </a>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>