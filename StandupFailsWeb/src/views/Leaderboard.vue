<template>
  <div class="leaderboard">
      <h1>Leaderboard</h1>

      <div class="text-center" v-if="isLoading"><n-spin class="text-center" v-if="isLoading" size="large" /></div>
      <p v-else-if="fails === null || fails.length === 0">No Fails!</p>
      <n-table v-else :bordered="false" :single-line="false">
          <thead>
              <tr>
                  <th>Name</th>
                  <th>Last Fail</th>
                  <th>Total Fails</th>
              </tr>
          </thead>
          <tbody>
              <tr v-for="fail in fails" :key="fail.username">
                  <td>{{ fail.username }}</td>
                  <td>{{ fail.lastFailedDate }}</td>
                  <td>{{ fail.failCount }}</td>
              </tr>
          </tbody>
      </n-table>
  </div>
</template>

<script>
import { ref } from "vue"
import { useStore } from "vuex"
import moment from "moment"

export default {
    name: "Leaderboard",
    setup() {
        const store = useStore()
        const fails = ref([])
        const isLoading = ref(true)

        store.dispatch("getFails").then((response) => {
            response.data.forEach(fail => {
                if (fail.lastFailedDate) {
                    fail.lastFailedDate = moment(fail.lastFaileDate).format("MMMM Do, YYYY")
                } else {
                    fail.lastFailedDate = "Never"
                }
            })
            fails.value = response.data

            isLoading.value = false
        })
        
        return {
            fails,
            isLoading,
        }
    },
}
</script>
