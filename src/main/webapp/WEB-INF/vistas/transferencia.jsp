<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<meta charset="ISO-8859-1">
<title>TRANSFERENCIA</title>
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
          <a class="nav-link"  href="/cajero/ingresar"><i class="bi bi-plus-square"> Ingresar</i></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/cajero/extraer"><i class="bi bi-dash-square"> Extraer</i></a>
        </li>
         <li class="nav-item">
          <a class= "nav-link active " aria-current="page" href="/cajero/transferencia"><i class="bi bi-arrow-left-right"> Transferencia</i></a>
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
<div style="margin:100px 400px 100px 400px">
	<div class="container3">
	<p>${fallo}</p>
	<form action="/cajero/transferencia" method="post">
  <div class="mb-3">
    <label for="cantidad" class="form-label">Introduce Cantidad:</label>
    <input type="number" step="0.01" min="1" name="cantidad" class="form-control" id="cantidad" required >
    
  </div>
  <div class="mb-3">
    <label for="idCuenta" class="form-label">Introduce Destino(ID Cuenta):</label>
    <input type="number"  name="idCuenta" class="form-control" id="idCuenta" required >
    
  </div>
 
  <button type="submit" class="btn btn-primary">Transferir</button>
  
</form>


</div>
</div>
</div>

</div>
</body>

</html>