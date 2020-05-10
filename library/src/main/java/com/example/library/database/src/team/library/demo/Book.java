package com.example.library.database.src.team.library.demo;

import com.example.library.database.src.team.library.demo.DatabaseController.*;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
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

    //管理员授理归还
    public static void BackBook(String checkout_id,String return_id,String book_id,String libr_id,String reader_id,String return_time)
    {
        if(SearchBookState(book_id)==2)
        {
            //Date time1=fromstringtodate(getcheckouttime(book_id,reader_id));
            Date time1=fromstringtodate(getcheckouttimebyid(checkout_id));
            Date time2=fromstringtodate(return_time);
            BigDecimal fine=countfine(time1,time2);
            insertreturn(return_id,libr_id,book_id,getbookname(book_id),reader_id,return_time,fine,getreadername(reader_id));
            updatefine(reader_id,fine,true);
        //controller3.CountPerBookFine(returninfo.book_id, returninfo.reader_id);
        //controller3.SetPerBookFine(returninfo.book_id, returninfo.reader_id);
        //controller3.UpdateReaderSumFine(returninfo.reader_id);
            EditBookState(book_id,0);
        }
	    else{System.out.println("the book has not been checked out");}         
    }
    //得到读者姓名
    public static String getreadername(String reader_id)
    {
        JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select READER_NAME from reader where READER_ID=? ";
        String name=template.queryForObject(sql,String.class,reader_id);
        return name;
    }
    //得打书名
    public static String getbookname(String book_id)
    {
        JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select BOOK_NAME from book where BOOK_ID=? ";
        String name=template.queryForObject(sql,String.class,book_id);
        return name;
    }
    public static Date fromstringtodate(String s)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time=null;
        try {
            // Fri Feb 24 00:00:00 CST 201
            time=sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
    public static void updatefine(String reader_id,BigDecimal count,boolean ifadd)
    {
        String sql="";
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        if(ifadd)
            sql="update reader set READER_FINE=(READER_FINE+?) where READER_ID=?";
        else
            sql="update reader set READER_FINE=(READER_FINE-?) where READER_ID=?";
       template.update(sql,count,reader_id);
    }

    public static BigDecimal countfine(Date starttime,Date endtime)
    {
        BigDecimal count=new BigDecimal(0);
        long days=(endtime.getTime()-starttime.getTime())/(1000 * 60 * 60 * 24);
        if(days>30)
            count.add(new BigDecimal(days));
        return count;
    }


    //给读者展示已借阅仍未归还的书
    public static List<CheckoutInfo> showborrowbooks(String reader_id)
    {
        List<CheckoutInfo> list1=showcheckouttoreader(reader_id);
        JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select * from return where READER_ID=?";
        List<ReturnInfo> list2=template.query(sql,new BeanPropertyRowMapper<ReturnInfo>(ReturnInfo.class),reader_id);
        for(int i=0;i<list2.size();i++)
        {
            for(int j=0;j<list1.size();j++)
            {
                if(list1.get(j).book_id.equals(list2.get(i).book_id))
                {
                    list1.remove(j);
                    break;
                }
            }
        }
        return list1;
    }

    public static String getcheckouttime(String book_id,String reader_id)
    {
        JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select * from checked_out where BOOK_ID=? and READER_ID=? order by END_TIME DESC ";
        List<CheckoutInfo> list =template.query(sql,new BeanPropertyRowMapper<CheckoutInfo>(CheckoutInfo.class),book_id,reader_id);
        return list.get(0).end_time;
    }

    public static String getcheckouttimebyid(String checkout_id)
    {
        JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select END_TIME from checked_out where CHECKOUT_ID=? ";
        String time=template.queryForObject(sql,String.class,checkout_id);
        return time;
    }

    //插入归还记录
    public static void insertreturn(String return_id,String libr_id,String book_id,String book_name,String reader_id,String returntime,BigDecimal fine,String reader_name)
    {
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="insert into `return` values(?,?,?,?,?,?,?,?)";
        int count=template.update(sql,return_id,libr_id,book_id,book_name,reader_id,returntime,fine,reader_name);
    }

    //给读者展示借阅记录
    public static List<ResvInfo> showResvtoreader(String reader_id)
    {
        JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select * from reserve where READER_ID= ? ";
        List<ResvInfo> list=template.query(sql,new BeanPropertyRowMapper<ResvInfo>(ResvInfo.class) , reader_id);
        for (ResvInfo info:list) {
            JdbcTemplate temp=new JdbcTemplate(JdbcUtils.getDataSource());
            String sql2="select READER_NAME from reader where READER_ID=?";
            String name=temp.queryForObject(sql2,String.class,info.reader_id);
            info.setReader_name(name);
        }
        return list;
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

    public static List<ReturnInfo> showReturnList()
    {
        JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select * from `return` ";
        List<ReturnInfo> list=template.query(sql,new BeanPropertyRowMapper<ReturnInfo>(ReturnInfo.class));
        for (ReturnInfo info:list) {
            JdbcTemplate temp=new JdbcTemplate(JdbcUtils.getDataSource());
            String sql2="select READER_NAME from reader where READER_ID=?";
            String name=temp.queryForObject(sql2,String.class,info.reader_id);
            info.setReader_name(name);
        }
        return list;
    }

    //给读者展示归还记录
    public static List<ReturnInfo> showReturntoreader (String reader_id)
    {
        JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select * from `return` where READER_ID= ? ";
        List<ReturnInfo> list=template.query(sql,new BeanPropertyRowMapper<ReturnInfo>(ReturnInfo.class),reader_id);
        for (ReturnInfo info:list) {
            JdbcTemplate temp=new JdbcTemplate(JdbcUtils.getDataSource());
            String sql2="select READER_NAME from reader where READER_ID=?";
            String name=temp.queryForObject(sql2,String.class,info.reader_id);
            info.setReader_name(name);
        }
        return list;
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
        List<CheckoutInfo> list=template.query(sql,new BeanPropertyRowMapper<CheckoutInfo>(CheckoutInfo.class),reader_id);
        for (CheckoutInfo info:list) {
            JdbcTemplate temp=new JdbcTemplate(JdbcUtils.getDataSource());
            String sql2="select READER_NAME from reader where READER_ID=?";
            String name=temp.queryForObject(sql2,String.class,info.reader_id);
            info.setReader_name(name);
        }
        return list;
    }
    //提供所有借出记录
    public static List<CheckoutInfo> showallcheckout()
    {
        JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select * from checked_out ";
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
