package sns.feed.register;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import sns.account.domain.ISnsAccount;
import sns.feed.FeedStorage;
import sns.feed.feed.domain.TwitterFeed;
import sns.feed.register.domain.FeedContent;

class TwitterFeedRegister implements IFeedRegister {

    private static final int FEED_LENGTH_LIMIT = 140;
    private final ISnsAccount account;

    public TwitterFeedRegister(ISnsAccount account) {
        this.account = account;
    }

    public Result register(FeedContent feedContent) {

        ISnsAccount account = getAccount();
        if (!StringUtils.isEmpty(account.getAPIKey())) {

            if (feedContent.getMessage().length() > FEED_LENGTH_LIMIT) {
                return IFeedRegister.Result.ERROR_NETWORK;
            }

            System.out.println(account.getSNSId() + " : 트위터 등록 완료");
            TwitterFeed feed = new TwitterFeed();
            feed.setAccountId(account.getSNSId());
            feed.setAccountName(account.getSNSId());
            feed.setCreated(new Date());
            feed.setMessage(feedContent.getMessage());
            feed.setId(UUID.randomUUID().toString());
            FeedStorage.getInstance().addFeed(feed);
            return IFeedRegister.Result.SUCCESS;
        } else {
            System.out.println(account.getSNSId() + " : 트위터 등록 실패");
            return IFeedRegister.Result.ERROR_NOT_AUTHORIZED;
        }


    }

    public ISnsAccount getAccount() {
        return account;
    }

}
