<template>
  <div>
    <div class="t-table" style="width:50%;">
      <t-table :table="table" :columns="columns" />
    </div>
    <el-form style="width: 50%" :model="schoolyear"  label-width="100px" class="demo-ruleForm">
      <el-form-item label="学年" prop="type">
        <el-input v-model="schoolyear" :value="schoolyear"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm()">提交</el-button>
      </el-form-item>
    </el-form>

  </div>

</template>

<script>
import {GetEducationList, GetSchoolYearList, GetTeacherTitleList} from "@/api/static_data_api";
import {
  AddEducation,
  AddSchoolyear,
  AddTeacherTitle,
  DeleteEducation,
  DeleteSchoolyear,
  DeleteTeacherTitle
} from "@/api/admin_api";

export default {
  name: "AdminTeacherEducation",
  data(){
    let that = this
    return {
      schoolyear:null,
      table: {
        data: [
        ]
      },
      rules: {
        schoolyear: [
          { required: true, message: '请输入学年', trigger: 'blur' }
        ]
      },
      columns: [
        { prop: 'schoolyear', label: '学年', minWidth: '100', sort: true },
        {
          prop: 'address', label: '操作', minWidth: '250',
          customRender: {
            comps: [
              {
                comp: 'el-popconfirm',
                bind: {
                  title: '确认删除该条数据？'
                },
                event (scope) {
                  return {
                    confirm () {
                      that.handledeleteTable(scope.row)
                    }
                  }
                },
                child: [
                  {
                    slot: 'reference',
                    comp: 'el-link',
                    text: '删除',
                    bind: {
                      type: 'primary'
                    }
                  }
                ]
              }
            ]
          }
        }

      ]
    }
  },
  created() {
    GetSchoolYearList().then(res => {
      this.table.data = res.data.data
    })
  },
  methods:{
    submitForm(){
      if(this.schoolyear==null){
        this.$message({
          message: '请输入学年',
          type: 'warning'
        });
      }
      //判断是否为数值
      else if(isNaN(this.schoolyear)){
        this.$message({
          message: '请输入正确的学年',
          type: 'warning'
        });
      }
      else{
        AddSchoolyear(this.schoolyear).then(res => {
          if(res.data.code===200){
            this.$message({
              message: '添加成功',
              type: 'success'
            });
            GetSchoolYearList().then(res => {
              this.table.data = res.data
            })
            //两秒后刷新页面
            setTimeout(function () {
              window.location.reload();
            }, 2000);
          }
          else{
            this.$message({
              message: '添加失败',
              type: 'error'
            });
          }
        })
      }
    },
    handledeleteTable(row){
      DeleteSchoolyear(row.schoolyear).then(res => {
        if(res.data.code===200){
          this.$message({
            message: '删除成功',
            type: 'success'
          });
          GetTeacherTitleList().then(res => {
            this.table.data = res.data
          })
          //两秒后刷新页面
          setTimeout(function () {
            window.location.reload();
          }, 2000);
        }
        else{
          this.$message({
            message: '删除失败',
            type: 'error'
          });
        }
      })
    }
  }
}
</script>

<style scoped>

</style>