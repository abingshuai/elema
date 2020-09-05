<template>
  <el-container id="order">
    <el-header>
      <el-row :gutter="20">
        <el-col :span="8">
          <el-input
            v-model="page.keywords"
            clearable
            placeholder="请输入关键字"
          />
        </el-col>
        <el-col :span="6">
          <el-button
            type="primary"
            icon="el-icon-search"
            @click="getOrderTableData"
          >搜索</el-button>
        </el-col>
        <el-col :span="6">
          <!-- 订单详情 -->
          <el-dialog
            :visible.sync="dialog.visible"
            :title="dialog.title"
            width="90%"
            :before-close="handleClose"
          >
            <el-table
              :data="itemList"
              stripe
              style="width: 100%">
              <el-table-column
                prop="orderNo"
                label="订单编号"
                width="180">
              </el-table-column>
              <el-table-column
                prop="goodsName"
                label="商品名称"
                width="180">
              </el-table-column>
              <el-table-column
                prop="goodsClassifyName"
                label="商品分类"
                width="180">
              </el-table-column>
              <el-table-column
                prop="goodsDescription"
                label="商品描述"
                width="180">
              </el-table-column>
              <el-table-column
                prop="goodsImg"
                label="商品图"
                width="180">
              </el-table-column>
              <el-table-column
                prop="goodsCount"
                label="商品数量"
                width="180">
              </el-table-column>
              <el-table-column
                prop="goodsPrice"
                label="商品单价"
                width="180">
              </el-table-column>
              <el-table-column
                prop="subTotal"
                label="小计"
                width="180">
              </el-table-column>
              <el-table-column
                prop="createTime"
                label="创建时间"
                width="180">
              </el-table-column>
            </el-table>
            <span slot="footer" class="dialog-footer">
            </span>
          </el-dialog>
        </el-col>
      </el-row>
    </el-header>
    <el-main>
      <el-table
        v-loading="loading"
        element-loading-text="拼命加载中..."
        element-loading-spinner="el-icon-loading"
        :data="tableData"
        border
        style="width: 100%"
      >
        <el-table-column prop="id" label="ID" width="80" fixed/>
        <el-table-column prop="orderNo" label="订单编号"  />
        <el-table-column prop="concatName" label="联系人名字" />
        <el-table-column prop="concat" label="联系人方式" />
        <el-table-column prop="totalPrice" label="商品总金额" />
        <el-table-column prop="totalQuantity" label="商品总数量" />
        <el-table-column label="订单状态" width="70">
          <template slot-scope="scope">{{
            scope.row.status | showGoodsStatus
            }}</template>
        </el-table-column>
        <el-table-column label="创建时间">
          <template slot-scope="scope">{{
            scope.row.createTime | formatDateTime
            }}</template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="250">
          <template slot-scope="scope">
            <el-button
              v-has="'order_list:item'"
              type="primary"
              @click="viewOrderItem(scope.row)"
            >查看订单详情</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-main>
    <el-footer>
      <el-pagination
        :page-size="page.pageNum"
        :total="page.total"
        layout="total,prev, pager, next, jumper"
        background
        @current-change="currentChange"
      />
    </el-footer>
  </el-container>
</template>

<script>
import {
  getOrderPage,
  getOrderItemByNo
} from '@/api/order/index'

  import UserAvatar from '@/components/UserAvatar'

  export default {
    name: 'Order',
    components: {
      UserAvatar
    },
    data() {
      return {
        page: {
          pageNum: 1,
          pageSize: 10,
          keywords: '',
          total: 0
        },
        entity: {
          id: '',
          goodsClassifyId: '',
          name: '', // 商品名称
          description: '', // 商品描述
          img: '', // 商品图
          originPrice: '', // 原价
          sellPrice: '', // 售价
          discount: '', // 折扣
          stock: '', // 库存
          limitNum: '', // 限购数量
          totalSales: '', // 总销量
          status: 0, // 状态
          deleted: ''
        },
        dialog: {
          visible: false,
          title: ''
        },
        userRole: {
          visible: false,
          title: ''
        },
        loading: false,
        tableData: [],
        itemList: [],
        cascaderProps: {
          children: 'children',
          label: 'title',
          value: 'id',
          emitPath: false,
          checkStrictly: true
        },
        optionsStatus: [
          { value: 0, label: '下架' },
          { value: 1, label: '上架' },
        ],
        pickerOptions: {
          disabledDate(time) {
            return time.getTime() > Date.now()
          }
        },
      }
    },
    created() {
      this.getOrderTableData()
    },
    methods: {
      getOrderTableData() {
        const _this = this
        _this.loading = true
        getOrderPage(_this.page).then(result => {
          _this.tableData = result.list
          _this.page.total = result.total
          _this.loading = false
        })
      },
      viewOrderItem(data){
        const _this = this
        getOrderItemByNo(data.orderNo).then(result =>{
          _this.itemList = result
        })
        this.dialog = {
          visible: true,
          title: '订单详情'
        }
      },
      currentChange(pageNum) {
        // 切换分页
        this.page.pageNum = pageNum
        this.getGoodsTableData()
      },
      handleNodeClick(data) {
        console.log('data :', data)
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done()
          })
          .catch(_ => {})
      },
    }
  }
</script>
<style lang="scss" scoped>
  .avatar-wrapper {
    cursor: pointer;
    position: relative;
    .user-avatar {
      width: 50px;
      height: 50px;
      border-radius: 10px;
    }
  }
</style>
<style lang="scss">
  #order {
    .el-tag:hover {
      cursor: pointer;
    }
    .el-date-editor {
      width: 100%;
    }
    .el-cascader {
      width: 100%;
    }
    .el-select {
      width: 100%;
    }
  }
</style>
