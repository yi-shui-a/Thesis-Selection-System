<template>
  <div>

    <div className="t-table" style="width:50%;">
      <t-table :table="table" :columns="columns"/>
    </div>
    <br>
    <div>
      <el-form
          ref="ruleForm"
          label-width="100px"
          style="width: 60%">
        <el-form-item label="学年" prop="schoolyear">
          <el-select style="float: left" v-model="ruleForm.schoolyear" placeholder="请选择学年">
            <el-option
                v-for="item in infoList.schoolyearList"
                :key="item.schoolyear"
                :label="item.schoolyear"
                :value="item.schoolyear"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="指导人数" prop="sno">
          <el-input style="width: 40%;float: left" v-model="ruleForm.canTeachNum"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')"
          >提交
          </el-button
          >
          <el-button @click="resetForm('ruleForm')">重置</el-button>
          <el-button @click="oldpage">返回上一页</el-button>

        </el-form-item>


      </el-form>
    </div>



  </div>

</template>

<script>
import { GetTeacherTeachNum} from "@/api/teacher_api";
import {GetSchoolYearList} from "@/api/static_data_api";
import {UpdateCanTeachNum} from "@/api/admin_api";

export default {
  data() {
    let that = this
    return {
      table: {
        data: []
      },

      infoList: {
        schoolyearList:[],
      },
      ruleForm: {
        tno:this.$route.query.tno,
        schoolyear:null,
        canTeachNum: null,
      },

      columns: [
        {prop: 'schoolyear', label: '学年', minWidth: '100', sort: true},
        {prop: 'canTeachNum', label: '可指导人数', minWidth: '180', sort: true},
        {prop: 'hasTeachNum', label: '已指导人数', minWidth: '180', sort: true},
      ],

    }
  },
  created() {

    GetTeacherTeachNum(this.$route.query.tno).then(res => {
      this.table.data = res.data.data
      this.infoList.schoolyearList = res.data.data

    })
  },
  methods:{
    submitForm(formName) {
      if(this.ruleForm.schoolyear==null){
        this.$message({
          message: '请选择学年',
          type: 'warning'
        });
        return
      }
      //判断是否为数字
      if(!/^[0-9]*$/.test(this.ruleForm.canTeachNum)){
        this.$message({
          message: '请输入数字',
          type: 'warning'
        });
        return
      }

      UpdateCanTeachNum([this.ruleForm]).then(res=>{
        if(res.data.code===200){
          this.$message({
            message: '修改成功',
            type: 'success'
          });
          GetTeacherTeachNum(this.$route.query.tno).then(res => {
            this.table.data = res.data.data
          })
        }
        else{
          this.$message({
            message: res.data.msg,
            type: 'error'
          });
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    oldpage(){
      this.$router.go(-1)
    }
  }

}
</script>

<style scoped>

</style>