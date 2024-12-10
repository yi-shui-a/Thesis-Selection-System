<template>
<div>
  <el-form style="width: 60%" label-width="100px">
    <el-form-item label="选题截止日期">
      <el-input  style="width: 20%;float: left" :disabled="true" v-model="deadline" :value="deadline"></el-input>
    </el-form-item>
    <el-form-item style="width: 20%;float: left" label="修改截止日期" prop="birth">
      <el-date-picker
          v-model="deadlinesend"
          type="date"
          placeholder="选择日期">
      </el-date-picker>
    </el-form-item>
    <el-form-item style="float: left">
      <br><br>
      <el-button type="primary" @click="submitForm()">提交</el-button>
      <el-button @click="resetForm()">重置</el-button>

    </el-form-item>
  </el-form>
</div>
</template>

<script>
import {GetSelectDeadline} from "@/api/static_data_api";
import {UpdateSelectDeadLine} from "@/api/admin_api";

export default {
  name: "AdminSelectDeadLine",
  data() {
    return {
      deadline: '',
      deadlinesend:null,
    }
  },
  created() {
    GetSelectDeadline().then(res => {
      this.deadline = res.data.data.selectDeadline
    })
  },
  methods:{
    submitForm(){
      this.$confirm('确定修改选题截止日期吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deadlinesend= this.dayjs(this.deadlinesend).format("YYYY-MM-DD")
        UpdateSelectDeadLine(this.deadlinesend).then(res => {
          if(res.data.code === 200){
            this.$message({
              type: 'success',
              message: '修改成功!'
            });
            //2s后刷新页面
            setTimeout(function () {
              window.location.reload();
            }, 2000);
        }
          else {
            this.$message({
              type: 'error',
              message: res.data.msg
            });
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消修改'
        });
      });
    },
    resetForm(){
      this.deadlinesend = null
    }
  }

}
</script>

<style scoped>

</style>