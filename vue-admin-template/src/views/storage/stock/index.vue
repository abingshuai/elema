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
        <el-table-column label="仓库名称" min-width="140px">
          <template slot-scope="scope">
            <span>{{ scope.row.storeHouseName }}</span>
          </template>
        </el-table-column>
        <el-table-column label="产品批号" min-width="140px">
          <template slot-scope="scope">
            <span>{{ scope.row.productId }}</span>
          </template>
        </el-table-column>
        <el-table-column label="产品名称" min-width="140px">
          <template slot-scope="scope">
            <span>{{ scope.row.productName }}</span>
          </template>
        </el-table-column>
         <el-table-column label="产品数量" min-width="140px">
          <template slot-scope="scope">
            <span>{{ scope.row.productNums }}</span>
          </template>
        </el-table-column>
        <el-table-column label="产品状态" min-width="140px">
          <template slot-scope="scope">
            <el-tag type="warning" v-if="scope.row.status==0">待处理</el-tag>
            <el-tag type="primary" v-if="scope.row.status==1">待入库</el-tag>
            <el-tag type="success" v-if="scope.row.status==2">已入库</el-tag>
            <el-tag  type="primary" v-if="scope.row.status==3">已出库</el-tag>
          </template>
        </el-table-column>
        
         <el-table-column fixed="right" label="操作" width="250">
          <template slot-scope="scope">
 
            <el-button v-if="scope.row.status==1" @click="handleInbound(scope.row)"  type="primary" >入库</el-button>
            <el-button v-if="scope.row.status==2" @click="handleOutbound(scope.row)"  type="danger" >出库</el-button>
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
      :visible.sync="inboundVisable"
      title="确认入库"
      center
      width="40%">
       <inbound-components :choosedData="choosedData"  @closeAll="closeDialog" v-if="inboundVisable"></inbound-components>
     </el-dialog>
     <el-dialog
      :visible.sync="outboundVisable"
      title="确认出库"
      center
      width="40%">
       <outbound-components :choosedData="choosedData"  @closeAll="closeDialog" v-if="outboundVisable"></outbound-components>
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
import {search} from '@/api/stock/index';
import searchComponents from '@/views/components/search'
// import newComponents from './new-components/index'
import inboundComponents from './check-components/inbound'
import outboundComponents from './check-components/outbound'
export default {
    components:{
     searchComponents,
     inboundComponents,
     outboundComponents
    //  newComponents,
    //  checkComponents,
    },
    data(){
        return{
            list:null,
            subOptionList:[
              {id:'area',name:'销售区域',value:'',type:'normal'},
              {id:'manager',name:'管理员',value:'',type:'normal'},
              {id:'address',name:'联系地址',value:'',type:'normal'},
              {id:'phone',name:'联系电话',value:'',type:'normal'},
              {id:'name',name:'经销商名称',value:'',type:'normal'},
              {id:'level',name:'经销商级别',value:'',type:'normal'}
            ],
            
            userId:'',
            params:[],
            param:{
              keyword:'',
              user_id:'',
              page:1,
              limit:5,
              status:'',
              total:null},
            listLoading:false,
            createVisable:false,
            updateVisable:false,
            inboundVisable:false,
            outboundVisable:false,
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
        handleDelete(data){
            alert(data)
           deleteProduct(data).then(res=>{
                this.$message({ message: '删除产品信息成功!', type: 'success' })
                this.commonSearch()
           })
        },
        createDealer(){
          this.createVisable=true
         
        },
        handleOutbound(data){
          this.choosedData=data
          this.outboundVisable=true
          
        },
        handleInbound(data){
          this.choosedData=data
          this.inboundVisable=true
          
        },
        closeDialog(){
         this.createVisable=false
         this.outboundVisable=false
         this.inboundVisable=false
         this.commonSearch()
        },
        commonSearch(){
          console.log(this.$refs.search.multiSearchVisable,'高级',!this.$refs.search.highSearchVisable)
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
        currentChange(page){    //分页
           this.param.page=page
           this.commonSearch()
        }
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