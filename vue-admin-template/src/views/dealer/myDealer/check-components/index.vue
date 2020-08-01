<template>
    <div>
        <el-form v-model="formData">
            <el-form-item :required="true" label="经销商名称:" label-width="140px">      
              <el-input  v-model="formData.name" ></el-input>
            </el-form-item>
            <el-form-item :required="true" label="经销商级别:"  label-width="140px">      
              <el-input v-model="formData.level" ></el-input>
            </el-form-item>
            <el-form-item :required="true" label="销售区域:"  label-width="140px">      
              <el-input v-model="formData.area" ></el-input>
            </el-form-item>
            <el-form-item :required="true" label="经销商管理员" label-width="140px">      
              <el-input  v-model="formData.manager" ></el-input>
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
            level:'',
            area:'',
            manager:'',
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
               this.$message({ message: '新增经销商成功!', type: 'success' })
               this.$emit('closeAll')
             }
           })
       }
    }
}
</script>