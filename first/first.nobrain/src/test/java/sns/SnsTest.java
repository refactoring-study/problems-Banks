package sns;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mockito;

import sns.SnsTypeUtil.SnsType;
import sns.account.domain.FacebookAccount;
import sns.account.domain.ISnsAccount;
import sns.account.domain.TwitterAccount;
import sns.feed.collector.SnsFeedCollector;
import sns.feed.domain.feed.IFeed;
import sns.feed.register.IFeedRegister.Result;
import sns.feed.register.SnsFeedRegister;
import sns.feed.register.validator.RegisterValidator;

public class SnsTest extends TestCase {

    private SnsFeedCollector snsFeedCollector;
    private SnsFeedRegister snsFeedRegister;
    private RegisterValidator registerValidator;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        snsFeedCollector = new SnsFeedCollector();
        snsFeedRegister = new SnsFeedRegister();
        registerValidator = new RegisterValidator();
    }

    @Test
    public void testRegisterAndCollect() throws Exception {

        List<ISnsAccount> accounts = new ArrayList<ISnsAccount>();
        ISnsAccount facebookAccount = Mockito.mock(FacebookAccount.class);
        Mockito.when(facebookAccount.getAPIKey()).thenReturn("facebook api key");
        ISnsAccount twitterAccount = Mockito.mock(TwitterAccount.class);
        Mockito.when(twitterAccount.getAPIKey()).thenReturn("facebook api key");

        accounts.add(facebookAccount);
        accounts.add(twitterAccount);

        int beforeSize = snsFeedCollector.collectFeed(accounts).size();

        List<SnsType> checkAccountList = registerValidator.checkAccountList(accounts);
        assertNotNull(checkAccountList);

        List<Result> registerFeed = snsFeedRegister.registerFeed(accounts, "gogogo");
        List<SnsType> notifyRetry = registerValidator.notifyRetry(accounts, registerFeed);
        assertEquals(0, notifyRetry.size());

        List<IFeed> collectedFeed = snsFeedCollector.collectFeed(accounts);

        assertTrue(beforeSize < collectedFeed.size());
        assertEquals(beforeSize + 2, collectedFeed.size());

    }

}
