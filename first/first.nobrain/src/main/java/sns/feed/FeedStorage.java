package sns.feed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sns.feed.collector.feed.domain.IFeed;

public class FeedStorage {

    private static FeedStorage instace;

    private List<IFeed> feeds;

    private FeedStorage() {
        feeds = Collections.synchronizedList(new ArrayList<IFeed>());
    }

    public static FeedStorage getInstance() {
        if (instace == null) {
            instace = new FeedStorage();
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

    public List<IFeed> getFeeds() {
        return Collections.unmodifiableList(feeds);
    }

    public List<IFeed> getFeedsSortByCreatedDate() {
        List<IFeed> createDatas = new ArrayList<IFeed>(feeds.size());
        Collections.copy(createDatas, feeds);

        Collections.sort(createDatas, new Comparator<IFeed>() {

            public int compare(IFeed o1, IFeed o2) {
                return (int) (o1.getCreated().getTime() - o2.getCreated().getTime());
            }
        });

        return createDatas;
    }

    public List<IFeed> getFeedsSortByModifiedDate() {
        List<IFeed> modifiedDatas = new ArrayList<IFeed>(feeds.size());
        Collections.copy(modifiedDatas, feeds);

        Collections.sort(modifiedDatas, new Comparator<IFeed>() {

            public int compare(IFeed o1, IFeed o2) {
                return (int) (o1.getModified().getTime() - o2.getModified().getTime());
            }
        });

        return modifiedDatas;

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
