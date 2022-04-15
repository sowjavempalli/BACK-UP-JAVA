package com.mycompany.domain;
/*POJO - plain old java object - classes which are supposed to hold data */
public class product1 {

    //since this class holds the same data as the db table, it should have the same fields as the table
    String productid;
    String productname;
    int productprice;

    //default constructor
    public product1(){

    }

    public product1(String productid, String productName, int productPrice) {
        this.productid = productid;
        this.productname = productName;
        this.productprice = productPrice;
    }

    //getter and setters


    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductName() {
        return productname;
    }

    public void setProductName(String productName) {
        this.productname = productName;
    }

    public int getProductPrice() {
        return productprice;
    }

    public void setProductPrice(int productPrice) {
        this.productprice = productPrice;
    }

    @Override
    public String toString(){
        return ("product [productid=" + productid + ", productName=" + productname + ", productPrice=" + productprice + "]");
    }

}