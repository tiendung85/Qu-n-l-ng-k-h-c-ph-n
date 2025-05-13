<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Đặt lại mật khẩu - N5</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f2f6fc;
      font-family: "Segoe UI", sans-serif;
    }
    .reset-container {
      min-height: 100vh;
      display: flex;
      align-items: center;
      justify-content: center;
    }
    .reset-box {
      background: #ffffff;
      padding: 30px;
      border-radius: 16px;
      box-shadow: 0 0 30px rgba(0, 0, 0, 0.1);
      max-width: 500px;
      width: 100%;
    }
    .logo-img {
      max-width: 100px;
    }
    .form-control, .btn {
      border-radius: 8px;
    }
    .login-header h5 {
      font-weight: 600;
      color: #007bff;
    }
    .btn-orange {
      background-color: #fd7e14;
      color: #fff;
      border: none;
    }
    .btn-orange:hover {
      background-color: #e56700;
    }
    .back-to-login a {
      color: #007bff;
      text-decoration: none;
    }
    .back-to-login a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
  <div class="container-fluid reset-container">
    <div class="reset-box mx-3">
      <div class="text-center login-header mb-3">
        <img src="img/logo.jpg" class="logo-img mb-2" alt="TMU Logo">
        
      </div>
      <h4 class="text-primary fw-semibold text-center mb-3">Đặt lại mật khẩu</h4>
      <% if (session.getAttribute("reset_email") == null) { %>
        <div class="alert alert-danger text-center">Không có quyền truy cập. Vui lòng sử dụng chức năng khôi phục mật khẩu.</div>
        <div class="text-center back-to-login">
          <a href="login.jsp">Quay lại đăng nhập</a>
        </div>
      <% } else { %>
        <% if (request.getAttribute("msg") != null) { %>
          <div class="alert alert-info text-center"><%= request.getAttribute("msg") %></div>
        <% } %>
        <% if (request.getAttribute("error") != null) { %>
          <div class="alert alert-danger text-center"><%= request.getAttribute("error") %></div>
        <% } %>
        <form action="reset-password" method="post">
          <div class="mb-3">
            <label for="newPassword" class="form-label">Mật khẩu mới</label>
            <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="Nhập mật khẩu mới" required>
          </div>
          <div class="mb-3">
            <label for="confirmPassword" class="form-label">Xác nhận mật khẩu</label>
            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Nhập lại mật khẩu" required>
          </div>
          <button type="submit" class="btn btn-orange w-100">Đặt lại mật khẩu</button>
        </form>
        <div class="text-center back-to-login mt-3">
          <a href="login.jsp">Quay lại đăng nhập</a>
        </div>
      <% } %>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>