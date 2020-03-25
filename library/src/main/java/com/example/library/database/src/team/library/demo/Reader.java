package com.example.library.database.src.team.library.demo;
import com.example.library.database.src.team.library.util.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Reader {
    /*
     * 用于测试，可能有重复定义的变量
     * */
   public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        System.out.println("请输入账名！");
        String id=in.nextLine();
        System.out.println("请输入密码！");
        String password=in.nextLine();
        boolean flag =new Reader().ReaderLogin(id,password);
        System.out.println("登录验证"+flag);
        System.out.println("请输入修改后的名字！");
        String name=in.nextLine();
        Boolean flag2=new Reader().NameModify(id,name);
        System.out.println("姓名修改验证"+flag2);
        System.out.println("请输入修改后的邮箱！");
        String e_mail=in.nextLine();
        Boolean flag3=new Reader().E_mailModify(id,e_mail);
        System.out.println("邮箱修改验证"+flag3);
        System.out.println("请输入修改后的密码！");
        password=in.nextLine();
        Boolean flag4=new Reader().PasswordModify(id,password);
        System.out.println("密码修改验证"+flag4);
        boolean flag5=new Reader().ReaderLogout(id);
        System.out.println("登出验证"+flag5);
    }

    /**
     * 读者登录验证
     * @return 返回boolean值，false为登录失败，true为成功
    * */
    public  boolean ReaderLogin(String reader_id,String passoword){
        if(reader_id==null||passoword==null){
            return false;
        }
        Boolean flag=false;
        Connection conn=null;
        ResultSet rs=null;
        PreparedStatement pstmt =null;
        try {
            conn= JdbcUtils.getConnection();
            String sql="select * from reader where READER_ID=? and PASSWORD=? ";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,reader_id);
            pstmt.setString(2,passoword);
            rs=pstmt.executeQuery();
            flag= rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(rs,pstmt,conn);
        }
        if(flag)
            ChangeState(reader_id,true);
        return flag;
    }
    /**
     * 修改状态
    **/

    private  boolean ChangeState(String reader_id,Boolean flag){
        Connection conn=null;
        PreparedStatement pstmt =null;
        try {
            conn= JdbcUtils.getConnection();
            String sql="update reader set STATE=? where READER_ID=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setBoolean(1,flag);
            pstmt.setString(2,reader_id);
            int count=pstmt.executeUpdate();
            if(count==1)
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
    public  boolean ReaderLogout(String reader_id){
        return ChangeState(reader_id,false);
    }
    /**
     * 读者个人信息修改
     * 修改姓名
    * */
    public boolean NameModify(String Reader_ID,String N_name)  {
        Connection con=null;
        PreparedStatement stmt=null;
        ResultSet rs =null;
        try {
            con=JdbcUtils.getConnection();
            String sql="update reader set READER_NAME=? where READER_ID=?";
            stmt=con.prepareStatement(sql);
            stmt.setString(1, N_name);
            stmt.setString(2, Reader_ID);
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
     * 读者个人信息修改
     * 修改邮箱
     * */
    public boolean E_mailModify(String Reader_ID,String N_Email)  {
        Connection con=null;
        PreparedStatement stmt=null;
        ResultSet rs =null;
        try {
            con=JdbcUtils.getConnection();
            String sql="update reader set E_MAIL=? where READER_ID=?";
            stmt=con.prepareStatement(sql);
            stmt.setString(1, N_Email);
            stmt.setString(2, Reader_ID);
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
     * 读者个人信息修改
     * 修改密码
     * */
     boolean PasswordModify(String Reader_ID,String N_Password)  {
        Connection con=null;
        PreparedStatement stmt=null;
        ResultSet rs =null;
        try {
            con=JdbcUtils.getConnection();
            String sql="update reader set PASSWORD=? where READER_ID=?";
            stmt=con.prepareStatement(sql);
            stmt.setString(1, N_Password);
            stmt.setString(2, Reader_ID);
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

}


