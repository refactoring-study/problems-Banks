package sns.feed.feeds.collector;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mockito;

import sns.account.domain.FacebookAccount;
import sns.account.domain.ISnsAccount;

public class FeedCollectorFactoryTest extends TestCase {

    @Test
    public void testCreateFeedCollector() throws Exception {

        ISnsAccount account;

        {
            account = Mockito.mock(FacebookAccount.class);
            Mockito.doReturn("facebook api key").when(account.getAPIKey());

            FeedCollectorFactory.createFeedCollector(account);
        }

    }

}
