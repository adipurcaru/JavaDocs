package ro.teamnet.zth.api.em;


import org.junit.Test;
import ro.teamnet.zth.api.database.DBManager;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Adrian.Purcaru on 13/07/2017.
 */
public class DBManagerTest {
    
    @Test
    public void testConnection() throws ClassNotFoundException {
        Connection conn = DBManager.getConnection();
        assertNotNull("should not be null", conn);
    }
}
