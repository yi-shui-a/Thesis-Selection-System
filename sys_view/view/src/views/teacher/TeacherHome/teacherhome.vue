<template>
<div>
  <el-container>
    <el-main>
      <!--        <h1>teacher home</h1>-->
      <!--        <info-card></info-card>-->
      <div>
        <el-card style="width: 60%
        ; margin-top: 10px">

          <el-descriptions title="我的信息">
            <el-descriptions-item label="工号">{{ teacherData.tno }}</el-descriptions-item>
            <el-descriptions-item label="姓名">{{ teacherData.teacherName }}</el-descriptions-item>
            <el-descriptions-item label="性别">{{ teacherData.sex }}</el-descriptions-item>
            <!--            年龄标签-->
            <el-descriptions-item label="生日">{{ teacherData.birth }}</el-descriptions-item>
            <el-descriptions-item label="电话">{{ teacherData.tel }}</el-descriptions-item>
            <el-descriptions-item label="邮箱">{{ teacherData.email }}</el-descriptions-item>

            <el-descriptions-item label="职称">

              <el-tag size="small">{{ teacherData.title }}</el-tag>
            </el-descriptions-item>
            <!--          学历标签-->
            <el-descriptions-item label="学历">
              <el-tag size="small">{{ teacherData.education }}</el-tag>
            </el-descriptions-item>
          </el-descriptions>
        </el-card>
      </div>
      <!-- <el-card style="width: 60%">
        <p><i class="el-icon-s-custom" style="margin-right: 18px">Created By 段宇辉</i></p>
        <p><i class="el-icon-s-flag" style="margin-right: 18px">前后端分离，基于Springboot和Vue，数据库使用Mysql，数据库ORM框架采用Mybatis-Plus</i>
        </p>

      </el-card> -->

    </el-main>
  </el-container>
</div>
</template>

<script>
import {getTeacherInfo, GetTeacherTeachNum} from "@/api/teacher_api";
export default {
  name: "teacherhome",
data() {
  return {
    tno: sessionStorage.getItem('tno'),
    name: sessionStorage.getItem('teacherName'),
    age: sessionStorage.getItem('age'),
    education: sessionStorage.getItem('education'),
    email: sessionStorage.getItem('email'),
    tel: sessionStorage.getItem('tel'),
    birth: sessionStorage.getItem('birth'),
    teacherData:{}

  };
},
created() {
  // getTeacherInfo(sessionStorage.getItem('tno')).then(res => {
  //   sessionStorage.setItem('name', res.data.data.name)
  //   sessionStorage.setItem('age', res.data.data.age)
  //   sessionStorage.setItem('education', res.data.data.education)
  //   sessionStorage.setItem('email', res.data.data.email)
  //   sessionStorage.setItem('tel', res.data.data.tel)
  //   sessionStorage.setItem('birth', res.data.data.birth)
  // })
  getTeacherInfo(sessionStorage.getItem('tno')).then(res => {
    this.teacherData = res.data.data
  })
  GetTeacherTeachNum(sessionStorage.getItem('tno')).then(res => {
    this.teacherData = res.data.data
  })
}
}
</script>


<style scoped>

</style>