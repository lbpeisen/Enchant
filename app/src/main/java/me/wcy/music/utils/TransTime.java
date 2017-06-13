package me.wcy.music.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by han78 on 2017/6/8.
 */

public class TransTime {
    public static String trans(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;
    }
}
