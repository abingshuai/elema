<template>
  <el-container id="type">
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
            @click="getTypeTableData"
          >搜索</el-button>
        </el-col>
        <el-col :span="4">
          <el-button
            v-has="'goods_type:create'"
            type="primary"
            @click="addTypeEntity"
          >添加分类</el-button>
        </el-col>
        <el-col :span="6">
          <!-- 修改分类信息 -->
          <el-dialog
            :visible.sync="dialog.visible"
            :title="dialog.title"
            width="30%"
            :before-close="handleClose"
          >
            <el-form
              id="type"
              ref="entity"
              :model="entity"
              :rules="rules"
              label-width="80px"
            >
              <template v-if="entity.id != ''">
                <el-form-item label="分类ID">
                  <el-input v-model="entity.id" disabled />
                </el-form-item>
              </template>
              <el-form-item label="分类名称" prop="name">
                <el-input v-model="entity.name" clearable />
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
        <el-table-column prop="name" label="分类名称"  />
        <el-table-column label="添加时间">
          <template slot-scope="scope">{{
            scope.row.createTime | formatDateTime
            }}</template>
        </el-table-column>
        <el-table-column label="修改时间">
          <template slot-scope="scope">{{
            scope.row.lastUpdateTime | formatDateTime
            }}</template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="250">
          <template slot-scope="scope">
            <el-button
              v-has="'goods_type:update'"
              type="primary"
              @click="updateTypeEntity(scope.row)"
            >编辑</el-button>
            <el-button
              v-has="'goods_type:delete'"
              type="danger"
              @click="deleteTypeEntity(scope.row)"
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
  getTypePage,
  saveType,
  updateType,
  deleteType
} from '@/api/goods/index'

  import UserAvatar from '@/components/UserAvatar'

  export default {
    name: 'Type',
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
          name: '', // 分类名称
          deleted: ''
        },
        dialog: {
          visible: false,
          title: ''
        },
        loading: false,
        tableData: [],
        cascaderProps: {
          children: 'children',
          label: 'title',
          value: 'id',
          emitPath: false,
          checkStrictly: true
        },
        pickerOptions: {
          disabledDate(time) {
            return time.getTime() > Date.now()
          }
        },
        // 校验规则
        rules: {
          name: [{ required: true, message: '分类名称不能为空', trigger: 'blur' }],

        }
      }
    },
    created() {
      this.getTypeTableData()
    },
    methods: {
      getTypeTableData() {
        const _this = this
        _this.loading = true
        getTypePage(_this.page).then(result => {
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
        // 清空分类信息
        this.entity = {
          id: '',
          name: '', // 分类名称
        }
      },
      addTypeEntity() {
        // 添加分类
        this.emptyEntity()
        this.dialog = {
          visible: true,
          title: '新建分类'
        }
      },
      handleNodeClick(data) {
        console.log('data :', data)
      },
      updateTypeEntity(data) {
        // 修改分类信息
        this.emptyEntity()
        this.entity = {
          id: data.id,
          name: data.name, // 分类名称
          deleted: data.deleted
        }
        this.dialog = {
          visible: true,
          title: '修改分类信息'
        }
      },
      deleteTypeEntity(data) {
        // 删除分类
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
              deleteType(data.id).then(result => {
                _this.$message({ type: 'success', message: '删除成功!' })
                _this.getTypeTableData()
              })
            })
        } else {
          _this.$notify.error({
            title: '错误',
            message: '请先选中分类,才可以删除'
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
        _this.$refs.entity.validate(valid => {
          if (valid) {
            if (_this.entity.id !== '') {
              updateType(_this.entity).then(result => {
                _this.$notify({
                  title: '成功',
                  message: '修改分类成功!',
                  type: 'success'
                })
                _this.getTypeTableData()
                _this.dialog.visible = false
              })
            } else {
              saveType(_this.entity).then(result => {
                _this.$notify({
                  title: '成功',
                  message: '添加分类成功!',
                  type: 'success'
                })
                _this.getTypeTableData()
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
  #type {
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
