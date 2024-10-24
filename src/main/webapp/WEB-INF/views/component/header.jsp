<header class="bg-white shadow">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-4 flex justify-between items-center">
        <h1 class="text-2xl font-bold text-blue-600">Insurance Co.</h1>
        <nav class="hidden md:flex">
            <a href="#services" class="text-gray-600 hover:text-blue-600 px-4">Services</a>
            <a href="#about" class="text-gray-600 hover:text-blue-600 px-4">About</a>
            <a href="#contact" class="text-gray-600 hover:text-blue-600 px-4">Contact</a>
        </nav>
        <div class="hidden md:flex">
            <a href="/login" class="text-white bg-blue-600 hover:bg-blue-700 px-4 py-2 rounded">Login</a>
            <a href="/register" class="ml-2 text-blue-600 hover:text-blue-700 px-4 py-2 border border-blue-600 rounded">Sign Up</a>
        </div>
        <!-- Mobile Menu Button -->
        <div class="md:hidden">
            <button id="menu-btn" class="text-gray-600 focus:outline-none">
                <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16"></path>
                </svg>
            </button>
        </div>
    </div>
    <!-- Mobile Menu -->
    <div id="mobile-menu" class="md:hidden px-4 py-2 space-y-2 hidden">
        <a href="#services" class="block text-gray-600 hover:text-blue-600">Services</a>
        <a href="#about" class="block text-gray-600 hover:text-blue-600">About</a>
        <a href="#contact" class="block text-gray-600 hover:text-blue-600">Contact</a>
        <a href="/login" class="block text-white bg-blue-600 hover:bg-blue-700 px-4 py-2 rounded">Login</a>
        <a href="/register" class="block text-blue-600 hover:text-blue-700 px-4 py-2 border border-blue-600 rounded">Sign Up</a>
    </div>
</header>

<script>
    const menuBtn = document.getElementById('menu-btn');
    const mobileMenu = document.getElementById('mobile-menu');

    menuBtn.addEventListener('click', () => {
        mobileMenu.classList.toggle('hidden');
    });
</script>