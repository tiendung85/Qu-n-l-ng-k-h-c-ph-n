<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.DangKyHocPhanViewModel" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Danh sách học phần đã đăng ký</title>
<!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Animate.css -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
        <style>
            body {
                background-color: #003087;
                color: #FFA500;
            }
            .header-banner {
                background: linear-gradient(45deg, #002470, #003087);
                padding: 2rem;
                margin-bottom: 2rem;
                box-shadow: 0 4px 15px rgba(0,0,0,0.2);
            }
            .container {
max-width: 95%;
                margin: 0 auto;
                background: rgba(255, 255, 255, 0.95);
                border-radius: 15px;
                padding: 2rem;
                box-shadow: 0 0 20px rgba(0,0,0,0.1);
                margin-bottom: 2rem;
            }
            .search-form {
                background: #f8f9fa;
                padding: 1.5rem;
                border-radius: 10px;
                margin-bottom: 2rem;
                transition: all 0.3s ease;
            }
            .search-form:hover {
                box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            }
.table-responsive {
                margin: 0 -15px;  /* Negative margin to counteract container padding */
            }
.table {
width: 100%;
                min-width: 1200px;  /* Minimum width to prevent cramping */
                                background: white;
border-radius: 8px;
                overflow: hidden;
                box-shadow: 0 0 10px rgba(0,0,0,0.05);
            }
            .table thead {
                background-color: #003087;
                color: #FFA500;
            }
.table th {
                white-space: nowrap;
                padding: 1rem;
                vertical-align: middle;
                font-weight: 600;
                text-align: center;
            }
            .table td {
                padding: 0.75rem;
                vertical-align: middle;
                text-align: center;
            }
            .table tbody tr {
                transition: all 0.2s ease;
            }
            .table tbody tr:hover {
                background-color: #f8f9fa;
                transform: scale(1.01);
            }
            .btn-primary {
                background-color: #003087;
                border-color: #003087;
                transition: all 0.3s ease;
            }
            .btn-primary:hover {
                background-color: #002470;
                border-color: #002470;
                transform: translateY(-2px);
            }
            .btn-danger {
                background-color: #dc3545;
                transition: all 0.3s ease;
            }
            .btn-danger:hover {
                transform: translateY(-2px);
            }
            .alert {
                border-radius: 8px;
                animation: fadeIn 0.5s ease;
            }
            @keyframes fadeIn {
                from { opacity: 0; transform: translateY(-10px); }
                to { opacity: 1; transform: translateY(0); }
            }
/* Column specific widths */
            .table th:nth-child(1), .table td:nth-child(1) { width: 7%; }   /* Mã ĐK */
            .table th:nth-child(2), .table td:nth-child(2) { width: 7%; }   /* Mã LHP */
            .table th:nth-child(3), .table td:nth-child(3) { width: 14%; }  /* Tên HP */
            .table th:nth-child(4), .table td:nth-child(4) { width: 5%; }   /* Số TC */
            .table th:nth-child(5), .table td:nth-child(5) { width: 10%; }  /* Giảng Viên */
            .table th:nth-child(6), .table td:nth-child(6) { width: 10%; }  /* Thời Gian */
            .table th:nth-child(7), .table td:nth-child(7) { width: 7%; }   /* Phòng Học */
            .table th:nth-child(8), .table td:nth-child(8) { width: 5%; }   /* Học Kỳ */
            .table th:nth-child(9), .table td:nth-child(9) { width: 5%; }   /* Năm Học */
            .table th:nth-child(10), .table td:nth-child(10) { width: 7%; } /* Ngày ĐK */
            .table th:nth-child(11), .table td:nth-child(11) { width: 7%; } /* Mốc ĐK */
            .table th:nth-child(12), .table td:nth-child(12) { width: 6%; } /* Số SV TD */
            .table th:nth-child(13), .table td:nth-child(13) { width: 5%; } /* Tình Trạng */
.table th:nth-child(14), .table td:nth-child(14) { width: 5%; } /* Thao Tác */
            /* Add horizontal scroll for small screens */
            @media (max-width: 1200px) {
                .table-responsive {
                    overflow-x: auto;
                    -webkit-overflow-scrolling: touch;
                }
            }
.btn-home {
    background-color: #FFA500;
    border-color: #FFA500;
    color: #003087;
    font-weight: 600;
    transition: all 0.3s ease;
    position: fixed;
    bottom: 20px;
    right: 20px;
    z-index: 1000;
    padding: 10px 20px;
    box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

.btn-home:hover {
    background-color: #FF8C00;
    border-color: #FF8C00;
    color: #003087;
    transform: translateY(-3px);
    box-shadow: 0 4px 15px rgba(0,0,0,0.2);
}

/* Custom button styles */
.btn-register {
    background-color: #003087;
    border-color: #003087;
    color: white;
    font-weight: 500;
    padding: 8px 16px;
    transition: all 0.3s ease;
    margin: 2px;
}

.btn-register:hover {
    background-color: #002470;
    border-color: #002470;
    color: white;
    transform: translateY(-2px);
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
}

.btn-cancel {
    background-color: #FFA500;
    border-color: #FFA500;
    color: #003087;
    font-weight: 500;
    padding: 8px 16px;
    transition: all 0.3s ease;
    margin: 2px;
}

.btn-cancel:hover {
    background-color: #FF8C00;
    border-color: #FF8C00;
    color: #003087;
    transform: translateY(-2px);
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
}
        </style>
    </head>
    <body>
<div class="header-banner">
        <h2 class="text-center animate__animated animate__fadeInDown">
Danh sách học phần đã đăng ký của sinh viên: ${maSV}
</h2>
</div>

        <div class="container animate__animated animate__fadeIn">
<% if (request.getParameter("message") != null) { %>
            <div class="alert alert-info text-center">
                <%= request.getParameter("message") %>
            </div>
        <% } %>

<div class="search-form">
            <form action="dangkyhocphan" method="get" class="row g-3 align-items-center justify-content-center">
                    <div class="col-auto">
                <input type="hidden" name="maSV" value="${maSV}">
                <input type="text" class="form-control" name="maLHP" value="${maLHP}" 
placeholder="Nhập mã lớp học phần...">
</div>
                    <div class="col-auto">
                <button type="submit" class="btn btn-primary">
                            <i class="fas fa-search"></i> Tìm kiếm
</button>
</div>
                <% if (request.getParameter("maLHP") != null) { %>
<div class="col-auto">
                    <a href="dangkyhocphan?maSV=${maSV}" class="btn btn-secondary">
                                <i class="fas fa-list"></i> Xem tất cả
</a>
</div>
                <% } %>
            </form>
        </div>

        <div class="table-responsive">
                <table class="table table-hover">
            <thead>
                <tr>
                    <th>Mã ĐK</th>
                    <th>Mã LHP</th>
                    <th>Tên Học Phần</th>
                    <th>Số Tín Chỉ</th>
                    <th>Giảng Viên</th>
                    <th>Thời Gian</th>
                    <th>Phòng Học</th>
                    <th>Học Kỳ</th>
                    <th>Năm Học</th>
                    <th>Ngày Đăng Ký</th>
                    <th>Mốc Đăng Ký</th>
                    <th>Tình Trạng</th>
<th>Thao Tác</th>
<th>Số SV Tối Đa</th>
                </tr>
            </thead>
            <tbody>
                <% 
                            List<DangKyHocPhanViewModel> dsDangKy = (List<DangKyHocPhanViewModel>) request.getAttribute("dsDangKy");
                            if (dsDangKy == null || dsDangKy.isEmpty()) { 
                        %>
                        <tr>
                            <td colspan="14" class="text-center">Không có dữ liệu đăng ký học phần.</td>
                        </tr>
                        <% 
                            } else { 
for (DangKyHocPhanViewModel dk : dsDangKy) { 
%>
                <tr>
                    <td><%= dk.getMaDK() %></td>
                    <td><%= dk.getMaLHP() %></td>
                    <td><%= dk.getTenHP() %></td>
                    <td><%= dk.getSoTC() %></td>
                    <td><%= dk.getTenGV() %></td>
                    <td><%= dk.getThoiGianHoc() %></td>
                    <td><%= dk.getPhongHoc() %></td>
                    <td><%= dk.getHocKy() %></td>
                    <td><%= dk.getNamHoc() %></td>
                    <td><%= dk.getNgayDK() %></td>
                    <td><%= dk.getMocDK() %></td>
                    <td><%= dk.getTinhTrang() %></td>
<td>
                        <% if (dk.getTinhTrang().equals("Chưa đăng ký") || dk.getTinhTrang().equals("Đã hủy")) { %>
                            <form action="dangkyhocphan" method="POST" style="display: inline;">
                                <input type="hidden" name="action" value="dangky">
                                <input type="hidden" name="maDK" value="<%= dk.getMaDK() %>">
                                <input type="hidden" name="maSV" value="${maSV}">
                                <input type="hidden" name="maLHP" value="<%= dk.getMaLHP() %>">
                                <button type="submit" class="btn btn-register">
                                    <i class="fas fa-check-circle"></i> Đăng ký
</button>
                            </form>
                        <% } else if (dk.getTinhTrang().equals("Đã đăng ký")) { %>
                            <form action="dangkyhocphan" method="POST" style="display: inline;">
                                <input type="hidden" name="action" value="huy">
                                <input type="hidden" name="maDK" value="<%= dk.getMaDK() %>">
                                <input type="hidden" name="maSV" value="${maSV}">
                                <input type="hidden" name="maLHP" value="<%= dk.getMaLHP() %>">
                                <button type="submit" class="btn btn-cancel">
                                    <i class="fas fa-times-circle"></i> Hủy đăng ký
</button>
                            </form>
                        <% } %>
                    </td>
<td><%= dk.getSoLuongSVTD() %></td>
                </tr>
                        <%
} 
            }
        %>
</tbody>
                </table>
            </div>
        </div>

<!-- Bootstrap JS and Font Awesome -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://kit.fontawesome.com/your-code.js" crossorigin="anonymous"></script>
<a href="home.jsp" class="btn btn-home animate__animated animate__fadeIn">
            <i class="fas fa-home"></i> Trở về trang chủ
        </a>
    </body>
</html>
 
