<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="/styles.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<meta charset="ISO-8859-1">
<title>LOGIN</title>
</head>
<body style="margin:200px 600px 100px 600px">
	<div class="container3 border border-5  bg-success p-2 text-white bg-opacity-25">
	<p>${fallo}</p>
	<form action="/login" method="post" >
  <div class="mb-3">
    <label for="idCuenta" class="form-label fw-bold d-flex justify-content-center "> <i class="bi bi-bank"> ID CUENTA BANCARIA</i>  </label>
    <input type="number" name="idCuenta" class="form-control" id="idCuenta" required >
    
  </div>
 
  <button type="submit" class="btn btn-success "><i class="bi bi-person "> Entrar</i></button>
  
</form>


</div>
</body>
</html>