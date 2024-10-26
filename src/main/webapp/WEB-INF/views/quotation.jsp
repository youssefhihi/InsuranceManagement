<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insurance Quotation</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="max-w-3xl mx-auto mt-10 bg-white p-8 rounded-lg shadow-md">
    <h1 class="text-2xl font-bold mb-6 text-blue-600">Insurance Quotation</h1>

    <div class="space-y-4">
        <c:if test="${not empty insuranceCar}">
            <h2 class="text-xl font-bold mb-4">Car Insurance Details</h2>
            <div class="flex justify-evenly">
                <span class="font-semibold">Driver Age:</span>
                <span>${insuranceCar.driverAge()}</span>
            </div>
            <div class="flex justify-evenly">
                <span class="font-semibold">Model:</span>
                <span>${insuranceCar.model()}</span>
            </div>
            <div class="flex justify-evenly">
                <span class="font-semibold">Brand:</span>
                <span>${insuranceCar.brand()}</span>
            </div>
            <div class="flex justify-evenly">
                <span class="font-semibold">Usage:</span>
                <span>${insuranceCar.usage() ? 'Professional' : 'Personal'}</span>
            </div>
            <div class="flex justify-evenly">
                <span class="font-semibold">Car Type:</span>
                <span>${insuranceCar.carType()}</span>
            </div>
            <div class="flex justify-evenly">
                <span class="font-semibold">Previous Claims:</span>
                <span>${insuranceCar.hadSinister() ? 'Yes' : 'No'}</span>
            </div>
        </c:if>

        <c:if test="${not empty insuranceHealth}">
            <h2 class="text-xl font-bold mb-4">Health Insurance Details</h2>
            <div class="flex justify-evenly">
                <span class="font-semibold">Age:</span>
                <span>${insuranceHealth.age()}</span>
            </div>
            <div class="flex justify-evenly">
                <span class="font-semibold">Health Status:</span>
                <span>${insuranceHealth.healthStatus()}</span>
            </div>
            <div class="flex justify-evenly">
                <span class="font-semibold">Coverage Type:</span>
                <span>${insuranceHealth.coverageType()}</span>
            </div>
        </c:if>
        <c:if test="${not empty insuranceHome}">
            <h2 class="text-xl font-bold mb-4">Home Insurance Details</h2>
            <div class="flex justify-evenly">
                <span class="font-semibold">Property Value (MAD):</span>
                <span>${insuranceHome.propertyValue()}</span>
            </div>
            <div class="flex justify-evenly">
                <span class="font-semibold">Property Type:</span>
                <span>${insuranceHome.propertyType()}</span>
            </div>
            <div class="flex justify-evenly">
                <span class="font-semibold">Risk Zone:</span>
                <span>${insuranceHome.isRiskZone() ? 'Yes' : 'No'}</span>
            </div>
            <div class="flex justify-evenly">
                <span class="font-semibold">Security System:</span>
                <span>${insuranceHome.hasSecuritySystem() ? 'Yes' : 'No'}</span>
            </div>
        </c:if>


        <div class="flex justify-between text-lg mt-6">
            <span class="font-bold text-blue-600">Total Amount:</span>
            <span class="font-bold text-blue-600">${amount} MAD</span>
        </div>
    </div>

    <div class="mt-8 text-right">
        <button id="acceptButton" class="bg-green-500 text-white font-semibold py-2 px-4 rounded-full shadow hover:bg-green-600">
            Accept Quotation
        </button>
    </div>

    <div id="contractForm" class="mt-6 hidden">
        <h2 class="text-xl font-bold mb-4 text-blue-600">Contract Details</h2>
        <form action="${pageContext.request.contextPath}/contract/create" method="post" enctype="multipart/form-data" class="space-y-4">
            <div>
                <label for="endDate" class="block text-sm font-medium text-gray-700">End Date</label>
                <input type="date" id="endDate" name="endDate" required class="mt-1 p-2 border border-gray-300 rounded-md w-full">
            </div>
            <div>
                <input type="hidden" name="amount" value="${amount}">
            </div>
            <div>
                <label for="document" class="block text-sm font-medium text-gray-700">Upload Document</label>
                <input type="file" multiple id="document" name="documents" required class="mt-1 p-2 border border-gray-300 rounded-md w-full">
            </div>
            <div class="text-right">
                <button type="submit" class="bg-blue-600 text-white font-semibold py-2 px-4 rounded-full shadow hover:bg-blue-700">
                    Submit Contract
                </button>
            </div>
        </form>
    </div>
</div>

<script>
    document.getElementById('acceptButton').addEventListener('click', function() {
        document.getElementById('contractForm').classList.toggle('hidden');
    });
</script>
</body>
</html>
