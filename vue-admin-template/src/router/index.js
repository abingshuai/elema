import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  },



  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard', noCache: true, affix: true }
    }]
  },

  {
    path: '/error',
    name: 'Error',
    component: Layout,
    redirect: '/error/404',
    hidden: true,
    children: [
      {
        path: '404',
        component: () => import('@/views/error/404/index')
      },
      {
        path: '401',
        component: () => import('@/views/error/401/index')
      }
    ]
  },

  // 404 page must be placed at the end !!!
  {
    path: '*',
    redirect: '/error/404',
    hidden: true
  }

]

// 异步挂载的路由
// 动态需要根据权限加载的路由表
export const asyncRouterMap = [
  {
    path: '/pre',
    name: 'Pre',
    component: Layout,
    redirect: '/pre/index',
    meta: {
      resources: 'pre',
      title: '权限管理'
    },
    children: [
      {
        path: 'index',
        name: 'PrePerm',
        component: () => import('@/views/pre/perm/index'),
        meta: {
          resources: 'pre_perm',
          title: '权限管理'
        }
      },
      {
        path: 'user',
        name: 'PreUser',
        component: () => import('@/views/pre/user/index'),
        meta: {
          resources: 'pre_user',
          title: '用户管理'
        }
      },
      {
        path: 'role',
        name: 'PreRole',
        component: () => import('@/views/pre/role/index'),
        meta: {
          resources: 'pre_role',
          title: '角色管理'
        }
      },
      {
        path: 'dept',
        component: () => import('@/views/pre/dept/index'),
        name: 'PreDept',
        meta: {
          resources: 'pre_dept',
          title: '部门管理'
        }
      }
    ]
  },
  {
    path: '/goods',
    name: 'Goods',
    component: Layout,
    redirect: '/goods/index',
    meta: {
      resources: 'goods',
      title: '商品管理'
    },
    children: [
      {
        path: 'list',
        name: 'GoodsList',
        component: () => import('@/views/goods/list/index'),
        meta: {
          resources: 'goods_list',
          title: '商品列表'
        }
      },
      {
        path: 'type',
        name: 'GoodsType',
        component: () => import('@/views/goods/type/index'),
        meta: {
          resources: 'goods_type',
          title: '商品分类'
        }
      }
    ]
  },
  {
    path: '/order',
    name: 'Order',
    component: Layout,
    redirect: '/order/index',
    meta: {
      resources: 'order',
      title: '订单管理'
    },
  },
  {
    path: '/dealer',
    name: 'Dealer',
    component: Layout,
    redirect: '/dealer/index',
    meta: {
      resources: 'dealer',
      title: '经销商管理'
    },
    children:[
      {
        path: 'myDealer',
        name: 'myDealer',
        component: () => import('@/views/dealer/myDealer/index'),
        meta: {
          resources: 'dealer_myDealer',
          title: '我的经销商'
        }
      },
      {
        path: 'myContact',
        name: 'myContact',
        component: () => import('@/views/dealer/myContact/index'),
        meta: {
          resources: 'dealer_myContact',
          title: '我的联系人'
        }
      },
    ]
  },
  {
    path: '/storage',
    name: 'Storage',
    component: Layout,
    redirect: '/storage/index',
    meta: {
      resources: 'storage',
      title: '仓储管理'
    },
    children:[
      {
        path: 'product',
        name: 'product',
        component: () => import('@/views/storage/product/index'),
        meta: {
          resources: 'storage_product',
          title: '产品管理'
        }
      },
      {
        path: 'storeHouse',
        name: 'storeHouse',
        component: () => import('@/views/storage/storeHouse/index'),
        meta: {
          resources: 'storage_storeHouse',
          title: '仓库管理'
        }
      },
      {
        path: 'stock',
        name: 'stock',
        component: () => import('@/views/storage/stock/index'),
        meta: {
          resources: 'storage_stock',
          title: '库存管理'
        }
      },
    ]
  },

  {
    path: '/sys',
    name: 'Sys',
    component: Layout,
    redirect: '/sys/index',
    meta: {
      resources: 'sys',
      title: '系统设置'
    },
    children: [

      {
        path: 'dictionary',
        component: () => import('@/views/sys/dictionary/index'),
        name: 'SysDictionary',
        meta: {
          resources: 'sys_dictionary',
          title: '字典集合'
        }
      },
      {
        path: 'logs',
        name: 'SysLogs',
        component: () => import('@/views/sys/logs/index'),
        meta: {
          resources: 'sys_logs',
          title: '日志数据'
        }
      },
      {
        path: 'swagger',
        name: 'SysSwagger',
        component: () => import('@/views/sys/swagger/index'),
        meta: {
          resources: 'sys_swagger2',
          title: 'API文档'
        }
      },
      {
        path: 'test',
        name: 'SysTest',
        component: () => import('@/views/sys/test/index'),
        meta: {
          resources: 'sys_test',
          title: 'API文档'
        }
      }
    ]
  }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
