package sns.feed.register;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.mockito.Mockito;

import sns.account.domain.FacebookAccount;
import sns.account.domain.SnsAccount;
import sns.account.domain.SnsAccount.AccountType;
import sns.feed.FeedStorage;
import sns.feed.register.IFeedRegister.Result;

public class SnsFeedRegisterTest extends TestCase{

    SnsFeedRegister feedRegister;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        feedRegister = new SnsFeedRegister();
    }

    public void testRegisterFeed() throws Exception {

        List<SnsAccount> accounts;
        Map<SnsAccount, Result> registerFeed;

        {
            accounts = new ArrayList<SnsAccount>();
            registerFeed = feedRegister.registerFeed(accounts, "message");

            assertEquals(0, registerFeed.size());
        }

        {
            accounts = new ArrayList<SnsAccount>();
            accounts.add(new FacebookAccount());
            registerFeed = feedRegister.registerFeed(accounts, "message");

            assertEquals(1, registerFeed.size());
            assertEquals(IFeedRegister.Result.ERROR_NOT_AUTHORIZED, registerFeed.get(accounts.get(0)));
        }

        {

            int originSize = FeedStorage.getInstance().getAllFeeds().size();
            accounts = new ArrayList<SnsAccount>();
            FacebookAccount account = Mockito.mock(FacebookAccount.class);
            Mockito.when(account.getAPIKey()).thenReturn("facebook api key");
            Mockito.when(account.getAccountType()).thenReturn(AccountType.ACCOUNT_TYPE_FACEBOOK);
            accounts.add(account);
            registerFeed = feedRegister.registerFeed(accounts, "message");

            assertEquals(1, registerFeed.size());
            assertEquals(IFeedRegister.Result.SUCCESS, registerFeed.get(accounts.get(0)));

            assertEquals(originSize + 1, FeedStorage.getInstance().getAllFeeds().size());
        }

    }

}
