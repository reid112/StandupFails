<template>
  <div class="home">
      <n-card size="huge"> 
          <div class="fail-button-container">
              <n-button @click="showModal = true" class="fail-button text-lg" type="error">
                  FAIL
              </n-button>
          </div>

          <n-divider/>

          <p class="biggest-failure-title text-center">Biggest Failure</p>
          <div class="text-center" v-if="isLoading"><n-spin size="large" /></div>
          <p v-else class="biggest-failure-value text-center">{{ biggestFailure ? biggestFailure.displayName : "No fails yet, what?!" }}</p>
      </n-card>

      <!-- TODO: Move this to its own component -->
      <n-modal v-model:show="showModal">
          <n-card style="width: 600px; text-align: center;" :bordered="false" size="huge">
              <n-result
                  status="500"
                  size="huge"
                  title="SHAME"
                  description="Who failed this time?"
              />

              <n-select v-model:value="selectedUser" :options="users" />
              
              <n-button color="#00A1A8" :loading="isLoading" :disabled="isLoading" @click="saveFailure" style="margin-top: 20px;">
                  FAIL
              </n-button>
          </n-card>
      </n-modal>
  </div>
</template>

<script>
import { ref } from "vue"
import { useStore } from "vuex"
import { useMessage } from "naive-ui"

export default {
    name: "Home",
    components: {

    },
    setup () {
        const users = ref([])
        const selectedUser = ref(null)
        const biggestFailure = ref(null)
        const showModal = ref(false)
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
        }
    },
}
</script>

<style lang="scss" scoped>
.home {
    .fail-button-container {
        text-align: center;

        .fail-button {
            height: 200px;
            width: 200px;
            margin: 20px auto;
            border-radius: 200px;
            border: 5px solid $black;
        }
    }

    .biggest-failure-title {
        margin: 0;
        font-size: 24px;
    }

    .biggest-failure-value {
        margin: 0;
        font-size: 48px;
        color: $red;
    }
}
</style>
