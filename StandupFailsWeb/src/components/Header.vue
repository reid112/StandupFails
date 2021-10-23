<template>
    <div class="w-full bg-white shadow-md sm:flex sm:flex-row sm:items-center">
        <div class="p-4 sm:w-1/3">
            <img src="standup-fails-logo.png" width="200" alt="Standup Fails" />
        </div>

        <div class="p-4 text-center bg-opacity-25 sm:w-1/3 bg-blue sm:bg-transparent">
            <div>
                <router-link class="router-link" :to="{ name: 'Home' }">Home</router-link>
                <router-link class="router-link" :to="{ name: 'Leaderboard' }">Leaderboard</router-link>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "Header",
    props: {
        
    },
}
</script>

<style scoped>
.router-link {
    @apply block w-full text-center font-bold hover:text-purple hover:underline sm:inline-block sm:w-auto text-lg sm:px-1 md:px-2;
}

.router-link-active {
    @apply text-purple underline;
}
</style>
