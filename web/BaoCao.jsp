<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.TaiKhoan"%>
<%@page import="model.BaoCaoThongKe"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách báo cáo thống kê</title>
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
            .table th {
                background-color: #003087;
                color: white;
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
            <h2 class="text-center mb-4">Danh sách báo cáo thống kê</h2>

            <div class="mb-4">
                <button class="btn btn-success" onclick="showAddModal()">
                    <i class="fas fa-plus"></i> Thêm báo cáo mới
                </button>
            </div>
            
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>Mã báo cáo</th>
                                    <th>Tên báo cáo</th>
                                    <th>Loại thống kê</th>
                                    <th>Ngày tạo</th>
                            <th>Người tạo</th>
                            <th>Nội dung</th>
                                    <th>Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listBaoCao}" var="baoCao">
                                    <tr>
                                        <td>${baoCao.maBC}</td>
                                        <td>${baoCao.tenBC}</td>
                                        <td>${baoCao.loaiTK}</td>
                                <td><fmt:formatDate value="${baoCao.ngayTao}" pattern="dd/MM/yyyy"/></td>
                                <td>${baoCao.maNguoiTao}</td>
                                        <td>${baoCao.noiDung}</td>
                                        <td>
                                            <a href="baocao?action=view&id=${baoCao.maBC}" class="btn btn-info btn-sm">
                                                <i class="fas fa-eye"></i>
                                    </a>
                                    
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                                </div>
            
                        <div class="mt-4">
                <a href="home.jsp" class="btn btn-secondary">
                    <i class="fas fa-arrow-left"></i> Quay lại
                </a>
            </div>
        </div>

        <!-- Add Modal -->
        <div class="modal fade" id="addModal" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Thêm báo cáo mới</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <form id="addForm" action="baocao" method="POST" class="needs-validation" novalidate>
                            <input type="hidden" name="action" value="add">
                            
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label class="form-label">Mã báo cáo:</label>
                                    <input type="text" class="form-control" name="maBC" required>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label">Tên báo cáo:</label>
                                    <input type="text" class="form-control" name="tenBC" required>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label class="form-label">Loại thống kê:</label>
                                    <select class="form-select" name="loaiTK" required>
                                        <option value="">Chọn loại thống kê</option>
                                        <option value="Đăng ký học phần">Đăng ký học phần</option>
                                        <option value="Sinh viên">Sinh viên</option>
                                        <option value="Lớp học phần">Lớp học phần</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <div class="col-12">
                                    <label class="form-label">Nội dung:</label>
                                    <textarea class="form-control" name="noiDung" rows="4" required></textarea>
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

        <script>
        let addModal;

        document.addEventListener('DOMContentLoaded', function() {
            addModal = new bootstrap.Modal(document.getElementById('addModal'));
            
            // Form validation
            const forms = document.querySelectorAll('.needs-validation');
            Array.from(forms).forEach(form => {
                form.addEventListener('submit', event => {
                    if (!form.checkValidity()) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        });

        function showAddModal() {
            addModal.show();
        }
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
            </body>
</html>