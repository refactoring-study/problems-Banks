package sns.feed.register;

import sns.feed.register.domain.FeedContent;

interface IFeedRegister {

    public enum Result {
        SUCCESS, ERROR_NETWORK, ERROR_NOT_AUTHORIZED
    }

    public Result register(FeedContent feedContent);

}
