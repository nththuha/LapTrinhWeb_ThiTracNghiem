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

<title>Trắc nghiệm</title>

<!-- Custom fonts for this template-->
<link
	href="<c:url value='/resources/vendor/fontawesome-free/css/all.min.css'/>"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
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
						<div class="card text-white bg-success mb-3"
								style="max-width: 60rem;">
								<div class="card-body">
									<div class="form-group d-flex justify-content-center visible">
										<h3 class="card-title">KẾT QUẢ CỦA BẠN: ${diem} ĐIỂM</h3>
									</div>
								</div>
							</div>
						<div class="card-body">
							<div class="card-header">
								<h4>Chi tiết:</h4>
							</div>
							<div class="card-header">
								<p class="font-weight-bold">Câu 1: ${nd0}</p>
								<div class="form-check">
									<p>${a0}</p>
									<p class="text-success">${a0d}</p>
									<p class="text-danger">${a0s}</p>
								</div>
								<div class="form-check">
									<p>${b0}</p>
									<p class="text-success">${b0d}</p>
									<p class="text-danger">${b0s}</p>
								</div>
								<div class="form-check">
									<p>${c0}</p>
									<p class="text-success">${c0d}</p>
									<p class="text-danger">${c0s}</p>
								</div>
								<div class="form-check">
									<p>${d0}</p>
									<p class="text-success">${d0d}</p>
									<p class="text-danger">${d0s}</p>
								</div>
							</div>
							<div class="card-header">
								<p class="font-weight-bold">Câu 2: ${nd1}</p>
								<div class="form-check">
									<p>${a1}</p>
									<p class="text-success">${a1d}</p>
									<p class="text-danger">${a1s}</p>
								</div>
								<div class="form-check">
									<p>${b1}</p>
									<p class="text-success">${b1d}</p>
									<p class="text-danger">${b1s}</p>
								</div>
								<div class="form-check">
									<p>${c1}</p>
									<p class="text-success">${c1d}</p>
									<p class="text-danger">${c1s}</p>
								</div>
								<div class="form-check">
									<p>${d1}</p>
									<p class="text-success">${d1d}</p>
									<p class="text-danger">${d1s}</p>
								</div>
							</div>
							<div class="card-header">
								<p class="font-weight-bold">Câu 3: ${nd2}</p>
								<div class="form-check">
									<p>${a2}</p>
									<p class="text-success">${a2d}</p>
									<p class="text-danger">${a2s}</p>
								</div>
								<div class="form-check">
									<p>${b2}</p>
									<p class="text-success">${b2d}</p>
									<p class="text-danger">${b2s}</p>
								</div>
								<div class="form-check">
									<p>${c2}</p>
									<p class="text-success">${c2d}</p>
									<p class="text-danger">${c2s}</p>
								</div>
								<div class="form-check">
									<p>${d2}</p>
									<p class="text-success">${d2d}</p>
									<p class="text-danger">${d2s}</p>
								</div>
							</div>
							<div class="card-header">
								<p class="font-weight-bold">Câu 4: ${nd3}</p>
								<div class="form-check">
									<p>${a3}</p>
									<p class="text-success">${a3d}</p>
									<p class="text-danger">${a3s}</p>
								</div>
								<div class="form-check">
									<p>${b3}</p>
									<p class="text-success">${b3d}</p>
									<p class="text-danger">${b3s}</p>
								</div>
								<div class="form-check">
									<p>${c3}</p>
									<p class="text-success">${c3d}</p>
									<p class="text-danger">${c3s}</p>
								</div>
								<div class="form-check">
									<p>${d3}</p>
									<p class="text-success">${d3d}</p>
									<p class="text-danger">${d3s}</p>
								</div>
							</div>
							<div class="card-header">
								<p class="font-weight-bold">Câu 5: ${nd4}</p>
								<div class="form-check">
									<p>${a4}</p>
									<p class="text-success">${a4d}</p>
									<p class="text-danger">${a4s}</p>
								</div>
								<div class="form-check">
									<p>${b4}</p>
									<p class="text-success">${b4d}</p>
									<p class="text-danger">${b4s}</p>
								</div>
								<div class="form-check">
									<p>${c4}</p>
									<p class="text-success">${c4d}</p>
									<p class="text-danger">${c4s}</p>
								</div>
								<div class="form-check">
									<p>${d4}</p>
									<p class="text-success">${d4d}</p>
									<p class="text-danger">${d4s}</p>
								</div>
							</div>
							<div class="card-header">
								<p class="font-weight-bold">Câu 6: ${nd5}</p>
								<div class="form-check">
									<p>${a5}</p>
									<p class="text-success">${a5d}</p>
									<p class="text-danger">${a5s}</p>
								</div>
								<div class="form-check">
									<p>${b5}</p>
									<p class="text-success">${b5d}</p>
									<p class="text-danger">${b5s}</p>
								</div>
								<div class="form-check">
									<p>${c5}</p>
									<p class="text-success">${c5d}</p>
									<p class="text-danger">${c5s}</p>
								</div>
								<div class="form-check">
									<p>${d5}</p>
									<p class="text-success">${d5d}</p>
									<p class="text-danger">${d5s}</p>
								</div>
							</div>
							<div class="card-header">
								<p class="font-weight-bold">Câu 7: ${nd6}</p>
								<div class="form-check">
									<p>${a6}</p>
									<p class="text-success">${a6d}</p>
									<p class="text-danger">${a6s}</p>
								</div>
								<div class="form-check">
									<p>${b6}</p>
									<p class="text-success">${b6d}</p>
									<p class="text-danger">${b6s}</p>
								</div>
								<div class="form-check">
									<p>${c6}</p>
									<p class="text-success">${c6d}</p>
									<p class="text-danger">${c6s}</p>
								</div>
								<div class="form-check">
									<p>${d6}</p>
									<p class="text-success">${d6d}</p>
									<p class="text-danger">${d6s}</p>
								</div>
							</div>
							<div class="card-header">
								<p class="font-weight-bold">Câu 8: ${nd7}</p>
								<div class="form-check">
									<p>${a7}</p>
									<p class="text-success">${a7d}</p>
									<p class="text-danger">${a7s}</p>
								</div>
								<div class="form-check">
									<p>${b7}</p>
									<p class="text-success">${b7d}</p>
									<p class="text-danger">${b7s}</p>
								</div>
								<div class="form-check">
									<p>${c7}</p>
									<p class="text-success">${c7d}</p>
									<p class="text-danger">${c7s}</p>
								</div>
								<div class="form-check">
									<p>${d7}</p>
									<p class="text-success">${d7d}</p>
									<p class="text-danger">${d7s}</p>
								</div>
							</div>
							<div class="card-header">
								<p class="font-weight-bold">Câu 9: ${nd8}</p>
								<div class="form-check">
									<p>${a8}</p>
									<p class="text-success">${a8d}</p>
									<p class="text-danger">${a8s}</p>
								</div>
								<div class="form-check">
									<p>${b8}</p>
									<p class="text-success">${b8d}</p>
									<p class="text-danger">${b8s}</p>
								</div>
								<div class="form-check">
									<p>${c8}</p>
									<p class="text-success">${c8d}</p>
									<p class="text-danger">${c8s}</p>
								</div>
								<div class="form-check">
									<p>${d8}</p>
									<p class="text-success">${d8d}</p>
									<p class="text-danger">${d8s}</p>
								</div>
							</div>
							<div class="card-header">
								<p class="font-weight-bold">Câu 10: ${nd9}</p>
								<div class="form-check">
									<p>${a9}</p>
									<p class="text-success">${a9d}</p>
									<p class="text-danger">${a9s}</p>
								</div>
								<div class="form-check">
									<p>${b9}</p>
									<p class="text-success">${b9d}</p>
									<p class="text-danger">${b9s}</p>
								</div>
								<div class="form-check">
									<p>${c9}</p>
									<p class="text-success">${c9d}</p>
									<p class="text-danger">${c9s}</p>
								</div>
								<div class="form-check">
									<p>${d9}</p>
									<p class="text-success">${d9d}</p>
									<p class="text-danger">${d9s}</p>
								</div>
							</div>
							<div class="card-header">
								<form action="http://localhost:8080/QLTTN/sinhvien/dangnhap.htm">
									<div class="form-group d-flex justify-content-center visible">
										<button class="btn btn-danger btn-lg">Thi lại</button>
									</div>
								</form>
							</div>
						</div>

					</div>
				</div>

			</div>
		</div>
	</div>
</body>

<!-- Bootstrap core JavaScript-->
<script src="<c:url value='/resources/vendor/jquery/jquery.min.js'/>"></script>

<script
	src="<c:url value='/resources/vendor/bootstrap/js/bootstrap.bundle.min.js'/>"></script>

<!-- Core plugin JavaScript-->
<script
	src="<c:url value='/resources/vendor/jquery-easing/jquery.easing.min.js'/>"></script>

<!-- Custom scripts for all pages-->
<script src="<c:url value='/resources/js/sb-admin-2.min.js'/>"></script>

<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
</body>

</body>

</html>