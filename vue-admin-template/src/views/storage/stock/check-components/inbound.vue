<template>
    <div>
        
        <el-form v-model="formData">
           
            <el-form-item  label="产品名称:"  label-width="140px">      
              <el-input v-model="formData.productName" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item  label="产品批号:"  label-width="140px">      
              <el-input v-model="formData.productId" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item  label="产品数量:"  label-width="140px">      
              <el-input v-model="formData.productNums" :disabled="true"></el-input>
            </el-form-item>
           
        </el-form>      
        <el-button style="margin-left:44%" @click="submit" type="primary">入库</el-button>
  
    </div>
</template>
<script>
import{updateProduct,getAvailableStoreHouse} from "@/api/product/index";
import{handleInbound} from "@/api/stock/index"
export default {
    props:{
     choosedData:{
         type:Object,
         default:null
     }
    },
    data(){
        return{
        storeHouseList:[],
        choosedSelect:null,
        formData:{
            storeHouseName:'',
            productId:'',
            productNums:'',
            productName:'',
            status:'',
            storeId:'',
            // userId:''
        },
        }
    },
    created(){
    
     this.formData=this.choosedData
     console.log(this.formData)
    },
    mounted(){
    //   this.formData.userId=this.$store.state.user.uid
    },
    methods:{
    //    handleSelect(value){
    //        this.formData.storeHouseName=value.name
    //        this.formData.storeId=value.id
    //        this.choosedData.status=1
    //        this.choosedData.location=value.name
    //        console.log(this.formData,'嘿嘿')
    //    },
     
       submit(){
           console.log(this.choosedData,'很哈')
           handleInbound(this.choosedData).then(res=>{
               
               this.$message({ message: '入库产品信息成功!', type: 'success' })
               this.$emit('closeAll')
             
           })
  
       }
    }
}
</script>