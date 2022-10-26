package com.example.Factory;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: spring_test1
 * @description: 工厂模式反射实现
 * @author: XX
 * @create: 2022-10-26 09:51
 **/
public class SimpleFactoryReflection {
    private static final Map<EnumProductType, Class> activityIdMap = new HashMap<>();

    public static void addProductKey (EnumProductType enumProductType, Class product){
        activityIdMap.put(enumProductType, product);
    }

    public static activityOne product(EnumProductType type) throws IllegalAccessException,
            InstantiationException {
        Class productClass = activityIdMap.get(type);
        return (activityOne) productClass.newInstance();
    }

    public static void main(String[] args) throws InstantiationException,IllegalAccessException{
        addProductKey(EnumProductType.activityOne, activityOne.class);
        activityOne product = product(EnumProductType.activityOne);
        System.out.println(product.toString());

    }

    public static class Product {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class activityOne extends Product {
        private  String stock;
        @Override
        public String toString() {
            return "activityOne{" + "stock='" + stock + '\'' +'}';
        }
    }

    public class activityTwo extends Product {
        private String stock;
    }

    public enum EnumProductType {
        activityOne,activityTwo;
    }
}
