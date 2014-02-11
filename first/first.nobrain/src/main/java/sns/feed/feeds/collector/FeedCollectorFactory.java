package sns.feed.feeds.collector;

import sns.account.AccountTypeUtil;
import sns.account.domain.ISnsAccount;
import sns.exception.NotAuthorException;


public class FeedCollectorFactory {

    public static IFeedCollector createFeedCollector(ISnsAccount snsAccount) throws NotAuthorException {

        IFeedCollector feedCollector= null;

        if (snsAccount.getAPIKey() == null || snsAccount.getAPIKey().length() == 0) {
            throw new NotAuthorException("인증키가 없습니다.");
        }

        AccountTypeUtil.AccountType accountType = AccountTypeUtil.getAccountType(snsAccount);

        switch (accountType) {
        case ACCOUNT_TYPE_FACEBOOK:
            feedCollector = new FacebookFeedCollector();
            break;
        case ACCOUNT_TYPE_KAKAO:
            break;
        case ACCOUNT_TYPE_NO:
            break;
        case ACCOUNT_TYPE_TWITTER:
            break;
        default:
            break;

        }

        return feedCollector;
    }

}
