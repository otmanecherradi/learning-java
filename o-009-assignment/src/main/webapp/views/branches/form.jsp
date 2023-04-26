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
                        <!-- Current: "bg-gray-900 text-white", Default: "text-gray-300 hover:bg-gray-700 hover:text-white" -->
                        <a
                                href="<c:url value="/dashboard/"/>"
                                class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium"
                                aria-current="page"
                        >Dashboard</a
                        >
                        <a
                                href="<c:url value="/branches/"/>"
                                class="bg-gray-900 text-white rounded-md px-3 py-2 text-sm font-medium"
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
            Branches
        </h1>
    </div>
</header>
<main>
    <div class="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
        ${error}
        <div class="overflow-hidden shadow rounded-md">
            <c:choose>
            <c:when test="${branch == null}">
            <form
                    action="<c:url value="/branches/create"/>"
                    method="POST"
            >
            </c:when>
            <c:otherwise>
            <form
                    action="<c:url value="/branches/${branch.pk}/edit"/>"
                    method="POST"
            >
            </c:otherwise>
            </c:choose>
                <div class="bg-white p-6">
                    <div class="grid grid-cols-6 gap-6">
                        <div class="col-span-4">
                            <label
                                    for="name"
                                    class="block text-sm font-medium text-gray-700"
                            >Name</label
                            >
                            <input
                                    type="text"
                                    name="name"
                                    id="name"
                                    autocomplete="text"
                                    class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                                    value="<c:if test="${branch != null}">${branch.name}</c:if>"
                            />
                        </div>
                    </div>
                </div>
                <div class="bg-gray-50 px-4 py-3 text-right sm:px-6">
                    <button
                            type="submit"
                            class="inline-flex justify-center rounded-md border border-transparent bg-indigo-600 py-2 px-4 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
                    >
                        Save
                    </button>
                </div>
            </form>
            <div class="bg-gray-50 px-4 py-3 text-right sm:px-6">
                <form
                        action="<c:url value="/branches/${branch.pk}/delete"/>"
                        method="post"
                >
                    <button
                            type="submit"
                            class="inline-flex w-full justify-center rounded-md border border-transparent bg-red-600 px-4 py-2 text-base font-medium text-white shadow-sm hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2 sm:ml-3 sm:w-auto sm:text-sm"
                    >
                        Delete
                    </button>
                </form>
            </div>
        </div>
    </div>
</main>
</body>
</html>
