<template>
  <div>
    <el-container>
      <el-main>
        <!--        <h1>student home</h1>-->
        <!--        <info-card></info-card>-->
        <div>
          <el-card style="width: 60%
        ; margin-top: 10px">

            <el-descriptions title="我的信息">
              <el-descriptions-item label="学号">{{ studentData.sno }}</el-descriptions-item>
              <el-descriptions-item label="姓名">{{ studentData.studentName }}</el-descriptions-item>
              <el-descriptions-item label="性别">{{ studentData.sex }}</el-descriptions-item>
              <!--            年龄标签-->
              <el-descriptions-item label="生日">{{ studentData.birth }}</el-descriptions-item>
              <el-descriptions-item label="电话">{{ studentData.tel }}</el-descriptions-item>
              <el-descriptions-item label="邮箱">{{ studentData.email }}</el-descriptions-item>

              <el-descriptions-item label="可选题目数">

                <el-tag size="small">{{ studentData.ableTopicNum }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="已选题目数">
                <el-tag size="small">{{ studentData.selectedTopicNum }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="入学年份">
                <el-tag size="small">{{ studentData.schoolyear }}</el-tag>
              </el-descriptions-item>
            </el-descriptions>
          </el-card>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import {GetStudentInfo} from "@/api/student_api";
export default {
  name: "studenthome",
  data() {
    return {
      studentData:{}

    };
  },
  created() {
    GetStudentInfo(sessionStorage.getItem('sno')).then(res => {
      this.studentData = res.data.data
    })

  }
}
</script>


<style scoped>

</style>