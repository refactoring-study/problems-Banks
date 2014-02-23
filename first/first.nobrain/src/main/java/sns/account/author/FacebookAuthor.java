package sns.account.author;

import sns.account.domain.SnsAccount;
import sns.exception.NotAuthorException;

class FacebookAuthor implements ISnsAuthor {

    public SnsAccount executeAuth(SnsAccount account) throws NotAuthorException {
        account.setAPIKey("facebook api key");
        return account;
    }

}
