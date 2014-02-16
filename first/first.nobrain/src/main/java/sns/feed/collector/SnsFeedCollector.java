package sns.feed.collector;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sns.account.domain.ISnsAccount;
import sns.exception.NotAuthorException;
import sns.feed.collector.feed.domain.IFeed;

public class SnsFeedCollector {

    public List<IFeed> collectFeed(List<ISnsAccount> accounts) {

        List<IFeed> feeds = new ArrayList<IFeed>();

        List<IFeedCollector> collectors = createCollectors(accounts);

        Date nowDate = new Date();
        for (int idx = 0, size = collectors.size(); idx < size; ++idx) {
            feeds.addAll(collectors.get(idx).getFeeds(nowDate));
        }

        return feeds;
    }

    private List<IFeedCollector> createCollectors(List<ISnsAccount> accounts) {
        List<IFeedCollector> collectors = new ArrayList<IFeedCollector>();
        for (int idx = 0, size = accounts.size(); idx < size; ++idx) {
            try {
                collectors.add(FeedCollectorFactory.createFeedCollector(accounts.get(idx)));
            } catch (NotAuthorException e) {
                e.printStackTrace();
            }
        }
        return collectors;
    }

}
