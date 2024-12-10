<template>
  <div>
    <el-form
        ref="ruleForm"
        :model="ruleForm"
        :rules="rules"
        class="demo-ruleForm"
        label-width="100px"
        style="width: 60%"
    >
      <el-form-item label="老师工号" prop="tno">
        <el-input v-model="ruleForm.tno"></el-input>
      </el-form-item>
      <el-form-item label="老师姓名" prop="teacherName">
        <el-input v-model="ruleForm.teacherName"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="ruleForm.sex" placeholder="请选择性别">
          <el-option
              v-for="item in infoList.sexList"
              :key="item"
              :label="item"
              :value="item"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="职称" prop="title">
        <el-select v-model="ruleForm.title" placeholder="请选择职称">
          <el-option
              v-for="item in infoList.titleList"
              :key="item.title"
              :label="item.title"
              :value="item.title"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="学历" prop="title">
        <el-select v-model="ruleForm.education" placeholder="请选择学历">
          <el-option
              v-for="item in infoList.educationList"
              :key="item.education"
              :label="item.education"
              :value="item.education"
          >
          </el-option>
        </el-select>
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

import {GetEducationList, GetSchoolYearList, GetTeacherTitleList} from "@/api/static_data_api";
import {AddStudent, AddTeacher} from "@/api/admin_api";

export default {
  data() {


    var check_tno = (rule, value, callback) => {
      if (!Number.isInteger(value)) {
        callback(new Error("请输入数字值"));
      } else {
        callback();
      }
    };

    return {
      infoList: {
        sexList: ["男", "女"],
        titleList:[],
        educationList:[],
      },
      ruleForm: {
        tno: "",
        password:123456,
        teacherName: "",
        sex: "",
        sexList: ["男", "女"],
        education: "",

      },

      rules: {

        tno: [
          { required: true, message: "请输入学号", trigger: "blur" },
          { pattern:/^-?\d+$/,message: "请输入数字", trigger: "blur" },
        ],
        teacherName: [
          { required: true, message: "请输入名称", trigger: "blur" },
          { min: 2, max: 5, message: "长度在 2 到 5 个字符", trigger: "blur" },
        ],
        sex: [
          { required: true, message: "请选择性别", trigger: "blur" },
        ],
        title: [
          { required: true, message: "请选择职称", trigger: "blur" },
        ],
        education: [
          { required: true, message: "请选择学历", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 通过前端校验
          AddTeacher(this.ruleForm).then((res) => {
            if (res.data.code === 200) {
              this.$message({
                message: "添加成功",
                type: "success",
              });
            } else {
              this.$message({
                message: res.data.msg,
                type: "error",
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
  },
  created() {
    GetTeacherTitleList().then((res) => {
      this.infoList.titleList = res.data.data;
    });
    GetEducationList().then((res) => {
      this.infoList.educationList = res.data.data;
    });
  }
};
</script>
