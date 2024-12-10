<template>
  <div class="t-table" style="width:100%;">
    <div class="t-table" style="width:100%;">
      <t-table
          ref="tableCheckbox"
          columnSetting
          :table="table"
          :columns="columns"
          @selection-change="selectionChange"
          isShowPagination/>
    </div>
    <el-input style="width: 30%;float: left" v-model="ableTopicNum" placeholder="学生可选题目数量"></el-input>
    <br><br><br>
    <el-button type="" style="float: left" @click="UpdateAbleTopicNum">更新</el-button>

  </div>
</template>

<script>
import QueryData from "@/views/teacher/paperFindQurey";
import '@/api/global.js'
import {GetAllPaperAdmin, GetStudentList, UpdateCanSelectNum} from "@/api/admin_api";

export default {
  data () {
    let that = this
    return {
      senddata:[],

      ableTopicNum: null,

      table: {
        total: 0,
        currentPage: 1,
        pageSize: 10,
        firstColumn: { type: 'selection' },
        data: [],
        senddata:[],
      },
      columns: [
        { prop: 'sno', label: '学号', minWidth: '100', sort: true },
        { prop: 'studentName', label: '姓名', minWidth: '200', sort: true },
        { prop: 'sex', label: '性别', minWidth: '100', sort: true },
        { prop: 'schoolyear', label: '学年', minWidth: '100', sort: true },
        { prop: 'ableTopicNum', label: '可选题目数', minWidth: '100', noShowColumn: true },
        { prop: 'selectedTopicNum', label: '已选题目数', minWidth: '100', noShowColumn: true },
        {
          prop: 'address', label: '操作', minWidth: '250',
          customRender: {
            comps: [
              {
                comp: 'el-link',
                text: '编辑',
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
              // {
              //   comp: 'el-popconfirm',
              //   bind: {
              //     title: '确认删除该条数据？'
              //   },
              //   event (scope) {
              //     return {
              //       confirm () {
              //         that.handledeleteTable(scope.row)
              //       }
              //     }
              //   },
              //   child: [
              //     {
              //       slot: 'reference',
              //       comp: 'el-link',
              //       text: '删除',
              //       bind: {
              //         type: 'primary'
              //       }
              //     }
              //   ]
              // }
            ]
          }
        }
      ]
    }
  },
  methods: {
    // 复选框选中
    selectionChange (val) {
      this.senddata = val;
      console.log('复选框选中值1', this.senddata)
      console.log('queryData', this.queryData)
    },
    // 取消选中
    cancelCheck () {
      this.$refs.tableCheckbox.clearSelection()
    },

    handleEditTable (val, type) {
      console.log('编辑', type)
      this.$router.push({path: '/admin/edit_student', query: {sno: type.sno}})

    },
    // handledeleteTable (val) {
    //   DeletePaper(val.sno).then(res=>{
    //     if(res.data.code===200){
    //       this.$message({
    //         message: '操作成功',
    //         type: 'success'
    //       });
    //     }
    //     else{
    //       this.$message({
    //         message: res.data.msg,
    //         type: 'error'
    //       });
    //     }
    //   })
    //   //两秒后刷新页面
    //   setTimeout(function () {
    //     window.location.reload();
    //   }, 2000);
    // }
    UpdateAbleTopicNum(){
      for(let i=0;i<this.senddata.length;i++){
        this.senddata[i].ableTopicNum=this.ableTopicNum
      }
      UpdateCanSelectNum(this.senddata).then(res=>{
        if(res.data.code===200){
          this.$message({
            message: '操作成功',
            type: 'success'
          });
        }
        else{
          this.$message({
            message: res.data.msg,
            type: 'error'
          });
        }
      })
    }
  },

  created() {
  GetStudentList().then(res=>{
    this.table.data=res.data.data
  })
  },
  watch:{

  }

}

</script>

<style scoped>

</style>