<template>
  <div >
    <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

      <el-form-item label="我的姓名" prop="studentName">
        <el-input v-model="ruleForm.studentName" :value="ruleForm.studentName"></el-input>
      </el-form-item>
      <el-form-item label="我的电话" prop="tel" >
        <el-input v-model="ruleForm.tel"></el-input>
      </el-form-item>
      <el-form-item label="我的邮箱" prop="email" >
        <el-input v-model="ruleForm.email"></el-input>
      </el-form-item>

      <el-form-item label="性别" prop="sex">
        <el-select v-model="ruleForm.sex" placeholder="请选择性别">
          <el-option
              v-for="item in infoList.tsexList"
              :key="item"
              :label="item"
              :value="item"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="生日" prop="birth">
        <el-date-picker
            v-model="ruleForm.birth"
            type="date"
            placeholder="选择日期">
        </el-date-picker>
      </el-form-item>


      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>

      </el-form-item>
    </el-form>
  </div>
</template>
<script>

import {EditStudentInfo, GetStudentInfo} from "@/api/student_api";

export default {
  data() {
    return {

      ruleForm: {
        sno: sessionStorage.getItem("sno"),
        studentName: null,
        sex:null,
        tel:null,
        email:null,
        birth: null,
      },
      infoList: {
        tsexList: ["男", "女"],
      },
      rules: {
        studentName: [
          {required: true, message: "请输入名称", trigger: "blur"},
          {min: 2, max: 5, message: "长度在 2 到 5 个字符", trigger: "blur"},
        ],
        sex: [
          {required: true, message: "请选择性别", trigger: "blur"},
        ],
        tel: [
          {required: true, message: "请输入电话", trigger: "blur"},
          {pattern: /^-?\d+$/, message: "请输入数字", trigger: "blur"},
        ],
        email: [
          {required: true, message: "请输入邮箱", trigger: "blur"},
          {type: "email", message: "请输入正确的邮箱地址", trigger: ["blur", "change"]},
        ],
      }
    };
  },
  created() {
    GetStudentInfo(sessionStorage.getItem('sno')).then(res => {
      this.ruleForm = res.data.data
    })


  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 通过前端校验
          const that = this
          console.log(this.ruleForm)
          this.ruleForm.birth= this.dayjs(this.ruleForm.birth).format("YYYY-MM-DD")
          EditStudentInfo(this.ruleForm).then(res=>{
            if (res.data.code === 200) {
              this.$message({
                message: "修改成功",
                type: "success",
              });
            } else {
              this.$message({
                message: "修改失败",
                type: "error",
                duration: 1000,
              });
            }
          })

        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    test() {
      console.log(this.ruleForm)
    },


  }

}
</script>