package sns.account.author;

import sns.account.domain.ISnsAccount;

public class GoplAuthor implements ISnsAuthor {

    public ISnsAccount executeAuth(ISnsAccount account) {
        account.setAPIKey("gupl api key");
        return account;
    }

}
