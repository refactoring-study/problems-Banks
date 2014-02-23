package sns.account.domain;

import java.util.ArrayList;
import java.util.List;


public interface SnsAccount {

    public enum AccountType {
        ACCOUNT_TYPE_FACEBOOK, ACCOUNT_TYPE_TWITTER, ACCOUNT_TYPE_GOPL, ACCOUNT_TYPE_NO;

        public static List<AccountType> getAccountTypes() {
            List<AccountType> types = new ArrayList<SnsAccount.AccountType>();
            types.add(ACCOUNT_TYPE_FACEBOOK);
            types.add(ACCOUNT_TYPE_GOPL);
            types.add(ACCOUNT_TYPE_TWITTER);

            return types;
        }
    }

    public String getSNSId();
    public void setSNSId(String snsId);

    public String getAPIKey();
    public void setAPIKey(String apiKey);

    public AccountType getAccountType();

}

