<template>
  <div class="t-table" style="width:50%;">
    <t-table :table="table" :columns="columns" />
  </div>
</template>

<script>
import {GetTeacherTeachNum} from "@/api/teacher_api";

export default {
  data () {
    let self = this
    return {
      table: {
        data: [
        ]
      },
      columns: [
        { prop: 'schoolyear', label: '学年', minWidth: '100', sort: true },
        { prop: 'canTeachNum', label: '可指导人数', minWidth: '180', sort: true },
        { prop: 'hasTeachNum', label: '已指导人数', minWidth: '180', sort: true },

      ]
    }
  },
  created() {
    GetTeacherTeachNum(sessionStorage.getItem("tno")).then(res => {
      this.table.data = res.data.data
    })
  }

}
</script>

<style scoped>

</style>