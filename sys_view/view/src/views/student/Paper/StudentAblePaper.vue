<template>
  <div>
    <el-form style="width: 60%" label-width="100px">
      <el-form-item label="选题截止日期">
        <el-input  style="width: 20%;float: left" :disabled="true" v-model="deadline" :value="deadline"></el-input>
      </el-form-item>
    </el-form>
  <div class="t-table" style="width:100%;">

    <t-table :table="table" :columns="columns" />
  </div>
  </div>
</template>

<script>
import {CancelPaper, GetAblePaper, GetPaperBySno, SelectPaper} from "@/api/student_api";
import {GetSelectDeadline} from "@/api/static_data_api";

export default {
  data () {
    let that = this
    return {
      deadline: '',
      table: {
        data: []
      },
      columns: [
        { prop: 'pno', label: '选题编号', minWidth: '100', sort: true },
        { prop: 'title', label: '名称', minWidth: '180', sort: true },
        { prop: 'type', label: '选题类型', minWidth: '100', sort: true },
        { prop: 'description', label: '详细信息', minWidth: '220', sort: true, noShowTip: true },
        { prop: 'teacherName', label: '指导老师', minWidth: '180', sort: true },
        {
          prop: 'address', label: '操作', minWidth: '250',
          customRender: {
            comps: [
              {
                comp: 'el-link',
                text: '选择',
                bind: {
                  type: 'primary'
                },
                event (scope) {
                  return {
                    click () {
                      that.handleEditTable('编辑', scope.row)
                    }
                  }
                }
              },
            ]}
        }
      ]
    }
  },
  created() {
    GetAblePaper(sessionStorage.getItem('sno')).then(res => {
      this.table.data = res.data.data
    })
    GetSelectDeadline().then(res => {
      this.deadline = res.data.data.selectDeadline
    })
  },
  methods: {
    handleEditTable (val, type) {
      console.log('编辑', type)
      //判断是否超过选题截止日期
      let deadline = new Date(this.deadline)
      let now = new Date()
      if(now > deadline){
        this.$message({
          message: '选题截止日期已过！',
          type: 'error'
        })
        return
      }
      SelectPaper(sessionStorage.getItem("sno"),type.pno).then(res => {
        if(res.data.code===200){
          this.$message({
            message: '选择成功！',
            type: 'success'
          })
          GetAblePaper(sessionStorage.getItem('sno')).then(res => {
            this.table.data = res.data.data
          })
        }else{
          this.$message({
            message: res.data.msg,
            type: 'error'
          })
        }
      })



    },

  },

}
</script>

<style scoped>

</style>