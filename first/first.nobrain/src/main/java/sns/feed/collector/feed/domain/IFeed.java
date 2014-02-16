package sns.feed.collector.feed.domain;

import java.util.Date;
import java.util.List;

import sns.feed.collector.reply.domain.IFeedReply;

public interface IFeed {

    public String getId();
    public String getMessage();
    public String getCategory();
    public int getLikeCount();
    public Date getCreated();
    public Date getModified();
    public String getAccountName();
    public String getAccountId();
    public List<IFeedReply> getReplies();

}
