package sns.account.author;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mockito;

import sns.account.domain.FacebookAccount;
import sns.account.domain.ISnsAccount;
import sns.account.domain.GoplAccount;
import sns.account.domain.TwitterAccount;

public class SNSAuthorFactoryTest extends TestCase {


    @Test
    public void testCreateSnsAuthor() throws Exception {
        {
            // Given : Facebook Account
            ISnsAccount account = new FacebookAccount();

            // When : Create Author & Authorize
            ISnsAuthor snsAuthor = SnsAuthorFactory.createSnsAuthor(account);

            // Then : is FacebookAuthor
            assertTrue(snsAuthor instanceof FacebookAuthor);
        }

        {
            // Given : Kakao Account
            ISnsAccount account = new GoplAccount();

            // When : Create Author & Authorize
            ISnsAuthor snsAuthor = SnsAuthorFactory.createSnsAuthor(account);

            // Then : is Kakao Author
            assertTrue(snsAuthor instanceof GoplAuthor);
        }

        {
            // Given : Twitter Account
            ISnsAccount account = new TwitterAccount();

            // When : Create Author & Authorize
            ISnsAuthor snsAuthor = SnsAuthorFactory.createSnsAuthor(account);

            // Then : is Twitter Author
            assertTrue(snsAuthor instanceof TwitterAuthor);
        }

        {
            // Given : UnknownType Account
            ISnsAccount account = Mockito.mock(ISnsAccount.class);

            // When : Create Author & Authorize
            ISnsAuthor snsAuthor = SnsAuthorFactory.createSnsAuthor(account);
            snsAuthor.executeAuth(account);

            // Then : API Key is null
            assertNull(account.getAPIKey());

        }


    }
}
