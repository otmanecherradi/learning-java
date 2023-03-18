<%--
  Created by IntelliJ IDEA.
  User: otmane
  Date: 3/12/23
  Time: 1:14 PM
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
  <title>Title</title>
</head>
<body>

<div class="container">

${error}

<form action="${pageContext.request.contextPath}/articles/edit" method="post">
  <input type="hidden" name="id" value="${article.pk}">
  <div class="mb-3">
    <label for="title" class="form-label">title</label>
    <input type="text" class="form-control" name="title" id="title" value="${article.title}">
  </div>
  <div class="mb-3">
    <label for="description" class="form-label">title</label>
    <textarea name="description" class="form-control" id="description" cols="30" rows="10">${article.description}</textarea>
  </div>
  <div class="mb-3">
    <label for="price" class="form-label">price</label>
    <input type="number" class="form-control" step="0.01" name="price" id="price" value="${article.price}">
  </div>
  <input type="submit" class="btn btn-primary" value="Send">
</form>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>