<template>
  <div>
<!--    <el-container>-->
<!--      <el-header style="text-align: right; font-size: 18px; background-color: #409EFF; border-radius: 3px">-->
<!--        <r-header></r-header>-->
<!--      </el-header>-->
<!--      <el-container>-->
<!--        <right-nav></right-nav>-->
<!--        <el-main>-->
    <div>
      <router-view></router-view>
    </div>
<!--        </el-main>-->
<!--      </el-container>-->
<!--    </el-container>-->
  </div>
</template>

<script>
import RHeader from "@/components/rheader";
import RightNav from "@/components/right-nav";
export default {
  name: "teacher",
  components: {RightNav, RHeader}
}
</script>

<style scoped>

</style>