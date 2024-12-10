<template>
  <div>
    <el-form
        ref="ruleForm"
        :model="ruleForm"
        :rules="rules"
        class="demo-ruleForm"
        label-width="140px"
        style="width: 60%"
    >

      <el-form-item label="修改密码" prop="password">
        <el-input v-model="ruleForm.password" show-password :value="ruleForm.password"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmpassword">
        <el-input v-model="ruleForm.confirmpassword" show-password :value="ruleForm.confirmpassword"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')"
        >提交
        </el-button
        >
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>


import {UpdateAdminPassword} from "@/api/admin_api";

export default {
  data() {

    let validateConfirmPassword = (rule, value, callback) => {
      if (value == '') {
        callback(new Error("请输入确认密码"));
      } else if (value !== this.ruleForm.password) {
        callback(new Error("两次密码输入不一致"));
      } else {
        callback();
      }

    };


    return {
      ruleForm: {
        uid: sessionStorage.getItem("uid"),
        password: '',
        confirmpassword:'',
      },
      rules: {
        password: [
          { required: true, message:"请输入密码", trigger: 'blur' }
        ],
        confirmpassword: [
          { required: true, validator: validateConfirmPassword, trigger: 'blur' }
        ],
      },


    };
  },



  methods: {
    submitForm(formName) {
      console.log(this.ruleForm);
      this.$refs[formName].validate((valid) => {
        console.log(this.ruleForm);
        if (valid) {
          // 通过前端校验
          UpdateAdminPassword(sessionStorage.getItem("uid"),this.ruleForm.password).then(response => {
            if (response.data.code === 200) {
              this.$message({
                message: '修改成功，2秒后跳转到登录页面',
                type: 'success'
              });
              //两秒钟后跳转到登录页面
              setTimeout(() => {
                this.$router.push({path: '/'});
              }, 2000);
            }
            else {
              this.$message({
                message: '修改失败',
                type: 'error'
              });
            }
          });

        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();

    },
    test() {
      console.log(this.ruleForm);
    },
  },

}
</script>

<style scoped>

</style>