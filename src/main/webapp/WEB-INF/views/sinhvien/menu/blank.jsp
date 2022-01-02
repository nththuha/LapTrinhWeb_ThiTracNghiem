<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Đăng nhập - Sinh viên</title>

<!-- Custom fonts for this template-->
<link
	href="<c:url value='/resources/vendor/fontawesome-free/css/all.min.css'/>"
	rel="stylesheet" type="text/css">
<link
	href="<c:url value='https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i'/>" 
	 rel="stylesheet">

<!-- Custom styles for this template-->
<link href="<c:url value='/resources/css/sb-admin-2.min.css'/>" 
	  rel="stylesheet">
	
	<link rel="stylesheet"
	href="<c:url value='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css'/>"
	 integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>

<body class="bg-gradient-primary">

	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<div class="card-header">
							<h1 class="h8 text-gray-900 mb-1">Xin chào ${tensinhvien}</h1>
						</div>
						<hr>
						<div class="card-header">
							<form:form class="form-inline" action="http://localhost:8080/QLTTN/sinhvien/thi.htm"
								modelAttribute="mh">
								<div class="form-group mb-2">
									<label class="font-weight-bold">Chọn môn thi:</label>
								</div>
								<div class="col-md-4">
									<form:select path="maMonHoc" items="${monHoc}"
										itemValue="maMonHoc" itemLabel="tenMonHoc"
										class="custom-select custom-select-sm"
										aria-label=".form-select-lg example">
									</form:select>
								</div>
								<button class="btn btn-success mb-2">BẮT ĐẦU LUYỆN THI</button>
								<div class="col-md-4">
								<a class="btn btn-danger mb-2" href="http://localhost:8080/QLTTN/sinhvien/dangxuat.htm" role="button">ĐĂNG XUẤT</a>
								</div>
								<p class="text-danger">${message}</p>
							</form:form>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<!-- Bootstrap core JavaScript-->
<script src="<c:url value='resources/vendor/jquery/jquery.min.js'/>"></script>

<script
	src="<c:url value='resources/vendor/bootstrap/js/bootstrap.bundle.min.js'/>"></script>

<!-- Core plugin JavaScript-->
<script
	src="<c:url value='resources/vendor/jquery-easing/jquery.easing.min.js'/>"></script>

<!-- Custom scripts for all pages-->
<script src="<c:url value='resources/js/sb-admin-2.min.js'/>"></script>

<script
	src="<c:url value='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js'/>" 
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
</body>

</body>

</html>