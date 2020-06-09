package com.example.library.database.src.team.library.demo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.ResultSet;

@Mapper
public interface LibraryMapper {

//    超管输入的单日罚金数，设置系统的当日罚金（更新所有超管的单日罚金数据）
    @Update("UPDATE admin SET FINE_SET='#{fineSet}'")
    void updateFine(@Param("fine") double fine);

    //根据超管输入的期限时间，设置系统的期限时间，（更新所有的超管的期限时长）
    @Update("UPDATE admin SET DATE_SET='#{dateSet}'")
    void updateDate(@Param("date") int date);
	
	

    //    根据读者编号、图书编号，得到当前书籍的借书时间
    @Select("SELECT END_TIME FROM checked_out WHERE READER_ID=#{readerId} AND BOOK_ID=#{bookId}")
    String SelectEndTime(@Param("READER_ID") String readerId,@Param("BOOK_ID") String bookId);

    //    根据还书表中的的读者编号、图书编号，获取当前图书的还书时间
    @Select("SELECT RETURN_TIME FROM return WHERE READER_ID=#{readerId} AND BOOK_ID=#{bookId}")
    String SelectReturnTime(@Param("READER_ID") String readerId,@Param("BOOK_ID") String bookId);
	
	
	
	
	//    根据当前读者编号，获取当前读者的罚金总数
	@Select("SELECT DISTINCT READER_FINE FROM reader WHERE READER_ID=#{readerId}")
    String SelectReaderFine(@Param("READER_ID") String readerId);		




    //    根据当前超管的编号，获取当前系统的单日罚金数
    @Select("SELECT DISTINCT FINE_SET FROM admin WHERE ADMIN_ID=#{adminId}")
    double SelectFine(@Param("adminId") String adminId);

    //    根据当前超管的编号，获取当前系统的设置的图书期限
    @Select("SELECT DISTINCT DATE_SET FROM admin WHERE ADMIN_ID=#{adminId}")
    int SelectDate(@Param("adminId") String adminId);
	
	
	

    //根据当前用户的读者编号、图书编号，设置当前书籍的罚金数目
    @Update("UPDATE return SET FINE='#{Fine}'WHERE  READER_ID=#{readerId} AND BOOK_ID='#{bookId}'")
    void updateReturnFine(@Param("FINE") double fine,@Param("READER_ID") String readerId,@Param("BOOK_ID") String bookId);
	
	//    根据读者编号，更新当前的总的罚金数
    @Update("UPDATE reader SET READER_FINE='#{readerFine}' WHERE  READER_ID=#{readerId}")
    void updateReaderFine(@Param("READER_FINE") double readerFine,@Param("READER_ID") String readerId);
}

