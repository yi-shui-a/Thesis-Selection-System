<template>
  <div >
    <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

      <el-form-item label="姓名" prop="teacherName">
        <el-input v-model="ruleForm.teacherName" :value="ruleForm.teacherName"></el-input>
      </el-form-item>
      <el-form-item label="教师密码" prop="password">
        <el-button type="primary" @click="resetPassword('ruleForm')"
        >重置教师密码为123456
        </el-button
        >
      </el-form-item>
      <el-form-item label="电话" prop="tel" >
        <el-input v-model="ruleForm.tel"></el-input>
      </el-form-item>
      <el-form-item label="我的邮箱" prop="email" >
        <el-input v-model="ruleForm.email"></el-input>
      </el-form-item>

      <el-form-item label="职称" prop="title">
        <el-select v-model="ruleForm.title"  placeholder="请选择">
          <el-option
              v-for="item in titleList"
              :label="item.title"
              :value="item.title"
          >
          </el-option>
        </el-select>
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

      <!--      学历-->
      <el-form-item label="学历" prop="education">
        <el-select v-model="ruleForm.education" placeholder="请选择">
          <el-option
              v-for="item in educationList"
              :key="item.label"
              :label="item.education"
              :value="item.education"
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
        <el-button @click="oldpage">返回上一页</el-button>

      </el-form-item>
    </el-form>
  </div>
</template>
<script>

import {GetEducationList, GetTeacherTitleList} from "@/api/static_data_api";
import {EditTeacherInfo, getTeacherInfo} from "@/api/teacher_api";
import {ResetStudentPassword, ResetTeacherPassword} from "@/api/admin_api";

export default {
  data() {
    return {
      titleList: [],
      educationList:[],


      ruleForm: {
        tno: this.$route.query.tno,
        teacherName: null,
        title: null,
        sex:null,
        tel:null,
        email:null,
        education: null,
        birth: null,
      },
      resetpass: {
        tno:null,
        password:123456,
      },
      infoList: {
        tsexList: ["男", "女"],
      },
      rules: {
        teacherName: [
          {required: true, message: "请输入名称", trigger: "blur"},
          {min: 2, max: 5, message: "长度在 2 到 5 个字符", trigger: "blur"},
        ],
        sex: [
          {required: true, message: "请选择性别", trigger: "blur"},
        ],
        collegeid: [
          {required: true, message: "请输入学院", trigger: "blur"},
        ],
        tel: [
          {required: true, message: "请输入电话", trigger: "blur"},
          {pattern: /^-?\d+$/, message: "请输入数字", trigger: "blur"},
        ],
        education: [
          {required: true, message: "请输入学历", trigger: "blur"},
        ],
        email: [
          {required: true, message: "请输入邮箱", trigger: "blur"},
          {type: "email", message: "请输入正确的邮箱地址", trigger: ["blur", "change"]},
        ],
      }
    };
  },
  created() {
    getTeacherInfo(this.$route.query.tno).then(res => {
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
          EditTeacherInfo(this.ruleForm).then(res=>{
            if (res.data.code === 200) {
              this.$message({
                message: "修改成功",
                type: "success",
                // duration: 1000,
                // onClose: function () {
                //   that.$router.push({path: "/teacher/teacherInfo"})
                // }
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
    oldpage() {
      this.$router.go(-1)
    },
    resetPassword(){
      ResetTeacherPassword(this.ruleForm.tno).then(res=>{
        if (res.data.code === 200) {
          this.$message({
            message: "重置成功",
            type: "success",
            duration: 1000,
          });
        } else {
          this.$message({
            message: "重置失败",
            type: "error",
            duration: 1000,
          });
        }
      })
    }


  }

}
</script>