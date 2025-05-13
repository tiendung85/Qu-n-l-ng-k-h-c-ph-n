<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Quản lý Môn học</title>
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
        .table {
            background: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 0 10px rgba(0,0,0,0.05);
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
            from { opacity: 0; transform: translateY(-10px); }
            to { opacity: 1; transform: translateY(0); }
        }
        .home-button {
            position: fixed;
            bottom: 20px;
            right: 20px;
            z-index: 1000;
        }
.btn-home {
            background-color: #FFA500; 
border-color: #FFA500;
            color: #003087;
font-weight: 600;
            padding: 10px 20px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            transition: all 0.3s ease;
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
        .table th:nth-child(1) { width: 15%; }
        .table th:nth-child(2) { width: 35%; }
        .table th:nth-child(3) { width: 10%; }
        .table th:nth-child(4) { width: 10%; }
        .table th:nth-child(5) { width: 30%; }
    </style>
</head>
<body>
    <div class="header-banner">
    <h1 class="text-center animate__animated animate__fadeInDown">Môn học</h1>
</div>
    
<div class="container animate__animated animate__fadeIn">
<!-- Form tìm kiếm -->  
    <div class="search-form">
        <form action="monhoc" method="GET" class="row g-3 align-items-center justify-content-center">
                <div class="col-md-8">
            <input type="text" class="form-control" name="maMH" placeholder="Nhập mã môn học..." value="${param.maMH}">
            </div>
                <div class="col-md-4 d-flex gap-2">
                    <button type="submit" class="btn btn-primary">
                        <i class="fas fa-search"></i> Tìm kiếm
</button>
<a href="monhoc" class="btn btn-secondary">Xem tất cả</a>
                </div>
        </form>
    </div>

<!-- Hiển thị thông báo -->
    <c:if test="${not empty message}">
        <div class="alert alert-danger">${message}</div>
    </c:if>

    <!-- Tiêu đề kết quả -->
    <c:if test="${not empty param.maMH}">
        <h3 class="mb-3">Kết quả tìm kiếm cho mã môn học: ${param.maMH}</h3>
    </c:if>
    <c:if test="${empty param.maMH}">
        <h3 class="mb-3">Danh sách tất cả môn học</h3>
    </c:if>

    <!-- Bảng hiển thị môn học -->    
<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover">
        <thead>
            <tr>
                <th>Mã MH</th>
                <th>Tên Môn học</th>
                <th>Số tín chỉ</th>
                <th>Số tiết</th>
                <th>Khoa phụ trách</th>
            </tr>
        </thead>
        <tbody>
<c:choose>
                <c:when test="${empty listMonHoc}">
                    <tr>
                        <td colspan="5" class="text-center">Không có dữ liệu</td>
                    </tr>
                </c:when>
                <c:otherwise>
            <c:forEach items="${listMonHoc}" var="mh">
                <tr>
                    <td>${mh.maMH}</td>
                    <td>${mh.tenMH}</td>
                    <td>${mh.soTC}</td>
                    <td>${mh.soTiet}</td>
                    <td>${mh.khoaPT}</td>
                </tr>
            </c:forEach>
</c:otherwise>
            </c:choose>
        </tbody>
    </table>
</div>

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