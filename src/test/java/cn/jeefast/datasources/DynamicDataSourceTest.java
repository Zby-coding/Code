package cn.jeefast.datasources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Test for the dynamic data source configuration
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicDataSourceTest {

    @Autowired
    private DynamicDataSource dynamicDataSource;

    @Test
    public void testDataSourceAvailability() {
        // Verify that the first data source is available
        assertTrue("First data source should be available", 
                   dynamicDataSource.getResolvedDataSources().containsKey(DataSourceNames.FIRST));
        
        // The second data source might not be available, but the system should still work
        // This test just verifies that the system doesn't crash when the second data source is not available
    }

    @Test
    public void testDataSourceSwitching() {
        // Set the data source to FIRST
        DynamicDataSource.setDataSource(DataSourceNames.FIRST);
        assertEquals("Data source should be set to FIRST", 
                     DataSourceNames.FIRST, DynamicDataSource.getDataSource());
        
        // Try to set the data source to SECOND
        // Even if SECOND is not available, this should not cause an error
        DynamicDataSource.setDataSource(DataSourceNames.SECOND);
        assertEquals("Data source should be set to SECOND", 
                     DataSourceNames.SECOND, DynamicDataSource.getDataSource());
        
        // Clear the data source
        DynamicDataSource.clearDataSource();
        assertNull("Data source should be cleared", DynamicDataSource.getDataSource());
    }
}