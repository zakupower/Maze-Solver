import java.math.BigInteger;

/**
 * Created by Tomov on 13.4.2017 г..
 */
public class Timer {
    private static BigInteger startingTimeInMs;
    private static BigInteger endingTimeInMs;
    private static BigInteger elapsedTimeInMs;

    private Timer(){}

    public static void start(){
        startingTimeInMs = new BigInteger(""+System.currentTimeMillis());
    }
    public static void stop(){
        endingTimeInMs = new BigInteger(""+System.currentTimeMillis());
        elapsedTimeInMs = endingTimeInMs.subtract(startingTimeInMs);
    }
    public static String getTimeInMs()throws Exception{
        if(endingTimeInMs==null) throw new Exception("Timer is still running.");
        return elapsedTimeInMs.toString();
    }
    public static String getTimeInS()throws Exception{
        if(endingTimeInMs==null) throw new Exception("Timer is still running.");
        return elapsedTimeInMs.divide(new BigInteger(1000+"")).toString();
    }
    public static String getTimeInM()throws Exception{
        if(endingTimeInMs==null) throw new Exception("Timer is still running.");
        return (Double.parseDouble(elapsedTimeInMs.toString())/60000)+"";

    }
}
