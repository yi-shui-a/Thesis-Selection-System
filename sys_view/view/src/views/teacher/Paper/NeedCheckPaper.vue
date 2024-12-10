<template>

  <div class="t-table" style="width:100%;">
    <div class="query-data">
      <t-query-condition ref="queryCondition" :opts="opts" @submit="conditionEnter">
        <template #checkStatus="{param}">
          <el-input v-model="param.checkStatus" clearable placeholder="自定义插槽输入框" />
        </template>
      </t-query-condition>
    </div>

    <t-table
        ref="tableCheckbox"
        columnSetting
        :table="table"
        :columns="columns"
        @selection-change="selectionChange"
        isShowPagination
    />
    <el-input style="width: 30%;float: left" v-model="refuseReason" placeholder="拒绝理由"></el-input>
    <br><br><br>
    <el-button type="" style="float: left" @click="acceptPaper">通过选题</el-button>

    <el-button type="danger" style="float: left" @click="refusePaper">拒绝选题</el-button>

  </div>
</template>

<script>
import {AcceptPaper, GetAllPaperByTno, GetPaperNeedCheck, RefusePaper} from "@/api/teacher_api";
import QueryData from "@/views/teacher/paperFindQurey";
import {GetSchoolYearList, GetTitleType} from "@/api/static_data_api";
import '@/api/global.js'

export default {
  components: {QueryData},
  data () {
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
        firstColumn: { type: 'selection' },
        data: [],
        senddata:[],

      },
      columns: [
        { prop: 'pno', label: '选题编号', minWidth: '200', noShowColumn: true },
        { prop: 'title', label: '标题', minWidth: '200', noShowColumn: true },
        { prop: 'type', label: '项目类型', minWidth: '100', noShowColumn: true },
        // { prop: 'checkStatus', label: '审核状态', minWidth: '220', noShowColumn: true },
        { prop: 'selectStatus', label: '选题状态', minWidth: '180', noShowColumn: true },
        { prop: 'studentName', label: '学生姓名', minWidth: '180', noShowColumn: true },
        { prop: 'schoolyear', label: '学年', minWidth: '180', noShowColumn: true },
        { prop: 'createTime', label: '选题时间', minWidth: '320', noShowColumn: true }
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
    acceptPaper(){
      if(this.senddata.length===0){
        this.$message({
          message: '请先选择要通过的选题',
          type: 'error'
        });
        return;
      }
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
    refusePaper(){
      for(let i=0;i<this.senddata.length;i++){
        this.senddata[i].refuseReason = this.refuseReason;
      }
      RefusePaper(this.senddata).then(res=>{
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
      GetPaperNeedCheck(sessionStorage.getItem('tno'),data).then(res => {
        this.table.data = res.data.data
      })

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
    GetPaperNeedCheck(sessionStorage.getItem('tno'),this.getQueryData).then(res => {
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