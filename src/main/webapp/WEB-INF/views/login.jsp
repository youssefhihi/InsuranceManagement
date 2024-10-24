<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login - Insurance Co.</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>
<div class="font-[sans-serif]">
    <div class="min-h-screen flex flex-col items-center justify-center">
        <div class="grid md:grid-cols-2 items-center gap-4 max-md:gap-8 max-w-6xl max-md:max-w-lg w-full p-4 m-4 shadow-[0_2px_10px_-3px_rgba(6,81,237,0.3)] rounded-md">
            <div class="md:max-w-md w-full px-4 py-4">
                <form action="${pageContext.request.contextPath}/auth/login" method="POST">
                    <div class="mb-8">
                        <h3 class="text-gray-800 text-3xl font-extrabold">Sign in</h3>
                        <p class="text-sm mt-4 text-gray-800">
                            Don't have an account? <a href="${pageContext.request.contextPath}/auth/register" class="text-blue-600 font-semibold hover:underline ml-1 whitespace-nowrap">Register here</a>
                        </p>
                    </div>

                    <!-- Success Message -->
                    <c:if test="${not empty success}">
                        <div
                                role="alert"
                                class=" mb-5 bg-green-100 dark:bg-green-900 border-l-4 border-green-500 dark:border-green-700 text-green-900 dark:text-green-100 p-2 rounded-lg flex items-center transition duration-300 ease-in-out hover:bg-green-200 dark:hover:bg-green-800 transform hover:scale-105"
                        >
                            <svg
                                    stroke="currentColor"
                                    viewBox="0 0 24 24"
                                    fill="none"
                                    class="h-5 w-5 flex-shrink-0 mr-2 text-green-600"
                                    xmlns="http://www.w3.org/2000/svg"
                            >
                                <path
                                        d="M13 16h-1v-4h1m0-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
                                        stroke-width="2"
                                        stroke-linejoin="round"
                                        stroke-linecap="round"
                                ></path>
                            </svg>
                            <p class="text-xs font-semibold">Success - ${success}</p>
                        </div>
                    </c:if>
                    <c:if test="${not empty error}">
                        <div
                                role="alert"
                                class="mb-5 bg-red-100 dark:bg-red-900 border-l-4 border-red-500 dark:border-red-700 text-red-900 dark:text-red-100 p-2 rounded-lg flex items-center transition duration-300 ease-in-out hover:bg-red-200 dark:hover:bg-red-800 transform hover:scale-105"
                        >
                            <svg
                                    stroke="currentColor"
                                    viewBox="0 0 24 24"
                                    fill="none"
                                    class="h-5 w-5 flex-shrink-0 mr-2 text-red-600"
                                    xmlns="http://www.w3.org/2000/svg"
                            >
                                <path
                                        d="M13 16h-1v-4h1m0-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
                                        stroke-width="2"
                                        stroke-linejoin="round"
                                        stroke-linecap="round"
                                ></path>
                            </svg>
                            <p class="text-xs font-semibold">Error - ${error}.</p>
                        </div>
                    </c:if>
                    <div>
                        <label class="text-gray-800 text-xs block mb-2">Email</label>
                        <div class="relative flex items-center">
                            <input name="email" type="text" required class="w-full text-gray-800 text-sm border-b border-gray-300 focus:border-blue-600 px-2 py-3 outline-none" placeholder="Enter email" />
                        </div>
                    </div>
                    <div class="mt-8">
                        <label class="text-gray-800 text-xs block mb-2">Password</label>
                        <div class="relative flex items-center">
                            <input name="password" type="password" required class="w-full text-gray-800 text-sm border-b border-gray-300 focus:border-blue-600 px-2 py-3 outline-none" placeholder="Enter password" />
                        </div>
                    </div>

                    <div class="mt-12">
                        <button type="submit" class="w-full shadow-xl py-2.5 px-4 text-sm tracking-wide rounded-md text-white bg-blue-600 hover:bg-blue-700 focus:outline-none">
                            Sign in
                        </button>
                    </div>
                </form>
            </div>
            <div class="md:h-full bg-[#000842] rounded-xl lg:p-12 p-8">
                <img src="https://readymadeui.com/signin-image.webp" class="w-full h-full object-contain" alt="login-image" />
            </div>
        </div>
    </div>
</div>
</body>
</html>
