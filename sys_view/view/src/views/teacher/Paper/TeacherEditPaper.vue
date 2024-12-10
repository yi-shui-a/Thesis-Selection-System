<template>
  <div >
    <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

      <el-form-item label="选题编号" prop="pno" >
        <el-input :disabled="true"v-model="ruleForm.pno" :value="ruleForm.pno"></el-input>
      </el-form-item>
      <el-form-item label="审核状态" prop="title">
        <el-input :disabled="true" v-model="checkStatus_vo" :value="checkStatus_vo"></el-input>
      </el-form-item>
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
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
        <el-button type="danger" @click="reCheckPaper('ruleForm')">重新提交审核</el-button>
        <el-button type="danger" @click="returnold()">返回上一页</el-button>
      </el-form-item>


    </el-form>
  </div>
</template>

<script>
import {GetEducationList, GetSchoolYearList, GetTeacherTitleList, GetTitleType} from "@/api/static_data_api";
import {EditPaper, EditTeacherInfo, GetPaperByPno, getTeacherInfo} from "@/api/teacher_api";
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
        pno: [
          {required: true, message: '请输入选题编号', trigger: 'blur'},
        ],
        title: [
          {required: true, message: '请输入选题名称', trigger: 'blur'},
        ],
        description: [
          {required: true, message: '请输入选题描述', trigger: 'blur'},
        ],
      }
    };
  },

  created() {
    GetPaperByPno(this.$route.query.pno).then(res => {
      this.ruleForm = res.data.data
      if(this.ruleForm.checkStatus === 'NOT_CHECK'){
        this.checkStatus_vo = "审核中"
      }else if(this.ruleForm.checkStatus === 'CHECK'){
        this.checkStatus_vo = "审核通过"
      }else if(this.ruleForm.checkStatus === 'REFUSE'){
        this.checkStatus_vo = "审核未通过"
      }
    })
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
          EditPaper(this.ruleForm).then(res => {
            if (res.data.code === 200) {
              this.$message({
                message: '修改成功',
                type: 'success'
              });
              //两秒后跳转
              setTimeout(function () {
                that.$router.push({path: '/teacher/my_paper'})
              }, 2000)

            } else {
              this.$message({
                message: '修改失败',
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
    reCheckPaper(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if(this.ruleForm.checkStatus === 'CHECK'){
            this.$message({
              message: '审核通过的选题不能重新提交审核',
              type: 'error'
            });
            return
          }
          // 通过前端校验
          const that = this
          this.ruleForm.checkStatus = 'NOT_CHECK'
          EditPaper(this.ruleForm).then(res => {
            if (res.data.code === 200) {
              this.$message({
                message: '修改成功',
                type: 'success'
              });
              //两秒后跳转
              setTimeout(function () {
                that.$router.push({path: '/teacher/my_paper'})
              }, 2000)

            } else {
              this.$message({
                message: '修改失败',
                type: 'error'
              });
            }
          })

        }
      });
    },
    returnold(){
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped>

</style>