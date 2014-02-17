package sns.feed.register.validator;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mockito;

import sns.SnsTypeUtil.SnsType;
import sns.account.domain.FacebookAccount;
import sns.account.domain.GoplAccount;
import sns.account.domain.ISnsAccount;
import sns.account.domain.TwitterAccount;
import sns.feed.register.IFeedRegister.Result;

public class RegisterValidatorTest extends TestCase {

    private RegisterValidator feedValidator;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        feedValidator = new RegisterValidator();
    }

    @Test
    public void testCheckAccounts() throws Exception {
        List<ISnsAccount> accounts;
        List<SnsType> notInvolvedList;

        {
            // 등록된 계정이 하나도 없음
            accounts = new ArrayList<ISnsAccount>();
            notInvolvedList = feedValidator.checkAccountList(accounts);

            assertEquals(SnsType.values().length - 1, notInvolvedList.size());
        }

        {
            accounts = new ArrayList<ISnsAccount>();
            accounts.add(new FacebookAccount());
            accounts.add(new TwitterAccount());
            notInvolvedList = feedValidator.checkAccountList(accounts);

            assertEquals(SnsType.values().length - 3, notInvolvedList.size());
        }

        {
            accounts = new ArrayList<ISnsAccount>();
            accounts.add(new FacebookAccount());
            accounts.add(new TwitterAccount());
            accounts.add(new GoplAccount());
            notInvolvedList = feedValidator.checkAccountList(accounts);

            assertEquals(0, notInvolvedList.size());
        }
    }

    @Test
    public void testNotifyRetry() throws Exception {

        List<ISnsAccount> accounts;
        List<SnsType> notInvolvedList;
        List<Result> results;

        {
            // 등록된 계정이 하나도 없음
            accounts = new ArrayList<ISnsAccount>();
            results = new ArrayList<Result>();
            notInvolvedList = feedValidator.notifyRetry(accounts, results);

            assertEquals(0, notInvolvedList.size());
        }

        {
            accounts = new ArrayList<ISnsAccount>();
            accounts.add(Mockito.mock(FacebookAccount.class));
            results = new ArrayList<Result>();
            results.add(Result.SUCCESS);
            notInvolvedList = feedValidator.notifyRetry(accounts, results);

            assertEquals(0, notInvolvedList.size());
        }

        {
            accounts = new ArrayList<ISnsAccount>();
            accounts.add(Mockito.mock(FacebookAccount.class));
            results = new ArrayList<Result>();
            results.add(Result.ERROR_NETWORK);
            notInvolvedList = feedValidator.notifyRetry(accounts, results);

            assertEquals(1, notInvolvedList.size());
            assertEquals(SnsType.ACCOUNT_TYPE_FACEBOOK, notInvolvedList.get(0));
        }

        {
            accounts = new ArrayList<ISnsAccount>();
            accounts.add(Mockito.mock(FacebookAccount.class));
            accounts.add(Mockito.mock(TwitterAccount.class));
            results = new ArrayList<Result>();
            results.add(Result.SUCCESS);
            results.add(Result.ERROR_NETWORK);
            notInvolvedList = feedValidator.notifyRetry(accounts, results);

            assertEquals(1, notInvolvedList.size());
            assertEquals(SnsType.ACCOUNT_TYPE_TWITTER, notInvolvedList.get(0));
        }


    }
}
