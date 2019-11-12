package webassist.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeFormat {

    public static String getFormattedDateTime() {
        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String today = formatter.format(date)
                .replace("-","")
                .replace(" ","")
                .replace(":","");
        return today;
    }


}
