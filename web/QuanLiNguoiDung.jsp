
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Quản Lí Người Dùng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .table-container {
            margin: 20px;
            padding: 20px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .section-title {
            color: #003087;
            margin-bottom: 20px;
            padding-bottom: 10px;
            border-bottom: 2px solid #003087;
        }
        .table {
            margin-bottom: 30px;
        }
        .table thead {
            background-color: #003087;
            color: white;
        }
        .back-button {
            margin: 20px;
            padding: 10px 20px;
            background-color: #003087;
            color: white;
            border: none;
            border-radius: 5px;
            text-decoration: none;
        }
        .back-button:hover {
            background-color: #002470;
            color: white;
        }
        .edit-button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
        }
        .edit-button:hover {
            background-color: #0056b3;
        }
        .delete-button {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
            margin-left: 5px;
        }
        .delete-button:hover {
            background-color: #c82333;
        }
        .search-container {
            margin: 20px;
            display: flex;
            gap: 10px;
            align-items: center;
        }
        .reset-button {
            background-color: #6c757d;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
        }
        .reset-button:hover {
            background-color: #5a6268;
            color: white;
        }
    </style>
</head>
<body>
<div class="container">
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>
    <a href="home.jsp" class="back-button">Quay lại</a>

    <!-- Search Form -->
    <div class="search-container">
        <form action="quanlinguoidung" method="get" class="d-flex">
            <input type="text" class="form-control" name="searchName" placeholder="Nhập tên để tìm kiếm" value="${searchName}">
            <button type="submit" class="btn btn-primary ms-2">Tìm kiếm</button>
        </form>
        <a href="quanlinguoidung" class="reset-button">Xem tất cả</a>
    </div>

    <div class="table-container">
        <h2 class="section-title">Danh sách Sinh viên</h2>
        <button class="btn btn-success mb-3" onclick="openAddModal('sinhvien')">Thêm Sinh viên</button>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Họ Tên</th>
                <th>Ngày Sinh</th>
                <th>Giới Tính</th>
                <th>Email</th>
                <th>Số Điện Thoại</th>
                <th>Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sinhViens}" var="sv">
                <tr>
                    <td>${sv.hoTen}</td>
                    <td><fmt:formatDate value="${sv.ngaySinh}" pattern="yyyy-MM-dd"/></td>
                    <td>${sv.gioiTinh}</td>
                    <td>${sv.email}</td>
                    <td>${sv.sdt}</td>
                    <td>
                        <button class="edit-button" onclick="openEditModal('sinhvien', '${sv.maSV}', '${sv.hoTen}', '<fmt:formatDate value="${sv.ngaySinh}" pattern="yyyy-MM-dd"/>', '${sv.gioiTinh}', '${sv.email}', '${sv.sdt}')">Sửa</button>
                        <button class="delete-button" onclick="confirmDelete('sinhvien', '${sv.maSV}')">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="table-container">
        <h2 class="section-title">Danh sách Giảng viên</h2>
        <button class="btn btn-success mb-3" onclick="openAddModal('giangvien')">Thêm Giảng viên</button>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Họ Tên</th>
                <th>Ngày Sinh</th>
                <th>Giới Tính</th>
                <th>Email</th>
                <th>Số Điện Thoại</th>
                <th>Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${giangViens}" var="gv">
                <tr>
                    <td>${gv.hoTen}</td>
                    <td><fmt:formatDate value="${gv.ngaySinh}" pattern="yyyy-MM-dd"/></td>
                    <td>${gv.gioiTinh}</td>
                    <td>${gv.email}</td>
                    <td>${gv.sdt}</td>
                    <td>
                        <button class="edit-button" onclick="openEditModal('giangvien', '${gv.maGV}', '${gv.hoTen}', '<fmt:formatDate value="${gv.ngaySinh}" pattern="yyyy-MM-dd"/>', '${gv.gioiTinh}', '${gv.email}', '${gv.sdt}')">Sửa</button>
                        <button class="delete-button" onclick="confirmDelete('giangvien', '${gv.maGV}')">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="table-container">
        <h2 class="section-title">Danh sách Nhân viên phòng đào tạo</h2>
        <button class="btn btn-success mb-3" onclick="openAddModal('nhanvien')">Thêm Nhân viên</button>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Họ Tên</th>
                <th>Ngày Sinh</th>
                <th>Giới Tính</th>
                <th>Email</th>
                <th>Số Điện Thoại</th>
                <th>Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${nhanViens}" var="nv">
                <tr>
                    <td>${nv.hoTen}</td>
                    <td><fmt:formatDate value="${nv.ngaySinh}" pattern="yyyy-MM-dd"/></td>
                    <td>${nv.gioiTinh}</td>
                    <td>${nv.email}</td>
                    <td>${nv.sdt}</td>
                    <td>
                        <button class="edit-button" onclick="openEditModal('nhanvien', '${nv.maNV}', '${nv.hoTen}', '<fmt:formatDate value="${nv.ngaySinh}" pattern="yyyy-MM-dd"/>', '${nv.gioiTinh}', '${nv.email}', '${nv.sdt}')">Sửa</button>
                        <button class="delete-button" onclick="confirmDelete('nhanvien', '${nv.maNV}')">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Edit Modal -->
    <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabel">Sửa Thông Tin</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="editForm" action="quanlinguoidung" method="post">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="userType" id="userType">
                        <input type="hidden" name="id" id="id">
                        <div class="mb-3">
                            <label for="hoTen" class="form-label">Họ Tên</label>
                            <input type="text" class="form-control" id="hoTen" name="hoTen" required>
                        </div>
                        <div class="mb-3">
                            <label for="ngaySinh" class="form-label">Ngày Sinh</label>
                            <input type="date" class="form-control" id="ngaySinh" name="ngaySinh" required>
                        </div>
                        <div class="mb-3">
                            <label for="gioiTinh" class="form-label">Giới Tính</label>
                            <select class="form-control" id="gioiTinh" name="gioiTinh" required>
                                <option value="Nam">Nam</option>
                                <option value="Nữ">Nữ</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="mb-3">
                            <label for="sdt" class="form-label">Số Điện Thoại</label>
                            <input type="text" class="form-control" id="sdt" name="sdt" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Lưu</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Add Modal -->
    <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addModalLabel">Thêm người dùng mới</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="addForm" action="quanlinguoidung" method="post">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="userType" id="addUserType">
                        <div class="mb-3">
                            <label for="addId" class="form-label">Mã số</label>
                            <input type="text" class="form-control" id="addId" name="id" required>
                        </div>
                        <div class="mb-3">
                            <label for="addHoTen" class="form-label">Họ Tên</label>
                            <input type="text" class="form-control" id="addHoTen" name="hoTen" required>
                        </div>
                        <div class="mb-3">
                            <label for="addNgaySinh" class="form-label">Ngày Sinh</label>
                            <input type="date" class="form-control" id="addNgaySinh" name="ngaySinh" required>
                        </div>
                        <div class="mb-3">
                            <label for="addGioiTinh" class="form-label">Giới Tính</label>
                            <select class="form-control" id="addGioiTinh" name="gioiTinh" required>
                                <option value="Nam">Nam</option>
                                <option value="Nữ">Nữ</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="addEmail" class="form-label">Email</label>
                            <input type="email" class="form-control" id="addEmail" name="email" required>
                        </div>
                        <div class="mb-3">
                            <label for="addSdt" class="form-label">Số Điện Thoại</label>
                            <input type="text" class="form-control" id="addSdt" name="sdt" required>
                        </div>
                        <div id="extraFields">
                            <!-- Additional fields will be added dynamically -->
                        </div>
                        <div class="mb-3">
                            <label for="addMatKhau" class="form-label">Mật khẩu</label>
                            <input type="password" class="form-control" id="addMatKhau" name="matKhau" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Thêm</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function openEditModal(userType, id, hoTen, ngaySinh, gioiTinh, email, sdt) {
        document.getElementById('userType').value = userType;
        document.getElementById('id').value = id;
        document.getElementById('hoTen').value = hoTen;
        document.getElementById('ngaySinh').value = ngaySinh;
        document.getElementById('gioiTinh').value = gioiTinh;
        document.getElementById('email').value = email;
        document.getElementById('sdt').value = sdt;

        var modal = new bootstrap.Modal(document.getElementById('editModal'));
        modal.show();
    }

    function confirmDelete(userType, id) {
        if (confirm('Bạn có chắc chắn muốn xóa người dùng này?')) {
            const form = document.createElement('form');
            form.method = 'POST';
            form.action = 'quanlinguoidung';

            const actionInput = document.createElement('input');
            actionInput.type = 'hidden';
            actionInput.name = 'action';
            actionInput.value = 'delete';

            const userTypeInput = document.createElement('input');
            userTypeInput.type = 'hidden';
            userTypeInput.name = 'userType';
            userTypeInput.value = userType;

            const idInput = document.createElement('input');
            idInput.type = 'hidden';
            idInput.name = 'id';
            idInput.value = id;

            form.appendChild(actionInput);
            form.appendChild(userTypeInput);
            form.appendChild(idInput);

            document.body.appendChild(form);
            form.submit();
        }
    }

    function openAddModal(userType) {
        document.getElementById('addUserType').value = userType;
        const extraFields = document.getElementById('extraFields');
        extraFields.innerHTML = '';

        if (userType === 'sinhvien') {
            extraFields.innerHTML = `
                <div class="mb-3">
                    <label for="addDiaChi" class="form-label">Địa Chỉ</label>
                    <input type="text" class="form-control" id="addDiaChi" name="diaChi" required>
                </div>
                <div class="mb-3">
                    <label for="addLopHC" class="form-label">Lớp Hành Chính</label>
                    <input type="text" class="form-control" id="addLopHC" name="lopHC" required>
                </div>
                <div class="mb-3">
                    <label for="addTinhTrang" class="form-label">Tình Trạng</label>
                    <input type="text" class="form-control" id="addTinhTrang" name="tinhTrang" required>
                </div>
            `;
        } else if (userType === 'giangvien') {
            extraFields.innerHTML = `
                <div class="mb-3">
                    <label for="addHocVi" class="form-label">Học Vị</label>
                    <input type="text" class="form-control" id="addHocVi" name="hocVi" required>
                </div>
                <div class="mb-3">
                    <label for="addKhoa" class="form-label">Khoa</label>
                    <input type="text" class="form-control" id="addKhoa" name="khoa" required>
                </div>
            `;
        } else if (userType === 'nhanvien') {
            extraFields.innerHTML = `
                <div class="mb-3">
                    <label for="addChucVu" class="form-label">Chức Vụ</label>
                    <input type="text" class="form-control" id="addChucVu" name="chucVu" required>
                </div>
                <div class="mb-3">
                    <label for="addPhongLV" class="form-label">Phòng Làm Việc</label>
                    <input type="text" class="form-control" id="addPhongLV" name="phongLV" required>
                </div>
            `;
        }

        var modal = new bootstrap.Modal(document.getElementById('addModal'));
        modal.show();
    }
</script>
</body>
</html>