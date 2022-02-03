<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<meta charset="ISO-8859-1">
<title>DATOS CUENTA</title>
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
          <a class="nav-link" href="/cajero/verMovimientos"><i class="bi bi-arrow-down-up"> Ver Movimientos</i></a>
        </li>
        <li class="nav-item">
          <a  class="nav-link" href="/cajero/ingresar"><i class="bi bi-plus-square"> Ingresar</i></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/cajero/extraer"><i class="bi bi-dash-square"> Extraer</i></a>
        </li>
         <li class="nav-item">
          <a class="nav-link" href="/cajero/transferencia"><i class="bi bi-arrow-left-right"> Transferencia</i></a>
        </li>
        
         <li class="nav-item" >
          <a  class="nav-link active " aria-current="page" href="/cajero/verDatosCuenta"><i class="bi bi-person-fill"> ID de la Cuenta: ${cuenta.idCuenta }</i></a>
        </li>
        <li class="nav-item" >
          <a class="nav-link" href="/logout"><i class="bi bi-box-arrow-right"> Cerrar Sesión</i></a>
        </li>
      </ul>
    </div>
  </div>
</nav>
</header>

<div class=" cuerpo bg-success p-2 text-white bg-opacity-25">
<div style="margin:100px 400px 100px 400px">
	

<table class="table border border-5">
  <thead>
    <tr>
      <th scope="col">ID_CUENTA</th>
      <th scope="col">TIPO</th>
      <th scope="col">SALDO</th>
     
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row"><i class="bi bi-person-circle"> ${cuenta.idCuenta } </i></th>
      <td><i class="bi bi-piggy-bank"> ${cuenta.tipoCuenta }</i></td>
      <td>${cuenta.saldo } <i class="bi bi-currency-euro"></i></td>
      
    </tr>
   
    
  </tbody>
</table>



</div>



</div>

</div>
</body>

</html>