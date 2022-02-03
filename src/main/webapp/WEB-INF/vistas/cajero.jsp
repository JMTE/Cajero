<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cajero</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">

</head>
<body>
<div class="container">
<header>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand  " href="/cajero/"><i class="bi bi-bank"> Cajero JMTE</i></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active " aria-current="page" href="/cajero/verMovimientos"><i class="bi bi-arrow-down-up"> Ver Movimientos</i></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/cajero/ingresar"><i class="bi bi-plus-square"> Ingresar</i></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/cajero/extraer"><i class="bi bi-dash-square"> Extraer</i></a>
        </li>
         <li class="nav-item">
          <a class="nav-link" href="/cajero/transferencia"><i class="bi bi-arrow-left-right"> Transferencia</i></a>
        </li>
        
         <li class="nav-item" >
          <a class="nav-link" href="/cajero/verDatosCuenta"><i class="bi bi-person-fill"> ID de la Cuenta: ${cuenta.idCuenta }</i></a>
        </li>
        <li class="nav-item" >
          <a class="nav-link" href="/logout"><i class="bi bi-box-arrow-right"> Cerrar Sesión</i></a>
        </li>
      </ul>
    </div>
  </div>
</nav>
</header>

<div class="cuerpo bg-success p-2 text-white bg-opacity-25">
<div class="imagenhome">
<br>
<h4> <i class="bi bi-person-check"> Esta es la cuenta con ID: ${cuenta.idCuenta }</i></h4>
<h3> <i class="bi bi-list-task"> ${mensajeMovimientos }</i></h3>
<h4> <span class="bi bi-exclamation-triangle"> ${error}</span> </h4>
<table class="table">
  <thead>
    <tr>
      <th scope="col">ID_Movimiento</th>
      <th scope="col">Fecha</th>
      <th scope="col">Cantidad</th>
      <th scope="col">Operación</th>
      
    </tr>
  </thead>
  
  <tbody>
  <c:forEach var="ele" items="${listamovimientos}">
  <tr>
      <th scope="row"><i class="bi bi-forward-fill"> ${ele.idMovimiento }</i></th>
      <td><i class="bi bi-clock"> <fmt:formatDate pattern = "dd-MM-yyyy --- HH:mm" value = "${ ele.fecha }" /></i> </td>
      <td>${ele.cantidad } <i class="bi bi-currency-euro"></i></td>
      <c:choose>
					 		<c:when test="${ele.operacion =='Ingreso' }">
					 		<td class="text-success fw-bold bi bi-emoji-smile"> ${ele.operacion}</td>
					 		</c:when>
					 		<c:otherwise>
					 		<td class="text-danger fw-bold bi bi-emoji-frown"> ${ele.operacion}</td>
					 		</c:otherwise>
	  </c:choose>
      
     
    </tr>
  </c:forEach>
   
   <tr> 
  
   
  <td></td>
  <td></td>
  <td  class="fw-bold "><i class="bi bi-wallet2"> Saldo Actual</i></td>
  <td class="fw-bold ">${cuenta.saldo } <i class="bi bi-currency-euro"></i></td>
  </tr>
   
  </tbody>
</table>
<button  type="button" class="btn btn-link " style="color:black" ><a href="/cajero/verMovimientos" class="text-dark fw-bold"><i class="bi bi-plus"> ${mensajeMasMovimientos }</i></a></button>
</div>
</div>

</div>
</body>
</html>