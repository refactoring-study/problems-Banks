package sns.feed.collector;

import java.util.Date;
import java.util.List;

import sns.feed.collector.feed.domain.IFeed;

public interface IFeedCollector {

    public List<IFeed> getFeeds(Date lastModifiedDate);

}
