package sns.feed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sns.feed.feeds.domain.IFeed;

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

    public void addAccount(IFeed snsAccount) {
        feeds.add(snsAccount);
    }

    public void removeAccount(IFeed snsAccount) {
        feeds.remove(snsAccount);
    }

    public IFeed get(int index) {
        return feeds.get(index);
    }

    public List<IFeed> getAccounts() {
        return Collections.unmodifiableList(feeds);
    }

}
