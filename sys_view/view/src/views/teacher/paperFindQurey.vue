<template>
  <div class="query-data">
    <t-query-condition ref="queryCondition" :opts="opts" @submit="conditionEnter" isShowOpen="false">
      <template #checkStatus="{param}">
        <el-input v-model="param.checkStatus" clearable placeholder="自定义插槽输入框" />
      </template>
    </t-query-condition>
  </div>
</template>
<script>
import moment from 'moment'
import {GetSchoolYearList, GetTitleType} from "@/api/static_data_api";
import '@/api/global.js'
import {TeacherGetAllPaper} from "@/api/teacher_api";

export default {
  name: 'queryDataPaper',
  data() {
    return {
      type_list:[],
      schoolyear_List:[],
      queryData: {
        title: null,
        type: null,
        checkStatus: null,
        acceptStatus: null,
        workshopNum: null,
        schoolyear: null,
      }

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
        checkStatus: {
          label: '审核状态',
          comp: 't-select',
          bind:{
            optionSource: checkStatus_List
          }
        },
        acceptStatus: {
          label: '选择状态',
          comp: 't-select',
          bind: {
            optionSource: selectStatus_List
          }
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
      const { title, type, checkStatus, acceptStatus, schoolyear, date1, date, } = this.queryData
      return {
        title,
        type,
        checkStatus,
        acceptStatus,
        schoolyear
      }
    }
  },
  // 方法
  methods: {
    // 点击查询按钮
    conditionEnter(data) {
      console.log('查询条件', data)
      this.queryData = data
      // console.log('最终条件', this.getQueryData)
      this.$store.commit('setQueryData', this.getQueryData)
      // TeacherGetAllPaper(sessionStorage.getItem("tno"),this.$store.state.queryData).then(res=>{
      //   const that = this;
      //   that.$store.commit('setPaperList', res.data.data)
      //   console.log('paperList',that.$store.state.paperList)
      // })
      console.log('最终条件', this.$store.state.queryData)

    }
  },
  created() {
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


  }
}
</script>