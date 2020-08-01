<template>
    <div>
        <el-form v-model="formData">
            <el-form-item :required="true" label="产品名称:" label-width="140px">      
              <el-input  v-model="formData.name" ></el-input>
            </el-form-item>
            <el-form-item :required="true" label="产品批号:"  label-width="140px">      
              <el-input v-model="formData.id" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item :required="true" label="产品数量:"  label-width="140px">      
              <el-input v-model="formData.nums" ></el-input>
            </el-form-item>
            <el-form-item :required="true" label="产品所在地" label-width="140px">      
              <el-input  v-model="formData.location" :disabled="true" ></el-input>
            </el-form-item>
         
        </el-form>      
        <el-button style="margin-left:44%" @click="submit" type="primary">提交</el-button>
    </div>
</template>
<script>
import{updateProduct} from "@/api/product/index";
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
           updateProduct(this.formData).then(res=>{
             if(res==1){
               this.$message({ message: '修改产品信息成功!', type: 'success' })
               this.$emit('closeAll')
             }
           })
       }
    }
}
</script>