package sns.account.author;

import sns.account.domain.SnsAccount;
import sns.exception.NotAuthorException;

public interface ISnsAuthor {

    public SnsAccount executeAuth(SnsAccount account) throws NotAuthorException;
}
