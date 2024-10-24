<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Auto Insurance - Insurance Co.</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<!-- Header -->
<%@ include file="./component/header.jsp" %>

<c:if test="${not empty insuranceCar}">
    <!-- Quotation Details Modal -->
    <div id="ContractModal" class="fixed inset-0 hidden items-center justify-center bg-gray-900 bg-opacity-50">
        <div class="bg-white rounded-lg w-96 shadow-lg p-6">
            <h3 class="text-lg font-bold mb-4">Contract Details</h3>
            <form action="${pageContext.request.contextPath}/auto-insurance/accept-quotation" method="POST" enctype="multipart/form-data" class="space-y-4">
                <!-- End Date Input -->
                <div>
                    <label for="endDate" class="block text-gray-700">End Date</label>
                    <input type="datetime-local" id="endDate" name="endDate" class="mt-1 w-full p-2 border rounded-lg focus:ring-blue-500 focus:border-blue-500" required>
                </div>

                <!-- File Input -->
                <div>
                    <label for="document" class="block text-gray-700">Upload Contract Document</label>
                    <input type="file" id="document" name="document" class="mt-1 w-full p-2 border rounded-lg focus:ring-blue-500 focus:border-blue-500" required>
                </div>

                <!-- Submit Button -->
                <div class="mt-6 flex justify-end space-x-2">
                    <button type="button" onclick="toggleModal()" class="bg-gray-500 text-white px-4 py-2 rounded-lg hover:bg-gray-600">
                        Cancel
                    </button>
                    <button type="submit" class="bg-green-600 text-white px-4 py-2 rounded-lg hover:bg-green-700">
                        Submit Contract
                    </button>
                </div>
            </form>
        </div>
    </div>
</c:if>

<!-- Auto Insurance Form Section -->
<section class="py-16 bg-white flex space-x-2">
    <div class="max-w-7xl px-4 sm:px-6 lg:px-8">
        <h2 class="text-3xl font-bold text-gray-800 text-center">Enter Auto Insurance Details</h2>
        <div class="mt-8 bg-gray-100 p-8 rounded-lg shadow">
            <!-- Your existing form for insurance calculation -->
                <form action="${pageContext.request.contextPath}/auto-insurance/calculate-quotation" method="POST" class="space-y-6">
                    <div>
                        <label for="driverAge" class="block text-gray-700">Driver's Age</label>
                        <input type="number" id="driverAge" name="driverAge" class="mt-1 w-full p-2 border rounded-lg focus:ring-blue-500 focus:border-blue-500" required>
                    </div>
                    <div>
                        <label for="model" class="block text-gray-700">Vehicle Model</label>
                        <input type="text" id="model" name="model" class="mt-1 w-full p-2 border rounded-lg focus:ring-blue-500 focus:border-blue-500" required>
                    </div>
                    <div>
                        <label for="brand" class="block text-gray-700">Vehicle Brand</label>
                        <input type="text" id="brand" name="brand" class="mt-1 w-full p-2 border rounded-lg focus:ring-blue-500 focus:border-blue-500" required>
                    </div>
                    <div>
                        <label class="block text-gray-700">Usage</label>
                        <div class="mt-1 flex space-x-4">
                            <label class="flex items-center">
                                <input type="radio" name="usage" value="false" class="text-blue-600 focus:ring-blue-500" required>
                                <span class="ml-2">Private</span>
                            </label>
                            <label class="flex items-center">
                                <input type="radio" name="usage" value="true" class="text-blue-600 focus:ring-blue-500" required>
                                <span class="ml-2">Professional</span>
                            </label>
                        </div>
                    </div>
                    <div>
                        <label  class="block text-gray-700">Car Type is luxury</label>
                        <div class="mt-1 flex space-x-4">
                            <select name="carType" >
                                <option value="luxury" > Luxury</option>
                                <option value="utility" > Utility</option>
                            </select>
                        </div>
                    </div>
                    <div>
                        <label class="block text-gray-700">Had Previous Sinister</label>
                        <div class="mt-1 flex space-x-4">
                            <label class="flex items-center">
                                <input type="radio" name="hadSinister" value="true" class="text-blue-600 focus:ring-blue-500" required>
                                <span class="ml-2">Yes</span>
                            </label>
                            <label class="flex items-center">
                                <input type="radio" name="hadSinister" value="false" class="text-blue-600 focus:ring-blue-500" required>
                                <span class="ml-2">No</span>
                            </label>
                        </div>
                    </div>
                    <button type="submit" class="w-full bg-blue-600 text-white p-3 rounded-lg hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500">
                        Submit
                    </button>
                </form>
        </div>
    </div>

    <c:if test="${not empty insuranceCar}">
        <!-- Quotation Details Section -->
        <div class="bg-white rounded-lg w-96 shadow-lg p-6">
            <h3 class="text-lg font-bold mb-4">Quotation Details</h3>
            <ul class="space-y-2">
                <li><span class="font-semibold">Driver's Age:</span> <c:out value="${insuranceCar.driverAge()}"/></li>
                <li><span class="font-semibold">Vehicle Model:</span> <c:out value="${insuranceCar.model()}"/></li>
                <li><span class="font-semibold">Vehicle Brand:</span> <c:out value="${insuranceCar.brand()}"/></li>
                <li><span class="font-semibold">Usage:</span> <c:out value="${insuranceCar.usage() ? 'Professional' : 'Private'}"/></li>
                <li><span class="font-semibold">Car Type:</span> <c:out value="${insuranceCar.carType().name()}"/></li>
                <li><span class="font-semibold">Had Previous Sinister:</span> <c:out value="${insuranceCar.hadSinister() ? 'Yes' : 'No'}"/></li>
                <li><span class="font-semibold">Quotation Amount:</span> $<c:out value="${insuranceCar.amount()}"/></li>
            </ul>
            <div class="mt-6 flex justify-end space-x-2">
                <button onclick="toggleModal()" class="bg-green-600 text-white px-4 py-2 rounded-lg hover:bg-green-700">
                    Accept
                </button>
            </div>
        </div>
    </c:if>
</section>

<!-- Footer -->
<footer class="bg-gray-800 text-white py-8">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
        <p>&copy; 2024 Insurance Co. All rights reserved.</p>
    </div>
</footer>

<script>
    function toggleModal() {
        const modal = document.getElementById('ContractModal');
        modal.classList.toggle('hidden');
        modal.classList.toggle('flex');
    }
</script>
</body>
</html>
