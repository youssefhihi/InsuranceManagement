<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insurance Management - Simplified</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<!-- Header -->
<%@ include file="./WEB-INF/views/component/header.jsp" %>

<!-- Hero Section -->
<section class="hero bg-blue-600 text-white">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-24 text-center" style="background-image: url('${pageContext.request.contextPath}/assets/images/hero-section.jpg');">
        <h2 class="text-4xl md:text-5xl font-extrabold">Manage Your Insurance Effortlessly</h2>
        <p class="mt-4 text-lg md:text-xl">Get quotes, manage contracts, and access all your insurance needs in one place. Simplified and secure.</p>
        <a href="#services" class="mt-8 inline-block bg-white text-blue-600 px-6 py-3 rounded-lg shadow hover:bg-gray-100">Get Started</a>
    </div>
</section>

<!-- Services Section -->
<section id="services" class="py-16 bg-white">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
        <h2 class="text-3xl md:text-4xl font-bold text-gray-800">Our Services</h2>
        <p class="mt-4 text-gray-600">We offer a range of services to meet all your insurance needs.</p>

        <div class="mt-10 grid gap-8 md:grid-cols-2 lg:grid-cols-3">
            <a href="${pageContext.request.contextPath}/auto-insurance" class="bg-gray-100 p-6 rounded-lg shadow-md">
                <h3 class="text-2xl font-semibold text-blue-600">Auto Insurance</h3>
                <p class="mt-4 text-gray-600">Get customized quotes based on your vehicle and driving history. Enjoy comprehensive coverage for your car.</p>
            </a>
            <a href="${pageContext.request.contextPath}/home-insurance" class="bg-gray-100 p-6 rounded-lg shadow-md">
                <h3 class="text-2xl font-semibold text-blue-600">Home Insurance</h3>
                <p class="mt-4 text-gray-600">Protect your home with our tailored policies, including coverage for natural disasters and theft.</p>
            </a>
            <a href="${pageContext.request.contextPath}/health-insurance" class="bg-gray-100 p-6 rounded-lg shadow-md">
                <h3 class="text-2xl font-semibold text-blue-600">Health Insurance</h3>
                <p class="mt-4 text-gray-600">Access affordable health plans and manage your policy online with ease.</p>
            </a>
        </div>
    </div>
</section>

<!-- About and Contact Sections (same as before) -->

<footer class="bg-gray-800 text-white py-8">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
        <p>&copy; 2024 Insurance Co. All rights reserved.</p>
        <p class="mt-2">Privacy Policy | Terms of Service</p>
    </div>
</footer>


</body>
</html>
