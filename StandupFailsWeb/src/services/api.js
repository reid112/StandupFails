import axios from "axios"

export default () => {
    let instance = axios.create({
        baseURL: process.env.VUE_APP_API_URL,
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