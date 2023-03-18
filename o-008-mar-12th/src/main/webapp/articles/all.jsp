<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: otmane
  Date: 3/12/23
  Time: 10:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

  <title>All articles</title>
</head>
<body>

${error}

<div class="container">
  <table class="table">
    <thead>
    <th>title</th>
    <th>description</th>
    <th>price</th>
    <th>actions</th>
    </thead>
    <tbody>

    <c:forEach items="${articles}" var="article">
      <tr>
        <td><c:out value="${article.title}"/></td>
        <td><c:out value="${article.description}"/></td>
        <td><c:out value="${article.price}"/></td>
        <td>
          <div class="btn-group" role="group" aria-label="Basic example">
            <a href="${pageContext.request.contextPath}/articles/edit?id=${article.pk}" class="btn btn-warning">edit</a>
            <a href="${pageContext.request.contextPath}/articles/delete?id=${article.pk}" class="btn btn-danger">delete</a>
          </div>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <a href="${pageContext.request.contextPath}/articles/edit" class="btn btn-primary">Add</a>
</div>




<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
