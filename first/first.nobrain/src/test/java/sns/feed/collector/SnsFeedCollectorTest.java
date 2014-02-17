package sns.feed.collector;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.mockito.Mockito;

import sns.account.domain.FacebookAccount;
import sns.account.domain.GoplAccount;
import sns.account.domain.ISnsAccount;
import sns.account.domain.TwitterAccount;
import sns.feed.feed.domain.IFeed;

public class SnsFeedCollectorTest extends TestCase {

    public void testGetFeeds() throws Exception {

        SnsFeedCollector collector = new SnsFeedCollector();


        List<ISnsAccount> accounts;
        List<IFeed> collectFeed;

        {
            accounts = new ArrayList<ISnsAccount>();
            ISnsAccount facebookAccount = Mockito.mock(FacebookAccount.class);
            ISnsAccount goplAccount = Mockito.mock(GoplAccount.class);
            ISnsAccount twitterAccount = Mockito.mock(TwitterAccount.class);
            accounts.add(facebookAccount);
            accounts.add(goplAccount);
            accounts.add(twitterAccount);
            collectFeed = collector.collectFeed(accounts);

            assertEquals(collectFeed.size(), 0);
        }

        {
            accounts = new ArrayList<ISnsAccount>();
            ISnsAccount facebookAccount = Mockito.mock(FacebookAccount.class);
            Mockito.when(facebookAccount.getAPIKey()).thenReturn("facebook Api Key");
            ISnsAccount goplAccount = Mockito.mock(GoplAccount.class);
            Mockito.when(goplAccount.getAPIKey()).thenReturn("facebook Api Key");
            ISnsAccount twitterAccount = Mockito.mock(TwitterAccount.class);
            Mockito.when(twitterAccount.getAPIKey()).thenReturn("facebook Api Key");
            accounts.add(facebookAccount);
            accounts.add(goplAccount);
            accounts.add(twitterAccount);
            collectFeed = collector.collectFeed(accounts);

            assertTrue(collectFeed.size() > 0);
        }


    }

}
