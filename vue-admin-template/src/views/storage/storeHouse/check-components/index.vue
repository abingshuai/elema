<template>
    <div>
        <el-form v-model="formData">
            <el-form-item :required="true" label="仓库名称:" label-width="140px">      
              <el-input  v-model="formData.name" ></el-input>
            </el-form-item>
            <el-form-item :required="true" label="仓库地址" label-width="140px">      
              <el-input  v-model="formData.address"  ></el-input>
            </el-form-item>
            <el-form-item :required="true" label="所属销售区域:"  label-width="140px">      
              <el-input v-model="formData.area" ></el-input>
            </el-form-item>
            <el-form-item :required="true" label="管理员:"  label-width="140px">      
              <el-input v-model="formData.manager" ></el-input>
            </el-form-item>
            <el-form-item :required="true" label="联系电话" label-width="140px">      
              <el-input  v-model="formData.phone"  ></el-input>
            </el-form-item>
            <el-form-item :required="true" label="状态:" label-width="140px">   
                <el-select v-model="formData.status">
                    <el-option :value="0" :label="'禁用'">禁用</el-option>
                    <el-option :value="1" :label="'启用'">启用</el-option>
                    </el-select>   
            </el-form-item>
            <el-form-item :required="true" label="最大容纳量" label-width="140px">      
              <el-input  v-model="formData.max"></el-input>
            </el-form-item>
         
        </el-form>      
        <el-button style="margin-left:44%" @click="submit" type="primary">提交</el-button>
    </div>
</template>
<script>
import{updateStoreHouse} from "@/api/storeHouse/index";
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
           updateStoreHouse(this.formData).then(res=>{
             if(res==1){
               this.$message({ message: '修改仓库信息成功!', type: 'success' })
               this.$emit('closeAll')
             }
           })
       }
    }
}
</script>