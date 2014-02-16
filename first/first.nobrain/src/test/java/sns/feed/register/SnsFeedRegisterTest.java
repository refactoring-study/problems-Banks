package sns.feed.register;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import sns.account.AccountTypeUtil.AccountType;
import sns.account.domain.FacebookAccount;
import sns.account.domain.GoplAccount;
import sns.account.domain.ISnsAccount;
import sns.account.domain.TwitterAccount;
import sns.feed.register.IFeedRegister.Result;

public class SnsFeedRegisterTest extends TestCase{

    SnsFeedRegister feedRegister;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        feedRegister = new SnsFeedRegister();
    }

    @Test
    public void testCheckAccounts() throws Exception {
        List<ISnsAccount> accounts;
        List<AccountType> notInvolvedList;

        {
            // 등록된 계정이 하나도 없음
            accounts = new ArrayList<ISnsAccount>();
            notInvolvedList = feedRegister.checkAccountList(accounts);

            assertEquals(AccountType.values().length - 1, notInvolvedList.size());
        }

        {
            accounts = new ArrayList<ISnsAccount>();
            accounts.add(new FacebookAccount());
            accounts.add(new TwitterAccount());
            notInvolvedList = feedRegister.checkAccountList(accounts);

            assertEquals(AccountType.values().length - 3, notInvolvedList.size());
        }

        {
            accounts = new ArrayList<ISnsAccount>();
            accounts.add(new FacebookAccount());
            accounts.add(new TwitterAccount());
            accounts.add(new GoplAccount());
            notInvolvedList = feedRegister.checkAccountList(accounts);

            assertEquals(0, notInvolvedList.size());
        }
    }

    public void testRegisterFeed() throws Exception {

        List<ISnsAccount> accounts;
        List<Result> registerFeed;

        {
            accounts = new ArrayList<ISnsAccount>();
            registerFeed = feedRegister.registerFeed(accounts, "message");

            assertEquals(0, registerFeed.size());
        }

        {
            accounts = new ArrayList<ISnsAccount>();
            accounts.add(new FacebookAccount());
            registerFeed = feedRegister.registerFeed(accounts, "message");

            assertEquals(1, registerFeed.size());
            assertEquals(IFeedRegister.Result.ERROR_NOT_AUTHORIZED, registerFeed.get(0));
        }


    }

}
