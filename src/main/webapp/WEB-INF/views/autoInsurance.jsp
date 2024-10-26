<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Auto Insurance</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <script>
        function togglePopup() {
            const popup = document.getElementById('createInsurancePopup');
            popup.classList.toggle('hidden');
        }
    </script>
</head>
<body class="bg-gray-100">

<!-- Hero Section -->
<section class="bg-blue-600 text-white py-20" style="background-image: url('assets/images/auto-section.jpg')">
    <div class="container mx-auto px-6 text-center">
        <h1 class="text-4xl font-bold mb-4">Secure Your Ride with Our Auto Insurance</h1>
        <p class="text-lg mb-6">Get comprehensive coverage and enjoy peace of mind while you're on the road. Tailored options for young drivers, luxury cars, and professional use vehicles.</p>
        <button class="bg-white text-blue-600 font-semibold py-2 px-6 rounded-full shadow hover:bg-gray-100" onclick="togglePopup()">Create New Auto Insurance</button>
    </div>
</section>

<div class="container mx-auto p-4 ">
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
    <h1 class="text-2xl font-bold mb-4">Your Auto Insurance Policies</h1>

    <c:if test="${not empty insurancesCar}">
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            <c:forEach var="insurance" items="${insurancesCar}">
                <div class="bg-white rounded-lg shadow-lg p-6">
                    <h2 class="text-xl font-semibold mb-2">Model: ${insurance.model()}</h2>
                    <p class="text-gray-700 mb-1"><strong>Brand:</strong> ${insurance.brand()}</p>
                    <p class="text-gray-700 mb-1"><strong>Driver Age:</strong> ${insurance.driverAge()}</p>
                    <p class="text-gray-700 mb-1"><strong>Usage:</strong> ${insurance.usage() ? 'Professional' : 'Private'}</p>
                    <p class="text-gray-700 mb-1"><strong>Car Type:</strong> ${insurance.carType()}</p>
                    <p class="text-gray-700 mb-1"><strong>Had Sinister:</strong> ${insurance.hadSinister() ? 'Yes' : 'No'}</p>
                    <form action="${pageContext.request.contextPath}/auto-insurance/quotation" method="get">
                        <input type="hidden" name="insurance" value="${insurance.id()}"/>
                        <button type="submit" class="bg-white text-blue-600 font-semibold py-2 px-4 rounded-full shadow hover:bg-gray-100" >Generate Quotation</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${empty insurancesCar}">
        <p class="text-gray-700">You have no insurance policies yet. Consider creating one!</p>
    </c:if>

</div>

<!-- Popup for Creating New Insurance -->
<div id="createInsurancePopup" class="fixed inset-0 bg-gray-800 bg-opacity-50 hidden flex items-center justify-center">
    <div class="bg-white p-8 rounded-lg shadow-lg w-1/2">
        <h2 class="text-2xl font-bold mb-4">Create New Auto Insurance</h2>
        <form action="${pageContext.request.contextPath}/auto-insurance/create-autoInsurance" method="POST">
            <div class="mb-4">
                <label for="driverAge" class="block text-sm font-medium text-gray-700">Driver Age</label>
                <input type="number" id="driverAge" name="driverAge" class="w-full border rounded px-3 py-2" required>
            </div>
            <div class="mb-4">
                <label for="model" class="block text-sm font-medium text-gray-700">Car Model</label>
                <input type="text" id="model" name="model" class="w-full border rounded px-3 py-2" required>
            </div>
            <div class="mb-4">
                <label for="brand" class="block text-sm font-medium text-gray-700">Car Brand</label>
                <input type="text" id="brand" name="brand" class="w-full border rounded px-3 py-2" required>
            </div>
            <div class="flex justify-evenly">
            <div class="mb-4 w-full">
                <label for="carType" class="block text-sm font-medium text-gray-700">Car Type</label>
                <select id="carType" name="carType" class="w-full border rounded px-3 py-2" required>
                    <option value="luxury">Luxury</option>
                    <option value="utility">Utility</option>
                    <!-- Add other options as needed -->
                </select>
            </div>
            <div class="mb-4 w-full">
                <label for="usage" class="block text-sm font-medium text-gray-700">Usage</label>
                <select id="usage" name="usage" class="w-full border rounded px-3 py-2" required>
                    <option value="true">Professional</option>
                    <option value="false">Private</option>
                </select>
            </div>
            </div>
            <div class="mb-4">
                <label for="hadSinister" class="block text-sm font-medium text-gray-700">Accident History for last 3 years</label>
                <select id="hadSinister" name="hadSinister" class="w-full border rounded px-3 py-2" required>
                    <option value="false">No Accidents</option>
                    <option value="true">Had Accidents</option>
                </select>
            </div>
            <div class="flex justify-between">
                <button type="button" class="bg-red-500 text-white py-2 px-4 rounded" onclick="togglePopup()">Cancel</button>
                <button type="submit" class="bg-blue-600 text-white py-2 px-4 rounded">Get Quote</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
