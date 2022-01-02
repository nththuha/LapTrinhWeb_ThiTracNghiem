<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="/WEB-INF/views/giangvien/include/header.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">
	<div class="card">
		<div class="card-header">
			<label class="font-weight-bold">THÊM/XÓA MÔN HỌC</label>
		</div>
		<div class="card-header">
			<form:form class="row g-5" modelAttribute="mh"
				action="http://localhost:8080/QLTTN/editmonhoc.htm">
				<div class="col-md-2">
					<label class="font-weight-bold">Mã môn học</label>
					<form:input path="maMonHoc" type="text" class="form-control"
						id="exampleFormControlInput1" placeholder="Nhập mã môn học" />
					<form:errors path="maMonHoc"></form:errors>			
				</div> 
				<div class="col-md-4">
					<label class="font-weight-bold">Tên môn học</label>
					<form:input path="tenMonHoc" type="text" class="form-control"
						id="exampleFormControlInput1" placeholder="Nhập tên môn học" />
					<form:errors path="tenMonHoc"></form:errors>			
				</div>

				<div class="col-md-6">
				<hr>
					<button name="${btnStatus}" class="btn btn-primary">Thêm
						môn học</button>
					<p5 class="text-success">${message1}</p5>
					<p5 class="text-danger">${message0}</p5>
				</div>
			</form:form>
		</div>
	</div>

	<HR>
	<!-- On rows -->
	<div class="card">
		<div class="card-header">
			<label class="font-weight-bold">DANH SÁCH MÔN HỌC</label>
		</div>
		<div class="card-header">
			<table class="table">
				<thead class="table-primary">
					<tr>
						<th scope="col">Mã môn học</th>
						<th scope="col">Tên môn học</th>
						<th scope="col">Xóa</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="mh" items="${mhoc}">
						<tr class="table-light">
							<td>${mh.maMonHoc}</td>
							<td>${mh.tenMonHoc}</td>
							<td><a name="btnDelete"
								href="http://localhost:8080/QLTTN/editmonhoc/${mh.maMonHoc}.htm?linkDelete"
								role="button"><img width="31" height="31"
									src="<c:url value='/resources/img/delete.png'/>" /></a>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

<script
	src="<c:url value ='/resources/assets/dist/js/bootstrap.bundle.min.js'/>"></script>

<%@include file="/WEB-INF/views/giangvien/include/footer.jsp"%>