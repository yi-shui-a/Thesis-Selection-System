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
    <el-input style="width: 30%;float: left" v-model="rejectReason" placeholder="拒绝理由"></el-input>
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
import {AdminAcceptPaper, AdminRefusePaper, GetAllPaperAdmin, GetAllPaperNeedCheckAdmin} from "@/api/admin_api";
import { MessageBox } from 'element-ui';

export default {
  components: {QueryData},
  data () {
    let that = this
    return {
      rejectReason: null,
      type_list:[],
      schoolyear_List:[],
      queryData: {
        title: null,
        type: null,
        teacherName: null,
        schoolyear: null,
        acceptStatus: 'NOT_CHECK',
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
        // { prop: 'selectStatus', label: '选题状态', minWidth: '180', noShowColumn: true },
        { prop: 'teacherName', label: '老师姓名', minWidth: '180', noShowColumn: true },
        { prop: 'schoolyear', label: '学年', minWidth: '180', noShowColumn: true },
        {prop: 'aaa', label: '选题描述', minWidth: '100',  customRender: {
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
    showDetails(val,type){
      MessageBox.alert(`详细信息: ${type.description}`, {
        confirmButtonText: 'OK',
        dangerouslyUseHTMLString: true
      });
    },
    acceptPaper(){
      if(this.senddata==null){
        this.$message({
          message: '请先选择选题',
          type: 'error'
        });
        return;
      }
      AdminAcceptPaper(this.senddata).then(res=>{
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
        this.senddata[i].rejectReason = this.rejectReason;
      }
      AdminRefusePaper(this.senddata).then(res=>{
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
      console.log('最终条件', this.$store.state.queryData)
      GetAllPaperNeedCheckAdmin(data).then(res => {
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
        teacherName: {
          label: '老师姓名',
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

      }
    },
    // 查询条件所需参数
    getQueryData() {
      const { title, type, teacherName, schoolyear } = this.queryData
      return {
        title,
        type,
        teacherName,
        schoolyear,

      }
    }
  },
  created() {
    GetAllPaperNeedCheckAdmin(this.getQueryData).then(res => {
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