<template>
  <div class="t-table" style="width:100%;">
    <div class="query-data">
      <t-query-condition ref="queryCondition" :opts="opts" @submit="conditionEnter">
        <template #checkStatus="{param}">
          <el-input v-model="param.checkStatus" clearable placeholder="自定义插槽输入框" />
        </template>
      </t-query-condition>
    </div>
    <div class="t-table" style="width:100%;">
      <t-table :table="table" :columns="columns" />
    </div>


  </div>
</template>

<script>
import {
  AcceptPaper,
  DeletePaper,
  GetAllPaperByTno,
  GetPaperNeedCheck,
  RefusePaper,
  TeacherGetAllPaper
} from "@/api/teacher_api";
import QueryData from "@/views/teacher/paperFindQurey";
import {GetSchoolYearList, GetTitleType} from "@/api/static_data_api";
import '@/api/global.js'
import { MessageBox } from 'element-ui';

export default {
  components: {QueryData},
  data () {
    let that = this
    return {
      refuseReason: null,
      type_list:[],
      schoolyear_List:[],
      queryData: {
        title: null,
        type: null,
        studentName: null,
        schoolyear: null,
        selectStatus: null,
      },
      table: {
        total: 0,
        currentPage: 1,
        pageSize: 10,
        // firstColumn: { type: 'selection' },
        data: [],
        senddata:[],

      },
      columns: [
        { prop: 'pno', label: '选题编号', minWidth: '200', sort: true },
        { prop: 'title', label: '标题', minWidth: '200', noShowColumn: true },
        { prop: 'type', label: '项目类型', minWidth: '100', noShowColumn: true },
        { prop: 'checkStatus', label: '审核状态', minWidth: '220', noShowColumn: true },
        { prop: 'selectStatus', label: '选题状态', minWidth: '180', noShowColumn: true },
        // { prop: 'studentName', label: '学生姓名', minWidth: '180', noShowColumn: true },
        { prop: 'schoolyear', label: '学年', minWidth: '180', noShowColumn: true },
        { prop: 'rejectReason', label: '拒绝原因', minWidth: '180', noShowColumn: true },
        {prop: 'aaa', label: '选题描述', minWidth: '80',  customRender: {
            comps: [
              {
                comp: 'el-link',
                text: '详细信息',
                bind: {
                  type: 'primary'
                },
                event (scope) {
                  return {
                    click () {
                      that.showDetails('编辑', scope.row)
                    }
                  }
                }
              },
            ]
          }},
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
  methods: {
    // 复选框选中
    selectionChange (val) {
      this.senddata = val;

      console.log('复选框选中值1', this.senddata)
      console.log('queryData', this.queryData)
    },
    showDetails(val,type){
      MessageBox.alert(`详细信息: ${type.description}`, {
        confirmButtonText: 'OK',
        dangerouslyUseHTMLString: true
      });
    },
    // 取消选中
    cancelCheck () {
      this.$refs.tableCheckbox.clearSelection()
    },
    acceptPaper(){
      AcceptPaper(this.senddata).then(res=>{
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
      //两秒后刷新页面
      setTimeout(function () {
        window.location.reload();
      }, 2000);


    },
    // 点击查询按钮
    conditionEnter(data) {
      console.log('查询条件', data)
      // this.queryData = data
      // // console.log('最终条件', this.getQueryData)
      // this.$store.commit('setQueryData', this.getQueryData)
      console.log('最终条件', this.$store.state.queryData)
      TeacherGetAllPaper(sessionStorage.getItem('tno'),data).then(res => {
        this.table.data = res.data.data
      })

    },
    handleEditTable (val, type) {
      console.log('编辑', type)

      this.$router.push({path: '/teacher/edit_paper', query: {pno: type.pno}})

    },
    handledeleteTable (val) {
      DeletePaper(val.pno).then(res=>{
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
      //两秒后刷新页面
      setTimeout(function () {
        window.location.reload();
      }, 2000);
    }
  },
  computed: {
    opts() {
      return {
        title: {
          label: '选题名称',
          comp: 'el-input'
        },
        type: {
          label: '选题类型',
          comp: 't-select',
          bind:{
            optionSource: this.type_list,
            valueKey:'type',
            labelKey:'type'
          }
        },
        // studentName: {
        //   label: '学生姓名',
        //   comp: 'el-input',
        // },
        schoolyear: {
          label: '学年',
          comp: 't-select',
          changeEvent: 'change',
          // defaultVal: 'W1',
          bind: {
            optionSource: this.schoolyear_List,
            valueKey:'schoolyear',
            labelKey:'schoolyear'
          },
        },
        selectStatus: {
          label: '选择状态',
          comp: 't-select',
          bind: {
            optionSource: selectStatus_List,
          },
        },

      }
    },
    // 查询条件所需参数
    getQueryData() {
      const { title, type, studentName, schoolyear,selectStatus } = this.queryData
      return {
        title,
        type,
        studentName,
        schoolyear,
        selectStatus
      }
    }
  },
  created() {
    TeacherGetAllPaper(sessionStorage.getItem('tno'),this.getQueryData).then(res => {
      this.table.data = res.data.data
      this.queryData=this.$store.state.queryData
    })
    GetTitleType().then(res=>{
      const that = this;
      that.type_list = res.data.data
      that.checkStatus_List = checkStatus_List
      console.log('checkStatus_List',that.checkStatus_List)
    })
    GetSchoolYearList().then(res=>{
      const that = this;
      that.schoolyear_List = res.data.data
      console.log('schoolyear_List',that.schoolyear_List)
    })
  },
  watch:{

  }

}

</script>

<style scoped>

</style>