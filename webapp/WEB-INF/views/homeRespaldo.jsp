<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- este taglib es para usar el core de jstl, que permite usar loops y condicionales, es decir usar por ejemplo el tag c:foreach que se activa con el prefix c  -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- este taglib es para usar fmt de jstl, que se usa para dar formato-->
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- este taglib es para usar tags de spring-->
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenido a Cineapp</title>

<!--  ${urlPublic} se renderiza como cineapp/resources, es decir permite apuntar hacia un directorio en especifico-->
<spring:url value="/resources" var="urlPublic" />
<!--En este caso usamos urlPublic para integrar bootstrap desde nuestro directorio local resources-->
<link rel="stylesheet" href="${urlPublic}/bootstrap/css/bootstrap.min.css">


</head>
<body>

	<%-- 	<h1>Lista de Películas</h1>
	
	<ol>
	
	<c:forEach items="${peliculas}" var="pelicula"> <!--  {peliculas} es el nombre de la lista definida en HomeController. var="pelicula" es como se usa aca en el html -->
		<li>${pelicula}</li>
	</c:forEach>
	</ol> --%>


	<div class="panel panel-default">

		<div class="panel heading">Lista de peliculas</div>

		<div class="panel-body">

			<table class="table table-striped table-bordered table-hover">
				<thread>
				<tr>
					<th>Id</th>
					<th>Titulo</th>
					<th>Duracion</th>
					<th>Clasificacion</th>
					<th>Genero</th>
					<th>Imagen</th>
					<th>Fecha Estreno</th>
					<th>Estatus</th>
				</tr>
				</thread>
				<tbody>

					<c:forEach items="${ peliculas }" var="pelicula">
						<!--  {peliculas} es el nombre de la lista definida en HomeController. var="pelicula" es como se usa aca en el html;
		(continuacion) en el fondo quiere decir que para cada objeto perteneciente a la clase <Pelicula> de la lista peliculas, ejecute el codigo que sigue a continuacion -->
						<tr>
							<td>${pelicula.id }</td>
							<td>${pelicula.titulo }</td>
							<td>${pelicula.duracion }min</td>
							<!-- se puede agregar texto, como en este caso min -->
							<td>${pelicula.clasificacion }</td>
							<td>${pelicula.genero }</td>
							<td><img src="${urlPublic}/images/${pelicula.imagen}"
								width="80" height="100"></td>
							<!-- urlPublic se setea en archivo serletDispatcher (que en nuestro caso es springMVC-servlet)  -->
							<td><fmt:formatDate value="${pelicula.fechaEstreno}" 
									pattern="dd-MM-yyyy" /></td>
							<!-- gracias al taglib fmt, se le da formato a fechaEstreno -->
							<td>
							<!--condicional para JSP. si pelicula.estatus es igual a Activa, que muestre un estilo, sino que muestre otra -->
							<c:choose> 
									<c:when test="${pelicula.estatus=='Activa'}">
										<span style="color:lightgreen">ACTIVA</span>
									</c:when>
									<c:otherwise>
										<span style="color:red">INACTIVA</span>
									</c:otherwise>
								</c:choose>
								
							</td>
						</tr>
					</c:forEach>

				</tbody>

			</table>
		</div>
	</div>

</body>
</html>