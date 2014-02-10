package sns.account.author;

import sns.account.ISnsAccount;

public class FacebookAuthor implements ISnsAuthor {

    public ISnsAccount executeAuth(ISnsAccount account) {
        account.setAPIKey("facebook api key");
        return account;
    }

}
