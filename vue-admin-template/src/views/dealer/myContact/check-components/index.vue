<template>
    <div>
        <el-form v-model="formData">
            <el-form-item :required="true" label="联系人名称:" label-width="140px">      
              <el-input  v-model="formData.name" ></el-input>
            </el-form-item>
            <el-form-item :required="true" label="经销商公司:"  label-width="140px">      
              <el-input v-model="formData.company" ></el-input>
            </el-form-item>
            <el-form-item :required="true" label="相关职称:"  label-width="140px">      
              <el-input v-model="formData.job" ></el-input>
            </el-form-item>
            <el-form-item :required="true" label="联系地址" label-width="140px">      
              <el-input  v-model="formData.address"  ></el-input>
            </el-form-item>
            <el-form-item :required="true" label="联系电话" label-width="140px">      
              <el-input  v-model="formData.phone" ></el-input>
            </el-form-item>
        </el-form>      
        <el-button style="margin-left:44%" @click="submit" type="primary">提交</el-button>
    </div>
</template>
<script>
import{updateContact} from "@/api/contact/index";
export default {
    props:{
     choosedData:{
         type:Object,
         default:null
     }
    },
    data(){
        return{
        formData:{
            name:'',
            job:'',
            company:'',
            address:'',
            phone:'',
            userId:''
        },
        }
    },
    created(){
     console.log(this.choosedData)
     this.formData=this.choosedData
    },
    mounted(){
      this.formData.userId=this.$store.state.user.uid
    },
    methods:{
       submit(){
           updateContact(this.formData).then(res=>{
             if(res==1){
               this.$message({ message: '修改联系人成功!', type: 'success' })
               this.$emit('closeAll')
             }
           })
       }
    }
}
</script>