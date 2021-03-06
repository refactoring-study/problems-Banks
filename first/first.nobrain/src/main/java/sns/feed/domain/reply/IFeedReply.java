package sns.feed.domain.reply;

import java.util.Date;

public interface IFeedReply {

    public String getId();
    public String getFeedId();
    public String getMessage();
    public int getLikeCount();
    public Date getCreated();
    public String getAccountName();
    public String getAccountId();
}
