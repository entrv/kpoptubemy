package com.suncaption.kpoptubemy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {
/*
    GMT/UTC 표준 시간 변환
    글로벌 프로젝트 중 PG사로 부터 결제완료 날짜 포멧을 아래와 같은 생소한 포멧으로 리턴 받은적이 있는데 컨버트 과정을 거쳐 해결함.
31/3/2015 4:44:30 PM <-- 개떡같은 포멧으로 리턴을 해줘서 컨버트 과정을 한 번 거쳤다
    국내는 GMT +9
    말래이시아 GMT +8



            * 작성일 : 2013. 11.27
            * 내 용 : GMT/UTC 일시 변환
*/

/*
 * convertToDate
 * @param datetime
 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss") HH 대문자 : 24 Hour
 * 소문자 : 12 Hour
 */
    public static String convertToDate(String inputDate) throws Exception{
        String dateTime = inputDate;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.US);
        Date parseDate = null;
        String convertedDate = null;

        try {
            parseDate = format.parse(dateTime);
            DateFormat dateFormatNeeded = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            convertedDate = dateFormatNeeded.format(parseDate);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        return convertedDate;
    }

/*
  * UTC time to local time
  * @param datetime
  */
    public static String utcToLocaltime(String datetime) throws Exception {
        String locTime = null;
        //TimeZone tz = TimeZone.getTimeZone("GMT+08:00"); 해당 국가 일시 확인 할 때, 한쿸은 +9
        TimeZone tz = TimeZone.getDefault();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date parseDate = sdf.parse(datetime);
            long milliseconds = parseDate.getTime();
            int offset = tz.getOffset(milliseconds);
            locTime = sdf.format(milliseconds + offset);
            locTime = locTime.replace("+0000", "");
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        return locTime;
    }

    /**
     * local time to UTC time
     */
    public static String localtimeToUTC(String inputdatetime) throws Exception {
        String utcTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeZone tz = TimeZone.getDefault();

        try {
            Date parseDate = sdf.parse(inputdatetime);
            long milliseconds = parseDate.getTime();
            int offset = tz.getOffset(milliseconds);
            utcTime = sdf.format(milliseconds - offset);
            utcTime = utcTime.replace("+0000", "");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        return utcTime;
    }

}
