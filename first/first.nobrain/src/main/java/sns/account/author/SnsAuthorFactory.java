package sns.account.author;

import sns.account.domain.SnsAccount;
import sns.account.domain.SnsAccount.AccountType;
import sns.exception.NotAuthorException;

public class SnsAuthorFactory {

    // Null 객체
    private static final ISnsAuthor NO_TYPE_AUTHOR = new ISnsAuthor() {

        public SnsAccount executeAuth(SnsAccount account) throws NotAuthorException {
            throw new NotAuthorException("계정 정보가 알 수 없는 타입입니다.");
        }
    };

    public static ISnsAuthor createSnsAuthor(SnsAccount account) {

        AccountType accountType = account.getAccountType();

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
