package com.example.Factory;

/**
 * @program: spring_test1
 * @description: FactoryMethod
 * @author: XX
 * @create: 2022-10-26 14:03
 **/
public abstract class FactoryMethod {
    protected abstract Product creatProduct(String name);

    public Product Product(String activity, String name){
        Product product = creatProduct(activity);
        product.setProductName(name);
        return product;
    }

    public static class Product {
        public String productName;

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
    }

    public enum EnumProductType {
        activityOne1("one"),activityTwo2("two");

        private String name;
        EnumProductType(String name){
            this.name=name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
