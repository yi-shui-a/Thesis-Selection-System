<template>
  <div>
    <el-container>
      <el-main>
        <div>
          <el-card style="width: 60%
        ; margin-top: 10px">

            <el-descriptions title="我的信息">
              <el-descriptions-item label="姓名">{{ adminData.name }}</el-descriptions-item>
              <el-descriptions-item label="性别">{{ adminData.sex }}</el-descriptions-item>
              <el-descriptions-item label="电话">{{ adminData.tel }}</el-descriptions-item>
              <el-descriptions-item label="邮箱">{{ adminData.email }}</el-descriptions-item>
            </el-descriptions>
          </el-card>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import {GetAdminInfo} from "@/api/admin_api";

export default {
  name: "AdminHome",
  data() {
    return {
      adminData:{}
    };
  },
  created() {
    GetAdminInfo(sessionStorage.getItem('uid')).then(res => {
      this.adminData = res.data.data
    })
  }
}
</script>

<style scoped>

</style>