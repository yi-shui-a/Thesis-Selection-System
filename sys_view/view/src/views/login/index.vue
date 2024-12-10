e<template>
  <div>
    <el-container>
      <el-header >
        <div style="text-align: center; font-size: 25px; font-weight: bolder">
          <i class="el-icon-s-home" style="margin-right: 25px"></i>
          论文选题系统
        </div>
      </el-header>
<!--      //登录界面，有两个输入框，一个是用户名，一个是密码，还有一个登录按钮，有3种角色，学生，老师，管理员-->
      <el-main>
        <el-form :model="form" :rules="rules" ref="form" label-width="80px" style="width: 20%; margin: auto auto">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="form.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="form.password" type="password"></el-input>
          </el-form-item>

          <el-form-item label="用户类型" prop="type">
            <el-radio-group v-model="form.type">
              <el-radio label="student" value="student">学生</el-radio>
              <el-radio label="teacher" value="teacher">老师</el-radio>
              <el-radio label="admin" value="admin">管理员</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="login">登录</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-main>

    </el-container>
  </div>
</template>

<script>
import {adminLogin, studentLogin, teacherLogin} from "@/api/login_api";

export default {
  name: "index",
  data() {
    return {
      form: {
        username: null,
        password: null,
        type: null
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" }
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" }
        ]
      }
    };
  },
  methods:{
    login() {
      const that=this;
      this.$refs.form.validate(valid => {
        if(that.form.type==='admin'){
          sessionStorage.removeItem("token")
          adminLogin(that.form.username,that.form.password).then(res=>{
            if(res.data.code===200){
              sessionStorage.setItem("token",res.data.data.token)
              sessionStorage.setItem("uid",that.form.username)
              sessionStorage.setItem("type",'admin')
              this.$router.push("/admin/home")
            }else{
              this.$message.error(res.data.msg)
            }
          })
        }
        else if(that.form.type==='teacher'){
          // sessionStorage.removeItem("token")
          teacherLogin(that.form.username,that.form.password).then(res=>{
            if(res.data.code===200){
              sessionStorage.setItem("tno",that.form.username)
              sessionStorage.setItem("token",res.data.data.token)
              sessionStorage.setItem("type",'teacher')
              this.$router.push("/teacher/home")
            }else{
              this.$message.error(res.data.msg)
            }
          })
        }
        else if(that.form.type==='student'){
          sessionStorage.removeItem("token")
          studentLogin(that.form.username,that.form.password).then(res=>{
            if(res.data.code===200){
              sessionStorage.setItem("sno",res.data.data.user.sno)
              sessionStorage.setItem("token",res.data.data.token)
              sessionStorage.setItem("type",'student')
              this.$router.push("/student/home")
            }else{
              this.$message.error(res.data.msg)
            }
          })
        }
        else if (that.form.type==='teacher'){
          sessionStorage.removeItem("token")
          teacherLogin(that.form.username,that.form.password).then(res=>{
            if(res.data.code===200){
              sessionStorage.setItem("token",res.data.data.token)
              sessionStorage.setItem("name",res.data.data.user.name)
              sessionStorage.setItem("email",res.data.data.user.email)
              sessionStorage.setItem("tel",res.data.data.user.tel)

              this.$router.push("/teacher")
            }else{
              this.$message.error(res.data.msg)
            }
          })
        }
        else{
          this.$message.error("请选择用户类型")
        }

      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

  }
}
</script>

<style scoped>

</style>