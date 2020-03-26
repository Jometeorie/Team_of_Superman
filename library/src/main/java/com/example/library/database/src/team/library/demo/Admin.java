package com.example.library.database.src.team.library.demo;

import com.example.library.database.src.team.library.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin {
    /*
    * 用于测试，可能有重复定义的变量
    * */
   /* public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        System.out.println("请输入账名！");
        String id=in.nextLine();
        System.out.println("请输入密码！");
        String password=in.nextLine();
        boolean flag =new Admin().AdminLogin(id,password);
        System.out.println("登录验证"+flag);
        System.out.println("请输入账名！");
        String id=in.nextLine();
        System.out.println("请输入修改后的名字！");
        String name=in.nextLine();
        Boolean flag2=new Admin().LNameModify(id,name);
        System.out.println("姓名修改验证"+flag2);
        System.out.println("请输入修改后的密码！");
        String password=in.nextLine();
        Boolean flag3=new Admin().LPasswordModify(id,password);
        System.out.println("密码修改验证"+flag3);
        System.out.println("请输入管理员账名！");
        String libr_id=in.nextLine();
        System.out.println("请输入管理员名字！");
        String libr_name=in.nextLine();
        Boolean flag4=new Admin().LibrRegister(libr_id,libr_name);
        System.out.println("管理员注册验证"+flag4);
        System.out.println("请输入要删除的管理员账名！");
        String libr1_id=in.nextLine();
        Boolean flag5=new Admin().DeleteLibr(libr1_id);
        System.out.println("删除管理员用户"+flag5);
        System.out.println("请输入登出用户账名！");
        String id1=in.nextLine();
        boolean flag6=new Librarian().LibrLogout(id1);
        System.out.println("登出验证"+flag6);

    }*/
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
        Connection conn=null;
        PreparedStatement pstmt =null;
        try {
            conn= JdbcUtils.getConnection();
            String sql="update admin set STATE=? where ADMIN_ID=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setBoolean(1,flag);
            pstmt.setString(2,admin_id);
            int count=pstmt.executeUpdate();
            if(count==1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(pstmt,conn);
        }
        return false;
    }
    /**
     * 读者登出
     * */
    public  boolean AdminLogout(String admin_id){
        return ChangeState(admin_id,false);
    }
    /**
     * 注册管理员账号
     * */
    public static boolean LibrRegister(String Libr_ID,String name) {
        Connection con=null;
        PreparedStatement stmt=null;
        ResultSet rs =null;
        try {
            con=JdbcUtils.getConnection();
            String sql="insert into librarian(LIBR_ID,LIBR_NAME)values(?,?)";
            stmt=con.prepareStatement(sql);
            stmt.setString(1, Libr_ID);
            stmt.setString(2, name);
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
    /**
     * 注册超级管理员账号
     * */
    public static boolean AdminRegister(String Admin_ID,String name,String password) {
        Connection con=null;
        PreparedStatement stmt=null;
        ResultSet rs =null;
        try {
            con=JdbcUtils.getConnection();
            String sql="insert into admin(ADMIN_ID,ADMIN_NAME,PASSWORD)values(?,?,?)";
            stmt=con.prepareStatement(sql);
            stmt.setString(1, Admin_ID);
            stmt.setString(2, name);
            stmt.setString(3, password);
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
    /**
     * 删除图书管理员账号
     * */
    private static boolean DeleteLibr(String Libr_ID) {
        Connection con=null;
        PreparedStatement stmt=null;
        ResultSet rs =null;
        try {
            con=JdbcUtils.getConnection();
            String sql="delete from librarian where LIBR_ID=?";
            stmt=con.prepareStatement(sql);
            stmt.setString(1, Libr_ID);
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

}
