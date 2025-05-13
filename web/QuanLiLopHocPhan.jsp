<!-- filepath: c:\Users\pc\Desktop\N5\N5\web\QuanLiLopHocPhan.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.TaiKhoan"%>
<%@page import="java.util.List"%>
<%@page import="model.LopHocPhan"%>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lí lớp học phần</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <style>
            body {
                background-color: #f5f5f5;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }
            .container {
                margin-top: 20px;
                background-color: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            .search-box {
                margin-bottom: 20px;
            }
            .table {
                background-color: white;
            }
            .table th {
                background-color: #003087;
                color: white;
            }
            .btn-action {
                margin: 2px;
            }
.modal-lg {
                max-width: 900px;
            }
        </style>
    </head>
    <body>
        <%
            TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("SessionLogin");
            if (taiKhoan == null || !"3".equals(taiKhoan.getQuyen())) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>

        <div class="container">
            <h2 class="text-center mb-4">Quản lí lớp học phần</h2>

            <!-- Search Form -->
            <div class="search-box">
                <form class="row g-3" action="quanlilophocphan" method="GET">
                    <div class="col-md-4">
                        <input type="text" class="form-control" name="search" 
                               value="${param.search}" placeholder="Tìm kiếm theo mã hoặc tên lớp học phần...">
                    </div>
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-search"></i> Tìm kiếm
                        </button>
                    </div>
                                    </form>
<div class="text-end mt-2">
                    <button class="btn btn-success" onclick="showAddModal()">
                        <i class="fas fa-plus"></i> Thêm mới lớp học phần
                    </button>
                </div>
            </div>

            <!-- Data Table -->
            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>Mã LHP</th>
                            <th>Tên HP</th>
                            <th>Giảng viên</th>
                            <th>Số TC</th>
                            <th>Thời gian học</th>
                            <th>Phòng học</th>
                            <th>Học kỳ</th>
                            <th>Năm học</th>
                            <th>SL SVTĐ</th>
                            <th>Mốc ĐK</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listLHP}" var="lhp">
                            <tr data-id="${lhp.maLHP}">
                                <td>${lhp.maLHP}</td>
                                <td>${lhp.tenHP}</td>
                                <td>${lhp.tenGV}</td>
                                <td>${lhp.soTC}</td>
                                <td>${lhp.thoiGianHoc}</td>
                                <td>${lhp.phongHoc}</td>
                                <td>${lhp.hocKy}</td>
                                <td>${lhp.namHoc}</td>
                                <td>${lhp.soLuongSVTD}</td>
                                <td>${lhp.mocDK}</td>
                                <td>
                                    <button onclick="editLHP('${lhp.maLHP}')" class="btn btn-warning btn-sm">
                                        <i class="fas fa-edit"></i>
                                    </button>
<button onclick="deleteLHP('${lhp.maLHP}')" class="btn btn-danger btn-sm">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- Add Modal -->
            <div class="modal fade" id="addModal" tabindex="-1">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Thêm lớp học phần mới</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body">
                            <form id="addForm" action="quanlilophocphan" method="POST" class="needs-validation" novalidate>
                                <input type="hidden" name="action" value="add">
                                
                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label class="form-label">Mã lớp học phần:</label>
                                        <input type="text" class="form-control" name="maLHP" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="form-label">Tên học phần:</label>
                                        <input type="text" class="form-control" name="tenHP" required>
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label class="form-label">Giảng viên:</label>
                                        <input type="text" class="form-control" name="tenGV" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="form-label">Mã môn học:</label>
                                        <input type="text" class="form-control" name="maMH" required>
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label class="form-label">Học kỳ:</label>
                                        <input type="text" class="form-control" name="hocKy" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="form-label">Năm học:</label>
                                        <input type="text" class="form-control" name="namHoc" required>
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label class="form-label">Số lượng SV tối đa:</label>
                                        <input type="number" class="form-control" name="soLuongSVTD" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="form-label">Mốc đăng ký:</label>
                                        <input type="date" class="form-control" name="mocDK" required>
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label class="form-label">Thời gian học:</label>
                                        <input type="text" class="form-control" name="thoiGianHoc" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="form-label">Phòng học:</label>
                                        <input type="text" class="form-control" name="phongHoc" required>
                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                    <button type="submit" class="btn btn-primary">Thêm mới</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Edit Modal -->
            <div class="modal fade" id="editModal" tabindex="-1">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Chỉnh sửa lớp học phần</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body">
                            <form id="editForm" action="quanlilophocphan" method="POST" class="needs-validation" novalidate>
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="maLHP" id="editMaLHP">
                                
                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label class="form-label">Tên học phần:</label>
                                        <input type="text" class="form-control" name="tenHP" id="editTenHP" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="form-label">Giảng viên:</label>
                                        <input type="text" class="form-control" name="tenGV" id="editTenGV" required>
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label class="form-label">Mã môn học:</label>
                                        <input type="text" class="form-control" name="maMH" id="editMaMH" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="form-label">Học kỳ:</label>
                                        <input type="text" class="form-control" name="hocKy" id="editHocKy" required>
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label class="form-label">Năm học:</label>
                                        <input type="text" class="form-control" name="namHoc" id="editNamHoc" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="form-label">Số lượng SV tối đa:</label>
                                        <input type="number" class="form-control" name="soLuongSVTD" id="editSoLuongSVTD" required>
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label class="form-label">Thời gian học:</label>
                                        <input type="text" class="form-control" name="thoiGianHoc" id="editThoiGianHoc" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="form-label">Phòng học:</label>
                                        <input type="text" class="form-control" name="phongHoc" id="editPhongHoc" required>
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label class="form-label">Mốc đăng ký:</label>
                                        <input type="date" class="form-control" name="mocDK" id="editMocDK" required>
                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                    <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Delete Confirmation Modal -->
            <div class="modal fade" id="deleteModal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Xác nhận xóa</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body">
                            <p>Bạn có chắc chắn muốn xóa lớp học phần này không?</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                            <button type="button" class="btn btn-danger" id="confirmDelete">Xóa</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Success Toast -->
            <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
                <div id="successToast" class="toast" role="alert">
                    <div class="toast-header bg-success text-white">
                        <strong class="me-auto">Thông báo</strong>
                        <button type="button" class="btn-close" data-bs-dismiss="toast"></button>
                    </div>
                    <div class="toast-body">
                        Xóa lớp học phần thành công!
                    </div>
                </div>
            </div>

            <!-- Error Toast -->
            <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
                <div id="errorToast" class="toast" role="alert">
                    <div class="toast-header bg-danger text-white">
                        <strong class="me-auto">Lỗi</strong>
                        <button type="button" class="btn-close" data-bs-dismiss="toast"></button>
                    </div>
                    <div class="toast-body" id="errorMessage">
                        Có lỗi xảy ra khi xóa lớp học phần!
                    </div>
                </div>
            </div>

            <!-- Navigation buttons -->
            <div class="mt-3">
                <a href="home.jsp" class="btn btn-secondary">
                    <i class="fas fa-arrow-left"></i> Quay lại
                </a>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
                        let deleteModal;
            let successToast;
            let errorToast;
            let deleteId = null;
let addModal;
let editModal;

            // Initialize components after DOM is loaded
            document.addEventListener('DOMContentLoaded', function() {
                deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
                successToast = new bootstrap.Toast(document.getElementById('successToast'));
                errorToast = new bootstrap.Toast(document.getElementById('errorToast'));
addModal = new bootstrap.Modal(document.getElementById('addModal'));
editModal = new bootstrap.Modal(document.getElementById('editModal'));
            });
            
            function deleteLHP(maLHP) {
                deleteId = maLHP;
if (deleteModal) {
                deleteModal.show();
} else {
                    console.error('Delete modal not initialized');
                }
}

function showAddModal() {
    addModal.show();
            }

function editLHP(maLHP) {
    // Gọi API để lấy thông tin lớp học phần
    fetch('quanlilophocphan?action=edit&id=' + maLHP)
        .then(response => response.json())
        .then(data => {
            // Điền dữ liệu vào form
            document.getElementById('editMaLHP').value = data.maLHP;
            document.getElementById('editTenHP').value = data.tenHP;
            document.getElementById('editTenGV').value = data.tenGV;
            document.getElementById('editMaMH').value = data.maMH;
            document.getElementById('editHocKy').value = data.hocKy;
            document.getElementById('editNamHoc').value = data.namHoc;
            document.getElementById('editSoLuongSVTD').value = data.soLuongSVTD;
            document.getElementById('editThoiGianHoc').value = data.thoiGianHoc;
            document.getElementById('editPhongHoc').value = data.phongHoc;
            document.getElementById('editMocDK').value = data.mocDK;
            
            // Hiển thị modal
            editModal.show();
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('errorMessage').textContent = 'Có lỗi xảy ra khi tải dữ liệu!';
            errorToast.show();
        });
}

// Xử lý submit form edit
document.getElementById('editForm').addEventListener('submit', function(e) {
    e.preventDefault();
    
    const formData = new FormData(this);
    const data = {};
    formData.forEach((value, key) => data[key] = value);

    fetch('quanlilophocphan', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams(formData)
    })
    .then(response => {
        if (response.ok) {
            editModal.hide();
            window.location.reload();
        } else {
            throw new Error('Không thể cập nhật lớp học phần');
        }
    })
    .catch(error => {
        document.getElementById('errorMessage').textContent = error.message;
        errorToast.show();
    });
});

// Delete confirmation handler
            document.getElementById('confirmDelete').addEventListener('click', function() {
                if (!deleteId) return;

                    fetch('quanlilophocphan?action=delete&id=' + deleteId)
                    .then(response => response.text())
                    .then(text => {
                        let data;
                        try {
                            data = JSON.parse(text);
                        } catch (e) {
                            console.error('Error parsing JSON:', text);
                            throw new Error('Invalid JSON response');
                        }
                        
                        deleteModal.hide();

                        if (data.success) {
                                                        // Show success toast and reload page after 1 second
                            successToast.show();
                            setTimeout(() => {
                                window.location.reload();
                            }, 1000);
                        } else {
                            throw new Error(data.message || 'Không thể xóa lớp học phần');
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        deleteModal.hide();
                        document.getElementById('errorMessage').textContent = 
error.message || 'Có lỗi xảy ra khi xóa lớp học phần!';
                        errorToast.show();
                    });
                            });
        </script>
    </body>
</html>