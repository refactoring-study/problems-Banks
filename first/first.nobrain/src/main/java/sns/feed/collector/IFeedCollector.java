package sns.feed.collector;

import java.util.Date;
import java.util.List;

import sns.feed.domain.feed.IFeed;

public interface IFeedCollector {

    public List<IFeed> getFeeds(Date lastModifiedDate);

}
