import { createStore } from "vuex"
import api from "@/services/api"

export default createStore({
    state: {
    },
    mutations: {
    },
    actions: {
        async getUsers() {
            const path = "v1/users"
            const response = await api().get(path)
            return response
        },
        async getUserWithMostFails() {
            const path = "v1/users/fails/top"
            const response = await api().get(path)
            return response
        },
        async getFails() {
            const path = "v1/fail"
            const response = await api().get(path)
            return response
                
        },
        async addFail(context, request) {
            const path = "v1/fail"
            const response = await api().post(path, request)
            return response
        }
    },
    modules: {
    },
})
