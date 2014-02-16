package sns.feed;

import junit.framework.TestCase;

import org.junit.Test;

public class FeedStorageTest extends TestCase {

    @Test
    public void testInit() {
        FeedStorage instance = FeedStorage.getInstance();

        assertNotNull(instance);
        assertTrue(instance.getFeeds().size() > 0);
    }

}
