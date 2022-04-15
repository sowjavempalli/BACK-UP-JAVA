package com.mycompany.dao;
/*DAO- data access object = classes with methods will interact with the database */

import java.sql.*;
import java.util.*;
import com.mycompany.dbutil.DBUtil;
import com.mycompany.domain.product1;

public class ProductManagementDAO {

    //get all products method.  used List instead of ArrayList so it's better code management
    //incase we want to change productList ArrayList to a LinkedList, we don't have to change the jdbc code that much
    public List<product1> getAllProducts()
    {
        List<product1> productList = new ArrayList<product1>();
        try
        {
            //typical jdbc coding
            Connection conn = DBUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM product");
            while(rs.next())
            {
                product1 product = new product1(rs.getString("productid"), rs.getString("productname"), rs.getInt("productprice"));
                productList.add(product);
            }
            DBUtil.closeConnection(conn);  //close connection
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return productList;
    }

    //different query
    public product1 getProductByid(String productId)
    {
        product1 product = null;
        try
        {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product WHERE productid = ?");
            ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();
            //iterate through result
            while(rs.next())
            {
                product = new product1(rs.getString("productid"), rs.getString("productNname"), rs.getInt("productPrice"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return product;
    }

    public int addProduct(product1 product)
    {
        //status displays 1 if successfully inserted data or error; successful or not
        int status = 0;
        try
        {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO product VALUES(?,?,?)");
            //set parameters of query here but using the values for the product object
            ps.setString(1, product.getProductid());
            ps.setString(2, product.getProductName());
            ps.setInt(3, product.getProductPrice());
            status = ps.executeUpdate();  //if successful status should return 1
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }

    //updates a product already in the table
    public int updateProduct(product1 product)
    {
        int status = 0;
        try
        {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE product SET productNname=?, productPprice=? WHERE productid=?");
            //set parameters of query here but using the values for the product object
            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getProductPrice());
            ps.setString(3, product.getProductid());
            status = ps.executeUpdate();  //if successful status should return 1
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }

    //deltes product already in the table
    public int deleteProduct(String productId)
    {
        int status = 0;
        try
        {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM product where productid = ?");
            //set parameters of query here but using the values for the product object
            ps.setString(1, productId);
            status = ps.executeUpdate();  //if successful status should return 1

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }

}