package com.example.enumTest;

/**
 * @program: spring_test1
 * @description: Test
 * @author: XX
 * @create: 2022-10-26 16:48
 **/
public class Test {
    public static void main(String[] args) {
        System.out.println(Season.SUMMER);
        System.out.println("nowSeason->"+Season.SPRING+", value->"+Season.SPRING.ordinal());
        System.out.println("nextSeason->"+Season.getNextSeason(Season.SPRING));
        System.out.println("SEASON.SPRING.compareTo(SEASON.WINTER) --> "+ Season.SPRING.compareTo(Season.WINTER));

    }


}
