package sns.feed.feed.domain;

import java.util.Date;
import java.util.List;

import sns.feed.reply.domain.IFeedReply;

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
