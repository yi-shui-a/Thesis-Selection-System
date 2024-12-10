<template>
  <div >
    <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

      <el-form-item label="选题名称" prop="title">
        <el-input v-model="ruleForm.title" :value="ruleForm.title"></el-input>
      </el-form-item>
      <el-form-item label="选题描述" prop="description" >
        <el-input v-model="ruleForm.description"></el-input>
      </el-form-item>
      <el-form-item label="选题类型" prop="type">
        <el-select v-model="ruleForm.type"  placeholder="请选择">
          <el-option
              v-for="item in typeList"
              :label="item.type"
              :value="item.type"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="选题学年" prop="schoolyear" >
        <el-select v-model="ruleForm.schoolyear"  placeholder="请选择">
          <el-option
              v-for="item in schoolyearList"
              :label="item.schoolyear"
              :value="item.schoolyear"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">创建选题</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>

      </el-form-item>


    </el-form>
  </div>
</template>

<script>
import {GetEducationList, GetSchoolYearList, GetTeacherTitleList, GetTitleType} from "@/api/static_data_api";
import {CreatePaper, EditPaper, EditTeacherInfo, GetPaperByPno, getTeacherInfo} from "@/api/teacher_api";
export default {
  name: "TeacherEditPaper",

  data() {
    let that = this
    return {

      typeList: [],
      schoolyearList:[],
      checkStatus_vo: null,


      ruleForm: {
        pno: this.$route.query.pno,
        title: null,
        type: null,
        checkStatus: null,
        schoolyear: null,
        description: null,
      },

      rules: {
        title: [
          {required: true, message: '请输入选题名称', trigger: 'blur'},
        ],
        description: [
          {required: true, message: '请输入选题描述', trigger: 'blur'},
        ],
        type: [
          {required: true, message: '请选择选题类型', trigger: 'blur'},
        ],
        schoolyear: [
          {required: true, message: '请选择选题学年', trigger: 'blur'},
        ],

      }
    };
  },

  created() {
    GetSchoolYearList().then(res => {
      this.schoolyearList = res.data.data
    })
    GetTitleType().then(res => {
      this.typeList = res.data.data
    })





  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 通过前端校验
          const that = this
          CreatePaper(sessionStorage.getItem("tno"),this.ruleForm).then(res => {
            if (res.data.code === 200) {
              this.$message({
                message: '创建成功',
                type: 'success'
              });


            } else {
              this.$message({
                message: '创建失败',
                type: 'error'
              });
            }
          })

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