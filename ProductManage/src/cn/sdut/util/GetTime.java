package cn.sdut.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTime {
	
	//获得系统当前时间
    public static String getCurrenttime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

}
