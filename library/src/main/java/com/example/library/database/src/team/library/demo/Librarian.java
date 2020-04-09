package com.example.library.database.src.team.library.demo;

// import sun.security.util.Password;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.library.database.src.team.library.util.JdbcUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.util.Scanner;

public class Librarian {
    /**
     * 登录测试
     * */
    @Test
    public void test1(){
        System.out.println(new Librarian().LibrLogin("17130177001","123456789"));//True
        System.out.println(new Librarian().LibrLogin("1351234567","12345678"));//False
    }
    /**
     * 管理员登录验证
     * @return 返回boolean值，false为登录失败，true为成功
     * */
    public  boolean LibrLogin(String libr_id,String passoword){
        if(libr_id==null||passoword==null){
            return false;
        }
        Boolean flag=false;
        Connection conn=null;
        ResultSet rs=null;
        PreparedStatement pstmt =null;
        try {
            //获取连接
            conn= JdbcUtils.getConnection();
            //定义sql
            String sql="select * from librarian where LIBR_ID=? and PASSWORD=? ";
            //获取执行sql的对象
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,libr_id);
            pstmt.setString(2,passoword);
            //执行查询
            rs=pstmt.executeQuery();
            //判断library
            flag= rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(rs,pstmt,conn);
        }
        if(flag)
            ChangeState(libr_id,true);
        return flag;
    }
    /**
     * 修改状态
     **/
    private  boolean ChangeState(String libr_id,Boolean flag){
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="update librarian set STATE=? where LIBR_ID=?";
        int count=template.update(sql,flag,libr_id);
        if(count==1)
            return true;
        return false;
    }

    /**
     * 登出测试
     * */
    @Test
    public void test2(){
        System.out.println(new Librarian().LibrLogout("17130177001"));//True
    }
    /**
     * 读者登出
     * */
    public  boolean LibrLogout(String libr_id){
        return ChangeState(libr_id,false);
    }


    /**￼

     * 管理员个人信息修改测试
     * */
    @Test
    public void test3() {
        System.out.println(new Librarian().LPasswordModify("17130177001", "1234555"));//true
        System.out.println(new Librarian().LNameModify("17130177001", "Tracy"));//true
    }

    /**
     * 管理员个人信息修改
     * 修改姓名
     * */
    public boolean LNameModify(String Libr_ID,String N_Name)  {
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="update librarian set LIBR_NAME=? where LIBR_ID=?";
        int count=template.update(sql,N_Name,Libr_ID);
        if(count==1)
            return true;
        return false;
    }
    /**
     * 管理员个人信息修改
     * 修改密码
     * */
    public boolean LPasswordModify(String Libr_ID,String N_Password)  {
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="update librarian set PASSWORD=? where LIBR_ID=?";
        int count=template.update(sql,N_Password,Libr_ID);
        if(count==1)
            return true;
        return false;
    }

    @Test
    public void test4(){
        System.out.println(new Librarian().FindPassword("13412345679","12345678@qq.com"));
    }
    /**
     * 找回密码
     * */
    public  String  FindPassword(String reader_id,String e_mail){
       if(reader_id==null||e_mail==null)
            return null;
        Connection conn=null;
        ResultSet rs=null;
        PreparedStatement pstmt =null;
        try {
            conn= JdbcUtils.getConnection();
            String sql="select * from reader where READER_ID=? and E_MAIL=? ";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,reader_id);
            pstmt.setString(2,e_mail);
            rs= pstmt.executeQuery();
            while(rs.next()){
                String res=rs.getString("PASSWORD");
                return res;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(rs,pstmt,conn);
        }
        return null;
    }

    @Test
    public  void test5(){
        System.out.println(new Librarian().ReaderRegister("13412345655","Momo","1234678@qq.com"));
        System.out.println(new Librarian().DeleteReader("13412345655"));

    }


    /**
     * 通过管理员注册
     * 注册读者账号
     * */
    public boolean ReaderRegister(String READER_ID,String name,String E_mail) {
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="insert into reader(READER_ID,READER_NAME,E_MAIL)values(?,?,?)";
        int count=template.update(sql,READER_ID,name,E_mail);
        if(count==1)
            return true;
        return false;
    }
    /**
     * 通过管理员删除读者账号
     * */
    public boolean DeleteReader(String Reader_ID) {
        Connection con=null;
        PreparedStatement stmt=null;
        ResultSet rs =null;
        try {
            con=JdbcUtils.getConnection();
            String sql="delete from reader where READER_ID=? and READER_FINE=?";
            stmt=con.prepareStatement(sql);
            stmt.setString(1,Reader_ID);
            stmt.setBigDecimal(2, BigDecimal.valueOf(0.00));
            int count=stmt.executeUpdate();
            if(count==1){
                return true;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(rs,stmt,con);
        }
        return false;
    }

    /**管理员修改读者信息
    * */
    public boolean NameModify(String Reader_ID,String N_name)  {
        return new Reader().NameModify(Reader_ID,N_name);
    }
    public boolean E_mailModify(String Reader_ID,String N_Email)  {
        return new Reader().E_mailModify(Reader_ID,N_Email);
    }
    boolean PasswordModify(String Reader_ID,String N_Password)  {
        return new Reader().PasswordModify(Reader_ID, N_Password);
    }


}