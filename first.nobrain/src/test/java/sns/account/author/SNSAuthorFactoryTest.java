package sns.account.author;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mockito;

import sns.account.FacebookAccount;
import sns.account.ISnsAccount;
import sns.account.KakaoAccount;
import sns.account.TwitterAccount;

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
            ISnsAccount account = new KakaoAccount();

            // When : Create Author & Authorize
            ISnsAuthor snsAuthor = SnsAuthorFactory.createSnsAuthor(account);

            // Then : is Kakao Author
            assertTrue(snsAuthor instanceof KakaoAuthor);
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
