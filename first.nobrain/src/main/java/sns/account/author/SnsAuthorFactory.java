package sns.account.author;

import sns.account.FacebookAccount;
import sns.account.ISnsAccount;
import sns.account.KakaoAccount;
import sns.account.TwitterAccount;

public class SnsAuthorFactory {

    // Null °´Ã¼
    private static final ISnsAuthor NO_TYPE_AUTHOR = new ISnsAuthor() {

        public ISnsAccount executeAuth(ISnsAccount account) {
            account.setAPIKey("");
            return account;
        }
    };

    private enum AccountType {
        ACCOUNT_TYPE_FACEBOOK, ACCOUNT_TYPE_TWITTER, ACCOUNT_TYPE_KAKAO, ACCOUNT_TYPE_NO
    }

    public static ISnsAuthor createSnsAuthor(ISnsAccount account) {

        AccountType accountType = getAccountType(account);

        ISnsAuthor snsAuthor;
        switch (accountType) {
        case ACCOUNT_TYPE_FACEBOOK:
            snsAuthor = new FacebookAuthor();
            break;
        case ACCOUNT_TYPE_KAKAO:
            snsAuthor = new KakaoAuthor();
            break;
        case ACCOUNT_TYPE_NO:
            snsAuthor = new FacebookAuthor();
            break;
        case ACCOUNT_TYPE_TWITTER:
            snsAuthor = new TwitterAuthor();
            break;
        default:
            snsAuthor = NO_TYPE_AUTHOR;
            break;
        }

        return snsAuthor;
    }

    private static AccountType getAccountType(ISnsAccount account) {
        if (account instanceof FacebookAccount) {
            return AccountType.ACCOUNT_TYPE_FACEBOOK;
        } else if (account instanceof KakaoAccount) {
            return AccountType.ACCOUNT_TYPE_KAKAO;
        } else if (account instanceof TwitterAccount) {
            return AccountType.ACCOUNT_TYPE_TWITTER;
        } else {
            return AccountType.ACCOUNT_TYPE_NO;
        }
    }

}
