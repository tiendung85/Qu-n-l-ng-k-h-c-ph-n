<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Đăng ký Giảng viên - N5</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f2f6fc;
      font-family: "Segoe UI", sans-serif;
    }
    .register-container {
      min-height: 100vh;
      display: flex;
      align-items: stretch;
    }
    .register-box {
      background: #ffffff;
      padding: 30px;
      border-radius: 16px;
      box-shadow: 0 0 30px rgba(0, 0, 0, 0.1);
    }
    .logo-img {
      max-width: 100px;
    }
    .form-control, .btn {
      border-radius: 8px;
    }
    .bg-campus {
      background-image: url('img/truong.jpg');
      background-size: cover;
      background-position: center;
    }
    .register-header h5 {
      font-weight: 600;
      color: #007bff;
    }
    .login-link a {
      color: #007bff;
      text-decoration: none;
    }
    .login-link a:hover {
      text-decoration: underline;
    }
    .btn-orange {
      background-color: #fd7e14;
      color: #fff;
      border: none;
    }
    .btn-orange:hover {
      background-color: #e56700;
    }
  </style>
</head>
<body>
  <div class="container-fluid register-container">
    <div class="row w-100">
      <div class="col-md-7 d-none d-md-block bg-campus"></div>
      <div class="col-md-5 d-flex align-items-center justify-content-center">
        <div class="register-box w-100 mx-3">
          <div class="text-center register-header mb-3">
            <img src="img/logo.jpg" class="logo-img mb-2" alt="Logo">
       
          </div>
          <h4 class="text-primary fw-semibold text-center mb-3">Đăng ký Giảng viên</h4>
          <% if (request.getAttribute("msg") != null) { %>
            <div class="alert alert-info text-center"><%= request.getAttribute("msg") %></div>
          <% } %>
          <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger text-center"><%= request.getAttribute("error") %></div>
          <% } %>
          <form action="register" method="post">
            <input type="hidden" name="Quyen" value="2">
            <div class="mb-3">
              <label for="MaGV" class="form-label">Mã giảng viên</label>
              <input type="text" class="form-control" id="MaGV" name="MaGV" placeholder="VD: GV001" maxlength="10" required>
            </div>
            <div class="mb-3">
              <label for="HoTen" class="form-label">Họ và tên</label>
              <input type="text" class="form-control" id="HoTen" name="HoTen" placeholder="VD: Nguyễn Văn B" required>
            </div>
            <div class="mb-3">
              <label for="NgaySinh" class="form-label">Ngày sinh</label>
              <input type="date" class="form-control" id="NgaySinh" name="NgaySinh" required>
            </div>
            <div class="mb-3">
              <label for="GioiTinh" class="form-label">Giới tính</label>
              <select class="form-control" id="GioiTinh" name="GioiTinh" required>
                <option value="" disabled selected>Chọn giới tính</option>
                <option value="Nam">Nam</option>
                <option value="Nữ">Nữ</option>
                <option value="Khác">Khác</option>
              </select>
            </div>
            <div class="mb-3">
              <label for="Email" class="form-label">Email</label>
              <input type="email" class="form-control" id="Email" name="Email" placeholder="VD: example@tmu.edu.vn" required>
            </div>
            <div class="mb-3">
              <label for="SDT" class="form-label">Số điện thoại</label>
              <input type="text" class="form-control" id="SDT" name="SDT" placeholder="VD: 0123456789" maxlength="10" required>
            </div>
            <div class="mb-3">
              <label for="PhongLV" class="form-label">Phòng làm việc</label>
              <input type="text" class="form-control" id="PhongLV" name="PhongLV" placeholder="VD: Phòng 101">
            </div>
            <div class="mb-3">
              <label for="HocVi" class="form-label">Học vị</label>
              <input type="text" class="form-control" id="HocVi" name="HocVi" placeholder="VD: Tiến sĩ">
            </div>
            <div class="mb-3">
              <label for="Khoa" class="form-label">Khoa</label>
              <input type="text" class="form-control" id="Khoa" name="Khoa" placeholder="VD: Khoa Kinh tế">
            </div>
            <div class="mb-3">
              <label for="MatKhau" class="form-label">Mật khẩu</label>
              <input type="password" class="form-control" id="MatKhau" name="MatKhau" placeholder="********" required>
            </div>
            <div class="mb-3">
              <label for="ConfirmMatKhau" class="form-label">Xác nhận mật khẩu</label>
              <input type="password" class="form-control" id="ConfirmMatKhau" name="ConfirmMatKhau" placeholder="********" required>
            </div>
            <button type="submit" class="btn btn-orange w-100 mt-3">Đăng ký</button>
            <div class="login-link text-center mt-3">
              <p>Đã có tài khoản? <a href="login.jsp">Đăng nhập</a></p>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>