package sns.feed.register;

import org.apache.commons.lang3.StringUtils;

import sns.account.domain.ISnsAccount;
import sns.feed.register.domain.FeedContent;

class FacebookFeedRegister implements IFeedRegister {

    private final ISnsAccount account;

    public FacebookFeedRegister(ISnsAccount account) {
        this.account = account;
    }

    public Result register(FeedContent feedContent) {

        ISnsAccount account = getAccount();
        if (!StringUtils.isEmpty(account.getAPIKey())) {
            System.out.println(account.getSNSId() + " : 페이스북 등록 완료");
            return IFeedRegister.Result.SUCCESS;
        } else {
            System.out.println(account.getSNSId() + " : 페이스북 등록 실패");
            return IFeedRegister.Result.ERROR_NOT_AUTHORIZED;
        }


    }

    public ISnsAccount getAccount() {
        return account;
    }

}
