package sns.feed.feeds.collector;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mockito;

import sns.account.domain.FacebookAccount;
import sns.account.domain.GoplAccount;
import sns.account.domain.ISnsAccount;
import sns.account.domain.TwitterAccount;
import sns.exception.NotAuthorException;
import sns.feed.collector.FacebookFeedCollector;
import sns.feed.collector.FeedCollectorFactory;
import sns.feed.collector.IFeedCollector;

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
        {
            account = Mockito.mock(TwitterAccount.class);
            try {
                feedCollector = FeedCollectorFactory.createFeedCollector(account);
                fail("비정상 피드 수집기임");
            } catch (NotAuthorException e) {

            }

            Mockito.when(account.getAPIKey()).thenReturn("twitter api");
            feedCollector = FeedCollectorFactory.createFeedCollector(account);

            assertNotNull(feedCollector);
            assertTrue(feedCollector instanceof FacebookFeedCollector);
        }
        {
            account = Mockito.mock(GoplAccount.class);
            try {
                feedCollector = FeedCollectorFactory.createFeedCollector(account);
                fail("비정상 피드 수집기임");
            } catch (NotAuthorException e) {

            }

            Mockito.when(account.getAPIKey()).thenReturn("gopl api");
            feedCollector = FeedCollectorFactory.createFeedCollector(account);

            assertNotNull(feedCollector);
            assertTrue(feedCollector instanceof FacebookFeedCollector);
        }

    }

}
