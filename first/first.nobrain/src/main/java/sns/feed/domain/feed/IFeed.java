package sns.feed.domain.feed;

import java.util.Date;
import java.util.List;

import sns.feed.domain.reply.IFeedReply;

public interface IFeed {

    public String getId();
    public String getMessage();
    public String getCategory();
    public int getLikeCount();
    public Date getCreated();
    public String getAccountName();
    public String getAccountId();
    public List<? extends IFeedReply> getReplies();

}
