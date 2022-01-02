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
	<!-- On rows -->
	<div class="card">
		<div class="card-header">
			<div class="col">
				<label class="font-weight-bold"><h4>DANH SÁCH SINH VIÊN
						ĐÃ THI</h4></label>
			</div>
			<div class="col">
				<label class="font-weight-bold"><h5>Môn: ${mon}</h5></label>
			</div>
		</div>
		<div class="card-header">
			<jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>
			<div class="card-header">
				<table class="table">
					<thead class="table-primary">
						<tr>
							<th scope="col">Mã sinh viên</th>
							<th scope="col">Họ và tên</th>
							<th scope="col">Môn học</th>
							<th scope="col">Điểm</th>
							<th scope="col">Ngày thi</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="ctt" items="${pagedListHolder.pageList}">
							<tr class="table-light">
								<td>${ctt.sinhVien_CTT.maSV}</td>
								<td>${ctt.sinhVien_CTT.ho} ${ctt.sinhVien_CTT.ten}</td>
								<td>${ctt.monHoc_CTT.tenMonHoc}</td>
								<td>${ctt.diem}</td>
								<td>${ctt.ngayThi}</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
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