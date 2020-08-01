<template>
<div>


     <el-row :gutter="20">
       
            <!-- 按:
            <el-select v-model="choosed">
                <el-option v-for="item in list" :key="item" :value="item">

                </el-option>
            </el-select> -->
            <el-col :span="8">
      <el-input placeholder="关键字查询" v-model="keyword"></el-input>
            </el-col>
            <el-col :span="3">
     <el-button @click="commonSearch" type="primary"
            icon="el-icon-search" size="small">查询</el-button>
            </el-col>
            <el-col :span="3">
    <el-button type="info" @click="createDealer">新增</el-button>
       </el-col>
        <el-col :span="10">
    <search-components  ref="search" :optionList="subOptionList"  style="width:430px"></search-components>
    </el-col>
     </el-row>
     
      

   


    <div class="container">
      
      <el-table
         class="biaodan"
        :data="list"
        v-loading="listLoading"
        :row-cell-style="{ 'text-algin': 'left' }"
        border
        fit
      >
        
        <el-table-column label="联系人名称" min-width="140px">
          <template slot-scope="scope">
            <el-tag>{{ scope.row.name }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="经销商公司"  min-width="140px">
          <template slot-scope="scope">
            <span>{{ scope.row.company }}</span>
          </template>
        </el-table-column>
        <el-table-column label="联系地址" min-width="140px">
          <template slot-scope="scope">
            <span>{{ scope.row.address }}</span>
          </template>
        </el-table-column>
        <el-table-column label="联系电话" min-width="140px">
          <template slot-scope="scope">
            <span>{{ scope.row.phone }}</span>
          </template>
        </el-table-column>
         <el-table-column label="职称" min-width="140px">
          <template slot-scope="scope">
            <span>{{ scope.row.job }}</span>
          </template>
        </el-table-column>
         <el-table-column fixed="right" label="操作" width="250">
          <template slot-scope="scope">
 
            <el-button @click="handleUpdate(scope.row)"  type="primary" >编辑</el-button>
            <el-button @click="handleDelete(scope.row.id)" type="danger" >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog
      :visible.sync="createVisable"
      title="新增经销商"
      center
      width="40%">
       <new-components @closeAll="closeDialog" v-if="createVisable"></new-components>
     </el-dialog>
     <el-dialog
      :visible.sync="updateVisable"
      title="编辑经销商"
      center
      width="40%">
       <check-components :choosedData="choosedData"  @closeAll="closeDialog" v-if="updateVisable"></check-components>
     </el-dialog>
     <el-footer>
      <el-pagination
        :page-size="param.limit"
        :total="param.total"
        layout="total,prev, pager, next, jumper"
        background
        @current-change="currentChange"
      />
    </el-footer>
</div>
</template>
<script>
import {search,highSearch,deleteContact} from '@/api/contact/index';
import searchComponents from '@/views/components/search'
import newComponents from './new-components/index'

import checkComponents from './check-components/index'
export default {
    components:{
     searchComponents,
     newComponents,
     checkComponents,
    },
    data(){
        return{
            list:null,
            subOptionList:[
              {id:'name',name:'联系人名称',value:'',type:'normal'},
              {id:'company',name:'经销商公司',value:'',type:'normal'},
              {id:'address',name:'联系地址',value:'',type:'normal'},
              {id:'phone',name:'联系电话',value:'',type:'normal'},
              {id:'job',name:'相关职称',value:'',type:'normal'},
            ],
            
            
            userId:'',
            params:[],
            param:{
              keyword:'',
              user_id:'',
              total:0,
              page:1,
              limit:10},
            listLoading:false,
            createVisable:false,
            updateVisable:false,
            choosedData:null,
            keyword:'',
            
        }
    },
    mounted(){       
        this.commonSearch()
    },
    created(){
      this.userId=this.$store.state.user.uid
    
        //  console.log(this.$refs.search.multiSearchVisable,'哈哈1')
        // this.param.user_id=1;
        // this.commonSearch(this.param)
    },
    methods:{
        currentChange(page){    //分页
           this.param.page=page
           this.commonSearch()
        },
        createDealer(){
          this.createVisable=true
         
        },
        handleUpdate(data){
          this.updateVisable=true
          this.choosedData=data
        },
        handleDelete(data){
           deleteContact(data).then(res=>{
                this.$message({ message: '删除联系人成功!', type: 'success' })
                this.commonSearch()
           })
        },
        closeDialog(){
         this.createVisable=false
         this.updateVisable=false
         this.commonSearch()
        },
        commonSearch(){
          this.param.user_id = this.userId
          if(!this.$refs.search.highSearchVisable){ //高级查询判断
          this.listLoading=true
          this.param.keyword=this.keyword
          console.log(this.param,'传入数据')
        search(this.param).then(res=>{
          console.log(res,'返回数据')
           this.list=res.data.rows
           this.param.total=res.data.total
           this.listLoading=false
        })
        }
       else{
         this.listLoading=true
         this.params = this.$refs.search.getListQuery(10,1)
         this.params.push({id:"user_id",value:this.userId})
         console.log(this.params)
        //  const temp=[],
        //  temp=this.$refs.search.checkItems
        //  this.params.push(temp)
          highSearch(this.params).then(res=>{
          console.log(res,'返回')
          this.list=res.data.rows
          this.param.total=res.data.total
          this.listLoading=false
          })
        }
        }
        ,
        //   tableRowStyle({row,rowIndex}){
        //         return 'background-color:pink;font-size:15px;'
        //     },
        //     //设置表头行的样式
        //     tableHeaderColor({row,column,rowIndex,columnIndex}){
        //         return 'background-color:lightblue;color:#fff;font-wight:500;font-size:15px;text-align:center'

        //     },
       
    }
    
}
</script>