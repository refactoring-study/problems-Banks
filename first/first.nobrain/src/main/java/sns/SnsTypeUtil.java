package sns;

import sns.account.domain.FacebookAccount;
import sns.account.domain.GoplAccount;
import sns.account.domain.ISnsAccount;
import sns.account.domain.TwitterAccount;

public class SnsTypeUtil {

    public enum SnsType {
        ACCOUNT_TYPE_FACEBOOK, ACCOUNT_TYPE_TWITTER, ACCOUNT_TYPE_GOPL, ACCOUNT_TYPE_NO
    }

    public static SnsType getSnsType(ISnsAccount account) {
        if (account instanceof FacebookAccount) {
            return SnsType.ACCOUNT_TYPE_FACEBOOK;
        } else if (account instanceof GoplAccount) {
            return SnsType.ACCOUNT_TYPE_GOPL;
        } else if (account instanceof TwitterAccount) {
            return SnsType.ACCOUNT_TYPE_TWITTER;
        } else {
            return SnsType.ACCOUNT_TYPE_NO;
        }
    }

}

