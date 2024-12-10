<template>
  <div>
    <div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import RHeader from "@/components/rheader";
import RightNav from "@/components/right-nav";
export default {
  name: "StudentIndex",
  components: {RightNav, RHeader}
}
</script>

<style scoped>

</style>