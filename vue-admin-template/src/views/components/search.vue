<template>
  <div class="search">
    <el-row>
      <el-button @click="getListQuery">测试</el-button>
      <el-button type="primary" @click="highSearch">高级查询</el-button>
    </el-row>
    <div v-show="highSearchVisable" class="searchComponents">
      <el-row :gutter="20">
        <el-col :span="10">
          <el-tag>选择多级查询条件</el-tag>
        </el-col>
        <el-col :span="10">
          <el-button type="primary" size="small" @click="reset">重置检索条件</el-button>
        </el-col>
      </el-row>
        <!-- <el-checkbox-group >  
                  -->
        <el-row :gutter="20">
        <el-col v-for="item in optionList"
          :key="item.id" :span="10">
        <el-checkbox
          v-model="checkItems"
          @change="handleCheckBox"
          :label="item"
        >{{item.name}}</el-checkbox>
        </el-col>
        <!-- <el-date-picker v-if="item.type=='time'"></el-date-picker>  -->

        <!-- </el-checkbox-group> -->
      </el-row>
      <div>
        <el-row
          :gutter="20"
          v-show="multiSearchVisable"
          v-for="items in checkItems"
          :key="items.id"
        >
          <el-col :span="8" style="line-height:32px;color:#409EFF">{{items.name+":"}}</el-col>
          <el-col :span="8">
            <div v-if="items.type=='time'">
              <el-date-picker
                size="small"
                v-model="items.value[0]"
                type="date"
                placeholder="选择起始日期"
                value-format="yyyy-MM-dd"
                style="width:160px;"
              ></el-date-picker>
              <el-date-picker
                size="small"
                v-model="items.value[1]"
                type="date"
                placeholder="选择结束日期"
                value-format="yyyy-MM-dd"
                style="width:160px;"
              ></el-date-picker>
            </div>
            <el-input
              style="width:150px"
              type="text"
              v-if="items.type=='normal'"
              v-model="items.value"
            ></el-input>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    optionList: Array
  },
  data() {
    return {
      checkItems: [],
      // choosedOptions:[],
      highSearchVisable: false,
      multiSearchVisable: false,
      choosed: null,
      keywords: null,
      listQuery: [],
      
    };
  },
  // watch:{
  //  choosedOptions(val){
  //      console.log(this.choosedOptions,'嘿嘿')
  //  }
  // },
  methods: {
    getListQuery(limit,page){
      if(this.listQuery.length>0){
        this.listQuery=[]
      }
      
    
     this.checkItems.forEach(element => {
       const searchParam={
        id:'',
        value:''
      }
       if(element.type=='time'){
        searchParam.id=element.id+"_start_time";
        searchParam.value=element.value[0],
        this.listQuery.push(searchParam)
        searchParam.id=element.id+"_end_time";
        searchParam.value=element.value[1],
        this.listQuery.push(searchParam)
       }
       else{
         searchParam.id=element.id
         searchParam.value=element.value
         this.listQuery.push(searchParam)
       }

     });
     this.listQuery.push({id:'limit',value:limit})
     this.listQuery.push({id:'page',value:page})
     return this.listQuery
    },
    reset() {
      this.multiSearchVisable = false;
      if (this.checkItems.length > 0) {
        this.checkItems.forEach(element => {
          if (element.value instanceof Array) {
         
            element.value[0] = "";
            element.value[1] = "";
          } else {
            element.value = null;
          }
        });
      }
      this.checkItems = [];
      this.multiSearchVisable = true;
    },

    handleCheckBox() {
      console.log(this.checkItems);

      if (this.checkItems.length > 0) {
        this.multiSearchVisable = true;
      } else {
        this.multiSearchVisable = false;
      }
    },
    highSearch() {
      if (!this.highSearchVisable) {
        this.highSearchVisable = true;
      } else {
        this.highSearchVisable = false;
      }
    },
    
  }
};
</script>
<style>
.searchRow {
  height: 80px;
  line-height: 80px;
  font-size: 15px;
}
.search {
  border: 1px solid #fff;
  border-radius: 5px;
}
.searchComponents {
  margin-left: -158%;
  padding: 15px;
  border: 1px solid #409eff;
  border-radius: 5px;
  width: 80%;
}
</style>
