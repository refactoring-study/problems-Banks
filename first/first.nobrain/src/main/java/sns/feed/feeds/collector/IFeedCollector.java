package sns.feed.feeds.collector;

import java.util.Date;
import java.util.List;

import sns.account.domain.ISnsAccount;
import sns.feed.feeds.domain.IFeed;

public interface IFeedCollector {

    public List<IFeed> getFeeds(ISnsAccount snsAccount, Date lastModifiedDate);
}
