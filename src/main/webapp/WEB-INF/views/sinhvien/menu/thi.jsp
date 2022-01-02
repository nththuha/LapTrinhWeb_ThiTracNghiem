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
						<form:form action="http://localhost:8080/QLTTN/sinhvien/thi.htm" modelAttribute="cauHoi">
							<div class="card text-white bg-info mb-3"
								style="max-width: 60rem;">
								<div class="card-body">
									<h3 class="card-title">Trắc nghiệm môn: ${tenmonhoc}</h3>
								</div>
							</div>
							<div class="card-header">
								<p class="font-weight-bold">Câu ${tt}: ${cauHoi.noiDung}</p>
							</div>
							<div class="card-header">
								<div class="form-check">
									<form:radiobutton path="luaChon" value="A"/>
									<label>${cauHoi.a}</label>
								</div>
								<div class="form-check">
									<form:radiobutton path="luaChon" value="B"/>
									<label>${cauHoi.b}</label>
								</div>
								<div class="form-check">
									<form:radiobutton path="luaChon" value="C"/>
									<label>${cauHoi.c}</label>
								</div>
								<div class="form-check">
									<form:radiobutton path="luaChon" value="D"/>
									<label>${cauHoi.d}</label>
								</div>
							</div>
							<div class="card-body">
								<div class="form-group d-flex justify-content-center visible">
										<button class="btn btn-success">Xác nhận</button>
									</div>
							</div>
						</form:form>
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