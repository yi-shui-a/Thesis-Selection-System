<template>
  <div>
    <div class="t-table" style="width:50%;">
      <t-table :table="table" :columns="columns" />
    </div>
    <el-form style="width: 50%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="选题类型" prop="name">
        <el-input v-model="ruleForm.type" :value="ruleForm.type"></el-input>
      </el-form-item>
      <el-form-item>
<el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
      </el-form-item>
    </el-form>

  </div>

</template>

<script>
import {GetTitleType} from "@/api/static_data_api";
import {AddTeacherTitle, AddTitleType, DeleteTitleType} from "@/api/admin_api";

export default {
  name: "TitleType",
  data () {
    let that = this
    return {
      ruleForm: {
        type:null,
      },

      table: {
        data: [
        ]
      },
      rules: {
        type: [
          { required: true, message: '请输入选题类型', trigger: 'blur' }
        ]
      },
      columns: [
        { prop: 'type', label: '选题类型', minWidth: '100', sort: true },
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
    GetTitleType().then(res => {
      this.table.data = res.data.data
    })
  },
  methods: {
    handledeleteTable(row) {
      DeleteTitleType(row.type).then(res => {
        this.$message({
          message: '删除成功',
          type: 'success'
        })
        GetTitleType().then(res => {
          this.table.data = res.data.data
        })
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if(this.ruleForm.type==null){
            this.$message({
              message: '请输入选题类型',
              type: 'error'
            })
            return false;
          }
          AddTitleType(this.ruleForm.type).then(res => {
            if (res.data.code === 200) {
              this.$message({
                message: '添加成功',
                type: 'success'
              })
              GetTitleType().then(res => {
                this.table.data = res.data.data
              })
              //两秒后刷新页面
              setTimeout(function () {
                window.location.reload();
              }, 2000);
            } else {
              this.$message({
                message: '添加失败',
                type: 'error'
              })
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },


    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

}
</script>

<style scoped>

</style>