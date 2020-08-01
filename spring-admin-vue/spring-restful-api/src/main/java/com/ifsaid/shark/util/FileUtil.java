package com.ifsaid.shark.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FileUtil implements Runnable {
    public static String fileDir="D:\\CUST\\Bing\\jj\\";
    public static StringBuffer sb=new StringBuffer();
    public static int totalCount=0;
    public static int count=0;
    public static synchronized int getTotalCount() {
        return totalCount;
    }

    public static synchronized void setTotalCount(int totalCount) {
        FileUtil.totalCount = totalCount;
    }

    public static synchronized StringBuffer getSb() {
        return sb;
    }

    public static synchronized void setSb(StringBuffer sb) {
        FileUtil.sb = sb;
    }

    public static synchronized void setCount(int count) {
        FileUtil.count = count;
    }


    public static synchronized int getCount() {

        return count++;

    }
    public static synchronized  int getCount(int a){
        return count;
    }

    public static synchronized StringBuffer getContext(){
        StringBuffer sb = new StringBuffer();
        sb.append(getCount()).append("-").append(getRandStr(5)).append("\n");
        return sb;
    }


    public static StringBuffer getRandStr(int length){
        String total="1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<length;i++) {
            sb.append(total.charAt(new Random().nextInt(62)));
        }

        return sb;
    }
    public static BufferedWriter createOrOpenFile(final String fullname,final boolean append){
        File file = new File(fullname);
        try {
        if(!file.getParentFile().exists()){
            if(!file.getParentFile().mkdir()){
            }
        }
        if(!file.exists()){
            file.createNewFile();
            FileWriter fw =new FileWriter(file.getAbsoluteFile(),append);
            BufferedWriter bw = new BufferedWriter(fw);
            return bw;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static void write(BufferedWriter bw,String str){
        try {
            bw.append(str);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bw.flush();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public synchronized void writeFile(StringBuffer content){

        System.out.println(FileUtil.getCount(1));
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
        Date date = new Date();
        String fullname = getRandStr(5)+".sql";
        setCount(getCount()+1);
        FileUtil.write(FileUtil.createOrOpenFile(FileUtil.fileDir + fullname, true), String.valueOf(content));

    }
    @Override
    public void run() {
        int i = 0;
        StringBuffer ssb = new StringBuffer();
        while (i<1000000) {
            ssb.append(i++).append("-").append(getRandStr(5)).append("\n");
            }
        writeFile(ssb);
        }




//           writeFile();

//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }



    public static void main(String[] args) {
        String fullname = FileUtil.getRandStr(5)+".txt";
        FileUtil.write(FileUtil.createOrOpenFile(fileDir+fullname,true),FileUtil.getRandStr(10)+"\r\n");
    }
}
