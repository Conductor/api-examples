package jdbc;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

/**
 * Created by anihalani on 6/5/15.
 */
public class JDBCConnectionTest {

    @Test
    public void testGetConnection() throws Exception {
        Connection conn = JDBCConnection.getConnection();

        // Check if jdbc connection is null
        assertNotNull(conn);
    }
}