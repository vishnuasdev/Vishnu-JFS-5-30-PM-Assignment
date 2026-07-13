package org.example;

public class Product {

    private int productId;
    private String productName;
    private long price;

    public void setProductId(int productId){this.productId = productId;}

    public int getProductId(){return productId;}

    public void setProductName(String productName){this.productName = productName;}

    public String getProductName(){return productName;}

    public void setPrice(long price){this.price = price;}

    public long getPrice(){return price;}

    @Override
    public String toString(){return productId +" " + productName + " " + price;}
}
