<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Chỉnh sửa thông tin</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Animate.css -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <style>
        body {
            background-color: #003087;
            color: #FFA500;
            padding: 2rem;
        }
        .container {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 15px;
            padding: 2rem;
            box-shadow: 0 0 20px rgba(0,0,0,0.1);
            margin-top: 2rem;
            animation: fadeIn 0.5s ease;
        }
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }
        .form-label {
            color: #003087;
            font-weight: 500;
        }
        .form-control {
            border-radius: 8px;
            border: 1px solid #ced4da;
            transition: all 0.3s ease;
        }
        .form-control:focus {
            border-color: #003087;
            box-shadow: 0 0 0 0.2rem rgba(0, 48, 135, 0.25);
        }
        .btn {
            padding: 8px 20px;
            border-radius: 8px;
            transition: all 0.3s ease;
        }
        .btn:hover {
            transform: translateY(-2px);
        }
        .btn-primary {
            background-color: #003087;
            border-color: #003087;
        }
        .btn-primary:hover {
            background-color: #002470;
            border-color: #002470;
        }
    </style>
</head>
<body>
    <div class="container animate__animated animate__fadeIn">
        <h3 class="text-center mb-4" style="color: #003087;">Chỉnh sửa thông tin người dùng</h3>

<c:if test="${not empty sessionScope.SessionLogin}">
  <form action="updateProfile" method="post" class="needs-validation" novalidate>
    <c:choose>
      <c:when test="${sessionScope.SessionLogin.quyen == '1' && not empty sessionScope.sinhVien}">
        <div class="mb-3">
          <label for="maSV" class="form-label">Mã SV</label>
          <input type="text" class="form-control" id="maSV" name="maSV" value="${sessionScope.sinhVien.maSV}" readonly>
        </div>
        <div class="mb-3">
          <label for="hoTen" class="form-label">Họ tên</label>
          <input type="text" class="form-control" id="hoTen" name="hoTen" value="${sessionScope.sinhVien.hoTen}" required>
        </div>
        <div class="mb-3">
          <label for="ngaySinh" class="form-label">Ngày sinh</label>
          <input type="date" class="form-control" id="ngaySinh" name="ngaySinh" 
                                   value="<fmt:formatDate pattern='yyyy-MM-dd' value='${sessionScope.sinhVien.ngaySinh}'/>" 
required>
        </div>
        <div class="mb-3">
          <label for="gioiTinh" class="form-label">Giới tính</label>
          <select class="form-control" id="gioiTinh" name="gioiTinh" required>
            <option value="Nam" ${sessionScope.sinhVien.gioiTinh == 'Nam' ? 'selected' : ''}>Nam</option>
            <option value="Nữ" ${sessionScope.sinhVien.gioiTinh == 'Nữ' ? 'selected' : ''}>Nữ</option>
          </select>
        </div>
        <div class="mb-3">
          <label for="email" class="form-label">Email</label>
          <input type="email" class="form-control" id="email" name="email" value="${sessionScope.sinhVien.email}" required>
        </div>
        <div class="mb-3">
          <label for="sdt" class="form-label">Số điện thoại</label>
          <input type="text" class="form-control" id="sdt" name="sdt" value="${sessionScope.sinhVien.sdt}" required>
        </div>
        <div class="mb-3">
          <label for="diaChi" class="form-label">Địa chỉ</label>
          <input type="text" class="form-control" id="diaChi" name="diaChi" value="${sessionScope.sinhVien.diaChi}" required>
        </div>
        <div class="mb-3">
          <label for="lopHC" class="form-label">Lớp hành chính</label>
          <input type="text" class="form-control" id="lopHC" name="lopHC" value="${sessionScope.sinhVien.lopHC}" required>
        </div>
        <div class="mb-3">
  <label for="tinhTrang" class="form-label">Tình trạng</label>
  <select class="form-control" id="tinhTrang" name="tinhTrang" required>
    <option value="Đang học" ${sessionScope.sinhVien.tinhTrang == 'Đang học' ? 'selected' : ''}>Đang học</option>
    <option value="Bảo lưu" ${sessionScope.sinhVien.tinhTrang == 'Bảo lưu' ? 'selected' : ''}>Bảo lưu</option>
  </select>
