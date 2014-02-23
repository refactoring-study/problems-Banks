package sns.feed.collector;

import java.util.Date;
import java.util.List;

import sns.account.domain.SnsAccount;
import sns.feed.domain.feed.IFeed;

public interface IFeedCollector {

    public List<IFeed> getFeeds(SnsAccount account, Date lastModifiedDate);

}
