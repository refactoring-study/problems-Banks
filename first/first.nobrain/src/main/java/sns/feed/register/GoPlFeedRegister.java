package sns.feed.register;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import sns.account.domain.ISnsAccount;
import sns.feed.FeedStorage;
import sns.feed.feed.domain.GoplFeed;
import sns.feed.register.domain.FeedContent;

class GoPlFeedRegister implements IFeedRegister {

    private final ISnsAccount account;

    public GoPlFeedRegister(ISnsAccount account) {
        this.account = account;
    }

    public Result register(FeedContent feedContent) {

        ISnsAccount account = getAccount();
        if (!StringUtils.isEmpty(account.getAPIKey())) {
            System.out.println(account.getSNSId() + " : ���� ��� �Ϸ�");
            GoplFeed feed = new GoplFeed();
            feed.setAccountId(account.getSNSId());
            feed.setAccountName(account.getSNSId());
            feed.setCreated(new Date());
            feed.setLikeCount(0);
            feed.setMessage(feedContent.getMessage());
            feed.setId(UUID.randomUUID().toString());
            FeedStorage.getInstance().addFeed(feed);
            return IFeedRegister.Result.SUCCESS;
        } else {
            System.out.println(account.getSNSId() + " : ���� ��� ����");
            return IFeedRegister.Result.ERROR_NOT_AUTHORIZED;
        }


    }

    public ISnsAccount getAccount() {
        return account;
    }

}
