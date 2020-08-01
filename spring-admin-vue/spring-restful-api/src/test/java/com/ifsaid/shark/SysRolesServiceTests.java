package com.ifsaid.shark;

import com.github.pagehelper.PageInfo;
import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.constant.GenderConstant;
import com.ifsaid.shark.constant.StatusConstant;
import com.ifsaid.shark.entity.SysUser;
import com.ifsaid.shark.mapper.DealerMapper;
import com.ifsaid.shark.service.IDealerService;
import com.ifsaid.shark.service.SysRoleService;
import com.ifsaid.shark.service.SysUserService;
import com.ifsaid.shark.util.JsonResult;
import com.ifsaid.shark.util.JsonUtils;
import com.ifsaid.shark.util.QueryParameter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;


/**

 * <p>
 * 测试 系统用户 Service 类
 * </p>
 *
 * @author LinZhaoBing<316638644@qq.com>
 * @version 2.0
 * @date 2019/12/12 22:36

 */


//@Slf4j
//@SpringBootTest
public class SysRolesServiceTests {
//    @Autowired
//    DealerMapper mapper;
    @Test
    public void test() throws ParseException {
        StringBuffer temp = new StringBuffer("insert into order_0 (user_id,birth) values");
        StringBuffer sql = new StringBuffer();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.54.117:3306/master", "backup", "123456");
            conn.setAutoCommit(false);

            Statement st = conn.createStatement();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = new Date();
            String timestamp = sdf.format(date);
            long begin = System.currentTimeMillis();
            for(int i = 0;i<10000;i++){
                sql.append(temp).append("("+i*2+",'"+timestamp+"');");

//                st.setInt(1,i*2);
//                st.setTimestamp(2,timestamp);
//                st.addBatch();
//                if((i+1)%100==0){
//                    st.executeBatch();
//                    st.clearBatch();
//                }
            }
            st.execute(sql.toString());
            long end = System.currentTimeMillis();
            System.out.println("耗时:"+(end-begin));
            conn.commit();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }


    }
    private static void dateParseTest(String timeZoneId, String strTime) throws Exception

    {

 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



// 指定传入的yyyyMMdd是哪个时区的时间

 sdf.setTimeZone(TimeZone.getTimeZone(timeZoneId));



 Date time = sdf.parse(strTime);

System.out.println(time);

    }



//    @Test
//    public void updateRoles() throws IOException {
//        String name = "test.txt";
//        String path = "D:\\Java\\";
//        File f = new File(path+name);
//        try(FileOutputStream fs= new FileOutputStream(f);
//            OutputStreamWriter os = new OutputStreamWriter(fs,"UTF-8");
//            BufferedWriter bw= new BufferedWriter(os);){
//            if(!f.exists()){
//                f.createNewFile();  //若不存在则创建
//            }
//            bw.write("wdnmd"); //写入字符
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }


}
