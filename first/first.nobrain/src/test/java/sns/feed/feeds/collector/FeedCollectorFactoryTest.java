package sns.feed.feeds.collector;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mockito;

import sns.account.domain.FacebookAccount;
import sns.account.domain.ISnsAccount;
import sns.exception.NotAuthorException;

public class FeedCollectorFactoryTest extends TestCase {

    @Test
    public void testCreateFeedCollector() throws Exception {

        ISnsAccount account;
        IFeedCollector feedCollector;

        {
            account = Mockito.mock(FacebookAccount.class);
            try {
                feedCollector = FeedCollectorFactory.createFeedCollector(account);
                fail("비정상 피드 수집기임");
            } catch (NotAuthorException e) {

            }

            Mockito.when(account.getAPIKey()).thenReturn("facebook api");
            feedCollector = FeedCollectorFactory.createFeedCollector(account);

            assertNotNull(feedCollector);
            assertTrue(feedCollector instanceof FacebookFeedCollector);
        }

    }

}
