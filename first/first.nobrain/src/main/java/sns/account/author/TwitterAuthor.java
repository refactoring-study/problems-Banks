package sns.account.author;

import sns.account.domain.SnsAccount;
import sns.exception.NotAuthorException;

public class TwitterAuthor implements ISnsAuthor {

    public SnsAccount executeAuth(SnsAccount account) throws NotAuthorException {
        account.setAPIKey("twitter api key");
        return account;
    }

}
