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
  <title>Title</title>
</head>
<body>

${error}

<form action="${pageContext.request.contextPath}/articles/edit/" method="post">
  <input type="hidden" name="id" value="${article.pk}">
  <div>
    <label for="title">title</label>
    <input type="text" name="title" id="title" value="${article.title}">
  </div>
  <div>
    <label for="description">title</label>
    <textarea name="description" id="description" cols="30" rows="10">
      ${article.description}
    </textarea>
  </div>
  <div>
    <label for="price">price</label>
    <input type="number" step="0.01" name="price" id="price" value="${article.price}">
  </div>
  <input type="submit" value="Send">
</form>

</body>
</html>
