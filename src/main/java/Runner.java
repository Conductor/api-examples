import jdbc.APIDataDumper;

/**
 * Created by anihalani on 6/4/15. Main Runner class
 */
public class Runner {
    public static final String CONDUCTOR_API_BASE_URL = "https://api.conductor.com";

    public static void main(String[] args) throws InterruptedException {

        APIDataDumper dataDumper = new APIDataDumper(CONDUCTOR_API_BASE_URL);
        try {
            /*
             * The thread sleeps for 1 sec after each endpoint call to make sure not more than one call is made to the
             * API in a single second
             */
            dataDumper.getLocationData();
            Thread.sleep(1000);
            dataDumper.getDeviceData();
            Thread.sleep(1000);
            dataDumper.getRankSourceData();
            Thread.sleep(1000);
            dataDumper.getWebPropertiesData();
        } finally {
            dataDumper.commit();
            dataDumper.closeConnection();
        }
    }
}
