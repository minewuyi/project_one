package com.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wuyi
 * @date 2019/8/12 16:38
 */
public class DateUtil {
    public static Timestamp timestamp(){
        Date date = new Date();//获取当前日期

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化 格式必须是yyyy-mm-dd hh:mm:ss

        String currentdate = sdf.format(date);//将时间格式化

        return Timestamp.valueOf(currentdate);//将String类型的事件格式转换成Timestamp格式
    }

}
