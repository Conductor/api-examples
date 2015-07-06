package jdbc;

import beans.*;
import com.mockrunner.mock.jdbc.MockResultSet;
import dao.DAO;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import streaming.APIPathBuilder;
import streaming.StreamBuilder;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by anihalani on 6/10/15. Tests for APIDataDumper
 */
public class APIDataDumperTest {

    @Mock
    DAO dao;
    @Mock
    StreamBuilder streamBuilder;
    @Mock
    InputStream ipStream;
    @Mock
    APIPathBuilder pathBuilderMock;
    @Mock
    ResultSet rs;
    @Mock
    ResultSet rs1;

    APIDataDumper dataDumper;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        dataDumper = new APIDataDumper("https://api.conductor.com");
        dataDumper.setDao(dao);
        dataDumper.setStreamBuilder(streamBuilder);

    }

    /**
     * Verify interaction with Path Builder
     */
    @Test
    public void verifyInteractionWithPathBuilder() {
        dataDumper.setPathBuilder(pathBuilderMock);
        pathBuilderMock.buildWithEndpoint("locations", null);
        verify(pathBuilderMock).buildWithEndpoint("locations", null);
    }

    /**
     * Verify interactions with getLocation method
     *
     * @throws Exception
     *             - SQL Exception while writing to database
     */
    @Test
    public void verifyInteractionsWithGetLocationData() throws Exception {
        // create method stub
        when(streamBuilder.buildInStream(anyString())).thenReturn(
                IOUtils.toInputStream("[{\n" + "    \"locationId\": \"1\",\n"
                        + "    \"description\": \"United States\"\n" + "}, {\n" + "    \"locationId\": \"2\",\n"
                        + "    \"description\": \"Canada\"\n" + "}]"));
        dataDumper.getLocationData();
        ArgumentCaptor<Location> arg = ArgumentCaptor.forClass(Location.class);
        verify(dao, times(2)).writeToDatabase(arg.capture());
        Assert.assertEquals(arg.getAllValues().get(0).getLocationId(), 1);
        Assert.assertEquals(arg.getAllValues().get(1).getLocationId(), 2);
    }

    /**
     * Verify interactions with getDevice method
     *
     * @throws Exception
     *             - SQL Exception while writing to database
     */
    @Test
    public void verifyInteractionsWithGetDeviceData() throws Exception {
        // create method stub
        when(streamBuilder.buildInStream(anyString())).thenReturn(
                IOUtils.toInputStream("[{\n" + "    \"description\": \"Desktop\",\n" + "    \"deviceId\": \"1\"\n"
                        + "}, {\n" + "    \"description\": \"Smartphone\",\n" + "    \"deviceId\": \"2\"\n" + "}, {\n"
                        + "    \"description\": \"Tablet\",\n" + "    \"deviceId\": \"3\"\n" + "}]"));
        dataDumper.getDeviceData();

        ArgumentCaptor<Device> arg = ArgumentCaptor.forClass(Device.class);
        verify(dao, times(3)).writeToDatabase(arg.capture());
        Assert.assertEquals(arg.getAllValues().get(0).getDeviceId(), 1);
        Assert.assertEquals(arg.getAllValues().get(1).getDeviceId(), 2);
        Assert.assertEquals(arg.getAllValues().get(2).getDescription(), "Tablet");

    }

    /**
     * Verify interactions with getRankSource method
     *
     * @throws Exception
     *             - SQL Exception while writing to database
     */
    @Test
    public void verifyInteractionsWithGetRankSourceData() throws Exception {
        // create method stub
        when(streamBuilder.buildInStream(anyString())).thenReturn(
                IOUtils.toInputStream("[{\n" + "    \"baseDomain\": \"google.com\",\n"
                        + "    \"description\": \"Google (US / English)\",\n" + "    \"rankSourceId\": \"1\",\n"
                        + "    \"name\": \"GOOGLE_EN_US\"\n" + "}, {\n" + "    \"baseDomain\": \"google.ca\",\n"
                        + "    \"description\": \"Google (Canada / English)\",\n" + "    \"rankSourceId\": \"4\",\n"
                        + "    \"name\": \"GOOGLE_EN_CA\"\n" + "}, {\n" + "    \"baseDomain\": \"google.co.uk\",\n"
                        + "    \"description\": \"Google (United Kingdom / English)\",\n"
                        + "    \"rankSourceId\": \"5\",\n" + "    \"name\": \"GOOGLE_EN_UK\"\n" + "}, {\n"
                        + "    \"baseDomain\": \"google.com.au\",\n"
                        + "    \"description\": \"Google (Australia / English)\",\n" + "    \"rankSourceId\": \"6\",\n"
                        + "    \"name\": \"GOOGLE_EN_AU\"\n" + "}]"));
        dataDumper.getRankSourceData();

        ArgumentCaptor<RankSource> arg = ArgumentCaptor.forClass(RankSource.class);
        verify(dao, times(4)).writeToDatabase(arg.capture());
        Assert.assertEquals(arg.getAllValues().size(), 4);
        Assert.assertEquals(arg.getAllValues().get(0).getRankSourceId(), 1);
        Assert.assertEquals(arg.getAllValues().get(3).getBaseDomain(), "google.com.au");

    }

    /**
     * Verify interactions with getWebProperty method
     *
     * @throws Exception
     *             - SQL Exception while writing to database
     */
    @Test
    public void verifyInteractionsWithGetWebProperty() throws Exception {
        // Method stub to return a web Web Property with 3 Comparison Web Properties
        when(streamBuilder.buildInStream(contains("web-properties?apiKey")))
                .thenReturn(
                        IOUtils.toInputStream("[{\n"
                                + "    \"isActive\": true,\n"
                                + "    \"rankSourceInfo\": [{\n"
                                + "        \"reports\": {\n"
                                + "            \"CURRENT\": {\n"
                                + "                \"startDate\": \"2015-06-21T00:00:00.000Z\",\n"
                                + "                \"endDate\": \"2015-06-27T23:59:59.000Z\",\n"
                                + "                \"webPropertySearchVolumeReport\": \"https://api.conductor.com/v3/4/web-properties/215/rank-sources/1/tp/CURRENT/search-volumes\",\n"
                                + "                \"webPropertyRankReport\": \"https://api.conductor.com/v3/4/web-properties/215/rank-sources/1/tp/CURRENT/serp-items\",\n"
                                + "                \"timePeriodId\": \"309\"\n"
                                + "            }\n"
                                + "        },\n"
                                + "        \"comparisonWebProperties\": [{\n"
                                + "            \"label\": \"Competitor\",\n"
                                + "            \"name\": \"3ds.com\",\n"
                                + "            \"webPropertyId\": \"219\"\n"
                                + "        }, {\n"
                                + "            \"label\": \"\",\n"
                                + "            \"name\": \"camstar-tv.com\",\n"
                                + "            \"webPropertyId\": \"47172\"\n"
                                + "        }, {\n"
                                + "            \"label\": null,\n"
                                + "            \"name\": \"ptc.com\",\n"
                                + "            \"webPropertyId\": \"218\"\n"
                                + "        }],\n"
                                + "        \"rankSourceId\": \"1\"\n"
                                + "    }],\n"
                                + "    \"webPropertyId\": \"215\",\n"
                                + "    \"trackedSearchList\": \"https://api.conductor.com/v3/accounts/4/web-properties/215/tracked-searches\",\n"
                                + "    \"name\": \"plm.automation.siemens.com\"\n" + "}]"));

        // Method stub to return 2 tracked searches when buildInStream is called
        when(streamBuilder.buildInStream(contains("tracked-searches"))).thenReturn(
                IOUtils.toInputStream("[{\n" + "    \"isActive\": true,\n" + "    \"trackedSearchId\": \"4576447\",\n"
                        + "    \"preferredUrl\": null,\n" + "    \"queryPhrase\": \"ADIRONDACK CHAIRS\",\n"
                        + "    \"locationId\": \"1\",\n" + "    \"rankSourceId\": \"1\",\n"
                        + "    \"deviceId\": \"1\"\n" + "}, {\n" + "    \"isActive\": true,\n"
                        + "    \"trackedSearchId\": \"4576448\",\n" + "    \"preferredUrl\": null,\n"
                        + "    \"queryPhrase\": \"ADIRONDACK CHAIR\",\n" + "    \"locationId\": \"1\",\n"
                        + "    \"rankSourceId\": \"1\",\n" + "    \"deviceId\": \"1\"\n" + "}]"));

        // create method stub to return 2 rank report items
        when(streamBuilder.buildInStream(contains("/tp/CURRENT/serp-items")))
                .thenReturn(
                        IOUtils.toInputStream("[{\n"
                                + "    \"ranks\": {\n"
                                + "        \"UNIVERSAL_RANK\": null,\n"
                                + "        \"TRUE_RANK\": 7,\n"
                                + "        \"CLASSIC_RANK\": null\n"
                                + "    },\n"
                                + "    \"webPropertyId\": null,\n"
                                + "    \"trackedSearchId\": 4576447,\n"
                                + "    \"itemType\": \"IMAGE_RESULT\",\n"
                                + "    \"target\": \"\",\n"
                                + "    \"targetDomainName\": \"www.cedaradirondackchairs.net\",\n"
                                + "    \"targetUrl\": \"http://www.cedaradirondackchairs.net/images/Premium%20adirondack%20chair%20redwood%20finish.jpg\"\n"
                                + "}, {\n"
                                + "    \"ranks\": {\n"
                                + "        \"UNIVERSAL_RANK\": null,\n"
                                + "        \"TRUE_RANK\": 7,\n"
                                + "        \"CLASSIC_RANK\": null\n"
                                + "    },\n"
                                + "    \"webPropertyId\": null,\n"
                                + "    \"trackedSearchId\": 4576447,\n"
                                + "    \"itemType\": \"IMAGE_RESULT\",\n"
                                + "    \"target\": \"\",\n"
                                + "    \"targetDomainName\": \"www.cedaradirondackchairs.net\",\n"
                                + "    \"targetUrl\": \"http://www.cedaradirondackchairs.net/images/premium%20adirondack%20chair.jpg\"\n"
                                + "}]"));
        // create method stub to return 1 search volume report item with 2 Volume items
        when(streamBuilder.buildInStream(contains("tp/CURRENT/search-volumes"))).thenReturn(
                IOUtils.toInputStream("[{\n" + "    \"averageVolume\": 49500,\n"
                        + "    \"trackedSearchId\": 4576447,\n" + "    \"volumeItems\": [{\n"
                        + "        \"volume\": null,\n" + "        \"month\": 5,\n" + "        \"year\": 2015\n"
                        + "    }, {\n" + "        \"volume\": null,\n" + "        \"month\": 4,\n"
                        + "        \"year\": 2015\n" + "    }]\n" + "}]"));

        dataDumper.getWebPropertiesData();
        // verify if correct web property data is returned
        ArgumentCaptor<WebProperty> arg = ArgumentCaptor.forClass(WebProperty.class);
        // A total of 11 objects are written to the database
        // 1 WebProperty, 3 Comparison WebProperties, 2 TrackSearches, 2 Rank Reports, 1 SearchVolume and 2 Volumes
        verify(dao, times(11)).writeToDatabase(arg.capture());
        Assert.assertEquals(arg.getAllValues().get(0).getWebPropertyId(), 215);

        // Verify if correct Comparison Web Property data is returned
        ArgumentCaptor<ComparisonWebProperty> comparisonWebPropertyArgumentCaptor = ArgumentCaptor.forClass(ComparisonWebProperty.class);
        verify(dao, times(11)).writeToDatabase(comparisonWebPropertyArgumentCaptor.capture());
        Assert.assertEquals(comparisonWebPropertyArgumentCaptor.getAllValues().get(1).getWebPropertyId(), 219);

        // Verify if correct tracked search data is returned
        ArgumentCaptor<TrackedSearch> trackedSearchArgumentCaptor = ArgumentCaptor.forClass(TrackedSearch.class);
        verify(dao, times(11)).writeToDatabase(trackedSearchArgumentCaptor.capture());
        Assert.assertEquals(trackedSearchArgumentCaptor.getAllValues().get(4).getTrackedSearchId(), 4576447);

        // Verify if correct Search Volume data is returned
        ArgumentCaptor<ClientWebPropertySearchVolumeReport> clientWebPropertySearchVolumeReportArgumentCaptor = ArgumentCaptor.forClass(ClientWebPropertySearchVolumeReport.class);
        verify(dao, times(11)).writeToDatabase(clientWebPropertySearchVolumeReportArgumentCaptor.capture());
        Assert.assertEquals(clientWebPropertySearchVolumeReportArgumentCaptor.getAllValues().get(8).getVolumeItems().get(0).getYear(), 2015);
        Assert.assertEquals(clientWebPropertySearchVolumeReportArgumentCaptor.getAllValues().get(8).getVolumeItems().get(1).getMonth(), 4);
        Assert.assertEquals(clientWebPropertySearchVolumeReportArgumentCaptor.getAllValues().get(8).getAverageVolume(), 49500);

        // Verify if correct Rank Report data is returned
        ArgumentCaptor<ClientWebPropertyRankReport> clientWebPropertyRankReportArgumentCaptor = ArgumentCaptor.forClass(ClientWebPropertyRankReport.class);
        verify(dao, times(11)).writeToDatabase(clientWebPropertyRankReportArgumentCaptor.capture());
        Assert.assertEquals(clientWebPropertyRankReportArgumentCaptor.getAllValues().get(9).getTargetDomainName(), "www.cedaradirondackchairs.net");
        Assert.assertEquals(clientWebPropertyRankReportArgumentCaptor.getAllValues().get(10).getTargetUrl(), "http://www.cedaradirondackchairs.net/images/premium%20adirondack%20chair.jpg");
    }

    /**
     * Returns the given data in the form of ResultSet
     * 
     * @return - ResultSet generated
     * @throws Exception
     *             - Java.Lang exception while getting resultset from mockResults
     */
    private ResultSet getMockResultSet() throws Exception {
        MyResultSet myResultSet = new MyResultSet();
        List<String> headers = new ArrayList<>();
        headers.add("account_id");
        headers.add("web_property_id");
        headers.add("rank_source_id");

        List<List<Object>> data = new ArrayList<>();

        List<Object> objects = new ArrayList<>();
        objects.add(3);
        objects.add(7);
        objects.add(1);
        data.add(objects);

        return myResultSet.getResultSet(headers, data);
    }

    // A sub class to help generate ResultSet from the data
    public class MyResultSet {

        public ResultSet getResultSet(List<String> headers, List<List<Object>> data) throws Exception {

            // validation
            if (headers == null || data == null) {
                throw new Exception("null parameters");
            }

            // create a mock result set
            MockResultSet mockResultSet = new MockResultSet("myResultSet");

            // add header
            for (String string : headers) {
                mockResultSet.addColumn(string);
            }

            // add data
            for (List<Object> list : data) {
                mockResultSet.addRow(list);
            }
            return mockResultSet;
        }
    }

}