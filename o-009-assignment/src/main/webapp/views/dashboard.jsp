<%--
  Created by IntelliJ IDEA.
  User: otmane
  Date: 3/29/23
  Time: 10:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html
        class="h-full bg-gray-100"
>
<head>
    <meta charset="UTF-8"/>
    <meta
            name="viewport"
            content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
    />
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Branches</title>

    <script src="${pageContext.request.contextPath}/assets/js/tailwindcss.js"></script>
</head>

<body class="h-full">
<nav class="bg-gray-800">
    <div class="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
        <div class="flex h-16 items-center justify-between">
            <div class="flex items-center">
                <div class="hidden md:block">
                    <div class="ml-10 flex items-baseline space-x-4">
                        <a
                                href="<c:url value="/dashboard/"/>"
                                class="bg-gray-900 text-white rounded-md px-3 py-2 text-sm font-medium"
                                aria-current="page"
                        >Dashboard</a
                        >
                        <a
                                href="<c:url value="/branches/"/>"
                                class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium"
                        >Branches</a
                        >
                        <a
                                href="<c:url value="/students/"/>"
                                class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium"
                        >Students</a
                        >
                    </div>
                </div>
            </div>
        </div>
    </div>
</nav>

<header class="bg-white shadow">
    <div class="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
        <h1 class="text-3xl font-bold tracking-tight text-gray-900">
            Dashboard
        </h1>
    </div>
</header>
<main>
    <div class="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8"></div>
</main>
</body>
</html>
