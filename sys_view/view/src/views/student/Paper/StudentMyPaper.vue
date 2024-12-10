<template>
  <div class="t-table" style="width:100%;">
    <t-table :table="table" :columns="columns" />
  </div>
</template>

<script>
import {CancelPaper, GetAcceptPaperBySno, GetPaperBySno} from "@/api/student_api";

export default {
  data () {
    let self = this
    return {
      table: {
        data: []
      },
      columns: [
        { prop: 'pno', label: '选题编号', minWidth: '100', sort: true },
        { prop: 'title', label: '名称', minWidth: '180', sort: true },
        { prop: 'description', label: '详细信息', minWidth: '220', sort: true, noShowTip: true },
        { prop: 'teacherName', label: '指导老师', minWidth: '180', sort: true },
        { prop: 'acceptStatus', label: '审核状态', minWidth: '180', sort: true },
        {
          prop: 'address', label: '操作', minWidth: '220',
          customRender: {
            comps: [
              {
                comp: 'el-popconfirm',
                bind: {
                  title: '确认退选吗？'
                },
                event (scope) {
                  return {
                    confirm () {
                      self.handledeleteTable(scope.row)
                    }
                  }
                },
                child: [
                  {
                    slot: 'reference',
                    comp: 'el-link',
                    text: '退选',
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
  methods: {
    handledeleteTable (val) {
      if(val.acceptStatus==='已接受'){
        this.$message({
          message: '已选择的课题不能退选！',
          type: 'error'
        });
      }
      else {
        CancelPaper(sessionStorage.getItem("sno"),val.pno).then(res=>{
          if(res.data.code===200){
            this.$message({
              message: '退选成功！',
              type: 'success'
            });
            GetPaperBySno(sessionStorage.getItem("sno"),'C').then(res1 => {
              this.table.data = res1.data.data
            })
          }
          else {
            this.$message({
              message: res.data.msg,
              type: 'error'
            });
          }
        })

      }
      console.log('删除', val)

    }
  },
  created() {
      GetPaperBySno(sessionStorage.getItem("sno"),'C').then(res1 => {
        this.table.data = res1.data.data
      })
  }
}
</script>

<style scoped>

</style>