package com.example.library.database.src.team.library.demo;

// import com.example.one.mapper.LibraryMapper;
import com.example.library.database.src.team.library.demo.LibraryMapper;
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

@Controller
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
    //获取当前用户，所借输的还书时间
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
                                 @RequestParam(name = "readerId") String readerId,
                                 @RequestParam(name ="systemFine") double systemFine,
                                 @RequestParam(name ="systemDate") int systemDate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime1 = sdf.parse(getBorrowTime(bookId,readerId));
        Date dateTime2 = sdf.parse(getReturnTime(bookId,readerId));
        long difference = dateTime1.getTime() - dateTime2.getTime();
        long days = difference / (1000 * 60 * 60 * 24);
        //    罚金金额
        if(days-systemDate>0){
            return  (days-systemDate)*systemFine;
        }
       return 0;
    }

    //    当读者还书时，根据当前系统的单日罚金、期限，设置罚金数
    public void SetPerBookFine(@RequestParam(name = "bookId") String bookId,
                               @RequestParam(name = "readerId") String readerId,
                               @RequestParam(name ="systemFine") double systemFine,
                               @RequestParam(name ="systemDate") int systemDate) throws ParseException {
        double count =CountPerBookFine(bookId, readerId, systemFine, systemDate);
        libraryMapper.updateReturnFine(count,readerId,bookId);

    }

    //计算当前用户，已还书籍的罚金总数
    public double SumReturnBookFine(@RequestParam(name ="readerId") String readerId,
                                    @RequestParam(name ="systemFine") double systemFine,
                                    @RequestParam(name ="systemDate") int systemDate) throws SQLException,
            ParseException {
        // 定义一个list用于接受数据库查询到的内容
        List<String> list = new ArrayList<String>();
        ResultSet ls =  libraryMapper.SelectListReturnBookId(readerId);
        while (ls.next()){
            list.add(ls.getString("BOOK_ID"));
        }
        double sumFine =0;
        for (String s : list) {
            sumFine +=CountPerBookFine(s, readerId, systemFine, systemDate);
        }
        return sumFine;
    }

    //计算当前未还书籍的罚金
    public double SumNotReturnBookFine(@RequestParam(name ="readerId") String readerId,
                                       @RequestParam(name ="systemFine") double systemFine,
                                       @RequestParam(name ="systemDate") int systemDate
                                       ) throws SQLException, ParseException {
    //        已还图书
        List<String> list1 = new ArrayList<String>();
        ResultSet ls1 =  libraryMapper.SelectListReturnBookId(readerId);
        while (ls1.next()){
            list1.add(ls1.getString("BOOK_ID"));
        }

    //        已借图书
        List<String> list2 = new ArrayList<String>();
        ResultSet ls2 =  libraryMapper.SelectListCheckedOutBookId(readerId);
        while (ls2.next()){
            list2.add(ls2.getString("BOOK_ID"));
        }
        //   （未还图书） 删除已借图书中的，已还图书
        for (String s1 : list1) {
            for (String s2 : list2) {
                if(s1.equals(s2)){
                    list2.remove(s2);
                }
            }
        }
        double sumFine =0;
        for (String s : list2) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String SystemTime = df.format(new Date());// new Date()为获取当前系统时间
            Date dateTime1 = df.parse(getBorrowTime(s,readerId));
            Date dateTime2 = df.parse(getReturnTime(s,readerId));
            long difference = dateTime1.getTime() - dateTime2.getTime();
            long days = difference / (1000 * 60 * 60 * 24);
            //    罚金金额
            if(days-systemDate>0){
                sumFine+= (days-systemDate)*systemFine;
            }
        }

        return sumFine;
    }

    //更新当前用户的罚金总金额
    public void UpdateReaderSumFine(@RequestParam(name ="readerId") String readerId,
                                    @RequestParam(name ="systemFine") double systemFine,
                                    @RequestParam(name ="systemDate") int systemDate
                                    ) throws SQLException, ParseException {
        double ReaderSumFine =SumNotReturnBookFine(readerId, systemFine,systemDate )+ SumReturnBookFine(readerId,systemFine,systemDate);
        libraryMapper.updateReaderFine(ReaderSumFine,readerId);
    }

}
