package sns.feed.feed.domain;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import sns.feed.reply.domain.IFeedReply;

public class TwitterFeed implements IFeed {
    private String id;
    private String message;
    private Date created;
    private String accountName;
    private String accountId;

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
        return "";
    }

    public int getLikeCount() {
        return -1;
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

    public List<IFeedReply> getReplies() {
        return Collections.emptyList();
    }

}
