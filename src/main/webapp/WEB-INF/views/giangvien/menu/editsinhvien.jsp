<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="/WEB-INF/views/giangvien/include/header.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">
	<div class="card">
		<div class="card-header">
			<label class="font-weight-bold">THÊM/XÓA/SỬA SINH VIÊN</label>
		</div>
		<div class="card-header">
			<form:form class="row g-3" modelAttribute="sinhVien"
				action="http://localhost:8080/QLTTN/editsinhvien.htm">
				<div class="col-md-2">
					<label class="font-weight-bold">Mã sinh viên</label>
					<form:input path="maSV" type="text" class="form-control"
						id="exampleFormControlInput1" placeholder="Nhập mã sinh viên" />
					<form:errors path="maSV"></form:errors>		
				</div>
				<div class="col-md-2">
					<label class="font-weight-bold">Họ</label>
					<form:input path="ho" type="text" class="form-control"
						id="exampleFormControlInput1" placeholder="Nhập họ sinh viên" />
					<form:errors path="ho"></form:errors>		
				</div>
				<div class="col-md-2">
					<label class="font-weight-bold">Tên</label>
					<form:input path="ten" type="text" class="form-control"
						id="exampleFormControlInput1" placeholder="Nhập tên sinh viên" />
					<form:errors path="ten"></form:errors>		
				</div>
				<div class="col-md-2">
					<label class="font-weight-bold">Email</label>
					<form:input path="email" type="text" class="form-control"
						id="exampleFormControlInput1" placeholder="Nhập email" />
					<form:errors path="email"></form:errors>		
				</div>
				<div class="col-md-2">
					<label class="font-weight-bold">Mật khẩu</label>
					<form:input path="matKhau" type="text" class="form-control"
						id="exampleFormControlInput1" placeholder="Nhập mật khẩu" />
					<form:errors path="matKhau"></form:errors>		
				</div>
				<div class="col-md-12">
					<hr>
				</div>
				<div class="col-5">
					<button name="${btnStatus}" class="btn btn-primary">Lưu
						sinh viên</button>
					<p5 class="text-success">${message1}</p5>
					<p5 class="text-danger">${message0}</p5>
				</div>
				<div class="form-inline col-5">
					<hr>
					<span id="result1"></span>

					<form class="form-inline">
						<input name="searchInput" id="searchInput" style="outline: 1px solid green;" class="form-control"
							type="search" placeholder="Tìm sinh viên" aria-label="Search">
						<div class="col-md-2">
							<button name="btnsearch" id="searchProduct"
								class="btn btn-success" type="submit">Tìm</button>
						</div>
					</form>
				</div>
			</form:form>
		</div>
	</div>

	<hr>
	<!-- On rows -->
	<div class="card">
		<div class="card-header">
			<label class="font-weight-bold">DANH SÁCH SINH VIÊN</label>
		</div>
		<div class="card-header">
			<jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>
			<table class="table">
				<thead class="table-primary">
					<tr>
						<th scope="col">Họ</th>
						<th scope="col">Tên</th>
						<th scope="col">Email</th>
						<th scope="col">Chỉnh sửa</th>
						<th scope="col">Xóa</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="sv" items="${pagedListHolder.pageList}">
						<tr class="table-light">
							<td>${sv.ho}</td>
							<td>${sv.ten}</td>
							<td>${sv.email}</td>
							<td><a
								href="http://localhost:8080/QLTTN/editsinhvien/${sv.maSV}.htm?linkEdit"><img
									width="46" height="36"
									src="<c:url value='/resources/img/edit.png'/>" /></a></td>
							<td><a name="btnDelete"
								href="http://localhost:8080/QLTTN/editsinhvien/${sv.maSV}.htm?linkDelete"
								role="button"><img width="31" height="31"
									src="<c:url value='/resources/img/delete.png'/>" /></a>
						</tr>
					</c:forEach>

				</tbody>
			</table>
			<tg:paging pagedListHolder="${pagedListHolder}"
				pagedLink="${pagedLink}" />
		</div>
	</div>
</div>

<script
	src="<c:url value ='/resources/assets/dist/js/bootstrap.bundle.min.js'/>"></script>

<script>
	function searchValue() {
		//event.preventDefault();
		var searchProductname = $("#searchInput").val();
		alert(searchProductname)
	}
</script>

<%@include file="/WEB-INF/views/giangvien/include/footer.jsp"%>