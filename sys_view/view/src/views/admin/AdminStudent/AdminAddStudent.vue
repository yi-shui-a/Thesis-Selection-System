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
      <el-form-item label="学生学号" prop="sno">
        <el-input v-model="ruleForm.sno"></el-input>
      </el-form-item>
      <el-form-item label="学生姓名" prop="studentName">
        <el-input v-model="ruleForm.studentName"></el-input>
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
      <el-form-item label="学年" prop="schoolyear">
        <el-select v-model="ruleForm.schoolyear" placeholder="请选择学年">
          <el-option
              v-for="item in infoList.schoolyearList"
              :key="item.schoolyear"
              :label="item.schoolyear"
              :value="item.schoolyear"
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

import {GetSchoolYearList} from "@/api/static_data_api";
import {AddStudent} from "@/api/admin_api";

export default {
  data() {


    var check_sno = (rule, value, callback) => {
      if (!Number.isInteger(value)) {
        callback(new Error("请输入数字值"));
      } else {
        callback();
      }
    };

    return {
      infoList: {
        sexList: ["男", "女"],
        schoolyearList:[]
      },
      ruleForm: {
        sno: "",
        password:123456,
        studentName: "",
        sex: "",
        sexList: ["男", "女"],
      },

      rules: {

        sno: [
          { required: true, message: "请输入学号", trigger: "blur" },
          { pattern:/^-?\d+$/,message: "请输入数字", trigger: "blur" },
        ],
        studentName: [
          { required: true, message: "请输入名称", trigger: "blur" },
          { min: 2, max: 5, message: "长度在 2 到 5 个字符", trigger: "blur" },
        ],
        sex: [
          { required: true, message: "请选择性别", trigger: "blur" },
        ],
        schoolyear: [
          { required: true, message: "请选择学年", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 通过前端校验
          AddStudent(this.ruleForm).then((res) => {
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
    GetSchoolYearList().then((res) => {
      this.infoList.schoolyearList = res.data.data;
    });
  }
};
</script>
