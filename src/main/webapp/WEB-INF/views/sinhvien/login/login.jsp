<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> 	
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %> 
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
<link href="<c:url value='resources/vendor/fontawesome-free/css/all.min.css'/>" rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

<!-- Custom styles for this template-->
<link href="<c:url value='resources/css/sb-admin-2.min.css'/>" rel="stylesheet">

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
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col-lg-6 d-none d-lg-block bg-login-image"
								style="background: url(/img/cinema.jpg); background-size: cover">
								<img src="resources/img/sinhvien.jpg" />
							</div>
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">CHÀO MỪNG ĐẾN VỚI
											WEBSITE THI TRẮC NGHIỆM</h1>
									</div>
									<form:form method="post" action="http://localhost:8080/QLTTN/sinhvien/dangnhap.htm" commandName="sinhVien">
										<div class="form-group">
											<form:input path="maSV" type="text"
												class="form-control form-control-user"
												id="exampleInputEmail" placeholder="Nhập mã sinh viên"/>
										</div>
										<div class="form-group">
											<form:input path="matKhau" type="password"
												class="form-control form-control-user"
												id="exampleInputPassword" placeholder="Nhập mật khẩu"/>
										</div>
										<p class="text-danger">${message}</p>
										<div class="form-group">
											<div class="custom-control custom-checkbox small">
												<input type="checkbox" class="custom-control-input"
													id="customCheck"> <label
													class="custom-control-label" for="customCheck">Ghi
													nhớ đăng nhập</label>
											</div>
										</div>
										<button class="btn btn-primary btn-lg btn-block">Đăng nhập</button>
									</form:form>
									<hr>
									<div class="text-center">
										<a class="small" href="sinhvien/matkhau.htm">Quên mật
											khẩu?</a>
									</div>
									<div class="text-center">
										<a class="small" href="http://localhost:8080/QLTTN/login.htm">Đi đến trang của giảng viên</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="<c:url value='resources/vendor/jquery/jquery.min.js'/>"></script>
	
	<script src="<c:url value='resources/vendor/bootstrap/js/bootstrap.bundle.min.js'/>"></script>

	<!-- Core plugin JavaScript-->
	<script src="<c:url value='resources/vendor/jquery-easing/jquery.easing.min.js'/>"></script>

	<!-- Custom scripts for all pages-->
	<script src="<c:url value='resources/js/sb-admin-2.min.js'/>"></script>
	
	<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
</body>

</body>

</html>