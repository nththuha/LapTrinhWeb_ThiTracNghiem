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
	<div div class="card">
		<div div class="card-header">
			<h3>Chọn môn học để xem điểm thi</h3>
			<form:form class="row g-3" modelAttribute="MHOC"
				action="http://localhost:8080/QLTTN/chitietthi2.htm">
				<div class="form-inline col-10">
					<form:select path="maMonHoc" items="${monHoc}" itemValue="maMonHoc"
						itemLabel="tenMonHoc" class="form-control"
						aria-label=".form-select-lg example">
					</form:select>
					<div class="col-6">
						<button name="btnSearch" class="btn btn-success">Xem</button>
					</div>
				</div>

			</form:form>
			<hr>
			<p5 class="text-danger">${message}</p5>
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