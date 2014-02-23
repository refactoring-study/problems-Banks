package sns.feed.collector;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import sns.account.domain.SnsAccount;
import sns.account.domain.SnsAccount.AccountType;
import sns.exception.NotAuthorException;
import sns.feed.domain.feed.IFeed;


public class FeedCollectorFactory {

    private static final IFeedCollector NO_FEED_COLLECTOR = new IFeedCollector() {

        public List<IFeed> getFeeds(SnsAccount account, Date lastModifiedDate) {

            return Collections.emptyList();
        }
    };

    /**
     * 피드 수집기 생성
     * @param snsAccount 생성할 피드 계정
     * @return
     * @throws NotAuthorException 인증 오류된 정보일시
     */
    public static IFeedCollector createFeedCollector(SnsAccount snsAccount) throws NotAuthorException {

        IFeedCollector feedCollector= null;

        if (StringUtils.isEmpty(snsAccount.getAPIKey() )) {
            throw new NotAuthorException("인증키가 없습니다.");
        }

        AccountType accountType = snsAccount.getAccountType();

        switch (accountType) {
        case ACCOUNT_TYPE_FACEBOOK:
            feedCollector = new FacebookFeedCollector();
            break;
        case ACCOUNT_TYPE_GOPL:
            feedCollector = new GoplFeedCollector();
            break;
        case ACCOUNT_TYPE_TWITTER:
            feedCollector = new TwitterFeedCollector();
            break;
        case ACCOUNT_TYPE_NO:
        default:
            feedCollector = NO_FEED_COLLECTOR;
            break;

        }

        return feedCollector;
    }

}
