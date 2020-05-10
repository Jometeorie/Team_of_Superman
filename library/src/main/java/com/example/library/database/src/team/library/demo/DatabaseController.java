package com.example.library.database.src.team.library.demo;

// import com.example.one.mapper.LibraryMapper;
import com.example.library.database.src.team.library.demo.LibraryMapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseController {
    @Autowired(required = false)
    private LibraryMapper libraryMapper;
    //    设置系统的单日罚金
    public void FineSet(@RequestParam(name = "fine")double fine){
        libraryMapper.updateFine(fine);
    }
    //设置系统的规定还书期限
    public void DateSet(@RequestParam(name = "date") int date){
        libraryMapper.updateDate(date);
    }



    //获取当前用户，所借的书籍的借书时间
    public String getBorrowTime(@RequestParam(name ="readerId") String readerId,
                                @RequestParam(name ="bookId") String bookId){
        return libraryMapper.SelectEndTime(readerId,bookId);
    }
    //获取当前用户，所借书的还书时间
    public String getReturnTime(@RequestParam(name ="readerId") String readerId,
                                @RequestParam(name ="bookId") String bookId){
        return libraryMapper.SelectReturnTime(readerId,bookId);
    }

    //获取当前系统的单日罚金
    public double getSystemFine(@RequestParam(name ="adminId") String adminId){
        return libraryMapper.SelectFine(adminId);
    }
    //获取当前系统的还书期限
    public int getSystemDate(@RequestParam(name ="adminId") String adminId){
        return libraryMapper.SelectDate(adminId);
    }
	
	

    //根据当前所借书的（借书时间、还书时间）与（当前系统的单日罚金，期限）计算每一本书的罚金，
    public double CountPerBookFine(@RequestParam(name = "bookId") String bookId,
                                 @RequestParam(name = "readerId") String readerId) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime1 = sdf.parse(getBorrowTime(bookId,readerId));
        Date dateTime2 = sdf.parse(getReturnTime(bookId,readerId));
        long difference = dateTime1.getTime() - dateTime2.getTime();
        long days = difference / (1000 * 60 * 60 * 24);
        //    罚金金额
       // if(days-systemDate>0){
           // return (days-getSystemDate(adminID))*getSystemFine(adminID);
        //}
       return 0;
    }


    //    当读者还书时，根据当前系统的单日罚金、期限，设置罚金数
    public void SetPerBookFine(@RequestParam(name = "bookId") String bookId,
                               @RequestParam(name = "readerId") String readerId) throws ParseException {
        //double count =CountPerBookFine(bookId, readerId, getSystemFine(adminID), getSystemDate(adminID));
       // libraryMapper.updateReturnFine(count,readerId,bookId);

    }

    //更新当前用户的罚金总金额
    public void UpdateReaderSumFine(@RequestParam(name ="readerId") String readerId) throws SQLException, ParseException {
       // double ReaderSumFine =CountPerBookFine(bookId, readerId)+SelectReaderFine(readerId);
       // libraryMapper.updateReaderFine(ReaderSumFine,readerId);
    }

}
