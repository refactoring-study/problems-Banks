package sns.feed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sns.account.AccountTypeUtil.AccountType;
import sns.feed.feed.domain.IFeed;

public class FeedDatas {

    private static FeedDatas instace;

    private List<IFeed> feeds;

    private FeedDatas() {
        feeds = Collections.synchronizedList(new ArrayList<IFeed>());
    }

    public static FeedDatas getInstance() {
        if (instace == null) {
            instace = new FeedDatas();
        }

        return instace;
    }

    public void addFeeds(IFeed feed) {
        feeds.add(feed);
    }

    public void removeFeed(IFeed feed) {
        feeds.remove(feed);
    }

    public IFeed get(int index) {
        return feeds.get(index);
    }

    public List<IFeed> getFeeds(AccountType type) {
        return Collections.unmodifiableList(feeds);
    }

    public void release() {
        synchronized (feeds) {
            feeds.clear();
        }

        synchronized (instace) {
            instace = null;
        }
    }

}
