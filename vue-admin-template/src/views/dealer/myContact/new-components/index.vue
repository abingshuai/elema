<template>
    <div>
        <el-form v-model="formData">
            <el-form-item :required="true" label="联系人名称:" label-width="140px">      
              <el-input  v-model="formData.name" ></el-input>
            </el-form-item>
            <el-form-item :required="true" label="经销商公司:"  label-width="140px">      
              <el-select v-model="formData.company">
                  <el-option v-for="index in dealerNames" :value="index" :key="index">{{index}}</el-option>
              </el-select>
            </el-form-item>
            <el-form-item :required="true" label="相关职称:" label-width="140px">      
              <el-input  v-model="formData.job" ></el-input>
            </el-form-item>
            <el-form-item :required="true" label="联系地址:" label-width="140px">      
              <el-input  v-model="formData.address"  ></el-input>
            </el-form-item>
            <el-form-item :required="true" label="联系电话:" label-width="140px">      
              <el-input  v-model="formData.phone" ></el-input>
            </el-form-item>
        </el-form>      
        <el-button style="margin-left:44%" @click="submit" type="primary">提交</el-button>
    </div>
</template>
<script>
import{createContact} from "@/api/contact/index";
import{search} from '@/api/dealer/index'
export default {
    data(){
        return{
        formData:{
            name:'',
            company:'',
            jog:'',
            address:'',
            phone:'',
            user_id:''
        },
        param:{
            user_id:null
        },
        dealerNames:[]
   
        }
    },
    created(){
    this.param.user_id=this.$store.state.user.uid
    this.formData.user_id=this.$store.state.user.uid
    this.getAllDealerName()
    },
    mounted(){
      
    },
    methods:{
       getAllDealerName(){
           search(this.param).then(res=>{
               console.log("我滴信息",res)
               res.data.rows.forEach(element => {
                   this.dealerNames.push(element.name)
               });
           })
       },
       submit(){
           createContact(this.formData).then(res=>{
             if(res==1){
               this.$message({ message: '新增联系人成功!', type: 'success' })
               this.$emit('closeAll')
             }
           })
       }
    }
}
</script>