package sns.feed.collector.feed.domain;

import java.util.Date;
import java.util.List;

import sns.feed.collector.reply.domain.FacebookFeedReply;

public class FacebookFeed implements IFeed {

    private String id;
    private String message;
    private String category;
    private int likeCount;
    private Date created;
    private String accountName;
    private String accountId;

    private List<FacebookFeedReply> replies;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public List<FacebookFeedReply> getReplies() {
        return replies;
    }

    public void setReplies(List<FacebookFeedReply> replies) {
        this.replies = replies;
    }

}
