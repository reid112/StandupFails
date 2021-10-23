<template>
    <div class="header">
        <div class="title-container">
            <p class="title">
                <span class="text-logo text-purple text-lg">S</span>
                <span class="text-md">tandup</span>
                <span class="text-logo text-blue text-lg">F</span>
                <span class="text-md">ails</span>
            </p>
        </div>

        <div class="nav-container">
            <div>
                <router-link :to="{ name: 'Home' }">Home</router-link>
                <router-link :to="{ name: 'Leaderboard' }">Leaderboard</router-link>
            </div>
        </div>

        <div class="avatar-container">
            <!-- <n-avatar class="avatar" round :size="36">RJ</n-avatar> -->
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

<style scoped lang="scss">
.header {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    height: 80px;
    border-bottom: 1px solid $separator;
    background: $white;
    width: 100%;

    .title-container, .nav-container, .avatar-container {
        width: 33vw;
        height: 100%;
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: center;
        padding: 0 10px;
    }

    .title-container {
        justify-content: flex-start;

        .text-logo {
            font-size: 48px;
        }

        .title {
            font-size: 36px;
        }
    }

    .nav-container {
        text-align: center;

        a {
            color: $primary-color;
            padding: 10px;
            font-size: 18px;

            &.router-link-exact-active {
                font-weight: bold;
                color: $purple;
            }
        }
    }

    .avatar-container {
        justify-content: flex-end;

        .avatar {
            background-color: $purple;
        }
    }
}
</style>
