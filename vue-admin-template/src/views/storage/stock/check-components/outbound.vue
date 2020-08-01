<template>
    <div>
        
        <el-form v-model="formData">
            <el-form-item  label="出库去向:"  label-width="140px">      
              <el-select v-model="formData.storeHouseName" >
                  <el-option :value="areaName+'周边零售商'" :label="areaName+'周边零售商'"></el-option>
                  <el-option :value="areaName+'xx批发商'" :label="areaName+'xx批发商'"></el-option>
                      
              </el-select>
            </el-form-item>
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
// import{updateProduct,getAvailableStoreHouse} from "@/api/product/index";
import{handleOutbound} from "@/api/stock/index"
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
        areaName:'',
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
     this.areaName=this.choosedData.storeHouseName.substring(0,3)
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
           handleOutbound(this.choosedData).then(res=>{
               
               this.$message({ message: '出库产品信息成功!', type: 'success' })
               this.$emit('closeAll')
             
           })
  
       }
    }
}
</script>