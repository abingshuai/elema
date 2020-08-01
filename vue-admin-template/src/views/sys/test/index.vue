<template>
<div>
  <el-input v-model="getUrl"  placeholder="输入查询get请求接口"></el-input>
  <el-button @click="handleTest">提交</el-button>
  <el-input v-model="postUrl"  placeholder="输入查询post请求接口"></el-input>
  <el-button @click="handleTest">提交</el-button>
</div>
</template>
<script>
import {getTest,postTest} from '@/api/test/index';
export default {
    data(){
        return{
        list:null,
        getUrl:null,
        postUrl:null,
        }
    },
    created(){

    },
    methods:{
     handleTest(){
         if(this.getUrl!=null){
         getTest(this.getUrl).then(res=>{
             console.log("get:",res)
         })}
         if(this.postUrl!=null){
         postTest(this.postUrl).then(res=>{
             console.log("post:",res)
         })
         }
     }
    }
}
</script>