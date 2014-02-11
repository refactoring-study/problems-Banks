package sns.account;

import sns.account.domain.FacebookAccount;
import sns.account.domain.GoplAccount;
import sns.account.domain.ISnsAccount;
import sns.account.domain.TwitterAccount;

public class AccountTypeUtil {

    public enum AccountType {
        ACCOUNT_TYPE_FACEBOOK, ACCOUNT_TYPE_TWITTER, ACCOUNT_TYPE_KAKAO, ACCOUNT_TYPE_NO
    }

    public static AccountType getAccountType(ISnsAccount account) {
        if (account instanceof FacebookAccount) {
            return AccountType.ACCOUNT_TYPE_FACEBOOK;
        } else if (account instanceof GoplAccount) {
            return AccountType.ACCOUNT_TYPE_KAKAO;
        } else if (account instanceof TwitterAccount) {
            return AccountType.ACCOUNT_TYPE_TWITTER;
        } else {
            return AccountType.ACCOUNT_TYPE_NO;
        }
    }

}

