<template>
  <el-container id="goods">
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
            @click="getGoodsTableData"
          >搜索</el-button>
        </el-col>
        <el-col :span="4">
          <el-button
            v-has="'goods_list:create'"
            type="primary"
            @click="addGoodsEntity"
          >添加商品</el-button>
        </el-col>
        <el-col :span="6">
          <!-- 修改商品信息 -->
          <el-dialog
            :visible.sync="dialog.visible"
            :title="dialog.title"
            width="30%"
            :before-close="handleClose"
          >
            <el-form
              id="goods"
              ref="entity"
              :model="entity"
              :rules="rules"
              label-width="80px"
            >
              <template v-if="entity.id != ''">
                <el-form-item label="商品ID">
                  <el-input v-model="entity.id" disabled />
                </el-form-item>
              </template>
              <el-form-item label="商品名称" prop="name">
                <el-input v-model="entity.name" clearable />
              </el-form-item>
              <el-form-item label="商品描述" prop="description">
                <el-input v-model="entity.description" clearable />
              </el-form-item>
              <el-form-item label="商品图" prop="img">
                <user-avatar :avatar="entity.img" />
              </el-form-item>
              <el-form-item label="分类" prop="goodsClassifyId">
                <el-select
                  v-model="entity.goodsClassifyId"
                  clearable
                  filterable
                  placeholder="请选择"
                >
                  <el-option
                    v-for="item in typeList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  />
                </el-select>
              </el-form-item>
              <el-form-item label="原价" prop="originPrice">
                <el-input v-model="entity.originPrice" clearable />
              </el-form-item>
              <el-form-item label="折扣" prop="discount">
                <el-input v-model="entity.discount" clearable />
              </el-form-item>
              <el-form-item label="库存" prop="stock">
                <el-input v-model="entity.stock" clearable />
              </el-form-item>
              <el-form-item label="限购数量" prop="limitNum">
                <el-input v-model="entity.limitNum" clearable />
              </el-form-item>
              <el-form-item label="状态" prop="status">
                <el-select
                  v-model="entity.status"
                  clearable
                  filterable
                  placeholder="请选择"
                >
                  <el-option
                    v-for="item in optionsStatus"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="dialog.visible = false">取 消</el-button>
              <el-button type="primary" @click="saveAndFlush">确 定</el-button>
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
        <el-table-column prop="name" label="商品名称"  />
        <el-table-column prop="description" label="商品描述" />
        <el-table-column prop="img" label="商品图" />
        <el-table-column prop="classifyName" label="分类名称" />
        <el-table-column prop="originPrice" label="原价" />
        <el-table-column prop="sellPrice" label="售价" />
        <el-table-column prop="discount" label="折扣" />
        <el-table-column prop="stock" label="库存" />
        <el-table-column prop="limitNum" label="限购数量" />
        <el-table-column prop="totalSales" label="总销量" />
        <el-table-column label="状态" width="70">
          <template slot-scope="scope">{{
            scope.row.status | showGoodsStatus
            }}</template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="250">
          <template slot-scope="scope">
            <el-button
              v-has="'goods_list:update'"
              type="primary"
              @click="updateGoodsEntity(scope.row)"
            >编辑</el-button>
            <el-button
              v-has="'goods_list:delete'"
              type="danger"
              @click="deleteGoodsEntity(scope.row)"
            >删除</el-button>
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
  search,
  getAll,
  getGoodsPage,
  saveGoods,
  updateGoods,
  deleteGoods
} from '@/api/goods/index'

  import UserAvatar from '@/components/UserAvatar'

  export default {
    name: 'Goods',
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
        typeList: [],
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
        // 校验规则
        rules: {
          name: [{ required: true, message: '商品名称不能为空', trigger: 'blur' }],
          description: [{ required: true, message: '商品描述不能为空', trigger: 'blur' }],
          originPrice: [{ required: true, message: '商品原价不能为空', trigger: 'blur' }],
          sellPrice: [{ required: true, message: '商品售价不能为空', trigger: 'blur' }],
          discount: [{ required: true, message: '商品折扣不能为空', trigger: 'blur' }],
          stock: [{ required: true, message: '商品库存不能为空', trigger: 'blur' }],
          limitNum: [{ required: true, message: '商品限购数量不能为空', trigger: 'blur' }],
          totalSales: [{ required: true, message: '商品总销量不能为空', trigger: 'blur' }],
          status: [{ required: true, message: '状态必须选择', trigger: 'blur' }],

        }
      }
    },
    created() {
      this.getGoodsTableData()
    },
    methods: {
      getGoodsTableData() {
        const _this = this
        _this.loading = true
        getGoodsPage(_this.page).then(result => {
          _this.tableData = result.list
          _this.page.total = result.total
          _this.loading = false
        })
      },
      currentChange(pageNum) {
        // 切换分页
        this.page.pageNum = pageNum
        this.getGoodsTableData()
      },
      emptyEntity() {
        // 清空用户信息
        this.entity = {
          id: '',
          goodsClassifyId: '',
          name: '', // 商品名称
          description: '', // 商品描述
          img: '', // 商品图
          originPrice: '', // 原价
          discount: '', // 折扣
          stock: '', // 库存
          limitNum: '', // 限购数量
          status: '', // 状态
        }
      },
      addGoodsEntity() {
        let _this = this
        getAll().then(result => {
          _this.typeList = result
          // 添加商品
          this.emptyEntity()
        })
        this.dialog = {
          visible: true,
          title: '新建商品'
        }
      },
      handleNodeClick(data) {
        console.log('data :', data)
      },
      updateGoodsEntity(data) {

        let _this = this
        getAll().then(result => {
          _this.typeList = result
          // 修改商品信息
          this.emptyEntity()
          this.entity = {
            id: data.id,
            goodsClassifyId: data.goodsClassifyId,
            name: data.name, // 商品名称
            description: data.description, // 登录账户
            img: data.img, // 商品图
            originPrice: data.originPrice, // 原价
            sellPrice: data.sellPrice, // 售价
            discount: data.discount, // 折扣
            stock: data.stock, // 库存
            limitNum: data.limitNum, // 限购数量
            totalSales: data.totalSales, // 总销量
            status: data.status, // 状态
            deleted: data.deleted
          }
          this.dialog = {
            visible: true,
            title: '修改商品信息'
          }
        })

      },
      deleteGoodsEntity(data) {
        // 删除商品
        const _this = this
        if (data.id !== null && data.id !== '') {
          _this
            .$confirm(
              '确定要删除【' + data.name + '】吗? 是否继续?',
              '警告',
              {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }
            )
            .then(() => {
              deleteGoods(data.id).then(result => {
                _this.$message({ type: 'success', message: '删除成功!' })
                _this.getGoodsTableData()
              })
            })
        } else {
          _this.$notify.error({
            title: '错误',
            message: '请先选中商品,才可以删除'
          })
        }
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done()
          })
          .catch(_ => {})
      },
      saveAndFlush() {
        const _this = this
        console.log(_this.entity)
        _this.$refs.entity.validate(valid => {
          if (valid) {
            if (_this.entity.id !== '') {
              updateGoods(_this.entity).then(result => {
                _this.$notify({
                  title: '成功',
                  message: '修改商品成功!',
                  type: 'success'
                })
                _this.getGoodsTableData()
                _this.dialog.visible = false
              })
            } else {
              saveGoods(_this.entity).then(result => {
                _this.$notify({
                  title: '成功',
                  message: '添加商品成功!',
                  type: 'success'
                })
                _this.getGoodsTableData()
                _this.dialog.visible = false
              })
            }
          }
        })
      }
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
  #goods {
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
