package com.example.test;

import java.util.HashMap;

/**
 * @program: spring_test1
 * @description:
 * @author: XX
 * @create: 2022-11-21 17:54
 **/
class Soap{
    private String s;
    public Soap() {
        System.out.println("Soap()无参构造器");
        s = "Constructed";		//在类构造器中初始化
    }

    @Override
    public String toString() { return s;}
}

public class Bath {
    private String s1 = "Happy";//在定义对象的地方初始化
    private Soap castille = new Soap();
    private String s2;
    private int i;

    public Bath() { System.out.println("Bath() 无参构造器");}
    //实例初始化
    {
        i = 31;
        System.out.println("初始化i为31");
    }

    @Override
    public String toString() {
        if(s2 == null) {		//惰性初始化
            s2 ="Java";
        }
        return s1 + "\t"+ s2 + "\t" + i + "\t" + castille;
    }

    public static void main(String[] args) {

//        System.out.println("===================");
//        String ser = "-Dserver.port";
//        String a = System.getProperty(ser);
//        System.out.println(a);
        HashMap<String,String> map = new HashMap<>();
        map.put("Apple","美国");
        map.put("Sony","日本");
        map.put("Huawei","中国");
        System.out.println(map.get("Apple"));

    }
}
