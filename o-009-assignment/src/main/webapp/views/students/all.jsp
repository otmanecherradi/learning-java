<%--
  Created by IntelliJ IDEA.
  User: otmane
  Date: 4/25/23
  Time: 10:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="students" scope="request" type="java.util.List"/>

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
                                class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium"
                        >Branches</a
                        >
                        <a
                                href="<c:url value="/students/"/>"
                                class="bg-gray-900 text-white rounded-md px-3 py-2 text-sm font-medium"
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
            Students
        </h1>

        <select
                id="branch"
                name="branch"
                class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:max-w-xs sm:text-sm sm:leading-6"
        >
            <option value="---">---</option>
            <c:forEach items="${branches}" var="branch">
                <option
                        value="${branch.pk}"
                        <c:if test="${param.branch_pk == branch.pk}">selected</c:if>
                >${branch.name}</option>
            </c:forEach>
        </select>

        <a
                href="<c:url value="/students/create"/>"
                class="text-white bg-indigo-500 border-0 py-2 px-6 focus:outline-none hover:bg-indigo-600 rounded text-lg"
        >Add student</a
        >
    </div>
    <div class="flex flex-wrap mb-4 justify-end"></div>
</header>
<main>
    <div class="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
        <c:forEach items="${students}" var="student">
            <div class="w-full mb-4">
                <div
                        class="flex items-center border-gray-200 border p-4 rounded-lg"
                >
                    <div class="flex-grow">
                        <h2
                                class="text-gray-900 title-font font-medium flex items-center"
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
                                        d="M15.75 6a3.75 3.75 0 11-7.5 0 3.75 3.75 0 017.5 0zM4.501 20.118a7.5 7.5 0 0114.998 0A17.933 17.933 0 0112 21.75c-2.676 0-5.216-.584-7.499-1.632z"
                                />
                            </svg>

                            <div>
                                <p class="ml-2">${student.firstName} ${student.lastName}</p>
                                <p class="ml-2 font-light">
                                    Gender: ${student.gender}
                                </p>
                                <p class="ml-2 font-light">
                                    Enrolling:
                                    <c:choose>
                                        <c:when test="${student.branch == null}">
                                            ---
                                        </c:when>
                                        <c:otherwise>
                                            ${student.branch.name}
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </h2>
                    </div>
                    <a
                            class="p-3"
                            href="<c:url value="/students/${student.pk}/edit"/>"
                    >
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

<script>
    document.querySelector('select#branch').addEventListener('change', (evt) => {
        const url = new URL(location.href);
        if (evt.target.value !== '---') {
            url.searchParams.set('branch_pk', evt.target.value);
        } else {
            url.searchParams.delete('branch_pk');
        }

        location.href = url.toString();
    });
</script>
</body>
</html>