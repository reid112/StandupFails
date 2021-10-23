<template>
  <div class="h-screen bg-background">
    <div class="max-w-screen-lg mx-auto">
      <Header />
      <div class="content">
        <n-message-provider>
          <router-view />
        </n-message-provider>
      </div>
      <Footer />
    </div>
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
