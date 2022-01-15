<template>
    <div class="w-full bg-white shadow-md">
        <div class="flex flex-row items-center justify-between mx-auto max-w-screen-2xl">
            <div class="p-4">
                <img src="standup-fails-logo.png" width="200" alt="Standup Fails" />
            </div>

            <!-- <div class="hidden p-4 text-center bg-opacity-25 sm:flex">
                <div class="space-x-8">
                    <router-link class="router-link" :to="{ name: 'Home' }">Home</router-link>
                </div>
            </div> -->
            
            <!-- <button class="flex items-center p-4 sm:hidden" @click="toggleMobileMenu">
                <svg xmlns="http://www.w3.org/2000/svg" class="w-6 h-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16" />
                </svg>
            </button> -->
        </div>

        <!-- <div id="mobileMenu" class="hidden py-4 sm:hidden">
            <div class="flex flex-col items-center space-y-1">
                <router-link class="mobile-router-link" :to="{ name: 'Home' }">Home</router-link>
            </div>
        </div> -->
    </div>
</template>

<script>
export default {
    name: "Header",
    setup() {
        const toggleMobileMenu = () => {
            const mobileMenu = document.querySelector("#mobileMenu")
            mobileMenu.classList.toggle("hidden")
        }

        return {
            toggleMobileMenu,
        }
    },
}
</script>

<style scoped>
.router-link {
    @apply font-bold hover:text-purple hover:underline text-lg py-4;
}

.mobile-router-link {
    @apply font-bold hover:bg-purple hover:bg-opacity-30 hover:underline text-lg py-2 w-full text-center;
}

.router-link-active {
    @apply text-purple underline;
}
</style>
