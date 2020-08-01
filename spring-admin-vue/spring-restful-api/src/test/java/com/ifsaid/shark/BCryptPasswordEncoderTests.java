package com.ifsaid.shark;

import com.ifsaid.shark.util.FileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.*;

/**

 * <p>
 * 用户密码加密
 * </p>
 *
 * @author LinZhaoBing<316638644@qq.com>
 * @version 2.0
 * @date 2019/12/25 21:47

 */

public class BCryptPasswordEncoderTests {
    private static ExecutorService pool;

    @Test
    public void test1(){
        int[] nums = {-1, 0, 1, 2, -1, -4};
        Arrays.sort(nums);
        for (int i =0;i<nums.length;i++){
            System.out.println(nums[i]);
        }
    }

    public static void main(String[] args) {
        pool = new ThreadPoolExecutor(5,1000000,60000, TimeUnit.MINUTES,new SynchronousQueue<Runnable>(), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        System.out.println("开始时间："+new Date());
        long starTime = System.currentTimeMillis();
        FileUtil fu = new FileUtil();
        for(int i=0;i<5;i++){
            pool.execute(new FileUtil());
            if(i>1000000) {
                System.out.println(FileUtil.getCount(1));
                if (FileUtil.getCount(1) == 1000000) {

                System.out.println(FileUtil.getCount(1));
                if(FileUtil.getCount(1)==10000) {

                }
            }
        }
        while (!pool.isShutdown()) {
            if (FileUtil.getCount(1) == 5) {
                System.out.println("结束池子");
                System.out.println("耗时:" + (System.currentTimeMillis() - starTime));
                pool.shutdown();

            }
        }
        }

        System.gc();



    }
    public void test(){

    }
    public static class ThreadTask implements Runnable{

        public ThreadTask() {

        }

        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

}
