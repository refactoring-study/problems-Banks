package sns.account.author;

import sns.account.domain.ISnsAccount;

public class KakaoAuthor implements ISnsAuthor {

    public ISnsAccount executeAuth(ISnsAccount account) {
        account.setAPIKey("kakao api key");
        return account;
    }

}
