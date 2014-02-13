package refactoring.first.account;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public interface Account {
    public String getId();

    public String getPw();

    public boolean isAutoLogin();

    public AccountState getStatus();

    class AccountTest {

        private Account account;

        public static String ACCOUNT_ID = "";
        public static String ACCOUNT_PW = "";
        public static boolean ACCOUNT_AUTOLOGIN = true;

        @Test
        public void fackbookAccount() throws Exception {
            account = new FaceBookAccount(ACCOUNT_ID, ACCOUNT_PW, ACCOUNT_AUTOLOGIN);
            assertAttributes();
        }

        private void assertAttributes() {
            assertThat(account.getId(), is(ACCOUNT_ID));
            assertThat(account.getPw(), is(ACCOUNT_PW));
            assertThat(account.isAutoLogin(), is(ACCOUNT_AUTOLOGIN));
        }

        @Test
        public void twitterAccount() throws Exception {
            account = new TwitterAccount(ACCOUNT_ID, ACCOUNT_PW, ACCOUNT_AUTOLOGIN);
            assertAttributes();
        }

        @Test
        public void googleAccount() throws Exception {
            account = new GooglePlusAccount(ACCOUNT_ID, ACCOUNT_PW, ACCOUNT_AUTOLOGIN);
            assertAttributes();
        }


    }
}
