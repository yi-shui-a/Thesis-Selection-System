<template>
  <div>
    <el-form style="width: 370px">
      <el-form-item  label="审核状态" >
      <el-select style="float: left"  v-model="acceptStatus" placeholder="审核状态">
        <el-option
            v-for="item in acceptStatusList"
            :key="item.key"
            :label="item.label"
            :value="item.key">
        </el-option>
      </el-select>
        <el-button style="float: right" type="primary" @click="resetval">重置</el-button>
        </el-form-item>
    </el-form>

    <div class="t-table" style="width:100%;">
      <t-table :table="table" :columns="columns" />
    </div>
  </div>
</template>

<script>
import {CancelPaper, GetAblePaper, GetPaperBySno, ReSelectPaper, SelectPaper} from "@/api/student_api";
import {GetSelectDeadline} from "@/api/static_data_api";

export default {
  data () {
    let that = this
    return {
      acceptStatusList:[],
      acceptStatus:null,
      table: {
        data: []
      },
      columns: [
        { prop: 'pno', label: '选题编号', minWidth: '100', sort: true },
        { prop: 'title', label: '名称', minWidth: '180', sort: true },
        { prop: 'type', label: '选题类型', minWidth: '100', sort: true },
        { prop: 'description', label: '详细信息', minWidth: '200', sort: true, noShowTip: true },
        { prop: 'teacherName', label: '指导老师', minWidth: '100', sort: true },
        { prop: 'acceptStatus', label: '审核状态', minWidth: '100', sort: true },
        { prop: 'refuseReason', label: '拒绝原因', minWidth: '200', sort: true },
        {
          prop: 'address', label: '操作', minWidth: '50',
          customRender: {
            comps: [
              {


                comp: 'el-link',
                text: '重新选择',
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
    GetPaperBySno(sessionStorage.getItem('sno'),).then(res => {
      this.table.data = res.data.data
      this.acceptStatusList = acceptStatus_List
      let that = this
    })
  },
  methods: {
    handleEditTable (val, type) {
      if(type.acceptStatus!=='已拒绝'){
        this.$message({
          message: '只有已拒绝的选题才能重新选择',
          type: 'error'
        })
        return
      }
      ReSelectPaper(sessionStorage.getItem('sno'),type.pno).then(res => {
        if(res.data.code===200){
          this.$message({
            message: '重新选择成功',
            type: 'success'
          })
          GetPaperBySno(sessionStorage.getItem('sno'),).then(res => {
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
    resetval(){
      this.acceptStatus = null
    }
  },
  watch: {
    acceptStatus(val){
      if(val===null){
        GetPaperBySno(sessionStorage.getItem('sno'),).then(res => {
          this.table.data = res.data.data
        })
      }else{
        GetPaperBySno(sessionStorage.getItem('sno'),val).then(res => {
          this.table.data = res.data.data
        })
      }
    }
  }

}
</script>

<style scoped>

</style>