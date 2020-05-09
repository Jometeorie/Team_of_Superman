package com.example.library.database.src.team.library.demo;

import com.example.library.database.src.team.library.demo.DatabaseController;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.library.database.src.team.library.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
    /**
     * 登录测试
     * */
    @Test
    public void test1(){
        System.out.println(new Admin().AdminLogin("17130166001","152152"));//True
        System.out.println(new Admin().AdminLogin("1351234567","12345678"));//False
    }
    /**
     * 超级管理员登录验证
     * @return 返回boolean值，false为登录失败，true为成功
     * */
    public  boolean AdminLogin(String admin_id,String passoword){
        if(admin_id==null||passoword==null){
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
            String sql="select * from admin where ADMIN_ID=? and PASSWORD=? ";
            //获取执行sql的对象
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,admin_id);
            pstmt.setString(2,passoword);
            //执行查询
            rs=pstmt.executeQuery();
            //判断
            flag= rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(rs,pstmt,conn);
        }
        if(flag)
            ChangeState(admin_id,true);
        return flag;
    }
    /**
     * 修改状态
     **/

    public  boolean ChangeState(String admin_id,Boolean flag){
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="update admin set STATE=? where ADMIN_ID=?";
        int count=template.update(sql,flag,admin_id);
        if(count==1)
            return true;
        return false;
    }
    /**
     * 登出测试
     * */
    @Test
    public void test2(){
        System.out.println(new Admin().AdminLogout("17130166001"));//True
    }
    /**
     * 超级管理员登出
     * */
    public  boolean AdminLogout(String admin_id){
        return ChangeState(admin_id,false);
    }
    /**
     * 注册管理员账号测试
     * */
    @Test
    public void test3(){
        System.out.println(Admin.LibrRegister("17130177005","Lora"));//报错可能是数据库中已经有了改用户
    }
    /**
     * 注册管理员账号
     * */
    public static boolean LibrRegister(String Libr_ID,String name) {
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="insert into librarian(LIBR_ID,LIBR_NAME)values(?,?)";
        int count=template.update(sql,Libr_ID,name);
        if(count==1)
            return true;
        return false;
    }

    /**
     * 删除管理员账号测试
     * */
    @Test
    public void test4(){
        System.out.println(Admin.DeleteLibr("17130177005"));
    }

    /**
     * 删除图书管理员账号
     * */
    private static boolean DeleteLibr(String Libr_ID) {
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="delete from librarian where LIBR_ID=?";
        int count=template.update(sql,Libr_ID);
        if(count==1)
            return true;
        return false;
    }

    /**
     * 超级管理员修改管理员个人信息测试
     * */
    @Test
    public void test5(){
        System.out.println(new Admin().LPasswordModify("17130177002","111111"));//true
        System.out.println(new Admin().LNameModify("17130177002","Brandt"));//true

    }
    /**
     * 超级管理员修改管理员个人信息
     * 修改密码
     * */
    public boolean LPasswordModify(String Libr_ID,String N_Password)  {
        return new Librarian().LPasswordModify(Libr_ID,N_Password);
    }
    /**
     * 超级管理员修改管理员个人信息
     * 修改姓名
     * */
    public boolean LNameModify(String Libr_ID,String N_Name)  {
        return new Librarian().LNameModify(Libr_ID,N_Name);
    }

    /**
     * 超级管理员找回管理员密码测试
     * */
    @Test
    public void test6(){
        System.out.println(new Admin().FindLPassword("17130177001"));//true
    }
    /**
     * 超级管理员找回管理员密码
     * */
    public String FindLPassword(String Libr_ID){
        Connection conn=null;
        ResultSet rs=null;
        PreparedStatement pstmt =null;
        try {
            conn= JdbcUtils.getConnection();
            String sql="select * from librarian where LIBR_ID=? ";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,Libr_ID);
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


    /**
     * 注册管理员账号测试
     * */
    @Test
    public void test7(){
        System.out.println(Admin.AdminRegister("17130166004","Nacy","123456"));//下次测试要更改id!!
    }
    /**
     * 注册超级管理员账号
     * */
    public static boolean AdminRegister(String Admin_ID,String name,String password) {
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="insert into admin(ADMIN_ID,ADMIN_NAME,PASSWORD)values(?,?,?)";
        int count=template.update(sql,Admin_ID,name,password);
        if(count==1)
            return true;
        return false;
    }

    /**
     * 超级管理员修改其密码测试
     * */
    @Test
    public void test8(){
        System.out.println(new Admin().APasswordModify("17130166003","1255"));
    }
    /**
     * 超级管理员修改其密码
     * */
    public boolean APasswordModify(String admin_id,String password)  {
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="UPDATE admin set PASSWORD=? where ADMIN_ID=?";
        int count=template.update(sql,password,admin_id);
        if(count==1)
            return true;
        return false;
    }
   /**
    * 超级管理员设置罚金
    * */ 
    public void fineSet(double fine){
        DatabaseController.FineSet(fine);
    }
    /**
    * 超级管理员设置还书期限天数
    * */ 
    public void dateSet(){
        DatabaseController.DateSet(date);
    } 
}
