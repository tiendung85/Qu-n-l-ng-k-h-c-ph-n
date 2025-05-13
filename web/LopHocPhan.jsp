<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="model.TaiKhoan"%>
<%@page import="model.GiangVien"%>
<%@page import="model.LopHocPhan"%>
<%@page import="dal.LopHocPhanDao"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Quản lý Lớp Học Phần</title>
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
                background: rgba(255, 255, 255, 0.95);
                border-radius: 15px;
                padding: 2rem;
                box-shadow: 0 0 20px rgba(0,0,0,0.1);
                margin-bottom: 2rem;
                max-width: 95%;
                margin: 0 auto;
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
                margin: 0 -15px;
            }
            .table {
                width: 100%;
                min-width: 1200px;
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
            .alert {
                border-radius: 8px;
                animation: fadeIn 0.5s ease;
            }
            @keyframes fadeIn {
                from {
                    opacity: 0;
                    transform: translateY(-10px);
                }
                to {
                    opacity: 1;
                    transform: translateY(0);
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
                transform: translateY(-3px);
                box-shadow: 0 4px 15px rgba(0,0,0,0.2);
            }
            h3 {
                color: #003087;
                margin-bottom: 1.5rem;
                font-weight: 600;
            }

            /* Column widths */
            .table th:nth-child(1), .table td:nth-child(1) {
                width: 8%;
            }  /* Mã LHP */
            .table th:nth-child(2), .table td:nth-child(2) {
                width: 15%;
            } /* Tên HP */
            .table th:nth-child(3), .table td:nth-child(3) {
                width: 10%;
            } /* Giảng viên */
            .table th:nth-child(4), .table td:nth-child(4) {
                width: 7%;
            }  /* Mã MH */
            .table th:nth-child(5), .table td:nth-child(5) {
                width: 5%;
            }  /* Học kỳ */
            .table th:nth-child(6), .table td:nth-child(6) {
                width: 8%;
            }  /* Năm học */
            .table th:nth-child(7), .table td:nth-child(7) {
                width: 5%;
            }  /* Số TC */
            .table th:nth-child(8), .table td:nth-child(8) {
                width: 8%;
            }  /* Số SV tối đa */
            .table th:nth-child(9), .table td:nth-child(9) {
                width: 12%;
            } /* Thời gian học */
            .table th:nth-child(10), .table td:nth-child(10) {
                width: 12%;
            } /* Phòng học */
            .table th:nth-child(11), .table td:nth-child(11) {
                width: 10%;
            } /* Mốc ĐK */
        </style>
    </head>
    <body>
        <div class="header-banner">
            <h1 class="text-center animate__animated animate__fadeInDown">Lớp Học Phần</h1>
        </div>

        <div class="container animate__animated animate__fadeIn">
            <!-- Form tìm kiếm - chỉ hiển thị cho admin -->
            <c:if test="${sessionScope.account.quyen != '2'}">
                <div class="search-form">
                    <form action="lophocphan" method="GET" class="row g-3 align-items-center justify-content-center">
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="maLHP" 
                                   placeholder="Nhập mã lớp học phần..." value="${param.maLHP}">
                        </div>
                        <div class="col-md-4 d-flex gap-2">
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-search"></i> Tìm kiếm
                            </button>
                            <a href="lophocphan" class="btn btn-secondary">
                                <i class="fas fa-sync-alt"></i> Xem tất cả
                            </a>
                        </div>
                    </form>
                </div>
            </c:if>

            <!-- Tiêu đề section -->
            <c:choose>
                <c:when test="${sessionScope.account.quyen == '2'}">
                    <h3 class="mb-3">Danh sách lớp học phần của giảng viên ${sessionScope.giangvien.hoTen}</h3>
                </c:when>
                <c:otherwise>
                    <c:if test="${not empty param.maLHP}">
                        <h3 class="mb-3">Kết quả tìm kiếm cho mã lớp học phần: ${param.maLHP}</h3>
                    </c:if>
                    <c:if test="${empty param.maLHP}">
                        <h3 class="mb-3">Danh sách tất cả lớp học phần</h3>
                    </c:if>
                </c:otherwise>
            </c:choose>

            <!-- Hiển thị thông báo nếu có -->
            <c:if test="${not empty message}">
                <div class="alert alert-warning" role="alert">
                    ${message}
                </div>
            </c:if>

            <!-- Bảng hiển thị lớp học phần -->
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Mã LHP</th>
                            <th>Tên HP</th>
                            <th>Giảng viên</th>
                            <th>Mã MH</th>
                            <th>Học kỳ</th>
                            <th>Năm học</th>
                            <th>Số TC</th>
                            <th>Số SV tối đa</th>
                            <th>Thời gian học</th>
                            <th>Phòng học</th>
                            <th>Mốc ĐK</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty listLopHocPhan}">
                                <tr>
                                    <td colspan="11" class="text-center">
                                        <c:choose>
                                            <c:when test="${sessionScope.account.quyen == '2'}">
                                                Hiện tại bạn không có lớp học phần nào được phân công.
                                            </c:when>
                                            <c:otherwise>
                                                Không tìm thấy lớp học phần nào.
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${listLopHocPhan}" var="lhp">
                                    <tr>
                                        <td>${lhp.maLHP}</td>
                                        <td>${lhp.tenHP}</td>
                                        <td>${lhp.tenGV}</td>
                                        <td>${lhp.maMH}</td>
                                        <td>${lhp.hocKy}</td>
                                        <td>${lhp.namHoc}</td>
                                        <td>${lhp.soTC}</td>
                                        <td>${lhp.soLuongSVTD}</td>
                                        <td>${lhp.thoiGianHoc}</td>
                                        <td>${lhp.phongHoc}</td>
                                        <td><fmt:formatDate value="${lhp.mocDK}" pattern="dd/MM/yyyy"/></td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>

            <!-- Nút trở về trang chủ -->
            <a href="home.jsp" class="btn btn-home animate__animated animate__fadeIn">
                <i class="fas fa-home"></i> Trở về trang chủ
            </a>
        </div>

        <!-- Bootstrap JS and Font Awesome -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://kit.fontawesome.com/your-code.js" crossorigin="anonymous"></script>
    </body>
</html>