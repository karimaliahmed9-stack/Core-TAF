package Core.TimeManager;

public class TimeManager {

    //Used for Screenshots - Logs - Reports
    public static String GetCurrentTime() {
        return new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new java.util.Date());
    }

    //Unique for each data
    public static long GetSimpleTimeForData() {
        return System.currentTimeMillis();
    }


}
