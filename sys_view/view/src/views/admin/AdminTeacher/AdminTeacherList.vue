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
    <el-input style="width: 30%;float: left" v-model="canTeachNum" placeholder="批量更新老师指导题目数量"></el-input>
    <br><br><br>
    <el-button type="" style="float: left" @click="UpdateCanTeachNum">批量更新</el-button>

  </div>
</template>

<script>
import QueryData from "@/views/teacher/paperFindQurey";
import '@/api/global.js'
import {
  GetAllPaperAdmin,
  GetStudentList,
  GetTeacherList,
  UpdateCanSelectNum,
  UpdateCanTeachNumPiLiang
} from "@/api/admin_api";
import {GetSchoolYearList} from "@/api/static_data_api";

export default {
  data () {
    let that = this
    return {
      senddata:[],
      schoolYearList:[],

      canTeachNum: null,

      table: {
        total: 0,
        currentPage: 1,
        pageSize: 10,
        firstColumn: { type: 'selection' },
        data: [],
        senddata:[],
      },
      columns: [
        { prop: 'tno', label: '工号', minWidth: '100', sort: true },
        { prop: 'teacherName', label: '姓名', minWidth: '100', sort: true },
        { prop: 'sex', label: '性别', minWidth: '100', sort: true },
        { prop: 'title', label: '职称', minWidth: '100', sort: true },
        { prop: 'education', label: '学历', minWidth: '100', sort: true },
        { prop: 'email', label: '邮箱', minWidth: '150', noShowColumn: true },
        { prop: 'tel', label: '电话', minWidth: '150', noShowColumn: true },
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
              {
                comp: 'el-link',
                text: '丨指导人数',
                bind: {
                  type: 'primary'
                },
                event (scope) {
                  return {
                    click () {
                      that.handleCanTeachNum('编辑', scope.row)
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
      this.$router.push({path: '/admin/edit_teacher', query: {tno: type.tno}})
    },
    handleCanTeachNum(val, type){
      this.$router.push({path: '/admin/edit_teacher_can_teach_num', query: {tno: type.tno}})

    },
    UpdateCanTeachNum(){
      this.senddata.forEach(item=>{
        item.canTeachNum=this.canTeachNum
      })
      UpdateCanTeachNumPiLiang(this.senddata).then(res=>{
        if(res.data.code===200){
          this.$message({
            message: '批量更新成功',
            type: 'success'
          })
        }
        else {
          this.$message({
            message: res.data.msg,
            type: 'error'
          })
        }
      })
  },
 },
  created() {
    GetTeacherList().then(res=>{
      this.table.data=res.data.data
    })
    GetSchoolYearList().then(res=>{
      this.schoolYearList=res.data.data
    })
  },
  watch:{

  }
}

</script>

<style scoped>

</style>