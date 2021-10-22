<template>
  <div class="container">
    <Header />
    <div class="content">
      <n-message-provider>
        <router-view />
      </n-message-provider>
    </div>
    <Footer />
  </div>
</template>

<script>
import Header from "@/components/Header"
import Footer from "@/components/Footer"

export default {
    name: "App",
    components: {
        Header,
        Footer,
    },
}
</script>

<style lang="scss">
.container {
  height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;

  .content {
    width: 60vw;
    padding: 20px;
    flex-grow: 1;
  }
}
</style>
