package sns.feed.collector;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.mockito.Mockito;

import sns.account.domain.FacebookAccount;
import sns.account.domain.GoplAccount;
import sns.account.domain.SnsAccount;
import sns.account.domain.SnsAccount.AccountType;
import sns.account.domain.TwitterAccount;
import sns.feed.domain.feed.IFeed;

public class SnsFeedCollectorTest extends TestCase {

    public void testGetFeeds() throws Exception {

        SnsFeedCollector collector = new SnsFeedCollector();


        List<SnsAccount> accounts;
        List<IFeed> collectFeed;

        {
            accounts = new ArrayList<SnsAccount>();
            SnsAccount facebookAccount = Mockito.mock(FacebookAccount.class);
            SnsAccount goplAccount = Mockito.mock(GoplAccount.class);
            SnsAccount twitterAccount = Mockito.mock(TwitterAccount.class);
            accounts.add(facebookAccount);
            accounts.add(goplAccount);
            accounts.add(twitterAccount);
            collectFeed = collector.collectFeed(accounts);

            assertEquals(collectFeed.size(), 0);
        }

        {
            accounts = new ArrayList<SnsAccount>();

            SnsAccount facebookAccount = Mockito.mock(FacebookAccount.class);
            Mockito.when(facebookAccount.getAPIKey()).thenReturn("facebook Api Key");
            Mockito.when(facebookAccount.getAccountType()).thenReturn(AccountType.ACCOUNT_TYPE_FACEBOOK);

            SnsAccount goplAccount = Mockito.mock(GoplAccount.class);
            Mockito.when(goplAccount.getAPIKey()).thenReturn("facebook Api Key");
            Mockito.when(goplAccount.getAccountType()).thenReturn(AccountType.ACCOUNT_TYPE_GOPL);

            SnsAccount twitterAccount = Mockito.mock(TwitterAccount.class);
            Mockito.when(twitterAccount.getAPIKey()).thenReturn("facebook Api Key");
            Mockito.when(twitterAccount.getAccountType()).thenReturn(AccountType.ACCOUNT_TYPE_TWITTER);

            accounts.add(facebookAccount);
            accounts.add(goplAccount);
            accounts.add(twitterAccount);
            collectFeed = collector.collectFeed(accounts);

            assertTrue(collectFeed.size() > 0);
        }


    }

}
