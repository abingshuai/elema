
## 数据库

大部分的权限管理系统都是5张表结构（同样我们这里也采用这种方式）

![image-20200803083829830](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200803083829830.png)

### **tb_sys_user -----> tb_sys_user_role  -----> tb_sys_role -----> tb_sys_role_permission ----> tb_sys_permission**

这里我们主要看权限表（tb_sys_permission ）

![image-20200803083906918](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200803083906918.png)

最重要的就是resources和type字段，这两个字段在后面和vue.js整合的时候会用到，

### type字段，只有两个类型，是按钮还是菜单
