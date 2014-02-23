package sns.feed.register;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import sns.account.domain.SnsAccount;
import sns.feed.FeedStorage;
import sns.feed.domain.feed.GoplFeed;
import sns.feed.register.domain.FeedContent;

class GoPlFeedRegister implements IFeedRegister {

    public Result register(SnsAccount account, FeedContent feedContent) {

        if (!StringUtils.isEmpty(account.getAPIKey())) {
            System.out.println(account.getSNSId() + " : 구플 등록 완료");
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
            System.out.println(account.getSNSId() + " : 구플 등록 실패");
            return IFeedRegister.Result.ERROR_NOT_AUTHORIZED;
        }


    }
}
