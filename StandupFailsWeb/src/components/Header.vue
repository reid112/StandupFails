<template>
    <div class="w-full sm:flex sm:flex-row sm:items-center sm:justify-between">
        <div class="p-4">
            <img class="mx-auto" src="standup-fails-logo.png" width="200" alt="Standup Fails" />
        </div>

        <div class="p-4 bg-opacity-25 sm:text-right bg-blue sm:bg-transparent">
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
    @apply block w-full text-center font-bold hover:text-purple hover:underline sm:inline-block sm:w-auto text-lg pl-4;
}

.router-link-active {
    @apply text-purple underline;
}
</style>
