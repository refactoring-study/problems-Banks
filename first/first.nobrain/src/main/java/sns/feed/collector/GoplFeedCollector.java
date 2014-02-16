package sns.feed.collector;

import java.util.Date;
import java.util.List;

import sns.account.domain.ISnsAccount;
import sns.feed.collector.feed.domain.IFeed;

public class GoplFeedCollector implements IFeedCollector {

    private final ISnsAccount snsAccount;

    public GoplFeedCollector(ISnsAccount snsAccount) {
        this.snsAccount = snsAccount;
    }

    public List<IFeed> getFeeds(Date lastModifiedDate) {
        return null;
    }

}
