<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Đăng nhập - N5</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f2f6fc;
      font-family: "Segoe UI", sans-serif;
    }
    .login-container {
      min-height: 100vh;
      display: flex;
      align-items: stretch;
    }
    .login-box {
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
    .note {
      font-size: 0.9rem;
      color: #d9534f;
    }
    .bg-campus {
      background-image: url('img/truong.jpg');
      background-size: cover;
      background-position: center;
    }
    .login-header h5 {
      font-weight: 600;
      color: #007bff;
    }
    .forgot-password a, .register-link a {
      color: #007bff;
      text-decoration: none;
    }
    .forgot-password a:hover, .register-link a:hover {
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
  <div class="container-fluid login-container">
    <div class="row w-100">
      <div class="col-md-7 d-none d-md-block bg-campus"></div>
      <div class="col-md-5 d-flex align-items-center justify-content-center">
        <div class="login-box w-100 mx-3">
          <div class="text-center login-header mb-3">
            <img src="img/logo.jpg" class="logo-img mb-2" alt="Logo">
          </div>
          <h4 class="text-primary fw-semibold text-center mb-3">Đăng nhập</h4>
          <% if (request.getAttribute("msg") != null) { %>
            <div class="alert alert-info text-center"><%= request.getAttribute("msg") %></div>
          <% } %>
          <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger text-center"><%= request.getAttribute("error") %></div>
          <% } %>
          <form action="login" method="post">
            <div class="mb-3">
              <label for="TenDangNhap" class="form-label">Tên đăng nhập</label>
              <input type="text" class="form-control" id="TenDangNhap" name="TenDangNhap" placeholder="VD: 23D19" maxlength="10" required>
            </div>
            <div class="mb-3">
              <label for="MatKhau" class="form-label">Mật khẩu</label>
              <input type="password" class="form-control" id="MatKhau" name="MatKhau" placeholder="********" required>
            </div>
            <div class="forgot-password text-center">
              <a href="#" data-bs-toggle="modal" data-bs-target="#forgotPasswordModal">Quên mật khẩu?</a>
            </div>
            <button type="submit" class="btn btn-primary w-100 mt-3">Đăng nhập</button>
            <div class="register-link text-center mt-3">
              <p>Chưa có tài khoản?</p>
              <p><a href="registerSinhVien.jsp">Đăng ký cho sinh viên</a></p>
              <p><a href="registerGiangVien.jsp">Đăng ký cho giảng viên</a></p>
              <p><a href="registerNhanVien.jsp">Đăng ký cho nhân viên phòng đào tạo</a></p>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>

  <div class="modal fade" id="forgotPasswordModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <form id="forgotPasswordForm" action="forgot-password" method="post">
          <div class="modal-header">
            <h5 class="modal-title">Khôi phục mật khẩu</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>
          <div class="modal-body">
            <label for="email" class="form-label">Nhập email đã đăng ký</label>
            <input type="email" class="form-control" id="email" name="email" required>
            <div id="emailError" class="alert alert-danger d-none mt-2">Email không tồn tại trong hệ thống.</div>
            <div id="userConfirmation" class="alert alert-info d-none mt-2"></div>
          </div>
          <div class="modal-footer">
            <button type="submit" style="background-color: #0B5ED7;" class="btn btn-orange">Gửi yêu cầu</button>
          </div>
        </form>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
  <script>
    document.getElementById('forgotPasswordForm').addEventListener('submit', function(e) {
      e.preventDefault();
      const email = document.getElementById('email').value;
      fetch('forgot-password?action=verify&email=' + encodeURIComponent(email))
        .then(res => res.json())
        .then(data => {
          const errorEl = document.getElementById('emailError');
          const confirmEl = document.getElementById('userConfirmation');
          if (data.found) {
            errorEl.classList.add('d-none');
            confirmEl.classList.remove('d-none');
            confirmEl.innerHTML = `
              <div class="text-center">
                <p>Đây có phải tài khoản của bạn không?</p>
                <button type="button" class="btn btn-secondary me-2" data-bs-dismiss="modal">Không</button>
                <button type="button" class="btn btn-orange" onclick="proceedReset('${email}')">Đúng</button>
              </div>
            `;
          } else {
            errorEl.classList.remove('d-none');
            confirmEl.classList.add('d-none');
          }
        }).catch(() => {
          document.getElementById('emailError').classList.remove('d-none');
          document.getElementById('userConfirmation').classList.add('d-none');
        });
    });

    function proceedReset(email) {
      const form = document.getElementById('forgotPasswordForm');
      form.submit();
    }
  </script>
</body>
</html>