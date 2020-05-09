package com.example.library.database.src.team.library.demo;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

// import com.alibaba.druid.sql.visitor.functions.Now;
import com.example.library.database.src.team.library.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
// import java.sql.SQLException;
import java.util.List;
// import java.util.Map;
import java.util.UUID;

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

    public List<BookInfo> SearchBook(String str){
        JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select BOOK_NAME,AUTHOR,LOCATION,PRICE,CATEGORY,STATE,BOOK_ID from book where BOOK_NAME like ?";
        List<BookInfo> list=template.query(sql,new BeanPropertyRowMapper<BookInfo>(BookInfo.class),"%" + str + "%");
        File[] covers=new File("library/src/main/resources/static/cover").listFiles();
        for (BookInfo book:list)
        {
            for (File f:covers)
            {
                if(!f.isDirectory()&&f.getName().contains(book.getBook_id()))
                    book.setBook_id(f.getName());
            }
        }
        return list;
    }

    public static int SearchBookState(String Book_id){
        JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select STATE from book where BOOK_ID = ?";
        int State=template.queryForObject(sql,Integer.class,Book_id);
        return State;
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

    public static Boolean EditBookState(String Book_id,int state)
    {
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="update book set STATE=? where BOOK_ID=?";
        int count=template.update(sql,state,Book_id);
        if(count==1)
            return true;
        return false;
    }
    /**
     * 删除书籍功能测试
     * */
    @Test
    public void test3(){
        System.out.println(Book.InsertBook(getUUID(), "hhhhh","lam","floor4-434-F", BigDecimal.valueOf(25)));
    }
    /**
     * 添加书籍
     * */
    public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString(); 
        String uuidStr=str.replace("-", "");
        return uuidStr;
      }
      
    public static boolean InsertBook(String B_ID, String B_Name,String author,String location,BigDecimal price ) {
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="insert into book(BOOK_ID, BOOK_NAME,AUTHOR,LOCATION,PRICE) values(?,?,?,?,?)";
        int count=template.update(sql,B_ID,B_Name,author,location,price);
        if(count==1)
            return true;
        return false;
    }
    @Test
    public  void test4(){
        boolean flag=Book.DeleteBook("17130177001",4,"hhhhh");
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

    public static boolean deleteResv(String RESV_ID)
    {
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="delete from reserve where RESV_ID=?";
        int count=template.update(sql,RESV_ID);
        if(count==1)
            return true;
        return false;
    }

    public static boolean changeResvState(String RESV_ID,String Status)
    {
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="update reserve set STATUS=? where RESV_ID=?";
        int count=template.update(sql,Status,RESV_ID);
        if(count==1)
            return true;
        return false;
    }

    //读者预约书，time格式为"2021-12-12 23:59:59"
    public static boolean reservebook(String RESV_ID,String Book_ID,String Book_name,String Starttime,String Endtime,String Reader_ID)
    {
        if(SearchBookState(Book_ID)==0) {
            Connection con = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                con = JdbcUtils.getConnection();
                String sql = "insert into reserve values (?,?,?,?,?,?,?)";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, RESV_ID);
                stmt.setString(2, Book_ID);
                stmt.setString(3, Book_name);
                stmt.setString(4, Reader_ID);
                stmt.setString(5, Starttime);
                stmt.setString(6, Endtime);
                stmt.setString(7, "Waiting");
                stmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                JdbcUtils.close(rs, stmt, con);
            }
            EditBookState(Book_ID,1);
            return true;
        }
        return  false;
    }

    public void updatereserve()  //更新预约表，删除超出时间的记录
    {
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="delete from reserve where unix_timestamp(DATE_ADD(BEGIN_TIME,INTERVAL 2 HOUR))<unix_timestamp(now())";
        int count=template.update(sql);
    }

    //管理员受理借阅
    public static boolean EditResv(String RESV_ID,String Book_ID,boolean IfAgree,CheckoutInfo checkedinfo)
    {
        if(IfAgree)
        {
            if(SearchBookState(Book_ID)==0)
            {
                EditBookState(Book_ID,2);
                deleteResv(RESV_ID);
                insertcheckout(checkedinfo.checkout_id,checkedinfo.libr_id,checkedinfo.book_id,checkedinfo.book_name,checkedinfo.reader_id,checkedinfo.end_time);
                return true;
            }
            else
            {System.out.println("the book has been reserved");return false;}
        }
        else
        {
            changeResvState(RESV_ID,"Disagree");
            EditBookState(Book_ID,0);
            return true;
        }
    }

    //还书,不可用
    public static void BackBook(String Book_ID,String Backtime)
    {
        EditBookState(Book_ID,0);
        //changeResvState(SearchReserveID(Book_ID), "Back");
    }

    //给管理员展示借书请求
    public static List<ResvInfo> showResvList()
    {
        JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select * from reserve ";
        List<ResvInfo> list=template.query(sql,new BeanPropertyRowMapper<ResvInfo>(ResvInfo.class));
        for (ResvInfo info:list) {
            JdbcTemplate temp=new JdbcTemplate(JdbcUtils.getDataSource());
            String sql2="select READER_NAME from reader where READER_ID=?";
            String name=temp.queryForObject(sql2,String.class,info.reader_id);
            info.setReader_name(name);
        }
        return list;
    }

    // 根据Book_ID 查找Reserve_ID
    public static String SearchReserveID(String Book_id){
        JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select RESV_ID from reserve where BOOK_ID = ?";
        String RESV_ID=template.queryForObject(sql,String.class,Book_id);
        return RESV_ID;
    }

    //插入借出记录
    public static void insertcheckout(String checkout_id,String libr_id,String book_id,String book_name,String reader_id,String checkouttime)
    {
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="insert into checked_out values(?,?,?,?,?,?)";
        int count=template.update(sql,checkout_id,libr_id,book_id,book_name,reader_id,checkouttime);
    }

    //向管理员提供该读者的借阅记录
    public static List<CheckoutInfo> showcheckouttoreader(String reader_id)
    {
        JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select * from checked_out where READER_ID = ? ";
        List<CheckoutInfo> list=template.query(sql,new BeanPropertyRowMapper<CheckoutInfo>(CheckoutInfo.class));
        for (CheckoutInfo info:list) {
            JdbcTemplate temp=new JdbcTemplate(JdbcUtils.getDataSource());
            String sql2="select READER_NAME from reader where READER_ID=?";
            String name=temp.queryForObject(sql2,String.class,info.reader_id);
            info.setReader_name(name);
        }
        return list;
    }

    //交钱，type为钱的类型，0为罚金，1为保证金
    public static void takemoney(String take_id,String libr_id,String reader_id,String take_time,BigDecimal money_Amount,int type)
    {
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="insert into takemoney values(?,?,?,?,?,?)";
        int count=template.update(sql,take_id,libr_id,reader_id,take_time,money_Amount,type);
        if(type==1)
        {
            JdbcTemplate temp=new JdbcTemplate(JdbcUtils.getDataSource());
            String sql1="update reader set READER_FINE=(READER_FINE-?) where READER_ID=?";
            temp.update(sql1,money_Amount,reader_id);
        }
    }

    //图书馆一段时间内交易记录，0为罚金，1为保证金，2为两者都是
    public static List<MoneyTakeInfo> showMoneyInfo(String starttime,String endtime,int type)
    {
        List<MoneyTakeInfo> list;
        if(type==0)
        {
            JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
            String sql = "select * from takemoney where MONEY_TYPE=0 and unix_timestamp(TAKE_TIME)>? and  unix_timestamp(TAKE_TIME)<?";
            list=template.query(sql,new BeanPropertyRowMapper<MoneyTakeInfo>(MoneyTakeInfo.class),starttime,endtime);
        }
        if(type==1)
        {
            JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
            String sql = "select * from takemoney where MONEY_TYPE=1 and unix_timestamp(TAKE_TIME)>? and  unix_timestamp(TAKE_TIME)<?";
            list=template.query(sql,new BeanPropertyRowMapper<MoneyTakeInfo>(MoneyTakeInfo.class),starttime,endtime);
        }
        else
        {
            JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
            String sql = "select * from takemoney where unix_timestamp(TAKE_TIME)>? and  unix_timestamp(TAKE_TIME)<?";
            list=template.query(sql,new BeanPropertyRowMapper<MoneyTakeInfo>(MoneyTakeInfo.class),starttime,endtime);
        }
        for (MoneyTakeInfo info:list) {
            JdbcTemplate temp=new JdbcTemplate(JdbcUtils.getDataSource());
            String sql2="select READER_NAME from reader where READER_ID=?";
            String name=temp.queryForObject(sql2,String.class,info.reader_id);
            info.setReader_name(name);
        }
        return list;
    }

    public static void main(String[] args)  {
            // Book.reservebook("123", "00e8682064fa4d1f92f622fc9c5de278", "2020-04-12 10:35:5", "2020-04-15 10:35:5", "13412345679");
            // Book.EditResv("123", "00e8682064fa4d1f92f622fc9c5de278", false);
            // Book.BackBook("00e8682064fa4d1f92f622fc9c5de278", "2020-04-14 10:35:5");
    }
}