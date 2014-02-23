package sns.feed.collector;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mockito;

import sns.account.domain.FacebookAccount;
import sns.account.domain.GoplAccount;
import sns.account.domain.SnsAccount;
import sns.account.domain.SnsAccount.AccountType;
import sns.account.domain.TwitterAccount;
import sns.exception.NotAuthorException;

public class FeedCollectorFactoryTest extends TestCase {

    @Test
    public void testCreateFeedCollector() throws Exception {

        SnsAccount account;
        IFeedCollector feedCollector;

        {
            account = Mockito.mock(FacebookAccount.class);
            try {
                feedCollector = FeedCollectorFactory.createFeedCollector(account);
                fail("인증 에러가 나야 합니다.");
            } catch (NotAuthorException e) {

            }

            Mockito.when(account.getAPIKey()).thenReturn("facebook api");
            Mockito.when(account.getAccountType()).thenReturn(AccountType.ACCOUNT_TYPE_FACEBOOK);
            feedCollector = FeedCollectorFactory.createFeedCollector(account);

            assertNotNull(feedCollector);
            assertTrue(feedCollector instanceof FacebookFeedCollector);
        }
        {
            account = Mockito.mock(TwitterAccount.class);
            try {
                feedCollector = FeedCollectorFactory.createFeedCollector(account);
                fail("인증 에러가 나야 합니다.");
            } catch (NotAuthorException e) {

            }

            Mockito.when(account.getAPIKey()).thenReturn("twitter api");
            Mockito.when(account.getAccountType()).thenReturn(AccountType.ACCOUNT_TYPE_TWITTER);
            feedCollector = FeedCollectorFactory.createFeedCollector(account);

            assertNotNull(feedCollector);
            assertTrue(feedCollector instanceof TwitterFeedCollector);
        }
        {
            account = Mockito.mock(GoplAccount.class);
            try {
                feedCollector = FeedCollectorFactory.createFeedCollector(account);
                fail("인증 에러가 나야 합니다.");
            } catch (NotAuthorException e) {

            }

            Mockito.when(account.getAPIKey()).thenReturn("gopl api");
            Mockito.when(account.getAccountType()).thenReturn(AccountType.ACCOUNT_TYPE_GOPL);
            feedCollector = FeedCollectorFactory.createFeedCollector(account);

            assertNotNull(feedCollector);
            assertTrue(feedCollector instanceof GoplFeedCollector);
        }

    }

}
