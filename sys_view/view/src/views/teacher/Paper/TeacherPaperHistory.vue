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
  GetAllPaperByTno, GetPaperHistoryByTno,
  GetPaperNeedCheck,
  RefusePaper,
  TeacherGetAllPaper
} from "@/api/teacher_api";
import QueryData from "@/views/teacher/paperFindQurey";
import {GetSchoolYearList, GetTitleType} from "@/api/static_data_api";
import '@/api/global.js'

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
        acceptStatus: null,
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
        { prop: 'acceptStatus', label: '选题状态', minWidth: '180', noShowColumn: true },
        { prop: 'studentName', label: '学生姓名', minWidth: '180', noShowColumn: true },
        { prop: 'schoolyear', label: '学年', minWidth: '180', noShowColumn: true },
        { prop: 'refuseReason', label: '拒绝原因', minWidth: '180', noShowColumn: true },
        { prop: 'createTime', label: '选题时间', minWidth: '180', noShowColumn: true },

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
    // 点击查询按钮
    conditionEnter(data) {
      console.log('查询条件', data)
      console.log('最终条件', this.$store.state.queryData)
      GetPaperHistoryByTno(sessionStorage.getItem('tno'),data).then(res => {
        this.table.data = res.data.data
      })

    },
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
        studentName: {
          label: '学生姓名',
          comp: 'el-input',
        },
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
        acceptStatus: {
          label: '选择状态',
          comp: 't-select',
          bind: {
            optionSource: acceptStatus_List,
          },
        },

      }
    },
    // 查询条件所需参数
    getQueryData() {
      const { title, type, studentName, schoolyear,acceptStatus } = this.queryData
      return {
        title,
        type,
        studentName,
        schoolyear,
        acceptStatus
      }
    }
  },
  created() {
    GetPaperHistoryByTno(sessionStorage.getItem('tno'),this.getQueryData).then(res => {
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

}

</script>

<style scoped>

</style>