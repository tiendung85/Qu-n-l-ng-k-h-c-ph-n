<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Danh Sách Lớp</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
            .card {
                border: none;
                border-radius: 10px;
                margin-bottom: 2rem;
                box-shadow: 0 0 15px rgba(0,0,0,0.1);
                overflow: hidden;
            }
            .card-header {
                background: #003087;
                color: #FFA500;
                padding: 1rem;
                border-bottom: none;
            }
            .table {
                background: white;
                margin-bottom: 0;
            }
            .table thead {
                background-color: #003087;
                color: #FFA500;
            }
            .table tbody tr {
                transition: all 0.2s ease;
            }
            .table tbody tr:hover {
                background-color: #f8f9fa;
                transform: scale(1.01);
            }
            .btn-home {
                background-color: #FFA500;
                border-color: #FFA500;
                color: #003087;
                font-weight: 600;
                padding: 10px 20px;
                box-shadow: 0 2px 10px rgba(0,0,0,0.1);
                transition: all 0.3s ease;
                position: fixed;
                bottom: 20px;
                right: 20px;
                z-index: 1000;
            }
            .btn-home:hover {
                background-color: #FF8C00;
                border-color: #FF8C00;
                transform: translateY(-3px);
                box-shadow: 0 4px 15px rgba(0,0,0,0.2);
            }
            .btn-info {
                background-color: #003087;
                border-color: #003087;
                color: #FFA500;
                transition: all 0.3s ease;
            }
            .btn-info:hover {
                background-color: #002470;
                border-color: #002470;
                transform: translateY(-2px);
            }
            h3 {
                color: inherit;
                margin-bottom: 0;
                font-weight: 600;
            }

            /* Thêm định dạng độ rộng cột */
            .table th:nth-child(1) {
                width: 10%;
            } /* Mã LHP */
            .table th:nth-child(2) {
                width: 20%;
            } /* Tên HP */
            .table th:nth-child(3) {
                width: 10%;
            } /* Học kỳ */
            .table th:nth-child(4) {
                width: 10%;
            } /* Năm học */
            .table th:nth-child(5) {
                width: 15%;
            } /* Thời gian */
            .table th:nth-child(6) {
                width: 10%;
            } /* Phòng */
            .table th:nth-child(7) {
                width: 10%;
            } /* Số SV */
            .table th:nth-child(8) {
                width: 15%;
            } /* Thao tác */
        </style>
    </head>
    <body>
        <div class="header-banner">
            <h1 class="text-center animate__animated animate__fadeInDown">Danh Sách Lớp Học Phần</h1>
        </div>

        <div class="container animate__animated animate__fadeIn">
            <div class="card">
                <div class="card-header">
                    <h3>Danh Sách Lớp Học Phần</h3>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>Mã LHP</th>
                                    <th>Tên Học Phần</th>
                                    <th>Học Kỳ</th>
                                    <th>Năm Học</th>
                                    <th>Thời Gian Học</th>
                                    <th>Phòng Học</th>
                                    <th>Số SV tối đa</th>
                                    <th>Thao Tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${danhSachLop}" var="lop">
                                    <tr>
                                        <td>${lop.maLHP}</td>
                                        <td>${lop.tenHP}</td>
                                        <td>${lop.hocKy}</td>
                                        <td>${lop.namHoc}</td>
                                        <td>${lop.thoiGianHoc}</td>
                                        <td>${lop.phongHoc}</td>
                                        <td>${lop.soLuongSVTD}</td>
                                        <td>
                                            <a href="danhsachlop?maLHP=${lop.maLHP}" 
                                               class="btn btn-sm btn-info">
                                                <i class="fas fa-eye"></i> Xem sinh viên
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <c:if test="${not empty danhSachSV}">
                <div class="card">
                    <div class="card-header">
                        <h3>Danh Sách Sinh Viên - Lớp ${maLHP}</h3>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>Mã SV</th>
                                        <th>Họ Tên</th>
                                        <th>Giới Tính</th>
                                        <th>Email</th>
                                        <th>SĐT</th>
                                        <th>Lớp</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${danhSachSV}" var="sv">
                                        <tr>
                                            <td>${sv.maSV}</td>
                                            <td>${sv.hoTen}</td>
                                            <td>${sv.gioiTinh}</td>
                                            <td>${sv.email}</td>
                                            <td>${sv.sdt}</td>
                                            <td>${sv.lopHC}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </c:if>

            <!-- Nút trở về trang chủ -->
            <div class="home-button">
                <a href="home.jsp" class="btn btn-home animate__animated animate__fadeIn">
                    <i class="fas fa-home"></i> Trở về trang chủ
                </a>
            </div>
        </div>

        <!-- Bootstrap JS and Font Awesome -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://kit.fontawesome.com/your-code.js" crossorigin="anonymous"></script>
    </body>
</html>