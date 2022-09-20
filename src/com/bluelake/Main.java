package com.bluelake;

import com.bluelake.utils.LevenshteinAlgorithm;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * @auther DR_bluelake
 * @create 2022/9/21-1:30
 */
public class Main {
    static final String ENCODING = "utf-8";
    static final String ANSWER_FILE = "D:\\paper_rechecking_answer.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //输入文件地址
        System.out.println("请输入文件1的地址：");
        String aAddress = sc.nextLine();
        System.out.println("请输入文件2的地址：");
        String bAddress = sc.nextLine();
        String a = readFile(aAddress);
        String b = readFile(bAddress);
        System.out.println("生成结果已经存储在"+ANSWER_FILE);
        //取得系统时间功能
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = df.format(System.currentTimeMillis())+aAddress+"于"+bAddress+ "的相似度为 ： "
                + String.format("%.3f", LevenshteinAlgorithm.Levenshtein(a, b));
        writeAnswer(result);
        System.out.println(result);
    }


    static public String readFile(String path) {
        File file = new File(path);
        try {
            InputStreamReader inputStreamReader=
                    new InputStreamReader(new FileInputStream(file), ENCODING);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            if (file.isFile() && file.exists()) {
                String line;
                //StringBuilder在单线程情况比StringBuffer下具有更好的性能
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();
            } else {
                System.out.println("该输入文件不存在");
            }
            inputStreamReader.close();
        } catch (Exception e) {
            System.out.println("文件读取失败");
        }
        return null;
    }

    static public void writeAnswer(String str) {
        File answer_file=new File(ANSWER_FILE);
        if(!answer_file.exists()){
            try {
                answer_file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        byte[] bytes =str.getBytes();
        int b=bytes.length;  //是字节的长度，不是字符串的长度
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(answer_file);
            fileOutputStream.write(bytes,0,b);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
