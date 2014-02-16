package sns.feed.register;

import org.apache.commons.lang3.StringUtils;

import sns.account.domain.ISnsAccount;
import sns.feed.register.domain.FeedContent;

class TwitterFeedRegister implements IFeedRegister {

    private final ISnsAccount account;

    public TwitterFeedRegister(ISnsAccount account) {
        this.account = account;
    }

    public Result register(FeedContent feedContent) {

        ISnsAccount account = getAccount();
        if (!StringUtils.isEmpty(account.getAPIKey())) {
            System.out.println(account.getSNSId() + " : 트위터 등록 완료");
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
