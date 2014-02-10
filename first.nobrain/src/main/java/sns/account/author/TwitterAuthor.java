package sns.account.author;

import sns.account.ISnsAccount;

public class TwitterAuthor implements ISnsAuthor {

    public ISnsAccount executeAuth(ISnsAccount account) {
        account.setAPIKey("twitter api key");
        return account;
    }

}
