<%--
  Created by IntelliJ IDEA.
  User: otmane
  Date: 3/29/23
  Time: 10:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="branches" scope="request" type="java.util.List"/>

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
    <div
            class="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8 flex flex-wrap justify-between"
    >
        <h1 class="text-3xl font-bold tracking-tight text-gray-900">
            Branches
        </h1>

        <a
                href="<c:url value="/branches/create"/>"
                class="text-white bg-indigo-500 border-0 py-2 px-6 focus:outline-none hover:bg-indigo-600 rounded text-lg"
        >Add branch</a
        >
    </div>
    <div class="flex flex-wrap mb-4 justify-end"></div>
</header>
<main>
    <div class="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
        <c:forEach items="${branches}" var="branch">
            <div class="w-full mb-4">
                <div
                        class="flex items-center border-gray-200 border p-4 rounded-lg"
                >
                    <div class="flex-grow">
                        <h2
                                class="text-gray-900 title-font font-medium flex"
                        >
                            <svg
                                    class="h-6 w-6 text-gray-600"
                                    fill="none"
                                    viewBox="0 0 24 24"
                                    stroke-width="1.5"
                                    stroke="currentColor"
                            >
                                <path
                                        stroke-linecap="round"
                                        stroke-linejoin="round"
                                        d="M4.26 10.147a60.436 60.436 0 00-.491 6.347A48.627 48.627 0 0112 20.904a48.627 48.627 0 018.232-4.41 60.46 60.46 0 00-.491-6.347m-15.482 0a50.57 50.57 0 00-2.658-.813A59.905 59.905 0 0112 3.493a59.902 59.902 0 0110.399 5.84c-.896.248-1.783.52-2.658.814m-15.482 0A50.697 50.697 0 0112 13.489a50.702 50.702 0 017.74-3.342M6.75 15a.75.75 0 100-1.5.75.75 0 000 1.5zm0 0v-3.675A55.378 55.378 0 0112 8.443m-7.007 11.55A5.981 5.981 0 006.75 15.75v-1.5"
                                />
                            </svg>
                            <p class="ml-2">${branch.name}</p>
                        </h2>
                    </div>
                    <a class="p-3" href="<c:url value="/branches/${branch.pk}/edit"/>">
                        <svg
                                xmlns="http://www.w3.org/2000/svg"
                                width="24"
                                height="24"
                                viewBox="0 0 24 24"
                                fill="none"
                                stroke="currentColor"
                                stroke-width="2"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                class="feather feather-edit"
                        >
                            <path
                                    d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"
                            ></path>
                            <path
                                    d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"
                            ></path>
                        </svg>
                    </a>
                </div>
            </div>
        </c:forEach>
    </div>
</main>
</body>
</html>

