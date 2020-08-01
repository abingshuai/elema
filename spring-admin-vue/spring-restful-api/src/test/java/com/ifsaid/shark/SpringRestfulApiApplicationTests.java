package com.ifsaid.shark;

import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.entity.Contact;
import com.ifsaid.shark.mapper.ContactMapper;
import com.ifsaid.shark.mapper.ProductMapper;
import com.ifsaid.shark.mapper.StoreHouseMapper;
import com.ifsaid.shark.service.IContactService;
import com.ifsaid.shark.service.IDealerService;
import com.ifsaid.shark.service.IProductService;
import com.ifsaid.shark.service.IStoreHouseService;
import com.ifsaid.shark.util.JsonResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class SpringRestfulApiApplicationTests {
    static class TestThread extends Thread{
        CyclicBarrier barrier;
        public TestThread(CyclicBarrier barrier){
            this.barrier=barrier;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+":到达栅栏!");
                barrier.await();
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+"冲破栅栏!");
                barrier.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void it(){
       CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
           @Override
           public void run() {
               System.out.println(Thread.currentThread().getName()+":完成任务!");
           }
       });
          for(int i = 0;i<10;i++){
              new TestThread(cyclicBarrier).start();
          }
    }
    public boolean equationsPossible(String[] equations) {
        HashSet<String> set = new HashSet();
        Map<String, HashSet<String>> map = new HashMap<>();
        List<String> list = new ArrayList();
        int count = 0;
        for(int i = 0;i<equations.length;i++){
            String temp1 = equations[i].substring(0,1);
            String temp2 = equations[i].substring(3,4);
           set.add(equations[i].substring(0,1));
           set.add(equations[i].substring(3,4));

           if(equations[i].substring(1,2).equals(equations[i].substring(2,3))) {
               if (map.get(temp1) != null) {
                   map.get(temp1).add(temp2);

               }
               else {
                   HashSet temp = new HashSet();
                   temp.add(temp2);
                   map.put(temp1,temp);
               }
               if (map.get(temp2) != null) {
                   map.get(temp2).add(temp1);
               }
               else {
                   HashSet temp = new HashSet();
                   temp.add(temp1);
                   map.put(temp2,temp);
               }
           }
           else {
               list.add(temp1);
               list.add(temp2);
           }
        }
        System.out.println(list);
        System.out.println(set);
        System.out.println(map);
        for (int j=0;j<list.size()-1;j+=2){
            if(map.get(list.get(j))!=null) {
                if (map.get(list.get(j)).contains(list.get(j + 1))) {
                    return false;
                }
                if (map.get(list.get(j + 1)).contains(list.get(j))) {
                    return false;
                }
                for (Map.Entry temp : map.entrySet()) {
                    if ((!temp.getKey().equals(list.get(j))) && (!temp.getKey().equals(list.get(j + 1)))) {
                        HashSet s = map.get(temp.getKey());
                        if (s.contains(list.get(j)) && s.contains(list.get(j + 1))) {
                            return false;
                        }
                    }
                }
            }
            if(list.get(j).equals(list.get(j+1))){
                return false;
            }
        }

        return true;
    }
    @Test
    void contextLoads() {
      String[] str = {"a==b","e==c","b==c","a!=e"};
        System.out.println(equationsPossible(str));


    }

}
