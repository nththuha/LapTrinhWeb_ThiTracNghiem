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
			<label class="font-weight-bold">THÊM/XÓA/SỬA CÂU HỎI</label>
		</div>
		<div class="card-header">
			<form:form class="row g-5" modelAttribute="cauHoi"
				action="http://localhost:8080/QLTTN/editcauhoi.htm">
				<div class="col-md-6">
					<label class="font-weight-bold">Nội dung câu hỏi</label>
					<form:input path="noiDung" type="text" class="form-control"
						id="exampleFormControlInput1" placeholder="Nhập nội dung câu hỏi" />
					<form:errors path="noiDung"></form:errors>		
				</div>
				<div class="col-md-6">
					<label class="font-weight-bold">Phương án A</label>
					<form:input path="a" type="text" class="form-control"
						id="exampleFormControlInput1" placeholder="Nhập phương án A" />
					<form:errors path="a"></form:errors>		
				</div>
				<div class="col-md-6">
					<label class="font-weight-bold">Phương án B</label>
					<form:input path="b" type="text" class="form-control"
						id="exampleFormControlInput1" placeholder="Nhập phương án B" />
					<form:errors path="b"></form:errors>		
				</div>
				<div class="col-md-6">
					<label class="font-weight-bold">Phương án C</label>
					<form:input path="c" type="text" class="form-control"
						id="exampleFormControlInput1" placeholder="Nhập phương án C" />
					<form:errors path="c"></form:errors>		
				</div>
				<div class="col-md-6">
					<label class="font-weight-bold">Phương án D</label>
					<form:input path="d" type="text" class="form-control"
						id="exampleFormControlInput1" placeholder="Nhập phương án D" />
					<form:errors path="d"></form:errors>		
				</div>
				<div class="col-md-6">
					<label class="font-weight-bold">Đáp án</label>
					<form:select path="dapAn" items="${dapAn}" class="form-control"
						aria-label=".form-select-lg example">

					</form:select>
				</div>
				<div class="col-md-6">
					<label class="font-weight-bold">Môn học</label>
					<form:select path="monHoc_CH.maMonHoc" items="${monHoc}"
						itemValue="maMonHoc" itemLabel="tenMonHoc" class="form-control"
						aria-label=".form-select-lg example">
					</form:select>
				</div>
				<div class="col-md-6">
				<hr>
					<button name="${btnStatus}" class="btn btn-primary">Lưu
						câu hỏi</button>
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
			<form:form modelAttribute="cauHoi2" action="http://localhost:8080/QLTTN/editcauhoi.htm">
				<div class="form-inline col-7">
						<form:select path="monHoc_CH.maMonHoc" items="${monHoc}"
							id="searchInput" itemValue="maMonHoc" itemLabel="tenMonHoc"
							style="outline: 1px solid green;" class="form-control"
							aria-label=".form-select-lg example">
						</form:select>
						<div class="col-6">
							<button name="btnSearch" class="btn btn-success">Xem theo môn</button>
						</div>
				</div>
			</form:form>
	</div>
	<div class="card-header">
		<jsp:useBean id="pagedListHolder" scope="request"
			type="org.springframework.beans.support.PagedListHolder" />
		<c:url value="" var="pagedLink">
			<c:param name="p" value="~" />
		</c:url>
		<!-- <div class="card-header">
			<label class="font-weight-bold">&nbsp;&nbsp;&nbsp;DANH SÁCH CÂU HỎI</label>
		</div> -->
		<table class="table">
			<thead class="table-primary">
				<tr>
					<th scope="col">Câu hỏi</th>
					<th scope="col">Môn</th>
					<th scope="col">Chỉnh sửa</th>
					<th scope="col">Xóa</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ch" items="${pagedListHolder.pageList}">
					<tr class="table-light">
						<td>${ch.noiDung}</td>
						<td>${ch.monHoc_CH.tenMonHoc}</td>
						<td><a
							href="http://localhost:8080/QLTTN/editcauhoi/${ch.iDCH}.htm?linkEdit"><img
								width="46" height="36"
								src="<c:url value='/resources/img/edit.png'/>" /></a></td>
						<td><a name="btnDelete"
							href="http://localhost:8080/QLTTN/editcauhoi/${ch.iDCH}.htm?linkDelete"
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

<%@include file="/WEB-INF/views/giangvien/include/footer.jsp"%>