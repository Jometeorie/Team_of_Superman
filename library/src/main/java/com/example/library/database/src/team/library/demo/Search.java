package com.example.library.database.src.team.library.demo;

import com.example.library.database.src.team.library.util.JdbcUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Search {
    private  JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
/*    @Test
    public void test1(){
        List<ReaderInfo> list = SearchReaderByName("Amy");
        for (ReaderInfo info : list) {
            System.out.println(info.reader_id);
            System.out.println(info.reader_name);
            System.out.println(info.password);
        }

    }*/

    /**
     * 通过读者id查询读者
     * @param id
     * @return
     */
    public List<ReaderInfo> SearchReaderById(String id){
        String sql = "select * from reader where READER_ID = ?";
        List<ReaderInfo> list = template.query(sql,new BeanPropertyRowMapper<ReaderInfo>(ReaderInfo.class),id);
        return list;
    }

    /**
     * 通过读者name查询读者
     * @param name
     * @return
     */
    public List<ReaderInfo> SearchReaderByName(String name){
        String sql = "select * from reader where READER_NAME like ?";
        List<ReaderInfo> list = template.query(sql,new BeanPropertyRowMapper<ReaderInfo>(ReaderInfo.class),"%" + name + "%");
        return list;
    }

    /**
     * 通过管理员id查询管理员
     * @param id
     * @return
     */
    public List<LibrarianInfo> SearchLibrById(String id){
        String sql = "select * from librarian where LIBR_ID = ?";
        List<LibrarianInfo> list = template.query(sql,new BeanPropertyRowMapper<LibrarianInfo>(LibrarianInfo.class),id);
        return list;
    }

    /**
     * 通过管理员name查询管理员
     * @param name
     * @return
     */
    public List<LibrarianInfo> SearchLibrByName(String name){
        String sql = "select * from librarian where LIBR_NAME like ?";
        List<LibrarianInfo> list = template.query(sql,new BeanPropertyRowMapper<LibrarianInfo>(LibrarianInfo.class),"%" + name + "%");
        return list;
    }
}
