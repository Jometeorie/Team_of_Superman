package com.example.library.database.src.team.library.demo;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.library.database.src.team.library.util.JdbcUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Book{

    /**
     * SearchBook功能测试
     * */
    @Test
    public void test1(){
        List<BookInfo> list = SearchBook("h");
        String name;
        String location;
       /* for (BookInfo bookinfo : list) {
            System.out.println(bookinfo);
        }*/
        for (BookInfo bookinfo : list) {
            name=bookinfo.getBook_name();
            System.out.println(name);
            location=bookinfo.getLocation();
            System.out.println(location);
        }
    }
    /**
     * 书籍模糊查询
     * 返回信息：BOOK_NAME,AUTHOR,LOCATION,PRICE,CATEGORY,STATE
     * 返回List集合
     * */
    }
    public List SearchBook(String str){
        JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select BOOK_NAME,AUTHOR,LOCATION,PRICE,CATEGORY,STATE from book where BOOK_NAME like ?";
        List<BookInfo> list=template.query(sql,new BeanPropertyRowMapper<BookInfo>(BookInfo.class),"%" + str + "%");
        return list;
    }
    /**
     * 书籍编辑功能测试
     * */
    @Test
    public void test2(){
        System.out.println(new Book().EditLocation("floor4-434-C",2));
        System.out.println(new Book().EditBook_Name("i want ",2));
        System.out.println(new Book().EditPrice(BigDecimal.valueOf(26),2));
        System.out.println(new Book().EditCategory("math",2));

    }
    /**
     * 编辑书籍信息
     * 编辑LOCATION
     * */

    public Boolean EditLocation(String location,int book_id){
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="update book set LOCATION=? where BOOK_ID=?";
        int count=template.update(sql,location,book_id);
        if(count==1)
            return true;
        return false;
    }
    /**
     * 编辑书籍信息
     * 编辑名称
     * */
    public Boolean EditBook_Name(String book_name,int book_id){
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="update book set BOOK_NAME=? where BOOK_ID=?";
        int count=template.update(sql,book_name,book_id);
        if(count==1)
            return true;
        return false;
    }
    /**
     * 编辑书籍信息
     * 编辑书籍价格
     * */
    public Boolean EditPrice(BigDecimal price, int book_id){
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="update book set PRICE=? where BOOK_ID=?";
        int count=template.update(sql,price,book_id);
        if(count==1)
            return true;
        return false;
    }
    /**
     * 编辑书籍信息
     * 编辑书籍类别
     * */
    public Boolean EditCategory(String category, int book_id){
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="update book set CATEGORY=? where BOOK_ID=?";
        int count=template.update(sql,category,book_id);
        if(count==1)
            return true;
        return false;
    }
    /**
     * 删除书籍功能测试
     * */
    @Test
    public void test3(){
        System.out.println(new Book().InsertBook("hhhhh","lam","floor4-434-F", BigDecimal.valueOf(25)));
    }
    /**
     * 添加书籍
     * */

    public static boolean InsertBook(String B_Name,String author,String location,BigDecimal price ) {
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="insert into book(BOOK_NAME,AUTHOR,LOCATION,PRICE) values(?,?,?,?)";
        int count=template.update(sql,B_Name,author,location,price);
        if(count==1)
            return true;
        return false;
    }
    @Test
    public  void test4(){
        boolean flag=new Book().DeleteBook("17130177001",4,"hhhhh");
        System.out.println(flag);

    }
    /**
     * 删除书籍，并将删除信息添加到book_deleted
     * */

    public static boolean DeleteBook(String Libr_ID,int Book_ID,String Book_Name) {
        Connection con=null;
        PreparedStatement stmt=null;
        ResultSet rs =null;
        try {
            con=JdbcUtils.getConnection();
            String sql="delete from book where BOOK_ID=?";
            stmt=con.prepareStatement(sql);
            stmt.setInt(1,Book_ID);
            int result=stmt.executeUpdate();
            if (result>0)
                InsertBookDeleted(Libr_ID,Book_ID,Book_Name);
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(rs,stmt,con);
        }
        return false;
    }
    public static boolean InsertBookDeleted(String Libr_ID,int Book_ID,String Book_Name) {
        Connection con =null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            con=JdbcUtils.getConnection();
            String sql="insert into book_deleted values (?,?,?)";
            stmt=con.prepareStatement(sql);
            stmt.setInt(1,Book_ID);
            stmt.setString(2,Book_Name);
            stmt.setString(3,Libr_ID);
            stmt.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(rs,stmt,con);
        }
        return false;
    }

    public static void main(String[] args)  {
        List<Map<String, Object>> list=new Book().SearchBook("The");
        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap.get("PRICE"));
            Object ooo = stringObjectMap.get("PRICE");
        }
    }
}