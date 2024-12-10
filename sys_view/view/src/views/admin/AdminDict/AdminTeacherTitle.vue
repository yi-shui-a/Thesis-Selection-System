<template>
  <div>
    <div class="t-table" style="width:50%;">
      <t-table :table="table" :columns="columns" />
    </div>
    <el-form style="width: 50%" :model="title"  label-width="100px" class="demo-ruleForm">
      <el-form-item label="职称类型" prop="type">
        <el-input v-model="title" :value="title"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm()">提交</el-button>
      </el-form-item>
    </el-form>

  </div>

</template>

<script>
import {GetEducationList, GetTeacherTitleList} from "@/api/static_data_api";
import {AddEducation, AddTeacherTitle, DeleteEducation, DeleteTeacherTitle} from "@/api/admin_api";

export default {
  name: "AdminTeacherEducation",
  data(){
    let that = this
    return {
      title:null,
      table: {
        data: [
        ]
      },
      rules: {
        title: [
          { required: true, message: '请输入职称类型', trigger: 'blur' }
        ]
      },
      columns: [
        { prop: 'title', label: '职称类型', minWidth: '100', sort: true },
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
    GetTeacherTitleList().then(res => {
      this.table.data = res.data.data
    })
  },
  methods:{
    submitForm(){
      if(this.title==null){
        this.$message({
          message: '请输入职称类型',
          type: 'warning'
        });
      }
      else{
        AddTeacherTitle(this.title).then(res => {
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
      DeleteTeacherTitle(row.title).then(res => {
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