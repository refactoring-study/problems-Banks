package sns.feed.collector;

import java.util.Date;
import java.util.List;

import sns.account.domain.ISnsAccount;
import sns.feed.FeedStorage;
import sns.feed.domain.feed.IFeed;

public class FacebookFeedCollector implements IFeedCollector {

    private final ISnsAccount snsAccount;

    public FacebookFeedCollector(ISnsAccount snsAccount) {
        this.snsAccount = snsAccount;
    }

    public List<IFeed> getFeeds(Date lastModifiedDate) {
        return FeedStorage.getInstance().getFacebookFeeds();
    }

}
