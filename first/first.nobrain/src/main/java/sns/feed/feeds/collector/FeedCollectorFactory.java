package sns.feed.feeds.collector;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import sns.account.AccountTypeUtil;
import sns.account.domain.ISnsAccount;
import sns.exception.NotAuthorException;
import sns.feed.feeds.domain.IFeed;


public class FeedCollectorFactory {

    private static final IFeedCollector NO_FEED_COLLECTOR = new IFeedCollector() {

        public List<IFeed> getFeeds(ISnsAccount snsAccount, Date lastModifiedDate) {
            return Collections.emptyList();
        }
    };

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
        case ACCOUNT_TYPE_GOPL:
            feedCollector = new GoplFeedCollector();
            break;
        case ACCOUNT_TYPE_NO:
            feedCollector = NO_FEED_COLLECTOR;
            break;
        case ACCOUNT_TYPE_TWITTER:
            feedCollector = new TwitterTwitCollector();
            break;
        default:
            break;

        }

        return feedCollector;
    }

}
