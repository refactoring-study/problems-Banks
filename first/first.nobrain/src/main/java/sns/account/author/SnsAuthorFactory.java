package sns.account.author;

import sns.account.AccountTypeUtil;
import sns.account.AccountTypeUtil.AccountType;
import sns.account.domain.ISnsAccount;

public class SnsAuthorFactory {

    // Null °´Ã¼
    private static final ISnsAuthor NO_TYPE_AUTHOR = new ISnsAuthor() {

        public ISnsAccount executeAuth(ISnsAccount account) {
            account.setAPIKey("");
            return account;
        }
    };

    public static ISnsAuthor createSnsAuthor(ISnsAccount account) {

        AccountType accountType = AccountTypeUtil.getAccountType(account);

        ISnsAuthor snsAuthor;
        switch (accountType) {
        case ACCOUNT_TYPE_FACEBOOK:
            snsAuthor = new FacebookAuthor();
            break;
        case ACCOUNT_TYPE_KAKAO:
            snsAuthor = new GoplAuthor();
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


}
