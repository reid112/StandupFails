import { createApp } from "vue"
import naive from "naive-ui"
import App from "./App.vue"
import router from "./router"
import store from "./store"
import "./index.css"

const app = createApp(App)

app.use(store)
app.use(router)
app.use(naive)

app.mount("#app")
