import { createRouter, createWebHashHistory } from "vue-router"
import Home from "../views/Home.vue"
import Leaderboard from "../views/Leaderboard"

const routes = [
    {
        path: "/",
        name: "Home",
        component: Home,
    },
    {
        path: "/leaderboard",
        name: "Leaderboard",
        component: Leaderboard,
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes,
})

export default router
