import jdbc.APIDataDumper;

/**
 * Created by anihalani on 6/4/15.
 */
public class Runner {
    public static final String CONDUCTOR_API_BASE_URL = "https://api.conductor.com";


    public static void main(String[] args) {

        APIDataDumper dataDumper = new APIDataDumper(CONDUCTOR_API_BASE_URL);
        dataDumper.getLocationData();
        dataDumper.getDeviceData();
        dataDumper.getRankSourceData();

    }


}
