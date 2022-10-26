package com.example.Factory;

/**
 * @program: spring_test1
 * @description: 商品工厂去继承抽象工厂方法
 * @author: XX
 * @create: 2022-10-26 14:17
 **/
public class ProductFactory extends FactoryMethod {

    @Override
    protected Product creatProduct(String activityId) {
        if (EnumProductType.activityOne1.getName().equals(activityId)) {
            //这里可以处理我们自己的想要的业务逻辑代码
            return new OneProduct();
        }else if (EnumProductType.activityTwo2.getName().equals(activityId)){
            return new TwoProduct();
        }
        return null;
    }

    public static class OneProduct extends Product {

    }
    public static class TwoProduct extends Product {

    }

    public static void main(String[] args) {
        FactoryMethod factoryMethod = new ProductFactory();
        Product product = factoryMethod.Product("one", "one");
        System.out.println(product.getProductName());
    }

}
