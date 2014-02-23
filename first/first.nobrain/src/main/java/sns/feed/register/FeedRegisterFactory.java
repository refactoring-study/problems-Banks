package sns.feed.register;

import sns.account.domain.SnsAccount;
import sns.account.domain.SnsAccount.AccountType;
import sns.feed.register.domain.FeedContent;

class FeedRegisterFactory {

    private static IFeedRegister noTypeRegister = new IFeedRegister() {

        public Result register(SnsAccount account, FeedContent feedContent) {

            return IFeedRegister.Result.ERROR_NOT_AUTHORIZED;
        }
    };

    public static IFeedRegister createFeedRegister(SnsAccount account) {

        AccountType accountType = account.getAccountType();

        IFeedRegister feedRegister;

        switch (accountType) {
        case ACCOUNT_TYPE_FACEBOOK:
            feedRegister = new FacebookFeedRegister();
            break;
        case ACCOUNT_TYPE_GOPL:
            feedRegister = new GoPlFeedRegister();
            break;
        case ACCOUNT_TYPE_TWITTER:
            feedRegister = new TwitterFeedRegister();
            break;
        case ACCOUNT_TYPE_NO:
        default:
            feedRegister = noTypeRegister;
            break;
        }

        return feedRegister;

    }

}
