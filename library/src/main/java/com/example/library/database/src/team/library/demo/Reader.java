package com.example.library.database.src.team.library.demo;
import com.example.library.database.src.team.library.util.JdbcUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
// import java.util.Scanner;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class Reader {
    /**
     * 登录测试
     * */
    @Test
    public void test1(){
        System.out.println(new Reader().ReaderLogin("13512345678","12345678"));//True
        System.out.println(new Reader().ReaderLogin("1351234567","12345678"));//False
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
     * 读者邮箱验证
     * @return 返回boolean值，false为失败，true为成功
     * */
    public boolean ReaderEmail(String reader_id,String e_mail){
        if(reader_id==null||e_mail==null){
            return false;
        }
        Boolean flag=false;
        Connection conn=null;
        ResultSet rs=null;
        PreparedStatement pstmt =null;
        try {
            conn= JdbcUtils.getConnection();
            String sql="select * from reader where READER_ID=? and E_MAIL=? ";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,reader_id);
            pstmt.setString(2,e_mail);
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
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="update reader set STATE=? where READER_ID=?";
        int count=template.update(sql,flag,reader_id);
        if(count==1)
            return true;
        return false;
    }
    /**
     * 登出测试
     * */
    @Test
    public void test2(){
        System.out.println(new Reader().ReaderLogout("13512345678"));//True
    }
    /**
     * 读者登出
     * */
    public  boolean ReaderLogout(String reader_id){
        return ChangeState(reader_id,false);
    }
    /**
     * 读者个人信息修改测试
     * */
    @Test
    public void test3(){
        System.out.println(new Reader().NameModify("13512345678","Amy"));//true
        System.out.println(new Reader().E_mailModify("13512345678","1234567777@463.com"));//true
        System.out.println(new Reader().PasswordModify("13512345678","123252"));//true
    }
    /**
     * 读者个人信息修改
     * 修改姓名
    * */
    public boolean NameModify(String Reader_ID,String N_name)  {
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="update reader set READER_NAME=? where READER_ID=?";
        int count=template.update(sql,N_name,Reader_ID);
        if(count==1)
            return true;
        return false;
    }
    /**
     * 读者个人信息修改
     * 修改邮箱
     * */
    public boolean E_mailModify(String Reader_ID,String N_Email)  {
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        String sql="update reader set E_MAIL=? where READER_ID=?";
        int count=template.update(sql,N_Email,Reader_ID);
        if(count==1)
            return true;
        return false;
    }
    /**
     * 读者个人信息修改
     * 修改密码
     * */
    public boolean PasswordModify(String Reader_ID,String N_Password)  {
         JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
         String sql="update reader set PASSWORD=? where READER_ID=?";
         int count=template.update(sql,N_Password,Reader_ID);
         if(count==1)
             return true;
         return false;
    }

    public List<ReaderInfo> getallreader()
    {
        JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select * from reader";
        List<ReaderInfo> list2=template.query(sql,new BeanPropertyRowMapper<ReaderInfo>(ReaderInfo.class));
        return list2;
    }

    //提供读者当前的罚金
    public BigDecimal getfine(String readerid)
    {
        JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select READER_FINE from reader where READER_ID=?";
        BigDecimal fine= template.queryForObject(sql,BigDecimal.class,readerid);
        return fine;
    }

}
