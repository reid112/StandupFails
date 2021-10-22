import axios from "axios"

export default () => {
    let instance = axios.create({
        baseURL: process.env.API_URL || "http://localhost:8880/",
    })

    // Set the AUTH token for any request
    instance.interceptors.request.use(async (config) => {
        // if (firebase.auth().currentUser) {
        //     const token = await firebase.auth().currentUser.getIdToken()
        //     config.headers.Authorization = token ? `Bearer ${token}` : ""
        // }

        return config
    })

    return instance
}