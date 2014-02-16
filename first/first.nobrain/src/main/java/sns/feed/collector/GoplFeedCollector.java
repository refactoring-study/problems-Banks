package sns.feed.collector;

import java.util.Date;
import java.util.List;

import sns.account.domain.ISnsAccount;
import sns.feed.collector.feed.domain.IFeed;

public class GoplFeedCollector implements IFeedCollector {

    public List<IFeed> getFeeds(ISnsAccount snsAccount, Date lastModifiedDate) {
        return null;
    }

}
