<template>
    <div>
        <el-form v-model="formData">
            <el-form-item :required="true" label="产品批号:" label-width="140px">      
              <el-input  v-model="formData.id" ></el-input>
            </el-form-item>
            <el-form-item :required="true" label="产品数量:"  label-width="140px">      
              <el-input v-model="formData.nums" ></el-input>
            </el-form-item>
            <el-form-item :required="true" label="销售名称:"  label-width="140px">      
              <el-input v-model="formData.name" ></el-input>
            </el-form-item>
        </el-form>      
        <el-button style="margin-left:44%" @click="submit" type="primary">提交</el-button>
    </div>
</template>
<script>
import{createProduct} from "@/api/product/index";

export default {
    data(){
        return{
        formData:{
            id:'',
            name:'',
            nums:'',
            status:0,
            location:'福福仓库'
        },
        }
    },
    created(){
     
    },
    mounted(){
    //   this.formData.userId=this.$store.state.user.uid
    },
    methods:{
       submit(){
           createProduct(this.formData).then(res=>{
             if(res==1){
               this.$message({ message: '新增产品信息成功!', type: 'success' })
               this.$emit('closeAll')
             }
           })
       }
    }
}
</script>