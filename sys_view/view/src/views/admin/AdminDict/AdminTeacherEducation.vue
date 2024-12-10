<template>
  <div>
    <div class="t-table" style="width:50%;">
      <t-table :table="table" :columns="columns" />
    </div>
    <el-form style="width: 50%" :model="education"  label-width="100px" class="demo-ruleForm">
      <el-form-item label="学历类型" prop="type">
        <el-input v-model="education" :value="education"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm()">提交</el-button>
      </el-form-item>
    </el-form>

  </div>

</template>

<script>
import {GetEducationList} from "@/api/static_data_api";
import {AddEducation, DeleteEducation} from "@/api/admin_api";

export default {
  name: "AdminTeacherEducation",
  data(){
    let that = this
    return {
      education:null,
      table: {
        data: [
        ]
      },
      rules: {
        education: [
          { required: true, message: '请输入学历类型', trigger: 'blur' }
        ]
      },
      columns: [
        { prop: 'education', label: '学历类型', minWidth: '100', sort: true },
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
    GetEducationList().then(res => {
      this.table.data = res.data.data
    })
  },
  methods:{
    submitForm(){
      if(this.education==null){
        this.$message({
          message: '请输入学历类型',
          type: 'warning'
        });
      }
      else{
        AddEducation(this.education).then(res => {
          if(res.data.code===200){
            this.$message({
              message: '添加成功',
              type: 'success'
            });
            GetEducationList().then(res => {
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
      DeleteEducation(row.education).then(res => {
        if(res.data.code===200){
          this.$message({
            message: '删除成功',
            type: 'success'
          });
          GetEducationList().then(res => {
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