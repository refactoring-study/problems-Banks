package sns;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mockito;

import sns.account.domain.FacebookAccount;
import sns.account.domain.SnsAccount;
import sns.account.domain.SnsAccount.AccountType;
import sns.account.domain.TwitterAccount;
import sns.feed.collector.SnsFeedCollector;
import sns.feed.domain.feed.IFeed;
import sns.feed.register.IFeedRegister.Result;
import sns.feed.register.SnsFeedRegister;
import sns.feed.register.SnsFeedRegister.OnAccountExcludeCheck;
import sns.feed.register.SnsFeedRegister.OnFeedResult;

public class SnsTest extends TestCase {

    private SnsFeedCollector snsFeedCollector;
    private SnsFeedRegister snsFeedRegister;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        snsFeedCollector = new SnsFeedCollector();
        snsFeedRegister = new SnsFeedRegister();
    }

    @Test
    public void testRegisterAndCollect() throws Exception {

        List<SnsAccount> accounts = new ArrayList<SnsAccount>();
        SnsAccount facebookAccount = Mockito.mock(FacebookAccount.class);
        Mockito.when(facebookAccount.getAPIKey()).thenReturn("facebook api key");
        Mockito.when(facebookAccount.getAccountType()).thenReturn(AccountType.ACCOUNT_TYPE_FACEBOOK);

        SnsAccount twitterAccount = Mockito.mock(TwitterAccount.class);
        Mockito.when(twitterAccount.getAPIKey()).thenReturn("facebook api key");
        Mockito.when(twitterAccount.getAccountType()).thenReturn(AccountType.ACCOUNT_TYPE_TWITTER);

        accounts.add(facebookAccount);
        accounts.add(twitterAccount);

        int beforeSize = snsFeedCollector.collectFeed(accounts).size();

        snsFeedRegister.setAccountCheck(new MockAccountCheck());
        snsFeedRegister.setFeedResult(new MockFeedResult());
        Map<SnsAccount, Result> registerFeed = snsFeedRegister.registerFeed(accounts, "gogogo");
        assertNotNull(registerFeed);
        assertEquals(2, registerFeed.size());

        List<IFeed> collectedFeed = snsFeedCollector.collectFeed(accounts);

        assertTrue(beforeSize < collectedFeed.size());
        assertEquals(beforeSize + 2, collectedFeed.size());

    }

    private class MockAccountCheck implements OnAccountExcludeCheck {

        public void onExcludeAccount(List<AccountType> accounts) {
            if (accounts == null || accounts.size() <= 0) {
                fail("제외된 계정이 있도록 테스트 케이스가 진행되어야 합니다.");
            }
        }

    }

    private class MockFeedResult implements OnFeedResult {

        public void onFeedFailResult(List<AccountType> failedAccount) {
            if (failedAccount == null || failedAccount.size() <= 0) {
                fail("아직 테스트 예정 계획이 없습니다.");
            }
        }

    }

}
