package sns.feed.collector;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import sns.account.AccountTypeUtil;
import sns.account.domain.ISnsAccount;
import sns.exception.NotAuthorException;
import sns.feed.collector.feed.domain.IFeed;


public class FeedCollectorFactory {

    private static final IFeedCollector NO_FEED_COLLECTOR = new IFeedCollector() {

        public List<IFeed> getFeeds(Date lastModifiedDate) {
            return Collections.emptyList();
        }
    };

    /**
     * 피드 수집기 생성
     * @param snsAccount 생성할 피드 계정
     * @return
     * @throws NotAuthorException 인증 오류된 정보일시
     */
    public static IFeedCollector createFeedCollector(ISnsAccount snsAccount) throws NotAuthorException {

        IFeedCollector feedCollector= null;

        if (StringUtils.isEmpty(snsAccount.getAPIKey() )) {
            throw new NotAuthorException("인증키가 없습니다.");
        }

        AccountTypeUtil.AccountType accountType = AccountTypeUtil.getAccountType(snsAccount);

        switch (accountType) {
        case ACCOUNT_TYPE_FACEBOOK:
            feedCollector = new FacebookFeedCollector(snsAccount);
            break;
        case ACCOUNT_TYPE_GOPL:
            feedCollector = new GoplFeedCollector(snsAccount);
            break;
        case ACCOUNT_TYPE_TWITTER:
            feedCollector = new TwitterTwitCollector(snsAccount);
            break;
        case ACCOUNT_TYPE_NO:
        default:
            feedCollector = NO_FEED_COLLECTOR;
            break;

        }

        return feedCollector;
    }

}
