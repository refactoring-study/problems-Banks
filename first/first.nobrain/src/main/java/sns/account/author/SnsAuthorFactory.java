package sns.account.author;

import sns.SnsTypeUtil;
import sns.SnsTypeUtil.SnsType;
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

        SnsType accountType = SnsTypeUtil.getSnsType(account);

        ISnsAuthor snsAuthor;
        switch (accountType) {
        case ACCOUNT_TYPE_FACEBOOK:
            snsAuthor = new FacebookAuthor();
            break;
        case ACCOUNT_TYPE_GOPL:
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
