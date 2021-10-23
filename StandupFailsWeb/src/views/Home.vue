<template>
    <div>
        <div class="card"> 
            <div class="text-center">
                <button 
                    class="w-40 h-40 text-2xl font-extrabold text-white transition duration-300 border-2 border-black rounded-full shadow-lg bg-opacity-90 bg-red hover:bg-opacity-100 active:bg-opacity-100"
                    @click="showModal = true"
                >
                    FAIL
                </button>
            </div>

            <n-divider/>

            <p class="m-4 text-3xl font-bold text-center">King of Fails:</p>
            <div class="text-center" v-if="isLoading"><n-spin size="large" /></div>
            <p v-else class="text-xl text-center text-red">{{ biggestFailure ? biggestFailure.displayName : "---" }}</p>
        </div>

        <n-modal v-model:show="showModal">
            <div class="shadow-none">
                <div class="w-screen max-w-screen-md mx-auto text-center card">
                    <n-result
                        status="500"
                        size="huge"
                        title="SHAME"
                        description="Who failed this time?"
                    />

                    <n-space vertical class="text-left">
                        <n-select v-model:value="selectedUser" :options="users" />
                        <n-input v-model:value="password" type="password" placeholder="Whats the super secret password??" />
                    </n-space>

                    <n-button color="#00A1A8" :loading="isLoading" :disabled="isLoading || !selectedUser || !password" @click="saveFailure" style="margin-top: 20px;">
                        FAIL
                    </n-button>
                </div>
            </div>
        </n-modal>
    </div>
</template>

<script>
import { ref } from "vue"
import { useStore } from "vuex"
import { useMessage } from "naive-ui"

export default {
    name: "Home",
    setup () {
        const users = ref([])
        const selectedUser = ref(null)
        const biggestFailure = ref(null)
        const showModal = ref(false)
        const password = ref(null)
        const isLoading = ref(true)

        const store = useStore()
        const message = useMessage()

        function success(msg) {
            message.success(msg)
        }
        
        function error(msg) {
            message.error(msg)
        }

        function getUserWithMostFails() {
            isLoading.value = true
            store.dispatch("getUserWithMostFails").then((response) => {
                isLoading.value = false
                biggestFailure.value = response.data
            })
        }

        function getUsers() {
            store.dispatch("getUsers").then((response) => {
                response.data.forEach(user => {
                    users.value.push({ label: user.displayName, value: user.id })
                })
            })
        }

        function saveFailure() {
            if (password.value !== process.env.VUE_APP_ADD_FAIL_PASSWORD) {
                error("Nice try.  Get Gud!")
                return
            }

            isLoading.value = true

            let request = {
                userId: selectedUser.value,
            }
            store.dispatch("addFail", request).then(() => {
                success("Fail Added Successfully!")
                isLoading.value = false
                showModal.value = false
                selectedUser.value = null

                getUserWithMostFails()
            }).catch(() => {
                error("Error")
            })
        }

        getUsers()
        getUserWithMostFails()

        return {
            showModal: showModal,
            selectedUser: selectedUser,
            users: users,
            biggestFailure: biggestFailure,
            isLoading: isLoading,
            saveFailure: saveFailure,
            password: password,
        }
    },
}
</script>