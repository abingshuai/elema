package com.ifsaid.shark;

import com.ifsaid.shark.common.domain.TreeNode;
import com.ifsaid.shark.service.SysDepartmentService;
import com.ifsaid.shark.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

/**

 * <p>
 * 测试 系统部门 Service 类
 * </p>
 *
 * @author LinZhaoBing<316638644@qq.com>
 * @version 2.0
 * @date 2019/12/12 22:36

 */

//@Slf4j
//@SpringBootTest

public class SysDepartmentServiceTests {

    public Object instance = null;
    private static final int _1MB=1024*1024;
    private byte[] bigSize = new byte[2*_1MB];
    @Test
    public  void testGC(){
       String[] arr = new String[4];
       arr[0] = "ab";
       arr[1] = "18";
       arr[2] = "14";
       arr[3] = "12";
       for(int i = 0;i<arr.length;i++){
           System.out.println(arr[i].equals("18"));
       }
    }

}
