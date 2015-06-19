import jdbc.APIDataDumper;

/**
 * Created by anihalani on 6/4/15.
 */
public class Runner {
    public static final String CONDUCTOR_API_BASE_URL = "https://api.conductor.com";

    public static void main(String[] args) {

        APIDataDumper dataDumper = new APIDataDumper(CONDUCTOR_API_BASE_URL);
        try {
//            Thread.sleep(1000);
//            dataDumper.getLocationData();
//            Thread.sleep(1000);
//            dataDumper.getDeviceData();
//            Thread.sleep(1000);
//            dataDumper.getRankSourceData();
//             Thread.sleep(1000);
//             dataDumper.getWebPropertiesData();
//             Thread.sleep(1000);
            //
            //
             Thread.sleep(1000);
             dataDumper.getWebPropertyRankReport();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            dataDumper.getDao().closeConnection();
        }

    }

}
