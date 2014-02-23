package sns.feed.register;

import sns.account.domain.SnsAccount;
import sns.feed.register.domain.FeedContent;

public interface IFeedRegister {

    public enum Result {
        SUCCESS, ERROR_NETWORK, ERROR_NOT_AUTHORIZED
    }

    public Result register(SnsAccount account, FeedContent feedContent);

}
