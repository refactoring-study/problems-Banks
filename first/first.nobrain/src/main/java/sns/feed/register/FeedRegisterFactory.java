package sns.feed.register;

import sns.SnsTypeUtil;
import sns.SnsTypeUtil.SnsType;
import sns.account.domain.ISnsAccount;
import sns.feed.register.domain.FeedContent;

public class FeedRegisterFactory {

    private static IFeedRegister noTypeRegister = new IFeedRegister() {

        public Result register(FeedContent feedContent) {

            System.out.println("노 답");

            return IFeedRegister.Result.ERROR_NOT_AUTHORIZED;
        }
    };

    public static IFeedRegister createFeedRegister(ISnsAccount account) {

        SnsType accountType = SnsTypeUtil.getSnsType(account);

        IFeedRegister feedRegister;

        switch (accountType) {
        case ACCOUNT_TYPE_FACEBOOK:
            feedRegister = new FacebookFeedRegister(account);
            break;
        case ACCOUNT_TYPE_GOPL:
            feedRegister = new GoPlFeedRegister(account);
            break;
        case ACCOUNT_TYPE_TWITTER:
            feedRegister = new TwitterFeedRegister(account);
            break;
        case ACCOUNT_TYPE_NO:
        default:
            feedRegister = noTypeRegister;
            break;
        }

        return feedRegister;

    }

}
