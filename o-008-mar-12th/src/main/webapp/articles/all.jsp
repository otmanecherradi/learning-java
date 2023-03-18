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
  <title>All articles</title>
</head>
<body>

${error}

<table border="1">
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
        <a href="${pageContext.request.contextPath}/articles/edit/?id=${article.pk}">edit</a>
        <a href="${pageContext.request.contextPath}/articles/delete/?id=${article.pk}">delete</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<a href="${pageContext.request.contextPath}/articles/add/">Add</a>

</body>
</html>
