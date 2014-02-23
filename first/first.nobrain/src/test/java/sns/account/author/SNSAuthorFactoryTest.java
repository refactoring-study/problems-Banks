package sns.account.author;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mockito;

import sns.account.domain.FacebookAccount;
import sns.account.domain.GoplAccount;
import sns.account.domain.SnsAccount;
import sns.account.domain.SnsAccount.AccountType;
import sns.account.domain.TwitterAccount;

public class SNSAuthorFactoryTest extends TestCase {


    @Test
    public void testCreateSnsAuthor() throws Exception {
        {
            // Given : Facebook Account
            SnsAccount account = new FacebookAccount();

            // When : Create Author & Authorize
            ISnsAuthor snsAuthor = SnsAuthorFactory.createSnsAuthor(account);

            // Then : is FacebookAuthor
            assertTrue(snsAuthor instanceof FacebookAuthor);
        }

        {
            // Given : Kakao Account
            SnsAccount account = new GoplAccount();

            // When : Create Author & Authorize
            ISnsAuthor snsAuthor = SnsAuthorFactory.createSnsAuthor(account);

            // Then : is Kakao Author
            assertTrue(snsAuthor instanceof GoplAuthor);
        }

        {
            // Given : Twitter Account
            SnsAccount account = new TwitterAccount();

            // When : Create Author & Authorize
            ISnsAuthor snsAuthor = SnsAuthorFactory.createSnsAuthor(account);

            // Then : is Twitter Author
            assertTrue(snsAuthor instanceof TwitterAuthor);
        }

        {
            // Given : UnknownType Account
            SnsAccount account = Mockito.mock(SnsAccount.class);
            Mockito.when(account.getAccountType()).thenReturn(AccountType.ACCOUNT_TYPE_NO);

            // When : Create Author & Authorize
            ISnsAuthor snsAuthor = SnsAuthorFactory.createSnsAuthor(account);
            snsAuthor.executeAuth(account);

            // Then : API Key is null
            assertNull(account.getAPIKey());

        }


    }
}
