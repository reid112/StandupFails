<template>
    <div>
        <fail-button @new-fail-added="newFailAdded" />
        <leaderboard :key="leaderboardKey" />
    </div>
</template>

<script>
import FailButton from "@/components/FailButton"
import Leaderboard from "@/components/Leaderboard"
import { ref } from "vue"

export default {
    name: "Home",
    components: {
        FailButton,
        Leaderboard
    },
    setup() {
        const leaderboardKey = ref(0)

        function newFailAdded() {
            leaderboardKey.value++
        }

        return {
            leaderboardKey: leaderboardKey,
            newFailAdded: newFailAdded,
        }
    },
}
</script>