</div>
      </c:when>

      <c:when test="${sessionScope.SessionLogin.quyen == '2' && not empty sessionScope.giangVien}">
        <div class="mb-3">
          <label for="maGV" class="form-label">Mã GV</label>
          <input type="text" class="form-control" id="maGV" name="maGV" value="${sessionScope.giangVien.maGV}" readonly>
        </div>
        <div class="mb-3">
          <label for="hoTen" class="form-label">Họ tên</label>
          <input type="text" class="form-control" id="hoTen" name="hoTen" value="${sessionScope.giangVien.hoTen}" required>
        </div>
        <div class="mb-3">
          <label for="ngaySinh" class="form-label">Ngày sinh</label>
          <input type="date" class="form-control" id="ngaySinh" name="ngaySinh" 
                                   value="<fmt:formatDate pattern='yyyy-MM-dd' value='${sessionScope.giangVien.ngaySinh}'/>" 
required>
        </div>
        <div class="mb-3">
          <label for="gioiTinh" class="form-label">Giới tính</label>
          <select class="form-control" id="gioiTinh" name="gioiTinh" required>
            <option value="Nam" ${sessionScope.giangVien.gioiTinh == 'Nam' ? 'selected' : ''}>Nam</option>
            <option value="Nữ" ${sessionScope.giangVien.gioiTinh == 'Nữ' ? 'selected' : ''}>Nữ</option>
          </select>
        </div>
        <div class="mb-3">
          <label for="email" class="form-label">Email</label>
          <input type="email" class="form-control" id="email" name="email" value="${sessionScope.giangVien.email}" required>
        </div>
        <div class="mb-3">
          <label for="sdt" class="form-label">Số điện thoại</label>
          <input type="text" class="form-control" id="sdt" name="sdt" value="${sessionScope.giangVien.sdt}" required>
        </div>
       
        <div class="mb-3">
          <label for="hocVi" class="form-label">Học vị</label>
          <input type="text" class="form-control" id="hocVi" name="hocVi" value="${sessionScope.giangVien.hocVi}" required>
        </div>
        <div class="mb-3">
          <label for="khoa" class="form-label">Khoa</label>
          <input type="text" class="form-control" id="khoa" name="khoa" value="${sessionScope.giangVien.khoa}" required>
        </div>
      </c:when>

      <c:when test="${sessionScope.SessionLogin.quyen == '3' && not empty sessionScope.nhanVien}">
        <div class="mb-3">
          <label for="maNV" class="form-label">Mã NV</label>
          <input type="text" class="form-control" id="maNV" name="maNV" value="${sessionScope.nhanVien.maNV}" readonly>
        </div>
        <div class="mb-3">
          <label for="hoTen" class="form-label">Họ tên</label>
          <input type="text" class="form-control" id="hoTen" name="hoTen" value="${sessionScope.nhanVien.hoTen}" required>
        </div>
        <div class="mb-3">
          <label for="ngaySinh" class="form-label">Ngày sinh</label>
          <input type="date" class="form-control" id="ngaySinh" name="ngaySinh" 
                                   value="<fmt:formatDate pattern='yyyy-MM-dd' value='${sessionScope.nhanVien.ngaySinh}'/>" 
required>
        </div>
        <div class="mb-3">
          <label for="gioiTinh" class="form-label">Giới tính</label>
          <select class="form-control" id="gioiTinh" name="gioiTinh" required>
            <option value="Nam" ${sessionScope.nhanVien.gioiTinh == 'Nam' ? 'selected' : ''}>Nam</option>
            <option value="Nữ" ${sessionScope.nhanVien.gioiTinh == 'Nữ' ? 'selected' : ''}>Nữ</option>
          </select>
        </div>
        <div class="mb-3">
          <label for="email" class="form-label">Email</label>
          <input type="email" class="form-control" id="email" name="email" value="${sessionScope.nhanVien.email}" required>
        </div>
        <div class="mb-3">
          <label for="sdt" class="form-label">Số điện thoại</label>
          <input type="text" class="form-control" id="sdt" name="sdt" value="${sessionScope.nhanVien.sdt}" required>
        </div>
        <div class="mb-3">
          <label for="chucVu" class="form-label">Chức vụ</label>
          <input type="text" class="form-control" id="chucVu" name="chucVu" value="${sessionScope.nhanVien.chucVu}" required>
        </div>
        <div class="mb-3">
          <label for="phongLV" class="form-label">Phòng làm việc</label>
          <input type="text" class="form-control" id="phongLV" name="phongLV" value="${sessionScope.nhanVien.phongLV}" required>
        </div>
      </c:when>
    </c:choose>

<div class="text-center mt-4">
    <button type="submit" class="btn btn-primary me-2">
                        <i class="fas fa-save"></i> Cập nhật
</button>
    <a href="home.jsp" class="btn btn-secondary">
                        <i class="fas fa-times"></i> Hủy
                    </a>
                </div>
  </form>
</c:if>
</div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
