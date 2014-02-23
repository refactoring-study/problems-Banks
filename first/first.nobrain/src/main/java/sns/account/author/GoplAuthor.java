package sns.account.author;

import sns.account.domain.SnsAccount;
import sns.exception.NotAuthorException;

public class GoplAuthor implements ISnsAuthor {

    public SnsAccount executeAuth(SnsAccount account) throws NotAuthorException {
        account.setAPIKey("gupl api key");
        return account;
    }

}
