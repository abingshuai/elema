<template>
    <div>
        
        <el-form v-model="formData">
            <el-form-item :required="true" label="可使用仓库:" label-width="140px">      
              <el-select value-key="id" v-model="choosedSelect" @change="handleSelect">
                  <el-option v-for="item in storeHouseList" :label="item.name" :key="item.id" :value="item"></el-option>
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
        <el-button style="margin-left:44%" @click="submit" type="primary">选择入库</el-button>
  
    </div>
</template>
<script>
import{updateProduct,getAvailableStoreHouse} from "@/api/product/index";
import{createStock} from "@/api/stock/index"
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
     console.log(this.choosedData)
     this.formData.productId=this.choosedData.id
     this.formData.productName=this.choosedData.name
     this.formData.productNums=this.choosedData.nums
     this.showList()
    },
    mounted(){
    //   this.formData.userId=this.$store.state.user.uid
    },
    methods:{
       handleSelect(value){
           this.formData.storeHouseName=value.name
           this.formData.storeId=value.id
           this.choosedData.status=1
           this.choosedData.location=value.name
           console.log(this.formData,'嘿嘿')
       },
       showList(){
          getAvailableStoreHouse(this.choosedData.nums).then(res=>{
          this.storeHouseList=res
     })
       },
       submit(){
           console.log(this.formData)
           createStock(this.formData).then(res=>{
            updateProduct(this.choosedData).then(res=>{
               this.$message({ message: '入库产品信息成功!', type: 'success' })
               this.$emit('closeAll')
           })
           })
  
       }
    }
}
</script>