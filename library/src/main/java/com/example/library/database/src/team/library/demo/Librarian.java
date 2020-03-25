package com.example.library.database.src.team.library.demo;

import sun.security.util.Password;
import com.example.library.database.src.team.library.util.JdbcUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Librarian {
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
        boolean flag =new Librarian().LibrLogin(id,password);
        System.out.println("登录验证"+flag);
        System.out.println("请输入修改后的名字！");
        String name=in.nextLine();
        Boolean flag2=new Librarian().LNameModify(id,name);
        System.out.println("姓名修改验证"+flag2);
        System.out.println("请输入修改后的密码！");
        password=in.nextLine();
        Boolean flag3=new Librarian().LPasswordModify(id,password);
        System.out.println("密码修改验证"+flag3);
        System.out.println("请输入读者账名！");
        String user_id=in.nextLine();
        System.out.println("请输入读者名字！");
        String user_name=in.nextLine();
        System.out.println("请输入读者邮箱！");
        String e_mail=in.nextLine();
        Boolean flag4=new Librarian().ReaderRegister(user_id,user_name,e_mail);
        System.out.println("用户注册验证"+flag4);
        System.out.println("请输入要删除的读者账名！");
        String userd_id=in.nextLine();
        Boolean flag5=new Librarian().DeleteReader("18391669235");
        System.out.println("删除读者用户"+flag5);
        System.out.println("请输入登出用户账名！");
        id=in.nextLine();
        boolean flag6=new Librarian().LibrLogout(id);
        System.out.println("登出验证"+flag6);
        System.out.println("请输入读者账名！");
        String user_id2=in.nextLine();
        System.out.println("请输入读者邮箱！");
        String e_mail2=in.nextLine();
        String rs=new Librarian().FindPassword(user_id2,e_mail2);
        System.out.println(rs);
    }*/
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
        Connection conn=null;
        PreparedStatement pstmt =null;
        try {
            conn= JdbcUtils.getConnection();
            String sql="update librarian set STATE=? where LIBR_ID=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setBoolean(1,flag);
            pstmt.setString(2,libr_id);
            pstmt.executeUpdate();
            return true;
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
    public  boolean LibrLogout(String libr_id){
        return ChangeState(libr_id,false);
    }

    /**
     * 管理员个人信息修改
     * 修改姓名
     * */
    public boolean LNameModify(String Libr_ID,String N_Name)  {
        Connection con=null;
        PreparedStatement stmt=null;
        ResultSet rs =null;
        try {
            con=JdbcUtils.getConnection();
            String sql="update librarian set LIBR_NAME=? where LIBR_ID=?";
            stmt=con.prepareStatement(sql);
            stmt.setString(1,N_Name );
            stmt.setString(2, Libr_ID);
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
     * 管理员个人信息修改
     * 修改密码
     * */
    public boolean LPasswordModify(String Libr_ID,String N_Password)  {
        Connection con=null;
        PreparedStatement stmt=null;
        ResultSet rs =null;
        try {
            con=JdbcUtils.getConnection();
            String sql="update librarian set PASSWORD=? where LIBR_ID=?";
            stmt=con.prepareStatement(sql);
            stmt.setString(1, N_Password);
            stmt.setString(2, Libr_ID);
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

    /**
     * 通过管理员注册
     * 注册读者账号
     * */
    public boolean ReaderRegister(String READER_ID,String name,String E_mail) {
        Connection con=null;
        PreparedStatement stmt=null;
        ResultSet rs =null;
        try {
            con=JdbcUtils.getConnection();
            String sql="insert into reader(READER_ID,READER_NAME,E_MAIL)values(?,?,?)";
            stmt=con.prepareStatement(sql);
            stmt.setString(1, READER_ID);
            stmt.setString(2, name);
            stmt.setString(3, E_mail);
            stmt.executeUpdate();
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(rs,stmt,con);
        }
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
