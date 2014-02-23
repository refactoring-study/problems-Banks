package sns.feed.collector;

import java.util.Date;
import java.util.List;

import sns.account.domain.SnsAccount;
import sns.feed.FeedStorage;
import sns.feed.domain.feed.IFeed;

class GoplFeedCollector implements IFeedCollector {

    public List<IFeed> getFeeds(SnsAccount account, Date lastModifiedDate) {
        return FeedStorage.getInstance().getGoplFeeds();
    }

}
